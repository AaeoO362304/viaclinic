package client.view.functions;

import client.view.login.ViewHandler;
import client.viewModel.appointment.BookAppointmentViewModel;
import client.viewModel.appointment.EditAppointmentViewModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import server.model.bookAppointment.Appointment;
import shared.AppointmentDTO;
import shared.DoctorDTO;
import shared.PatientDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Locale;

public class EditAppointmentWindowController {

    @FXML private Button cancelButton;
    @FXML private Button confirmButton;
    @FXML private DatePicker datePicker;
    @FXML private ComboBox<String> timeComboBox;
    @FXML private ComboBox<DoctorDTO> doctorComboBox;
    @FXML private Label errorLabel;

    private Region root;
    private ViewHandler viewHandler;
    private EditAppointmentViewModel editAppointmentViewModel;
    private ArrayList<String> appointmentHours = new ArrayList<String>();
    private int appointmentId;

    public EditAppointmentWindowController() {}

    public void init(ViewHandler viewHandler,
                     EditAppointmentViewModel viewModel,
                     Region root,
                     AppointmentDTO appointment)
    {
        this.viewHandler = viewHandler;
        this.editAppointmentViewModel = viewModel;
        this.root = root;

        viewModel.setAppointment(appointment);
        loadDoctors();

    }

    private void loadDoctors() {
        try {
            doctorComboBox.setItems(FXCollections.observableArrayList(editAppointmentViewModel.getAllDoctors()));
        }
        catch (Exception e) {
            editAppointmentViewModel.getErrorProperty().set(e.getMessage() == null ? "Could not load doctors." : e.getMessage());
        }
    }

    @FXML
    public void confirmButtonPressed() {
        editAppointmentViewModel.setDate(datePicker.getValue());
        editAppointmentViewModel.setDoctor(doctorComboBox.getValue());
        editAppointmentViewModel.getTimeProperty().set(timeComboBox.getValue());

        boolean updated = editAppointmentViewModel.update();

        if (updated) {
            viewHandler.openView("my_appointments");
        }
    }

    @FXML
    public void cancelButtonPressed() {
        viewHandler.openView("patient");
    }

    public void reset() {
        editAppointmentViewModel.clear();
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

        if (datePicker.getValue().equals(date)){
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
