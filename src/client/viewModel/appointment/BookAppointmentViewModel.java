package client.viewModel.appointment;

import client.model.ClinicClient;
import client.viewModel.doctor.DoctorViewModel;
import client.viewModel.patient.PatientViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.DoctorDTO;
import shared.SessionDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * View model for the Book Appointment screen.
 * Stores the data shown on screen and has the methods the controller calls.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class BookAppointmentViewModel {
    /** The doctor view model. */
    private final DoctorViewModel doctorViewModel;
    /** The patient view model. */
    private final PatientViewModel patientViewModel;
    /** The client. */
    private final ClinicClient client;

    /** The appointment date. */
    private LocalDateTime appointmentDate;
    /** The date. */
    private LocalDate date;
    /** The time. */
    private final StringProperty time;
    /** The error. */
    private final StringProperty error;
    /** The notes. */
    private final StringProperty notes;
    /** The doctor. */
    private DoctorDTO doctor;
    /** The status. */
    private boolean status;

    /**
     * Creates a new {@code BookAppointmentViewModel} initialised with the given client, patient view model, doctor view model.
     *
     * @param client the client
     * @param patientViewModel the patient view model
     * @param doctorViewModel the doctor view model
     */
    public BookAppointmentViewModel(ClinicClient client,
                                    PatientViewModel patientViewModel,
                                    DoctorViewModel doctorViewModel) {
        this.client = client;
        this.patientViewModel = patientViewModel;
        this.doctorViewModel = doctorViewModel;
        this.time = new SimpleStringProperty("");
        this.notes = new SimpleStringProperty("");
        this.error = new SimpleStringProperty("");
        this.status = false;
    }

    /**
     * Returns all doctors.
     *
     * @return the all doctors
     * @throws Exception if the operation cannot be completed
     */
    public ArrayList<DoctorDTO> getAllDoctors() throws Exception {
        return client.getAllDoctors();
    }

    /**
     * Creates a new local date time.
     *
     * @param date the date
     * @param time the time
     */
    private void createLocalDateTime(LocalDate date, String time) {
        LocalTime localTime = LocalTime.parse(time);
        appointmentDate = LocalDateTime.of(date, localTime);
    }

    /**
     * Creates a new record.
     *
     * @return {@code true} if the operation succeeded, otherwise {@code false}
     */
    public boolean create() {
        error.set("");

        try {
            if (date == null) { error.set("Please select a date."); return false; }
            if (date.isBefore(LocalDate.now())) { error.set("Select a date from today or the future."); return false; }
            if (doctor == null) { error.set("Please select a doctor."); return false; }
            if (time.get() == null || time.get().isBlank()) { error.set("Please select a time for your appointment."); return false; }

            SessionDTO session = patientViewModel.getLoginViewModel().getCurrentSession();
            if (session == null || session.getUserId() <= 0) {
                error.set("No patient is logged in.");
                return false;
            }

            createLocalDateTime(date, time.get());
            client.createAppointment(session.getUserId(), doctor.getDoctorID(), appointmentDate, status, notes.get());
            System.out.println("Appointment created successfully");
            return true;
        }
        catch (Exception e) {
            error.set(e.getMessage() == null ? "Something went wrong." : e.getMessage());
            return false;
        }
    }

    /**
     * Clears the values in this view model.
     */
    public void clear() {
        time.set("");
        notes.set("");
        error.set("");
        date = null;
        appointmentDate = null;
        doctor = null;
        status = false;
    }

    /**
     * Sets the date.
     *
     * @param date the date
     */
    public void setDate(LocalDate date) { this.date = date; }
    /**
     * Sets the doctor.
     *
     * @param doctor the doctor
     */
    public void setDoctor(DoctorDTO doctor) { this.doctor = doctor; }
    /**
     * Returns the doctor view model.
     *
     * @return the doctor view model
     */
    public DoctorViewModel getDoctorViewModel() { return doctorViewModel; }
    /**
     * Returns the patient view model.
     *
     * @return the patient view model
     */
    public PatientViewModel getPatientViewModel() { return patientViewModel; }
    /**
     * Returns the time property used for data binding.
     *
     * @return the time property
     */
    public StringProperty getTimeProperty() { return time; }
    /**
     * Returns the notes property used for data binding.
     *
     * @return the notes property
     */
    public StringProperty getNotesProperty() { return notes; }
    /**
     * Returns the error property used for data binding.
     *
     * @return the error property
     */
    public StringProperty getErrorProperty() { return error; }
}
