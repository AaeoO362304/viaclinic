package client.viewModel.appointment;

import client.viewModel.login.DoctorViewModel;
import client.viewModel.login.PatientViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import server.model.bookAppointment.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class BookAppointmentViewModel {

    private DoctorViewModel doctorViewModel;
    private PatientViewModel patientViewModel;
    private UserHandler handler;

    private LocalDateTime appointmentDate;
    private LocalDate date;

    private StringProperty time;
    private StringProperty error;
    private StringProperty notes;

    private Doctor doctor;
    private Patient patient;

    private boolean status;

    public BookAppointmentViewModel(UserHandler handler,
                                    PatientViewModel patientViewModel,
                                    DoctorViewModel doctorViewModel) {
        this.handler = handler;
        this.patientViewModel = patientViewModel;
        this.doctorViewModel = doctorViewModel;

        this.time = new SimpleStringProperty("");
        this.notes = new SimpleStringProperty("");
        this.error = new SimpleStringProperty("");

        this.status = false;
    }

    public void createLocalDateTime(LocalDate date, String time) {
        LocalTime localTime = LocalTime.parse(time);
        appointmentDate = LocalDateTime.of(date, localTime);
    }

    public boolean create() {
        error.set("");

        try {
            if (date == null) {
                error.set("Please select a date.");
                return false;
            }

            if (date.isBefore(LocalDate.now())) {
                error.set("Select a date from today or the future.");
                return false;
            }

            if (doctor == null) {
                error.set("Please select a doctor.");
                return false;
            }

            if (time.get() == null || time.get().isBlank()) {
                error.set("Please select a time for your appointment.");
                return false;
            }

            if (patient == null) {
                patient = (Patient) patientViewModel
                        .getLoginViewModel()
                        .getCurrentSession()
                        .getUser();
            }

            if (patient == null) {
                error.set("No patient is logged in.");
                return false;
            }

            createLocalDateTime(date, time.get());

            AppointmentDAO appointmentDAO = AppointmentDAO.getInstance();

            appointmentDAO.create(
                    patient,
                    doctor,
                    appointmentDate,
                    status,
                    notes.get()
            );

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
        patient = null;
        status = false;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public DoctorViewModel getDoctorViewModel() {
        return doctorViewModel;
    }

    public PatientViewModel getPatientViewModel() {
        return patientViewModel;
    }

    public StringProperty getTimeProperty() {
        return time;
    }

    public StringProperty getNotesProperty() {
        return notes;
    }

    public StringProperty getErrorProperty() {
        return error;
    }
}