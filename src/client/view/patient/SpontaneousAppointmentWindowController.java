package client.view.patient;

import client.view.ViewHandler;
import client.viewModel.patient.SpontaneousAppointmentViewModel;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import shared.AppointmentDTO;
import shared.DoctorDTO;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Controller for the Spontaneous Appointment Window view.
 * Reacts to button clicks and other input in this window and calls the view model to do the work.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class SpontaneousAppointmentWindowController {
    /** The cancel button. */
    @FXML private Button cancelButton;

    /** The empty label. */
    @FXML private Label emptyLabel;

    /** The view handler. */
    private ViewHandler viewHandler;
    /** The view model. */
    private SpontaneousAppointmentViewModel viewModel;
    /** The root. */
    private Region root;

    /**
     * Sets up the controller with its view handler, view model and root.
     *
     * @param viewHandler the view handler
     * @param viewModel the view model
     * @param root the root
     * @throws Exception if the operation cannot be completed
     */
    public void init(ViewHandler viewHandler, SpontaneousAppointmentViewModel viewModel,
                     Region root) throws Exception {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;

        ArrayList<DoctorDTO> doctors = viewModel.getAllDoctors();
        ArrayList<AppointmentDTO> appointments = viewModel.getAllAppointments();
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        int x = 0;

        for (int i=0; i<appointments.size(); i++) {
            if (appointments.get(i).getDate().toLocalDate().equals(date) && appointments.get(i).getDate().getHour()==time.getHour()) {
                x++;
            }
        }

        if (time.getHour()>18 || time.getHour()<10) {
            emptyLabel.setText("Clinic is closed.");
        } else if (doctors.size()-x>=0) {
            emptyLabel.setText(doctors.size()-x+" free slot(s) for this hour.");
        } else emptyLabel.setText("Overbooked.");



    }

    /**
     * Handles the cancel button being pressed in the view.
     */
    public void cancelButtonPressed() {
        viewHandler.openView("patient");
    }

    /**
     * Returns the root of this window.
     *
     * @return the root
     */
    public Region getRoot() {
        return root;
    }

    /**
     * Clears the input fields and puts the window back to the start.
     */
    public void reset() {
        viewModel.clear();
    }

}
