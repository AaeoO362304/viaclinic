package server.service;

import server.model.auth.AuthenticationException;
import server.model.auth.LoginAuthenticator;
import server.model.auth.Session;
import server.model.bookAppointment.*;
import shared.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The main server class. It uses the DAOs, checks logins, and converts objects to DTOs.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class ClinicServiceImpl implements ClinicService {
    /** The login authenticator. */
    private final LoginAuthenticator loginAuthenticator;

    /**
     * Creates a new {@code ClinicServiceImpl} instance.
     */
    public ClinicServiceImpl() {
        this.loginAuthenticator = new LoginAuthenticator();
    }

    /**
     * Checks the login and starts a session.
     *
     * @param username the username
     * @param password the password
     * @return the resulting session DTO
     * @throws AuthenticationException if the operation cannot be completed
     */
    @Override
    public SessionDTO login(String username, String password) throws AuthenticationException {
        Session session = loginAuthenticator.login(username, password);
        return DTOMapper.toSessionDTO(session);
    }

    /**
     * Logs the user out.
     */
    @Override
    public void logout() {
        loginAuthenticator.logout();
    }

    /**
     * Creates a new patient.
     *
     * @param patientDTO the patient DTO
     * @return the resulting patient DTO
     * @throws SQLException if the operation cannot be completed
     */
    @Override
    public PatientDTO createPatient(PatientDTO patientDTO) throws SQLException {
        Patient patient = DTOMapper.toPatient(patientDTO);
        Patient created = PatientDAO.getInstance().createPatient(patient);
        return DTOMapper.toPatientDTO(created);
    }

    /**
     * Updates the patient.
     *
     * @param patientDTO the patient DTO
     * @return the resulting patient DTO
     * @throws SQLException if the operation cannot be completed
     */
    @Override
    public PatientDTO updatePatient(PatientDTO patientDTO) throws SQLException {
        Patient patient = DTOMapper.toPatient(patientDTO);
        Patient updated = PatientDAO.getInstance().updatePatient(patient);
        return DTOMapper.toPatientDTO(updated);
    }

    /**
     * Deletes the patient.
     *
     * @param patientId the identifier of the patient
     * @throws SQLException if the operation cannot be completed
     */
    @Override
    public void deletePatient(int patientId) throws SQLException {
        PatientDAO.getInstance().deletePatient(patientId);
    }

    /**
     * Creates a new doctor.
     *
     * @param doctorDTO the doctor DTO
     * @return the resulting doctor DTO
     * @throws SQLException if the operation cannot be completed
     */
    @Override
    public DoctorDTO createDoctor(DoctorDTO doctorDTO) throws SQLException {
        Doctor doctor = DTOMapper.toDoctor(doctorDTO);
        Doctor created = DoctorDAO.getInstance().createDoctor(doctor);
        return DTOMapper.toDoctorDTO(created);
    }

    /**
     * Creates a new receptionist.
     *
     * @param receptionistDTO the receptionist DTO
     * @return the resulting receptionist DTO
     * @throws SQLException if the operation cannot be completed
     */
    @Override
    public ReceptionistDTO createReceptionist(ReceptionistDTO receptionistDTO) throws SQLException {
        Receptionist receptionist = DTOMapper.toReceptionist(receptionistDTO);
        Receptionist created = ReceptionistDAO.getInstance().createReceptionist(receptionist);
        return DTOMapper.toReceptionistDTO(created);
    }

    /**
     * Returns all patients.
     *
     * @return the all patients
     * @throws SQLException if the operation cannot be completed
     */
    @Override
    public ArrayList<PatientDTO> getAllPatients() throws SQLException {
        ArrayList<PatientDTO> result = new ArrayList<>();
        for (Patient patient : PatientDAO.getInstance().getAllPatients()) {
            result.add(DTOMapper.toPatientDTO(patient));
        }
        return result;
    }

    /**
     * Returns all doctors.
     *
     * @return the all doctors
     * @throws SQLException if the operation cannot be completed
     */
    @Override
    public ArrayList<DoctorDTO> getAllDoctors() throws SQLException {
        ArrayList<DoctorDTO> result = new ArrayList<>();
        for (Doctor doctor : DoctorDAO.getInstance().getAllDoctors()) {
            result.add(DTOMapper.toDoctorDTO(doctor));
        }
        return result;
    }

    /**
     * Returns all receptionists.
     *
     * @return the all receptionists
     * @throws SQLException if the operation cannot be completed
     */
    @Override
    public ArrayList<ReceptionistDTO> getAllReceptionists() throws SQLException {
        ArrayList<ReceptionistDTO> result = new ArrayList<>();
        for (Receptionist receptionist : ReceptionistDAO.getInstance().getAllReceptionists()) {
            result.add(DTOMapper.toReceptionistDTO(receptionist));
        }
        return result;
    }

    /**
     * Returns all appointments.
     *
     * @return the all appointments
     * @throws SQLException if the operation cannot be completed
     */
    @Override
    public ArrayList<AppointmentDTO> getAllAppointments() throws SQLException {
        ArrayList<AppointmentDTO> result = new ArrayList<>();
        for (Appointment appointment : AppointmentDAO.getInstance().getAllAppointments()) {
            result.add(DTOMapper.toAppointmentDTO(appointment));
        }
        return result;
    }

    /**
     * Returns the patient by id.
     *
     * @param patientId the identifier of the patient
     * @return the patient by id
     * @throws SQLException if the operation cannot be completed
     */
    @Override
    public PatientDTO getPatientById(int patientId) throws SQLException {
        Patient patient = PatientDAO.getInstance().getPatientById(patientId);
        return DTOMapper.toPatientDTO(patient);
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
     * @throws SQLException if the operation cannot be completed
     */
    @Override
    public AppointmentDTO createAppointment(int patientId, int doctorId, LocalDateTime date, boolean status, String notes) throws SQLException {
        Patient patient = PatientDAO.getInstance().getPatientById(patientId);
        Doctor doctor = DoctorDAO.getInstance().getDoctorById(doctorId);
        if (patient == null) throw new SQLException("Patient not found: " + patientId);
        if (doctor == null) throw new SQLException("Doctor not found: " + doctorId);

        Appointment appointment = AppointmentDAO.getInstance().create(patient, doctor, date, status, notes);
        return DTOMapper.toAppointmentDTO(appointment);
    }

    /**
     * Updates the appointment.
     *
     * @param appointmentId the identifier of the appointment
     * @param doctorId the identifier of the doctor
     * @param date the date
     * @return the resulting appointment DTO
     * @throws SQLException if the operation cannot be completed
     */
    @Override
    public AppointmentDTO updateAppointment(int appointmentId, int doctorId, LocalDateTime date) throws SQLException {
        Doctor doctor = DoctorDAO.getInstance().getDoctorById(doctorId);
        Appointment appointment = AppointmentDAO.getInstance().updateAppointment(appointmentId, date, doctorId);
        return DTOMapper.toAppointmentDTO(appointment);
    }

    /**
     * Deletes the appointment.
     *
     * @param appointmentId the identifier of the appointment
     * @throws SQLException if the operation cannot be completed
     */
    @Override
    public void deleteAppointment(int appointmentId) throws SQLException {
        AppointmentDAO.getInstance().deleteAppointment(appointmentId);
    }

    /**
     * Finishes the appointment.
     *
     * @param appointmentId the identifier of the appointment
     * @param notes the notes
     * @return the resulting appointment DTO
     * @throws SQLException if the operation cannot be completed
     */
    @Override
    public AppointmentDTO finishAppointment(int appointmentId, String notes) throws SQLException {
       AppointmentDAO appointmentDAO  = AppointmentDAO.getInstance();
        Appointment appointment = appointmentDAO.getAppointmentById(appointmentId);

        if (appointment == null) {
            throw new IllegalArgumentException("Appointment not found.");
        }

        appointment.setNotes(notes);
        appointment.finish();

        appointment = appointmentDAO.finishAppointment(
                appointment.getAppointmentID(),
                appointment.isStatus(),
                notes
        );

        return DTOMapper.toAppointmentDTO(appointment);
    }

    /**
     * Returns the appointments by patient id.
     *
     * @param patientId the identifier of the patient
     * @return the appointments by patient id
     * @throws SQLException if the operation cannot be completed
     */
    @Override
    public ArrayList<AppointmentDTO> getAppointmentsByPatientId(int patientId) throws SQLException {
        ArrayList<AppointmentDTO> result = new ArrayList<>();
        for (Appointment appointment : AppointmentDAO.getInstance().getAppointmentsByPatientId(patientId)) {
            result.add(DTOMapper.toAppointmentDTO(appointment));
        }
        return result;
    }

    /**
     * Returns the appointments by doctor id.
     *
     * @param doctorId the identifier of the doctor
     * @return the appointments by doctor id
     * @throws SQLException if the operation cannot be completed
     */
    @Override
    public ArrayList<AppointmentDTO> getAppointmentsByDoctorId(int doctorId) throws SQLException {
        ArrayList<AppointmentDTO> result = new ArrayList<>();
        for (Appointment appointment : AppointmentDAO.getInstance().getAppointmentsByDoctorId(doctorId)) {
            result.add(DTOMapper.toAppointmentDTO(appointment));
        }
        return result;
    }
}
