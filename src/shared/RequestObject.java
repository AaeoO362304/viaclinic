package shared;

import java.util.List;

public class RequestObject {
    private boolean success;
    private String status;
    private String message;
    private int patientId;
    private AppointmentDTO appointment;
    private List<AppointmentDTO> appointments;
    private List<DoctorDTO> doctors;
    private List<PatientDTO> patients;

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public int getPatientId() { return patientId; }
    public void setPatientId(int patientId) { this.patientId = patientId; }
    public AppointmentDTO getAppointment() { return appointment; }
    public void setAppointment(AppointmentDTO appointment) { this.appointment = appointment; }
    public List<AppointmentDTO> getAppointments() { return appointments; }
    public void setAppointments(List<AppointmentDTO> appointments) { this.appointments = appointments; }
    public List<DoctorDTO> getDoctors() { return doctors; }
    public void setDoctors(List<DoctorDTO> doctors) { this.doctors = doctors; }
    public List<PatientDTO> getPatients() { return patients; }
    public void setPatients(List<PatientDTO> patients) { this.patients = patients; }
}
