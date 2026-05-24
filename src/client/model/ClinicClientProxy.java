package client.model;

import server.service.ClinicService;
import server.service.ClinicServiceImpl;
import shared.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Client-side class that passes the client's requests on to the server service.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class ClinicClientProxy implements ClinicClient {
    /** The service. */
    private final ClinicService service;

    /**
     * Creates a new {@code ClinicClientProxy} instance.
     */
    public ClinicClientProxy() {
        this(new ClinicServiceImpl());
    }

    /**
     * Creates a new {@code ClinicClientProxy} initialised with the given service.
     *
     * @param service the service
     */
    public ClinicClientProxy(ClinicService service) {
        this.service = service;
    }

    /**
     * Checks the login and starts a session.
     *
     * @param username the username
     * @param password the password
     * @return the resulting session DTO
     * @throws Exception if the operation cannot be completed
     */
    @Override
    public SessionDTO login(String username, String password) throws Exception { return service.login(username, password); }

    /**
     * Logs the user out.
     *
     * @throws Exception if the operation cannot be completed
     */
    @Override
    public void logout() throws Exception { service.logout(); }

    /**
     * Creates a new patient.
     *
     * @param patient the patient
     * @return the resulting patient DTO
     * @throws Exception if the operation cannot be completed
     */
    @Override
    public PatientDTO createPatient(PatientDTO patient) throws Exception { return service.createPatient(patient); }

    /**
     * Updates the patient.
     *
     * @param patientDTO the patient DTO
     * @return the resulting patient DTO
     * @throws Exception if the operation cannot be completed
     */
    @Override
    public PatientDTO updatePatient(PatientDTO patientDTO) throws Exception {
        return service.updatePatient(patientDTO);
    }

    /**
     * Deletes the patient.
     *
     * @param patientId the identifier of the patient
     * @throws Exception if the operation cannot be completed
     */
    @Override
    public void deletePatient(int patientId) throws Exception {
        service.deletePatient(patientId);
    }

    /**
     * Creates a new doctor.
     *
     * @param doctor the doctor
     * @return the resulting doctor DTO
     * @throws Exception if the operation cannot be completed
     */
    @Override
    public DoctorDTO createDoctor(DoctorDTO doctor) throws Exception { return service.createDoctor(doctor); }

    /**
     * Creates a new receptionist.
     *
     * @param receptionist the receptionist
     * @return the resulting receptionist DTO
     * @throws Exception if the operation cannot be completed
     */
    @Override
    public ReceptionistDTO createReceptionist(ReceptionistDTO receptionist) throws Exception { return service.createReceptionist(receptionist); }

    /**
     * Returns all patients.
     *
     * @return the all patients
     * @throws Exception if the operation cannot be completed
     */
    @Override
    public ArrayList<PatientDTO> getAllPatients() throws Exception { return service.getAllPatients(); }

    /**
     * Returns all doctors.
     *
     * @return the all doctors
     * @throws Exception if the operation cannot be completed
     */
    @Override
    public ArrayList<DoctorDTO> getAllDoctors() throws Exception { return service.getAllDoctors(); }

    /**
     * Returns all receptionists.
     *
     * @return the all receptionists
     * @throws Exception if the operation cannot be completed
     */
    @Override
    public ArrayList<ReceptionistDTO> getAllReceptionists() throws Exception { return service.getAllReceptionists(); }

    /**
     * Returns all appointments.
     *
     * @return the all appointments
     * @throws Exception if the operation cannot be completed
     */
    @Override
    public ArrayList<AppointmentDTO> getAllAppointments() throws Exception {
        return service.getAllAppointments();
    }

    /**
     * Returns the patient by id.
     *
     * @param patientId the identifier of the patient
     * @return the patient by id
     * @throws Exception if the operation cannot be completed
     */
    @Override
    public PatientDTO getPatientById(int patientId) throws Exception {
        return service.getPatientById(patientId);
    }

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
    @Override
    public AppointmentDTO createAppointment(int patientId, int doctorId, LocalDateTime date, boolean status, String notes) throws Exception {
        return service.createAppointment(patientId, doctorId, date, status, notes);
    }

    /**
     * Updates the appointment.
     *
     * @param appointmentId the identifier of the appointment
     * @param doctorId the identifier of the doctor
     * @param date the date
     * @return the resulting appointment DTO
     * @throws Exception if the operation cannot be completed
     */
    @Override
    public AppointmentDTO updateAppointment(int appointmentId, int doctorId, LocalDateTime date) throws Exception {
        return service.updateAppointment(appointmentId, doctorId, date);
    }

    /**
     * Deletes the appointment.
     *
     * @param appointmentId the identifier of the appointment
     * @throws SQLException if the operation cannot be completed
     */
    @Override
    public void deleteAppointment(int appointmentId) throws SQLException {
        service.deleteAppointment(appointmentId);
    }

    /**
     * Finishes the appointment.
     *
     * @param appointmentId the identifier of the appointment
     * @param notes the notes
     * @return the resulting appointment DTO
     * @throws Exception if the operation cannot be completed
     */
    @Override
    public AppointmentDTO finishAppointment(int appointmentId, String notes) throws Exception {
        return service.finishAppointment(appointmentId, notes);
    }

    /**
     * Returns the appointments by patient id.
     *
     * @param patientId the identifier of the patient
     * @return the appointments by patient id
     * @throws Exception if the operation cannot be completed
     */
    @Override
    public ArrayList<AppointmentDTO> getAppointmentsByPatientId(int patientId) throws Exception {
        return service.getAppointmentsByPatientId(patientId);
    }

    /**
     * Returns the appointments by doctor id.
     *
     * @param doctorId the identifier of the doctor
     * @return the appointments by doctor id
     * @throws Exception if the operation cannot be completed
     */
    @Override
    public ArrayList<AppointmentDTO> getAppointmentsByDoctorId(int doctorId) throws Exception {
        return service.getAppointmentsByDoctorId(doctorId);
    }
}
