package client.viewModel.doctor;

import client.model.ClinicClient;
import client.view.doctor.TodayAppointmentsWindowController;
import client.viewModel.login.DoctorViewModel;
import shared.AppointmentDTO;
import shared.SessionDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class TodayAppointmentsViewModel {
    ClinicClient client;
    DoctorViewModel doctorViewModel;

    public TodayAppointmentsViewModel(ClinicClient client, DoctorViewModel doctorViewModel) {
        this.client=client;
        this.doctorViewModel=doctorViewModel;
    }

    public ArrayList<AppointmentDTO> getAllAppointmentsByDoctorId() throws Exception {
        SessionDTO session = doctorViewModel.getLoginViewModel().getCurrentSession();
        if (session == null || session.getUserId() <= 0) {
            return new ArrayList<>();
        }
        return client.getAppointmentsByDoctorId(session.getUserId());
    }

    public void deleteAppointment(int appointmentId) throws SQLException {
        client.deleteAppointment(appointmentId);
    }
}
