package shared;

import java.time.LocalDateTime;

/**
 * Holds the appointment data that is sent between the server and the client.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class AppointmentDTO {
    /** The date. */
    private LocalDateTime date;
    /** The doctor. */
    private DoctorDTO doctor;
    /** The patient ID. */
    private int patientID;
    /** The id. */
    private int id;
    /** The patient. */
    private PatientDTO patient;
    /** The status. */
    private boolean status;
    /** The notes. */
    private String notes;

    /**
     * Creates a new {@code AppointmentDTO} instance.
     */
    public AppointmentDTO() {}

    /**
     * Creates a new {@code AppointmentDTO} initialised with the given id, date, doctor, patient, status, notes.
     *
     * @param id the id of the entity
     * @param date the date
     * @param doctor the doctor
     * @param patient the patient
     * @param status the status
     * @param notes the notes
     */
    public AppointmentDTO(int id, LocalDateTime date, DoctorDTO doctor, PatientDTO patient, boolean status, String notes) {
        this.id = id;
        this.date = date;
        this.doctor = doctor;
        this.patient = patient;
        this.patientID = patient == null ? 0 : patient.getPatientID();
        this.status = status;
        this.notes = notes;
    }

    /**
     * Creates a new {@code AppointmentDTO} initialised with the given id, date, doctor, patient ID, status, notes.
     *
     * @param id the id of the entity
     * @param date the date
     * @param doctor the doctor
     * @param patientID the id of the patient
     * @param status the status
     * @param notes the notes
     */
    public AppointmentDTO(int id, LocalDateTime date, DoctorDTO doctor, int patientID, boolean status, String notes) {
        this.id = id;
        this.date = date;
        this.doctor = doctor;
        this.patientID = patientID;
        this.status = status;
        this.notes = notes;
    }

    /**
     * Returns the doctor.
     *
     * @return the doctor
     */
    public DoctorDTO getDoctor() { return doctor; }
    /**
     * Returns the notes.
     *
     * @return the notes
     */
    public String getNotes() { return notes; }
    /**
     * Returns the patient ID.
     *
     * @return the patient ID
     */
    public int getPatientID() { return patientID; }
    /**
     * Returns the id.
     *
     * @return the id
     */
    public int getId() { return id; }
    /**
     * Returns the patient.
     *
     * @return the patient
     */
    public PatientDTO getPatient() { return patient; }
    /**
     * Returns the date.
     *
     * @return the date
     */
    public LocalDateTime getDate() { return date; }
    /**
     * Indicates whether status.
     *
     * @return {@code true} if status, otherwise {@code false}
     */
    public boolean isStatus() { return status; }
    /**
     * Returns the status.
     *
     * @return {@code true} if the operation succeeded, otherwise {@code false}
     */
    public boolean getStatus() { return status; }

    /**
     * Sets the patient ID.
     *
     * @param patientID the identifier of the patient
     */
    public void setPatientID(int patientID) { this.patientID = patientID; }
    /**
     * Sets the date.
     *
     * @param date the date
     */
    public void setDate(LocalDateTime date) { this.date = date; }
    /**
     * Sets the notes.
     *
     * @param notes the notes
     */
    public void setNotes(String notes) { this.notes = notes; }
    /**
     * Sets the status.
     *
     * @param status the status
     */
    public void setStatus(boolean status) { this.status = status; }
    /**
     * Sets the doctor.
     *
     * @param doctor the doctor
     */
    public void setDoctor(DoctorDTO doctor) { this.doctor = doctor; }
    /**
     * Sets the patient.
     *
     * @param patient the patient
     */
    public void setPatient(PatientDTO patient) { this.patient = patient; }
    /**
     * Sets the id.
     *
     * @param id the id of the entity
     */
    public void setId(int id) { this.id = id; }
}
