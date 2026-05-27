package server.service;

import server.model.auth.AuthenticationException;
import shared.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Lists the clinic operations the server offers to the client.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public interface ClinicService {
    /**
     * Checks the login and starts a session.
     *
     * @param username the username
     * @param password the password
     * @return the resulting session DTO
     * @throws AuthenticationException if the operation cannot be completed
     */
    SessionDTO login(String username, String password) throws AuthenticationException;
    /**
     * Logs the user out.
     */
    void logout();

    /**
     * Creates a new patient.
     *
     * @param patient the patient
     * @return the resulting patient DTO
     * @throws SQLException if the operation cannot be completed
     */
    PatientDTO createPatient(PatientDTO patient) throws SQLException;
    /**
     * Updates the patient.
     *
     * @param patient the patient
     * @return the resulting patientDTO
     * @throws SQLException if the operation cannot be completed
     */
    PatientDTO updatePatient(PatientDTO patient) throws SQLException;
    /**
     * Creates a new doctor.
     *
     * @param doctor the doctor
     * @return the resulting doctorDTO
     * @throws SQLException if the operation cannot be completed
     */
    DoctorDTO createDoctor(DoctorDTO doctor) throws SQLException;
    /**
     * Creates a new receptionist.
     *
     * @param receptionist the receptionist
     * @return the resulting receptionistDTO
     * @throws SQLException if the operation cannot be completed
     */
    ReceptionistDTO createReceptionist(ReceptionistDTO receptionist) throws SQLException;

    /**
     * Returns all patients.
     *
     * @return the all patients
     * @throws SQLException if the operation cannot be completed
     */
    ArrayList<PatientDTO> getAllPatients() throws SQLException;
    /**
     * Returns all doctors.
     *
     * @return the all doctors
     * @throws SQLException if the operation cannot be completed
     */
    ArrayList<DoctorDTO> getAllDoctors() throws SQLException;
    /**
     * Returns all receptionists.
     *
     * @return the all receptionists
     * @throws SQLException if the operation cannot be completed
     */
    ArrayList<ReceptionistDTO> getAllReceptionists() throws SQLException;
    /**
     * Returns all appointments.
     *
     * @return the all appointments
     * @throws SQLException if the operation cannot be completed
     */
    ArrayList<AppointmentDTO> getAllAppointments() throws SQLException;

    /**
     * Returns the patient by id.
     *
     * @param patientId the id of the patient
     * @return the patient by id
     * @throws SQLException if the operation cannot be completed
     */
    PatientDTO getPatientById(int patientId) throws SQLException;
    /**
     * Deletes the patient.
     *
     * @param patientId the id of the patient
     * @throws SQLException if the operation cannot be completed
     */
    void deletePatient(int patientId) throws SQLException;

    /**
     * Creates a new appointment.
     *
     * @param patientId the id of the patient
     * @param doctorId the id of the doctor
     * @param date the date
     * @param status the status
     * @param notes the notes
     * @return the resulting appointmentDTO
     * @throws SQLException if the operation cannot be completed
     */
    AppointmentDTO createAppointment(int patientId, int doctorId, LocalDateTime date, boolean status, String notes) throws SQLException;
    /**
     * Updates the appointment.
     *
     * @param appointmentId the id of the appointment
     * @param doctorId the id of the doctor
     * @param date the date
     * @return the resulting appointmentDTO
     * @throws SQLException if the operation cannot be completed
     */
    AppointmentDTO updateAppointment(int appointmentId, int doctorId, LocalDateTime date) throws SQLException;
    /**
     * Deletes the appointment.
     *
     * @param appointmentId the id of the appointment
     * @throws SQLException if the operation cannot be completed
     */
    void deleteAppointment(int appointmentId) throws SQLException;
    /**
     * Finishes the appointment.
     *
     * @param appointmentId the id of the appointment
     * @param notes the notes
     * @return the resulting appointment DTO
     * @throws SQLException if the operation cannot be completed
     */
    AppointmentDTO finishAppointment(int appointmentId, String notes) throws SQLException;
    /**
     * Returns the appointments by patient id.
     *
     * @param patientId the id of the patient
     * @return the appointments by patient id
     * @throws SQLException if the operation cannot be completed
     */
    ArrayList<AppointmentDTO> getAppointmentsByPatientId(int patientId) throws SQLException;
    /**
     * Returns the appointments by doctor id.
     *
     * @param doctorId the id of the doctor
     * @return the appointments by doctor id
     * @throws SQLException if the operation cannot be completed
     */
    ArrayList<AppointmentDTO> getAppointmentsByDoctorId(int doctorId) throws SQLException;
}
