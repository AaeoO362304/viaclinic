package server.model;

import server.model.state.AppointmentState;
import server.model.state.FinishedState;
import server.model.state.NotFinishedState;

import javax.print.Doc;
import java.time.LocalDateTime;

/**
 * An appointment between a patient and a doctor. It remembers whether it is finished or not.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class Appointment {
    /** The appointment ID. */
    private int appointmentID;
    /** The patient. */
    private Patient patient;
    /** The doctor. */
    private Doctor doctor;
    /** The date. */
    private LocalDateTime date;
    /** The state. */
    private AppointmentState state;
    /** The notes. */
    private String notes;
    /** The status. */
    private boolean status;

    /**
     * Creates a new {@code Appointment} initialised with the given patient, doctor, date.
     *
     * @param patient the patient
     * @param doctor the doctor
     * @param date the date
     */
    public Appointment(Patient patient, Doctor doctor, LocalDateTime date) {
        this.date=date;
        this.doctor=doctor;
        this.patient=patient;
        status=false;
        notes="";
    }

    /**
     * Creates a new {@code Appointment} initialised with the given patient, doctor, date, status, notes.
     *
     * @param patient the patient
     * @param doctor the doctor
     * @param date the date
     * @param status the status
     * @param notes the notes
     */
    public Appointment(Patient patient, Doctor doctor, LocalDateTime date, boolean status, String notes) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.notes = notes;

        if (status) {
            this.state = new FinishedState();
        } else {
            this.state = new NotFinishedState();
        }
    }

    /**
     * Creates a new {@code Appointment} initialised with the given appointment ID, patient, doctor, date.
     *
     * @param appointmentID the id of the appointment
     * @param patient the patient
     * @param doctor the doctor
     * @param date the date
     */
    public Appointment(int appointmentID, Patient patient, Doctor doctor, LocalDateTime date) {
        this.appointmentID=appointmentID;
        this.date=date;
        this.doctor=doctor;
        this.patient=patient;
        status=false;
        notes="";
    }

    /**
     * Creates a new {@code Appointment} initialised with the given appointment ID, patient, doctor, date, status, notes.
     *
     * @param appointmentID the id of the appointment
     * @param patient the patient
     * @param doctor the doctor
     * @param date the date
     * @param status the status
     * @param notes the notes
     */
    public Appointment(int appointmentID, Patient patient, Doctor doctor, LocalDateTime date, boolean status, String notes) {
        this.appointmentID=appointmentID;
        this.date=date;
        this.doctor=doctor;
        this.patient=patient;
        if (status) {
            this.state = new FinishedState();
        } else {
            this.state = new NotFinishedState();
        }
        this.notes=notes;
    }

    /**
     * Returns the patient.
     *
     * @return the patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Sets the patient.
     *
     * @param patient the patient
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * Returns the doctor.
     *
     * @return the doctor
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * Sets the doctor.
     *
     * @param doctor the doctor
     */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    /**
     * Sets the status.
     *
     * @param status the status
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Returns the notes.
     *
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets the notes.
     *
     * @param notes the notes
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Returns the date.
     *
     * @return the date
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Sets the date.
     *
     * @param date the date
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * Returns the appointment ID.
     *
     * @return the appointment ID
     */
    public int getAppointmentID() {
        return appointmentID;
    }

    /**
     * Sets the appointment ID.
     *
     * @param appointmentID the id of the appointment
     */
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**
     * Marks the appointment as finished.
     */
    public void finish() {
        state.finish(this);
    }

    /**
     * Reopens a finished appointment.
     */
    public void reopen() {
        state.reopen(this);
    }

    /**
     * Indicates whether status.
     *
     * @return {@code true} if status, otherwise {@code false}
     */
    public boolean isStatus() {
        return state.asBoolean();
    }

    /**
     * Returns the status text.
     *
     * @return the status text
     */
    public String getStatusText() {
        return state.getName();
    }

    /**
     * Sets the state.
     *
     * @param state the state
     */
    public void setState(AppointmentState state) {
        this.state = state;
    }

    /**
     * Returns the state.
     *
     * @return the state
     */
    public AppointmentState getState() {
        return state;
    }

}
