package client.viewModel.receptionist;

import client.model.ClinicClient;
import shared.AppointmentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * View model for the Receptionist Today Appointments screen.
 * Stores the data shown on screen and has the methods the controller calls.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class ReceptionistTodayAppointmentsViewModel {
    /** The client. */
    ClinicClient client;

    /**
     * Creates a new {@code ReceptionistTodayAppointmentsViewModel} initialised with the given client.
     *
     * @param client the client
     */
    public ReceptionistTodayAppointmentsViewModel(ClinicClient client) {
        this.client=client;
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
     * Deletes the appointment.
     *
     * @param appointmentId the id of the appointment
     * @throws SQLException if the operation cannot be completed
     */
    public void deleteAppointment(int appointmentId) throws SQLException {
        client.deleteAppointment(appointmentId);
    }
}
