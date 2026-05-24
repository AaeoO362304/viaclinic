package server.model.bookAppointment.state;

import server.model.bookAppointment.Appointment;

/**
 * Interface for the two appointment states (finished and not finished).
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public interface AppointmentState {
    /**
     * Marks the appointment as finished.
     *
     * @param appointment the appointment
     */
    void finish(Appointment appointment);
    /**
     * Reopens a finished appointment.
     *
     * @param appointment the appointment
     */
    void reopen(Appointment appointment);
    /**
     * Returns this state as true or false.
     *
     * @return {@code true} if the operation succeeded, otherwise {@code false}
     */
    boolean asBoolean();
    /**
     * Returns the name.
     *
     * @return the name
     */
    String getName();
}