package server.service;

import server.model.auth.AuthenticationException;
import shared.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface ClinicService {
    SessionDTO login(String username, String password) throws AuthenticationException;
    void logout();

    PatientDTO createPatient(PatientDTO patient) throws SQLException;
    PatientDTO updatePatient(PatientDTO patient) throws SQLException;
    DoctorDTO createDoctor(DoctorDTO doctor) throws SQLException;
    ReceptionistDTO createReceptionist(ReceptionistDTO receptionist) throws SQLException;

    ArrayList<PatientDTO> getAllPatients() throws SQLException;
    ArrayList<DoctorDTO> getAllDoctors() throws SQLException;
    ArrayList<ReceptionistDTO> getAllReceptionists() throws SQLException;

    PatientDTO getPatientById(int patientId) throws SQLException;
    void deletePatient(int patientId) throws SQLException;

    AppointmentDTO createAppointment(int patientId, int doctorId, LocalDateTime date, boolean status, String notes) throws SQLException;
    AppointmentDTO updateAppointment(int appointmentId, int doctorId, LocalDateTime date) throws SQLException;
    void deleteAppointment(int appointmentId) throws SQLException;
    AppointmentDTO finishAppointment(int appointmentId, String notes) throws SQLException;
    ArrayList<AppointmentDTO> getAppointmentsByPatientId(int patientId) throws SQLException;
    ArrayList<AppointmentDTO> getAppointmentsByDoctorId(int doctorId) throws SQLException;
}
