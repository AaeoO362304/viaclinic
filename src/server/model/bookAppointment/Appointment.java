package server.model.bookAppointment;

import server.model.bookAppointment.state.AppointmentState;
import server.model.bookAppointment.state.FinishedState;
import server.model.bookAppointment.state.NotFinishedState;

import javax.print.Doc;
import java.time.LocalDateTime;

public class Appointment {
    private int appointmentID;
    private Patient patient;
    private Doctor doctor;
    private LocalDateTime date;
    private AppointmentState state;
    private String notes;
    private boolean status;

    public Appointment(Patient patient, Doctor doctor, LocalDateTime date) {
        this.date=date;
        this.doctor=doctor;
        this.patient=patient;
        status=false;
        notes="";
    }

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
        if (status) {
            this.state = new FinishedState();
        } else {
            this.state = new NotFinishedState();
        }
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

    public void finish() {
        state.finish(this);
    }

    public void reopen() {
        state.reopen(this);
    }

    public boolean isStatus() {
        return state.asBoolean();
    }

    public String getStatusText() {
        return state.getName();
    }

    public void setState(AppointmentState state) {
        this.state = state;
    }

    public AppointmentState getState() {
        return state;
    }

}
