package shared;

import java.util.List;

/**
 * Carries the data for a request from the client to the server.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class RequestObject {
    /** The success. */
    private boolean success;
    /** The status. */
    private String status;
    /** The message. */
    private String message;
    /** The patient id. */
    private int patientId;
    /** The appointment. */
    private AppointmentDTO appointment;
    /** The appointments. */
    private List<AppointmentDTO> appointments;
    /** The doctors. */
    private List<DoctorDTO> doctors;
    /** The patients. */
    private List<PatientDTO> patients;

    /**
     * Indicates whether success.
     *
     * @return {@code true} if success, otherwise {@code false}
     */
    public boolean isSuccess() { return success; }
    /**
     * Sets the success.
     *
     * @param success the success
     */
    public void setSuccess(boolean success) { this.success = success; }
    /**
     * Returns the status.
     *
     * @return the status
     */
    public String getStatus() { return status; }
    /**
     * Sets the status.
     *
     * @param status the status
     */
    public void setStatus(String status) { this.status = status; }
    /**
     * Returns the message.
     *
     * @return the message
     */
    public String getMessage() { return message; }
    /**
     * Sets the message.
     *
     * @param message the message
     */
    public void setMessage(String message) { this.message = message; }
    /**
     * Returns the patient id.
     *
     * @return the patient id
     */
    public int getPatientId() { return patientId; }
    /**
     * Sets the patient id.
     *
     * @param patientId the identifier of the patient
     */
    public void setPatientId(int patientId) { this.patientId = patientId; }
    /**
     * Returns the appointment.
     *
     * @return the appointment
     */
    public AppointmentDTO getAppointment() { return appointment; }
    /**
     * Sets the appointment.
     *
     * @param appointment the appointment
     */
    public void setAppointment(AppointmentDTO appointment) { this.appointment = appointment; }
    /**
     * Returns the appointments.
     *
     * @return the appointments
     */
    public List<AppointmentDTO> getAppointments() { return appointments; }
    /**
     * Sets the appointments.
     *
     * @param appointments the appointments
     */
    public void setAppointments(List<AppointmentDTO> appointments) { this.appointments = appointments; }
    /**
     * Returns the doctors.
     *
     * @return the doctors
     */
    public List<DoctorDTO> getDoctors() { return doctors; }
    /**
     * Sets the doctors.
     *
     * @param doctors the doctors
     */
    public void setDoctors(List<DoctorDTO> doctors) { this.doctors = doctors; }
    /**
     * Returns the patients.
     *
     * @return the patients
     */
    public List<PatientDTO> getPatients() { return patients; }
    /**
     * Sets the patients.
     *
     * @param patients the patients
     */
    public void setPatients(List<PatientDTO> patients) { this.patients = patients; }
}
