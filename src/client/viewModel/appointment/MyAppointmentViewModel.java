package client.viewModel.appointment;

import client.model.ClinicClient;
import client.viewModel.login.PatientViewModel;
import shared.AppointmentDTO;
import shared.SessionDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class MyAppointmentViewModel {
    private final ClinicClient client;
    private final PatientViewModel patientViewModel;

    public MyAppointmentViewModel(ClinicClient client, PatientViewModel patientViewModel) {
        this.client = client;
        this.patientViewModel = patientViewModel;
    }

    public ArrayList<AppointmentDTO> getAllAppointmentsByPatientId() throws Exception {
        SessionDTO session = patientViewModel.getLoginViewModel().getCurrentSession();
        if (session == null || session.getUserId() <= 0) {
            return new ArrayList<>();
        }
        return client.getAppointmentsByPatientId(session.getUserId());
    }

    public void deleteAppointment(int appointmentId) throws SQLException {
        client.deleteAppointment(appointmentId);
    }

    public PatientViewModel getPatientViewModel() {
        return patientViewModel;
    }
}
