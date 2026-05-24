package client.viewModel.receptionist;

import client.model.ClinicClient;
import client.view.doctor.TodayAppointmentsWindowController;
import client.viewModel.login.DoctorViewModel;
import shared.AppointmentDTO;
import shared.SessionDTO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * View model for the Receptionist All Appointments screen.
 * Stores the data shown on screen and has the methods the controller calls.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class ReceptionistAllAppointmentsViewModel {
    /** The client. */
    ClinicClient client;

    /**
     * Creates a new {@code ReceptionistAllAppointmentsViewModel} initialised with the given client.
     *
     * @param client the client
     */
    public ReceptionistAllAppointmentsViewModel(ClinicClient client) {
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
     * @param appointmentId the identifier of the appointment
     * @throws SQLException if the operation cannot be completed
     */
    public void deleteAppointment(int appointmentId) throws SQLException {
        client.deleteAppointment(appointmentId);
    }
}
