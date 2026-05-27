package client.viewModel.doctor;

import client.model.ClinicClient;
import shared.AppointmentDTO;
import shared.SessionDTO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * View model for the Today Appointments screen.
 * Stores the data shown on screen and has the methods the controller calls.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class TodayAppointmentsViewModel {
    /** The client. */
    ClinicClient client;
    /** The doctor view model. */
    DoctorViewModel doctorViewModel;

    /**
     * Creates a new {@code TodayAppointmentsViewModel} initialised with the given client, doctor view model.
     *
     * @param client the client
     * @param doctorViewModel the doctor view model
     */
    public TodayAppointmentsViewModel(ClinicClient client, DoctorViewModel doctorViewModel) {
        this.client=client;
        this.doctorViewModel=doctorViewModel;
    }

    /**
     * Returns all appointments by doctor id.
     *
     * @return the all appointments by doctor id
     * @throws Exception if the operation cannot be completed
     */
    public ArrayList<AppointmentDTO> getAllAppointmentsByDoctorId() throws Exception {
        SessionDTO session = doctorViewModel.getLoginViewModel().getCurrentSession();
        if (session == null || session.getUserId() <= 0) {
            return new ArrayList<>();
        }
        return client.getAppointmentsByDoctorId(session.getUserId());
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
