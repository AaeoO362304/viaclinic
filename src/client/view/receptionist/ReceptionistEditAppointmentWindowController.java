package client.view.receptionist;

import client.view.ViewHandler;
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

/**
 * Controller for the Receptionist Edit Appointment Window view.
 * Reacts to button clicks and other input in this window and calls the view model to do the work.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class ReceptionistEditAppointmentWindowController {
    /** The cancel button. */
    @FXML private Button cancelButton;
    @FXML private Button confirmButton;
    @FXML private DatePicker datePicker;
    @FXML private ComboBox<String> timeComboBox;
    @FXML private ComboBox<DoctorDTO> doctorComboBox;
    @FXML private Label errorLabel;

    /** The root. */
    private Region root;
    /** The view handler. */
    private ViewHandler viewHandler;
    /** The edit appointment view model. */
    private ReceptionistEditAppointmentViewModel editAppointmentViewModel;
    /** The appointment hours. */
    private ArrayList<String> appointmentHours = new ArrayList<String>();
    /** The appointment id. */
    private int appointmentId;

    /**
     * Creates a new {@code ReceptionistEditAppointmentWindowController} instance.
     */
    public ReceptionistEditAppointmentWindowController() {}

    /**
     * Sets up the controller with its view handler, view model and root.
     *
     * @param viewHandler the view handler
     * @param viewModel the view model
     * @param root the root
     * @param appointment the appointment
     */
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

    /**
     * Loads the doctors into the view.
     */
    private void loadDoctors() {
        try {
            doctorComboBox.setItems(FXCollections.observableArrayList(editAppointmentViewModel.getAllDoctors()));
        }
        catch (Exception e) {
            editAppointmentViewModel.getErrorProperty().set(e.getMessage() == null ? "Could not load doctors." : e.getMessage());
        }
    }

    /**
     * Handles the confirm button being pressed in the view.
     */
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

    /**
     * Handles the cancel button being pressed in the view.
     */
    @FXML
    public void cancelButtonPressed() {
        viewHandler.openView("receptionist");
    }

    /**
     * Clears the input fields and puts the window back to the start.
     */
    public void reset() {
        editAppointmentViewModel.clear();
        datePicker.setValue(null);
        doctorComboBox.getSelectionModel().clearSelection();
        timeComboBox.getSelectionModel().clearSelection();
        loadDoctors();
    }

    /**
     * Returns the root of this window.
     *
     * @return the root
     */
    public Region getRoot() { return root; }

    /**
     * Handles the Enter key being pressed.
     *
     * @param actionEvent the action event
     */
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

    /**
     * Handles the date select event from the view.
     */
    @FXML
    private void onDateSelect() {
        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.now();

        if (!(datePicker.getValue() ==null) && datePicker.getValue().equals(date) && time.getHour()>9 && time.getHour()<19){
            for (int i = 0; i < 18 - time.getHour(); i++) {
                int x = 18-i;
                appointmentHours.add(x + ":00");
            }

            timeComboBox.setItems(
                    FXCollections.observableArrayList(
                            appointmentHours
                    )
            );
        } else if (datePicker.getValue().equals(date)) timeComboBox.setItems(null);

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

    /**
     * Sets up the controller with its view handler, view model and root.
     *
     * @param viewHandler the view handler
     * @param receptionistEditAppointmentViewModel the receptionist edit appointment view model
     * @param root the root
     */
    public void init(ViewHandler viewHandler, ReceptionistEditAppointmentViewModel receptionistEditAppointmentViewModel, Region root) {

    }
}
