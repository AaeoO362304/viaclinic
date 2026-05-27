package client.viewModel.appointment;

import client.model.ClinicClient;
import client.viewModel.patient.PatientViewModel;
import shared.AppointmentDTO;
import shared.SessionDTO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * View model for the My Appointment screen.
 * Stores the data shown on screen and has the methods the controller calls.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class MyAppointmentViewModel {
    /** The client. */
    private final ClinicClient client;
    /** The patient view model. */
    private final PatientViewModel patientViewModel;

    /**
     * Creates a new {@code MyAppointmentViewModel} initialised with the given client, patient view model.
     *
     * @param client the client
     * @param patientViewModel the patient view model
     */
    public MyAppointmentViewModel(ClinicClient client, PatientViewModel patientViewModel) {
        this.client = client;
        this.patientViewModel = patientViewModel;
    }

    /**
     * Returns all appointments by patient id.
     *
     * @return the all appointments by patient id
     * @throws Exception if the operation cannot be completed
     */
    public ArrayList<AppointmentDTO> getAllAppointmentsByPatientId() throws Exception {
        SessionDTO session = patientViewModel.getLoginViewModel().getCurrentSession();
        if (session == null || session.getUserId() <= 0) {
            return new ArrayList<>();
        }
        return client.getAppointmentsByPatientId(session.getUserId());
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

    /**
     * Returns the patient view model.
     *
     * @return the patient view model
     */
    public PatientViewModel getPatientViewModel() {
        return patientViewModel;
    }
}
