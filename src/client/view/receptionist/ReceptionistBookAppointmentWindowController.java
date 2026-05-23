package client.view.receptionist;

import client.view.login.ViewHandler;
import client.viewModel.appointment.BookAppointmentViewModel;
import client.viewModel.receptionist.ReceptionistBookAppointmentViewModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import shared.DoctorDTO;
import shared.PatientDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ReceptionistBookAppointmentWindowController {
    @FXML
    private Button cancelButton;
    @FXML private Button confirmButton;
    @FXML private DatePicker datePicker;
    @FXML private ComboBox<String> timeComboBox;
    @FXML private ComboBox<DoctorDTO> doctorComboBox;
    @FXML private ComboBox<PatientDTO> patientComboBox;
    @FXML private Label errorLabel;

    private Region root;
    private ViewHandler viewHandler;
    private ReceptionistBookAppointmentViewModel bookAppointmentViewModel;
    private ArrayList<String> appointmentHours = new ArrayList<String>();

    public ReceptionistBookAppointmentWindowController() {}

    public void init(ViewHandler viewHandler,
                     ReceptionistBookAppointmentViewModel bookAppointmentViewModel,
                     Region root) {
        this.viewHandler = viewHandler;
        this.bookAppointmentViewModel = bookAppointmentViewModel;
        this.root = root;

        errorLabel.textProperty().bind(bookAppointmentViewModel.getErrorProperty());
        loadDoctors();
        loadPatients();

        timeComboBox.setItems(
                FXCollections.observableArrayList(
                        "10:00", "11:00", "12:00",
                        "13:00", "14:00", "15:00",
                        "16:00", "17:00", "18:00"
                )
        );


    }

    private void loadDoctors() {
        try {
            doctorComboBox.setItems(FXCollections.observableArrayList(bookAppointmentViewModel.getAllDoctors()));
        }
        catch (Exception e) {
            bookAppointmentViewModel.getErrorProperty().set(e.getMessage() == null ? "Could not load doctors." : e.getMessage());
        }
    }

    private void loadPatients() {
        try {
            patientComboBox.setItems(FXCollections.observableArrayList(bookAppointmentViewModel.getAllPatients()));
        } catch (Exception e) {
            bookAppointmentViewModel.getErrorProperty().set(e.getMessage() == null ? "Could not load patients." : e.getMessage());
        }
    }

    @FXML
    public void confirmButtonPressed() {
        bookAppointmentViewModel.setDate(datePicker.getValue());
        bookAppointmentViewModel.setDoctor(doctorComboBox.getValue());
        bookAppointmentViewModel.setPatient(patientComboBox.getValue());
        bookAppointmentViewModel.getTimeProperty().set(timeComboBox.getValue());

        boolean created = bookAppointmentViewModel.create();

        if (created) {
            viewHandler.openView("receptionist");
        }
    }

    @FXML
    public void cancelButtonPressed() {
        viewHandler.openView("receptionist");
    }

    public void reset() {
        bookAppointmentViewModel.clear();
        datePicker.setValue(null);
        doctorComboBox.getSelectionModel().clearSelection();
        timeComboBox.getSelectionModel().clearSelection();
        loadDoctors();
    }

    public Region getRoot() { return root; }

    @FXML
    private void onEnter(ActionEvent actionEvent) {
        if (actionEvent.getSource() == datePicker) {
            doctorComboBox.requestFocus();
        }
        else if (actionEvent.getSource() == doctorComboBox) {
            timeComboBox.requestFocus();
        }
        else {
            confirmButtonPressed();
        }
    }

    @FXML
    private void onDateSelect() {
        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.now();

        if (datePicker.getValue().equals(date) && time.getHour()>9 && time.getHour()<19){
            for (int i = 0; i < 18 - time.getHour(); i++) {
                int x = 18-i;
                appointmentHours.add(x + ":00");
            }

            timeComboBox.setItems(
                    FXCollections.observableArrayList(
                            appointmentHours
                    )
            );
        }

        if (datePicker.getValue() == null || !datePicker.getValue().equals(date)) {
            timeComboBox.setItems(
                    FXCollections.observableArrayList(
                            "10:00", "11:00", "12:00",
                            "13:00", "14:00", "15:00",
                            "16:00", "17:00", "18:00"
                    )
            );
        }

    }
}
