package client.view.receptionist;

import client.view.ViewHandler;
import client.viewModel.receptionist.ReceptionistTodayAppointmentsViewModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import shared.AppointmentDTO;
import shared.PatientDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Controller for the Receptionist Todays Appointments Window view.
 * Reacts to button clicks and other input in this window and calls the view model to do the work.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class ReceptionistTodaysAppointmentsWindowController {
    /** The cancel button. */
    @FXML private Button cancelButton;
    @FXML private Button editButton;
    @FXML private Button removeButton;
    @FXML private TableView<AppointmentDTO> appointmentTable;
    @FXML private TableColumn<AppointmentDTO, LocalDateTime> appointmentDateColumn;
    @FXML private TableColumn<AppointmentDTO, PatientDTO> patientColumn;
    @FXML private TableColumn<AppointmentDTO, Boolean> statusColumn;
    @FXML private TableColumn<AppointmentDTO, String> notesColumn;

    /** The root. */
    private Region root;
    /** The view handler. */
    private ViewHandler viewHandler;
    /** The view model. */
    private ReceptionistTodayAppointmentsViewModel viewModel;

    /**
     * Sets up the controller with its view handler, view model and root.
     *
     * @param viewHandler the view handler
     * @param viewModel the view model
     * @param root the root
     */
    public void init(ViewHandler viewHandler, ReceptionistTodayAppointmentsViewModel viewModel, Region root) {
        this.viewModel = viewModel;
        this.viewHandler = viewHandler;
        this.root = root;

        editButton.setDisable(true);
        removeButton.setDisable(true);

        appointmentDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        patientColumn.setCellValueFactory(new PropertyValueFactory<>("patient"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));

        loadAppointments();
    }

    /**
     * Loads the appointments into the view.
     */
    private void loadAppointments() {
        try {
            LocalDate today = LocalDate.now();
            ArrayList<AppointmentDTO> appointments = viewModel.getAllAppointments();
            ArrayList<AppointmentDTO> todaysAppointments = new ArrayList<AppointmentDTO>();
            if (appointments == null) {
                appointments = new ArrayList<>();
            }
            for (int i=0; i<appointments.size(); i++)
            {
                if (appointments.get(i).getDate().toLocalDate().equals(today))
                    todaysAppointments.add(appointments.get(i));
            }
            appointmentTable.setItems(FXCollections.observableArrayList(todaysAppointments));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles an appointment being clicked.
     */
    public void clickAppointment() {
        int index = appointmentTable.getSelectionModel().getSelectedIndex();
        if (index >= 0)
        {
            editButton.setDisable(false);
            removeButton.setDisable(false);
        }
    }

    /**
     * Handles the edit button being pressed in the view.
     */
    @FXML
    public void editButtonPressed() {
        AppointmentDTO selectedAppointment = appointmentTable.getSelectionModel().getSelectedItem();

        viewHandler.openReceptionistEditAppointmentWindow(selectedAppointment);
    }

    /**
     * Handles the remove button being pressed in the view.
     *
     * @throws SQLException if the operation cannot be completed
     */
    @FXML
    public void removeButtonPressed() throws SQLException {
        int index = appointmentTable.getSelectionModel().getSelectedIndex();
        if (index >= 0)
        {
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Remove Appointment");
            confirmation.setHeaderText("Are you sure you want to remove this appointment?");
            confirmation.setContentText("This action cannot be undone.");

            ButtonType result = confirmation.showAndWait()
                    .orElse(ButtonType.CANCEL);

            if (result == ButtonType.OK)
            {
                int appointmentId = appointmentTable.getSelectionModel().getSelectedItem().getId();
                viewModel.deleteAppointment(appointmentId);
                loadAppointments();
            }
        }
    }

    /**
     * Handles the cancel button being pressed in the view.
     */
    @FXML
    public void cancelButtonPressed() {
        editButton.setDisable(true);
        removeButton.setDisable(true);
        viewHandler.openView("receptionist"); }

    /**
     * Returns the root of this window.
     *
     * @return the root
     */
    public Region getRoot() { return root; }

    /**
     * Clears the input fields and puts the window back to the start.
     */
    public void reset() { loadAppointments(); }
}
