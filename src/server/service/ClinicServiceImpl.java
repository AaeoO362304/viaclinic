package server.service;

import server.model.auth.AuthenticationException;
import server.model.auth.LoginAuthenticator;
import server.model.auth.Session;
import server.model.bookAppointment.*;
import shared.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ClinicServiceImpl implements ClinicService {
    private final LoginAuthenticator loginAuthenticator;

    public ClinicServiceImpl() {
        this.loginAuthenticator = new LoginAuthenticator();
    }

    @Override
    public SessionDTO login(String username, String password) throws AuthenticationException {
        Session session = loginAuthenticator.login(username, password);
        return DTOMapper.toSessionDTO(session);
    }

    @Override
    public void logout() {
        loginAuthenticator.logout();
    }

    @Override
    public PatientDTO createPatient(PatientDTO patientDTO) throws SQLException {
        Patient patient = DTOMapper.toPatient(patientDTO);
        Patient created = PatientDAO.getInstance().createPatient(patient);
        return DTOMapper.toPatientDTO(created);
    }

    @Override
    public PatientDTO updatePatient(PatientDTO patientDTO) throws SQLException {
        Patient patient = DTOMapper.toPatient(patientDTO);
        Patient updated = PatientDAO.getInstance().updatePatient(patientDTO.getUserName());
        return DTOMapper.toPatientDTO(updated);
    }

    @Override
    public DoctorDTO createDoctor(DoctorDTO doctorDTO) throws SQLException {
        Doctor doctor = DTOMapper.toDoctor(doctorDTO);
        Doctor created = DoctorDAO.getInstance().createDoctor(doctor);
        return DTOMapper.toDoctorDTO(created);
    }

    @Override
    public ReceptionistDTO createReceptionist(ReceptionistDTO receptionistDTO) throws SQLException {
        Receptionist receptionist = DTOMapper.toReceptionist(receptionistDTO);
        Receptionist created = ReceptionistDAO.getInstance().createReceptionist(receptionist);
        return DTOMapper.toReceptionistDTO(created);
    }

    @Override
    public ArrayList<PatientDTO> getAllPatients() throws SQLException {
        ArrayList<PatientDTO> result = new ArrayList<>();
        for (Patient patient : PatientDAO.getInstance().getAllPatients()) {
            result.add(DTOMapper.toPatientDTO(patient));
        }
        return result;
    }

    @Override
    public ArrayList<DoctorDTO> getAllDoctors() throws SQLException {
        ArrayList<DoctorDTO> result = new ArrayList<>();
        for (Doctor doctor : DoctorDAO.getInstance().getAllDoctors()) {
            result.add(DTOMapper.toDoctorDTO(doctor));
        }
        return result;
    }

    @Override
    public ArrayList<ReceptionistDTO> getAllReceptionists() throws SQLException {
        ArrayList<ReceptionistDTO> result = new ArrayList<>();
        for (Receptionist receptionist : ReceptionistDAO.getInstance().getAllReceptionists()) {
            result.add(DTOMapper.toReceptionistDTO(receptionist));
        }
        return result;
    }

    @Override
    public PatientDTO getPatientById(int patientId) throws SQLException {
        Patient patient = PatientDAO.getInstance().getPatientById(patientId);
        return DTOMapper.toPatientDTO(patient);
    }

    @Override
    public AppointmentDTO createAppointment(int patientId, int doctorId, LocalDateTime date, boolean status, String notes) throws SQLException {
        Patient patient = PatientDAO.getInstance().getPatientById(patientId);
        Doctor doctor = DoctorDAO.getInstance().getDoctorById(doctorId);
        if (patient == null) throw new SQLException("Patient not found: " + patientId);
        if (doctor == null) throw new SQLException("Doctor not found: " + doctorId);

        Appointment appointment = AppointmentDAO.getInstance().create(patient, doctor, date, status, notes);
        return DTOMapper.toAppointmentDTO(appointment);
    }

    @Override
    public AppointmentDTO updateAppointment(int appointmentId, int doctorId, LocalDateTime date) throws SQLException {
        Doctor doctor = DoctorDAO.getInstance().getDoctorById(doctorId);
        Appointment appointment = AppointmentDAO.getInstance().updateAppointment(appointmentId, date, doctorId);
        return DTOMapper.toAppointmentDTO(appointment);
    }

    @Override
    public void deleteAppointment(int appointmentId) throws SQLException {
        AppointmentDAO.getInstance().deleteAppointment(appointmentId);
    }

    @Override
    public ArrayList<AppointmentDTO> getAppointmentsByPatientId(int patientId) throws SQLException {
        ArrayList<AppointmentDTO> result = new ArrayList<>();
        for (Appointment appointment : AppointmentDAO.getInstance().getAppointmentsByPatientId(patientId)) {
            result.add(DTOMapper.toAppointmentDTO(appointment));
        }
        return result;
    }

    @Override
    public ArrayList<AppointmentDTO> getAppointmentsByDoctorId(int doctorId) throws SQLException {
        ArrayList<AppointmentDTO> result = new ArrayList<>();
        for (Appointment appointment : AppointmentDAO.getInstance().getAppointmentsByDoctorId(doctorId)) {
            result.add(DTOMapper.toAppointmentDTO(appointment));
        }
        return result;
    }
}
