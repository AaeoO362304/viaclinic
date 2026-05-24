package server.model.bookAppointment.state;

import server.model.bookAppointment.Appointment;

/**
 * The state of an appointment that is finished.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class FinishedState implements AppointmentState {

    /**
     * Marks the appointment as finished.
     *
     * @param appointment the appointment
     */
    @Override
    public void finish(Appointment appointment) {
        throw new IllegalStateException("Appointment is already finished.");
    }

    /**
     * Reopens a finished appointment.
     *
     * @param appointment the appointment
     */
    @Override
    public void reopen(Appointment appointment) {
        appointment.setState(new NotFinishedState());
    }

    /**
     * Returns this state as true or false.
     *
     * @return {@code true} if the operation succeeded, otherwise {@code false}
     */
    @Override
    public boolean asBoolean() {
        return true;
    }

    /**
     * Returns the name.
     *
     * @return the name
     */
    @Override
    public String getName() {
        return "Finished";
    }
}