package client.view.receptionist;

import client.view.login.ViewHandler;
import client.viewModel.receptionist.ReceptionistAllAppointmentsViewModel;
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

public class ReceptionistAllAppointmentsWindowController {
    @FXML private Button cancelButton;
    @FXML private Button editButton;
    @FXML private Button removeButton;
    @FXML private TableView<AppointmentDTO> appointmentTable;
    @FXML private TableColumn<AppointmentDTO, LocalDateTime> appointmentDateColumn;
    @FXML private TableColumn<AppointmentDTO, PatientDTO> patientColumn;
    @FXML private TableColumn<AppointmentDTO, Boolean> statusColumn;
    @FXML private TableColumn<AppointmentDTO, String> notesColumn;

    private Region root;
    private ViewHandler viewHandler;
    private ReceptionistAllAppointmentsViewModel viewModel;

    public void init(ViewHandler viewHandler, ReceptionistAllAppointmentsViewModel viewModel, Region root) {
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

    private void loadAppointments() {
        try {
            LocalDate today = LocalDate.now();
            ArrayList<AppointmentDTO> appointments = viewModel.getAllAppointments();
            appointmentTable.setItems(FXCollections.observableArrayList(appointments));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickAppointment() {
        int index = appointmentTable.getSelectionModel().getSelectedIndex();
        if (index >= 0)
        {
            editButton.setDisable(false);
            removeButton.setDisable(false);
        }
    }

    public void editButtonPressed() {
        AppointmentDTO selectedAppointment = appointmentTable.getSelectionModel().getSelectedItem();

        viewHandler.openReceptionistEditAppointmentWindow(selectedAppointment);
    }

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

    public void cancelButtonPressed() {
        editButton.setDisable(true);
        removeButton.setDisable(true);
        viewHandler.openView("receptionist"); }

    public Region getRoot() { return root; }

    public void reset() { loadAppointments(); }
}
