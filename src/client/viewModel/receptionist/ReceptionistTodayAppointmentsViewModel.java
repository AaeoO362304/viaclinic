package client.viewModel.receptionist;

import client.model.ClinicClient;
import client.view.doctor.TodayAppointmentsWindowController;
import client.viewModel.login.DoctorViewModel;
import shared.AppointmentDTO;
import shared.SessionDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReceptionistTodayAppointmentsViewModel {
    ClinicClient client;

    public ReceptionistTodayAppointmentsViewModel(ClinicClient client) {
        this.client=client;
    }

    public ArrayList<AppointmentDTO> getAllAppointments() throws Exception {
        return client.getAllAppointments();
    }

    public void deleteAppointment(int appointmentId) throws SQLException {
        client.deleteAppointment(appointmentId);
    }
}
