package client.view.chat;

import client.model.ClinicClient;
import client.networking.ChatConnection;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import shared.DoctorDTO;
import shared.PatientDTO;
import shared.RoleDTO;
import shared.SessionDTO;
import shared.networking.ChatMessage;

import java.util.ArrayList;

/**
 * A simple chat window. A patient sees a list of doctors to message, and a doctor
 * sees a list of patients. When a person is chosen, the saved history with that
 * person is loaded and shown, and new messages can be sent and are received live.
 * The window talks to the chat server through a {@link ChatConnection}.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class ChatWindow {

    /** The session of the logged-in user. */
    private final SessionDTO session;
    /** The client used to load the list of doctors or patients. */
    private final ClinicClient client;
    /** The connection to the chat server. */
    private final ChatConnection connection;

    /** The area that shows the messages. */
    private final TextArea messageArea = new TextArea();
    /** The field where the user types a message. */
    private final TextField input = new TextField();
    /** The dropdown to choose who to chat with. */
    private final ComboBox<Object> partnerBox = new ComboBox<>();

    /** The patient id of the chat currently open. */
    private int currentPatientId = -1;
    /** The doctor id of the chat currently open. */
    private int currentDoctorId = -1;
    /** The user id of the person currently chatting with. */
    private int currentPartnerId = -1;

    /**
     * Creates a new chat window for the logged-in user.
     *
     * @param session the session of the logged-in user
     * @param client the client used to load the list of partners
     * @throws Exception if the chat connection cannot be opened
     */
    public ChatWindow(SessionDTO session, ClinicClient client) throws Exception {
        this.session = session;
        this.client = client;
        this.connection = new ChatConnection(session.getUserId(), session.getDisplayName());
        this.connection.startListening(this::onMessage);
    }

    /**
     * Builds and shows the chat window.
     *
     * @throws Exception if the list of partners cannot be loaded
     */
    public void show() throws Exception {
        messageArea.setEditable(false);
        messageArea.setWrapText(true);

        partnerBox.setPromptText("Choose who to chat with");
        loadPartners();
        partnerBox.setOnAction(e -> openChatWith(partnerBox.getValue()));

        Button send = new Button("Send");
        send.setOnAction(e -> sendCurrent());
        input.setOnAction(e -> sendCurrent());

        HBox bottom = new HBox(8, input, send);
        HBox.setHgrow(input, javafx.scene.layout.Priority.ALWAYS);
        bottom.setPadding(new Insets(8, 0, 0, 0));

        VBox top = new VBox(6, new Label("Chat"), partnerBox);

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(12));
        root.setTop(top);
        root.setCenter(messageArea);
        root.setBottom(bottom);

        Stage stage = new Stage();
        stage.setTitle("VIA Clinic Chat - " + session.getDisplayName());
        stage.setScene(new Scene(root, 420, 480));
        stage.setOnHidden(e -> connection.close());
        stage.show();
    }

    /**
     * Loads the list of people the user can chat with, based on their role.
     *
     * @throws Exception if the list cannot be loaded
     */
    private void loadPartners() throws Exception {
        ArrayList<Object> items = new ArrayList<>();
        if (session.getRole() == RoleDTO.PATIENT) {
            for (DoctorDTO d : client.getAllDoctors()) {
                items.add(d);
            }
        } else {
            for (PatientDTO p : client.getAllPatients()) {
                items.add(p);
            }
        }
        partnerBox.getItems().setAll(items);
        // show a readable name in the dropdown
        partnerBox.setConverter(new javafx.util.StringConverter<>() {
            @Override
            public String toString(Object o) {
                if (o instanceof DoctorDTO d) {
                    return "Dr. " + d.getFirstName() + " " + d.getLastName();
                }
                if (o instanceof PatientDTO p) {
                    return p.getFirstName() + " " + p.getLastName();
                }
                return "";
            }

            @Override
            public Object fromString(String s) {
                return null;
            }
        });
    }

    /**
     * Opens the chat with the chosen person and loads the saved history.
     *
     * @param partner the chosen doctor or patient
     */
    private void openChatWith(Object partner) {
        if (partner == null) {
            return;
        }
        if (session.getRole() == RoleDTO.PATIENT && partner instanceof DoctorDTO d) {
            currentPatientId = session.getUserId();
            currentDoctorId = d.getDoctorID();
            currentPartnerId = d.getDoctorID();
        } else if (partner instanceof PatientDTO p) {
            currentPatientId = p.getPatientID();
            currentDoctorId = session.getUserId();
            currentPartnerId = p.getPatientID();
        }
        messageArea.clear();
        connection.requestHistory(currentPatientId, currentDoctorId);
    }

    /**
     * Sends the text currently typed in the input field.
     */
    private void sendCurrent() {
        String text = input.getText().trim();
        if (text.isEmpty() || currentPartnerId == -1) {
            return;
        }
        connection.sendMessage(currentPartnerId, currentPatientId, currentDoctorId, text);
        input.clear();
    }

    /**
     * Handles a message that arrived from the server by showing it in the area.
     * This runs on the listener thread, so the UI update is moved to the JavaFX
     * thread with {@link Platform#runLater}.
     *
     * @param msg the message that arrived
     */
    private void onMessage(ChatMessage msg) {
        Platform.runLater(() -> messageArea.appendText(msg.getFromName() + ": " + msg.getText() + "\n"));
    }
}
