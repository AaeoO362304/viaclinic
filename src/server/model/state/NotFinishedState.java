package server.model.state;

import server.model.Appointment;

/**
 * The state of an appointment that is not finished yet.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class NotFinishedState implements AppointmentState {

    /**
     * Marks the appointment as finished.
     *
     * @param appointment the appointment
     */
    @Override
    public void finish(Appointment appointment) {
        appointment.setState(new FinishedState());
    }

    /**
     * Reopens a finished appointment.
     *
     * @param appointment the appointment
     */
    @Override
    public void reopen(Appointment appointment) {
        throw new IllegalStateException("Appointment is already not finished.");
    }

    /**
     * Returns this state as true or false.
     *
     * @return {@code true} if the operation succeeded, otherwise {@code false}
     */
    @Override
    public boolean asBoolean() {
        return false;
    }

    /**
     * Returns the name.
     *
     * @return the name
     */
    @Override
    public String getName() {
        return "Not Finished";
    }
}