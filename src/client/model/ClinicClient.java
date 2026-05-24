package client.model;

import server.model.bookAppointment.AppointmentDAO;
import server.model.bookAppointment.PatientDAO;
import shared.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Lists all the things the client can ask the server to do.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public interface ClinicClient {
    /**
     * Checks the login and starts a session.
     *
     * @param username the username
     * @param password the password
     * @return the resulting session DTO
     * @throws Exception if the operation cannot be completed
     */
    SessionDTO login(String username, String password) throws Exception;
    /**
     * Logs the user out.
     *
     * @throws Exception if the operation cannot be completed
     */
    void logout() throws Exception;

    /**
     * Creates a new patient.
     *
     * @param patient the patient
     * @return the resulting patient DTO
     * @throws Exception if the operation cannot be completed
     */
    PatientDTO createPatient(PatientDTO patient) throws Exception;
    /**
     * Updates the patient.
     *
     * @param patientDTO the patient DTO
     * @return the resulting patient DTO
     * @throws Exception if the operation cannot be completed
     */
    PatientDTO updatePatient(PatientDTO patientDTO) throws Exception;
    /**
     * Creates a new doctor.
     *
     * @param doctor the doctor
     * @return the resulting doctor DTO
     * @throws Exception if the operation cannot be completed
     */
    DoctorDTO createDoctor(DoctorDTO doctor) throws Exception;
    /**
     * Creates a new receptionist.
     *
     * @param receptionist the receptionist
     * @return the resulting receptionist DTO
     * @throws Exception if the operation cannot be completed
     */
    ReceptionistDTO createReceptionist(ReceptionistDTO receptionist) throws Exception;

    /**
     * Returns all patients.
     *
     * @return the all patients
     * @throws Exception if the operation cannot be completed
     */
    ArrayList<PatientDTO> getAllPatients() throws Exception;
    /**
     * Returns all doctors.
     *
     * @return the all doctors
     * @throws Exception if the operation cannot be completed
     */
    ArrayList<DoctorDTO> getAllDoctors() throws Exception;
    /**
     * Returns all receptionists.
     *
     * @return the all receptionists
     * @throws Exception if the operation cannot be completed
     */
    ArrayList<ReceptionistDTO> getAllReceptionists() throws Exception;
    /**
     * Returns all appointments.
     *
     * @return the all appointments
     * @throws Exception if the operation cannot be completed
     */
    ArrayList<AppointmentDTO> getAllAppointments() throws Exception;

    /**
     * Returns the patient by id.
     *
     * @param patientId the identifier of the patient
     * @return the patient by id
     * @throws Exception if the operation cannot be completed
     */
    PatientDTO getPatientById(int patientId) throws Exception;
    /**
     * Deletes the patient.
     *
     * @param patientId the identifier of the patient
     * @throws Exception if the operation cannot be completed
     */
    void deletePatient(int patientId) throws Exception;

    /**
     * Creates a new appointment.
     *
     * @param patientId the identifier of the patient
     * @param doctorId the identifier of the doctor
     * @param date the date
     * @param status the status
     * @param notes the notes
     * @return the resulting appointment DTO
     * @throws Exception if the operation cannot be completed
     */
    AppointmentDTO createAppointment(int patientId, int doctorId, LocalDateTime date, boolean status, String notes) throws Exception;
    /**
     * Updates the appointment.
     *
     * @param appointmentId the identifier of the appointment
     * @param doctorId the identifier of the doctor
     * @param date the date
     * @return the resulting appointment DTO
     * @throws Exception if the operation cannot be completed
     */
    AppointmentDTO updateAppointment(int appointmentId, int doctorId, LocalDateTime date) throws Exception;
    /**
     * Deletes the appointment.
     *
     * @param appointmentId the identifier of the appointment
     * @throws SQLException if the operation cannot be completed
     */
    void deleteAppointment(int appointmentId) throws SQLException;
    /**
     * Finishes the appointment.
     *
     * @param appointmentId the identifier of the appointment
     * @param notes the notes
     * @return the resulting appointment DTO
     * @throws Exception if the operation cannot be completed
     */
    AppointmentDTO finishAppointment(int appointmentId, String notes) throws Exception;
    /**
     * Returns the appointments by patient id.
     *
     * @param patientId the identifier of the patient
     * @return the appointments by patient id
     * @throws Exception if the operation cannot be completed
     */
    ArrayList<AppointmentDTO> getAppointmentsByPatientId(int patientId) throws Exception;
    /**
     * Returns the appointments by doctor id.
     *
     * @param doctorId the identifier of the doctor
     * @return the appointments by doctor id
     * @throws Exception if the operation cannot be completed
     */
    ArrayList<AppointmentDTO> getAppointmentsByDoctorId(int doctorId) throws Exception;
}
