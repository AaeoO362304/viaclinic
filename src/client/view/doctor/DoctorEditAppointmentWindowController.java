package client.view.doctor;

import client.view.login.ViewHandler;
import client.viewModel.appointment.EditAppointmentViewModel;
import client.viewModel.doctor.DoctorEditAppointmentViewModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import shared.AppointmentDTO;
import shared.DoctorDTO;

import java.util.ArrayList;

/**
 * Controller for the Doctor Edit Appointment Window view.
 * Reacts to button clicks and other input in this window and calls the view model to do the work.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class DoctorEditAppointmentWindowController {
    /** The cancel button. */
    @FXML private Button cancelButton;
    @FXML private Button confirmButton;
    @FXML private ComboBox<Boolean> statusComboBox;
    @FXML private TextArea notes;


    /** The root. */
    private Region root;
    /** The view handler. */
    private ViewHandler viewHandler;
    /** The view model. */
    private DoctorEditAppointmentViewModel viewModel;;
    /** The appointment id. */
    private int appointmentId;

    /**
     * Sets up the controller with its view handler, view model and root.
     *
     * @param viewHandler the view handler
     * @param viewModel the view model
     * @param root the root
     * @param appointment the appointment
     */
    public void init(ViewHandler viewHandler,
                     DoctorEditAppointmentViewModel viewModel,
                     Region root,
                     AppointmentDTO appointment)
    {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;
        statusComboBox.setItems(FXCollections.observableArrayList(true, false));

        viewModel.setAppointment(appointment);
    }

    /**
     * Handles the confirm button being pressed in the view.
     */
    @FXML
    public void confirmButtonPressed()
    {
        viewModel.setNotes(notes.getText());

        boolean finished = viewModel.finishAppointment();

        if (finished)
        {
            viewHandler.openView("today_appointments");
        }
    }

    /**
     * Handles the cancel button being pressed in the view.
     */
    @FXML
    public void cancelButtonPressed() {
        viewHandler.openView("doctor");
    }

    /**
     * Clears the input fields and puts the window back to the start.
     */
    public void reset() {
        viewModel.clear();
        statusComboBox.getSelectionModel().clearSelection();
    }

    /**
     * Returns the root of this window.
     *
     * @return the root
     */
    public Region getRoot() { return root; }
}
