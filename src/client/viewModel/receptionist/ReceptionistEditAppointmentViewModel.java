package client.viewModel.receptionist;

import client.model.ClinicClient;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.AppointmentDTO;
import shared.DoctorDTO;
import shared.PatientDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class ReceptionistEditAppointmentViewModel {
    private final ClinicClient client;

    private LocalDateTime appointmentDate;
    private LocalDate date;
    private final StringProperty time;
    private final StringProperty error;
    private final StringProperty notes;
    private AppointmentDTO appointment;
    private DoctorDTO doctor;
    private PatientDTO patient;
    private boolean status;

    public ReceptionistEditAppointmentViewModel(ClinicClient client, AppointmentDTO appointment
    ) {
        this.client = client;
        this.appointment=appointment;
        this.doctor=appointment.getDoctor();
        this.patient=appointment.getPatient();
        this.time = new SimpleStringProperty("");
        this.notes = new SimpleStringProperty("");
        this.error = new SimpleStringProperty("");
        this.status = false;
    }

    public ArrayList<DoctorDTO> getAllDoctors() throws Exception {
        return client.getAllDoctors();
    }

    private void createLocalDateTime(LocalDate date, String time) {
        LocalTime localTime = LocalTime.parse(time);
        appointmentDate = LocalDateTime.of(date, localTime);
    }

    public boolean update() {
        error.set("");

        try {
            if (date == null) { error.set("Please select a date."); return false; }
            if (date.isBefore(LocalDate.now())) { error.set("Select a date from today or the future."); return false; }
            if (doctor == null) { error.set("Please select a doctor."); return false; }
            if (time.get() == null || time.get().isBlank()) { error.set("Please select a time for your appointment."); return false; }

            createLocalDateTime(date, time.get());
            client.updateAppointment(appointment.getId(), doctor.getDoctorID(),  appointmentDate);
            return true;
        }
        catch (Exception e) {
            error.set(e.getMessage() == null ? "Something went wrong." : e.getMessage());
            return false;
        }
    }

    public void clear() {
        time.set("");
        notes.set("");
        error.set("");
        date = null;
        appointmentDate = null;
        doctor = null;
        patient=null;
        status = false;
    }

    public void setDate(LocalDate date) { this.date = date; }
    public void setDoctor(DoctorDTO doctor) { this.doctor = doctor; }
    public void setAppointment(AppointmentDTO appointment) {this.appointment=appointment;}
    public StringProperty getTimeProperty() { return time; }
    public StringProperty getNotesProperty() { return notes; }
    public StringProperty getErrorProperty() { return error; }
}
