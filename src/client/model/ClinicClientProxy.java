package client.model;

import server.service.ClinicService;
import server.service.ClinicServiceImpl;
import shared.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Client-side access point. Right now it delegates in-process to the server service.
 * Later this class can be replaced by socket/RMI communication without changing ViewModels.
 */
public class ClinicClientProxy implements ClinicClient {
    private final ClinicService service;

    public ClinicClientProxy() {
        this(new ClinicServiceImpl());
    }

    public ClinicClientProxy(ClinicService service) {
        this.service = service;
    }

    @Override
    public SessionDTO login(String username, String password) throws Exception { return service.login(username, password); }

    @Override
    public void logout() throws Exception { service.logout(); }

    @Override
    public PatientDTO createPatient(PatientDTO patient) throws Exception { return service.createPatient(patient); }

    @Override
    public PatientDTO updatePatient(PatientDTO patientDTO) throws Exception {
        return service.updatePatient(patientDTO);
    }

    @Override
    public void deletePatient(int patientId) throws Exception {
        service.deletePatient(patientId);
    }

    @Override
    public DoctorDTO createDoctor(DoctorDTO doctor) throws Exception { return service.createDoctor(doctor); }

    @Override
    public ReceptionistDTO createReceptionist(ReceptionistDTO receptionist) throws Exception { return service.createReceptionist(receptionist); }

    @Override
    public ArrayList<PatientDTO> getAllPatients() throws Exception { return service.getAllPatients(); }

    @Override
    public ArrayList<DoctorDTO> getAllDoctors() throws Exception { return service.getAllDoctors(); }

    @Override
    public ArrayList<ReceptionistDTO> getAllReceptionists() throws Exception { return service.getAllReceptionists(); }

    @Override
    public ArrayList<AppointmentDTO> getAllAppointments() throws Exception {
        return service.getAllAppointments();
    }

    @Override
    public PatientDTO getPatientById(int patientId) throws Exception {
        return service.getPatientById(patientId);
    }

    @Override
    public AppointmentDTO createAppointment(int patientId, int doctorId, LocalDateTime date, boolean status, String notes) throws Exception {
        return service.createAppointment(patientId, doctorId, date, status, notes);
    }

    @Override
    public AppointmentDTO updateAppointment(int appointmentId, int doctorId, LocalDateTime date) throws Exception {
        return service.updateAppointment(appointmentId, doctorId, date);
    }

    @Override
    public void deleteAppointment(int appointmentId) throws SQLException {
        service.deleteAppointment(appointmentId);
    }

    @Override
    public AppointmentDTO finishAppointment(int appointmentId, String notes) throws Exception {
        return service.finishAppointment(appointmentId, notes);
    }

    @Override
    public ArrayList<AppointmentDTO> getAppointmentsByPatientId(int patientId) throws Exception {
        return service.getAppointmentsByPatientId(patientId);
    }

    @Override
    public ArrayList<AppointmentDTO> getAppointmentsByDoctorId(int doctorId) throws Exception {
        return service.getAppointmentsByDoctorId(doctorId);
    }
}
