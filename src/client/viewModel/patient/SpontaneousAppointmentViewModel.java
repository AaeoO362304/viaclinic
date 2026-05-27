package client.viewModel.patient;

import client.model.ClinicClient;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.AppointmentDTO;
import shared.DoctorDTO;

import java.util.ArrayList;

/**
 * View model for the Spontaneous Appointment screen.
 * Stores the data shown on screen and has the methods the controller calls.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class SpontaneousAppointmentViewModel {
    /** The client. */
    private final ClinicClient client;
    /** The empty. */
    private final StringProperty empty;

    /**
     * Creates a new {@code SpontaneousAppointmentViewModel} initialised with the given client.
     *
     * @param client the client
     */
    public SpontaneousAppointmentViewModel(ClinicClient client) {
        this.client=client;
        empty = new SimpleStringProperty();
    }

    /**
     * Clears the values in this view model.
     */
    public void clear() {
        empty.set("");
    }

    /**
     * Returns all doctors.
     *
     * @return the all doctors
     * @throws Exception if the operation cannot be completed
     */
    public ArrayList<DoctorDTO> getAllDoctors() throws Exception {
        return client.getAllDoctors();
    }

    /**
     * Returns all appointments.
     *
     * @return the all appointments
     * @throws Exception if the operation cannot be completed
     */
    public ArrayList<AppointmentDTO> getAllAppointments() throws Exception {
        return client.getAllAppointments();
    }

    /**
     * Returns the empty property used for data binding.
     *
     * @return the empty property
     */
    public StringProperty getEmptyProperty() {return empty; }

}
