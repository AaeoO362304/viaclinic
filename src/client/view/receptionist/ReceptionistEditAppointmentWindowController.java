package client.view.receptionist;

import client.view.login.ViewHandler;
import client.viewModel.appointment.EditAppointmentViewModel;
import client.viewModel.receptionist.ReceptionistEditAppointmentViewModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import shared.AppointmentDTO;
import shared.DoctorDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ReceptionistEditAppointmentWindowController {
    @FXML private Button cancelButton;
    @FXML private Button confirmButton;
    @FXML private DatePicker datePicker;
    @FXML private ComboBox<String> timeComboBox;
    @FXML private ComboBox<DoctorDTO> doctorComboBox;
    @FXML private Label errorLabel;

    private Region root;
    private ViewHandler viewHandler;
    private ReceptionistEditAppointmentViewModel editAppointmentViewModel;
    private ArrayList<String> appointmentHours = new ArrayList<String>();
    private int appointmentId;

    public ReceptionistEditAppointmentWindowController() {}

    public void init(ViewHandler viewHandler,
                     ReceptionistEditAppointmentViewModel viewModel,
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
            viewHandler.openView("receptionist");
        }
    }

    @FXML
    public void cancelButtonPressed() {
        viewHandler.openView("receptionist");
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

        if (!(datePicker.getValue() == null) || datePicker.getValue().equals(date) && time.getHour() > 9 && time.getHour() < 19) {
            for (int i = 0; i < 18 - time.getHour(); i++) {
                int x = 18 - i;
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

    public void init(ViewHandler viewHandler, ReceptionistEditAppointmentViewModel receptionistEditAppointmentViewModel, Region root) {

    }
}
