package client.model;

import shared.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface ClinicClient {
    SessionDTO login(String username, String password) throws Exception;
    void logout() throws Exception;

    PatientDTO createPatient(PatientDTO patient) throws Exception;
    DoctorDTO createDoctor(DoctorDTO doctor) throws Exception;
    ReceptionistDTO createReceptionist(ReceptionistDTO receptionist) throws Exception;

    ArrayList<PatientDTO> getAllPatients() throws Exception;
    ArrayList<DoctorDTO> getAllDoctors() throws Exception;
    ArrayList<ReceptionistDTO> getAllReceptionists() throws Exception;

    AppointmentDTO createAppointment(int patientId, int doctorId, LocalDateTime date, boolean status, String notes) throws Exception;
    AppointmentDTO updateAppointment(int appointmentId, int doctorId, LocalDateTime date) throws Exception;
    void deleteAppointment(int appointmentId) throws SQLException;
    ArrayList<AppointmentDTO> getAppointmentsByPatientId(int patientId) throws Exception;
    ArrayList<AppointmentDTO> getAppointmentsByDoctorId(int doctorId) throws Exception;
}
