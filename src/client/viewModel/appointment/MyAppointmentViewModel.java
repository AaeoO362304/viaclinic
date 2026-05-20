package client.viewModel.appointment;

import client.viewModel.login.DoctorViewModel;
import client.viewModel.login.PatientViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import server.model.auth.Session;
import server.model.bookAppointment.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class MyAppointmentViewModel {

    private UserHandler handler;

    private AppointmentDAO appointmentDAO;

    private DoctorViewModel doctorViewModel;
    private PatientViewModel patientViewModel;

    private Doctor doctor;
    private Patient patient;
    private LocalDate appointmentDate;
    private StringProperty notes;

    public MyAppointmentViewModel(UserHandler handler, PatientViewModel patientViewModel) {
        this.handler=handler;
        this.notes = new SimpleStringProperty();
        this.patientViewModel=patientViewModel;
    }

    public ArrayList<Appointment> getAllAppointmentsByPatientId() throws SQLException
    {
        Patient patient = (Patient) patientViewModel.getLoginViewModel().getCurrentSession().getUser();


        int patientId = patient.getPatientID();

        return AppointmentDAO.getInstance().getAppointmentsByPatientId(patientId);
    }


}
