package client.view.doctor;

import client.view.login.ViewHandler;
import client.viewModel.appointment.MyAppointmentViewModel;
import client.viewModel.doctor.TodayAppointmentsViewModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import shared.AppointmentDTO;
import shared.DoctorDTO;
import shared.PatientDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TodayAppointmentsWindowController {
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
    private TodayAppointmentsViewModel viewModel;

    public void init(ViewHandler viewHandler, TodayAppointmentsViewModel viewModel, Region root) {
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
            ArrayList<AppointmentDTO> appointments = viewModel.getAllAppointmentsByDoctorId();
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

        viewHandler.openDoctorEditAppointmentWindow(selectedAppointment);
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
        viewHandler.openView("doctor"); }

    public Region getRoot() { return root; }

    public void reset() { loadAppointments(); }
}
