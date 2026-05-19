package server.model.bookAppointment;

import javax.print.Doc;
import java.time.LocalDateTime;

public class Appointment {
    private int appointmentID;
    private LocalDateTime date;
    private Doctor doctor;
    private Patient patient;
    private boolean status;
    private String notes;

    public Appointment(Patient patient, Doctor doctor, LocalDateTime date) {
        this.date=date;
        this.doctor=doctor;
        this.patient=patient;
        status=false;
        notes="";
    }

    public Appointment(Patient patient, Doctor doctor, LocalDateTime date, boolean status, String notes) {
        this.date=date;
        this.doctor=doctor;
        this.patient=patient;
        this.status=status;
        this.notes=notes;
    }

    public Appointment(int appointmentID, Patient patient, Doctor doctor, LocalDateTime date) {
        this.appointmentID=appointmentID;
        this.date=date;
        this.doctor=doctor;
        this.patient=patient;
        status=false;
        notes="";
    }

    public Appointment(int appointmentID, Patient patient, Doctor doctor, LocalDateTime date, boolean status, String notes) {
        this.appointmentID=appointmentID;
        this.date=date;
        this.doctor=doctor;
        this.patient=patient;
        this.status=status;
        this.notes=notes;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }


}
