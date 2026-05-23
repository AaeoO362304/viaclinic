package server.model.bookAppointment.state;

import server.model.bookAppointment.Appointment;

public class NotFinishedState implements AppointmentState {

    @Override
    public void finish(Appointment appointment) {
        appointment.setState(new FinishedState());
    }

    @Override
    public void reopen(Appointment appointment) {
        throw new IllegalStateException("Appointment is already not finished.");
    }

    @Override
    public boolean asBoolean() {
        return false;
    }

    @Override
    public String getName() {
        return "Not Finished";
    }
}