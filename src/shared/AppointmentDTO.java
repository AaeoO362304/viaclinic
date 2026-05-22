package shared;

import java.time.LocalDateTime;

public class AppointmentDTO {
    private LocalDateTime date;
    private DoctorDTO doctor;
    private int patientID;
    private int id;
    private PatientDTO patient;
    private boolean status;
    private String notes;

    public AppointmentDTO() {}

    public AppointmentDTO(int id, LocalDateTime date, DoctorDTO doctor, PatientDTO patient, boolean status, String notes) {
        this.id = id;
        this.date = date;
        this.doctor = doctor;
        this.patient = patient;
        this.patientID = patient == null ? 0 : patient.getPatientID();
        this.status = status;
        this.notes = notes;
    }

    public AppointmentDTO(int id, LocalDateTime date, DoctorDTO doctor, int patientID, boolean status, String notes) {
        this.id = id;
        this.date = date;
        this.doctor = doctor;
        this.patientID = patientID;
        this.status = status;
        this.notes = notes;
    }

    public DoctorDTO getDoctor() { return doctor; }
    public String getNotes() { return notes; }
    public int getPatientID() { return patientID; }
    public int getId() { return id; }
    public PatientDTO getPatient() { return patient; }
    public LocalDateTime getDate() { return date; }
    public boolean isStatus() { return status; }
    public boolean getStatus() { return status; }

    public void setPatientID(int patientID) { this.patientID = patientID; }
    public void setDate(LocalDateTime date) { this.date = date; }
    public void setNotes(String notes) { this.notes = notes; }
    public void setStatus(boolean status) { this.status = status; }
    public void setDoctor(DoctorDTO doctor) { this.doctor = doctor; }
    public void setPatient(PatientDTO patient) { this.patient = patient; }
    public void setId(int id) { this.id = id; }
}
