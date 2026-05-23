package server.model.bookAppointment.state;

import server.model.bookAppointment.Appointment;

public class FinishedState implements AppointmentState {

    @Override
    public void finish(Appointment appointment) {
        throw new IllegalStateException("Appointment is already finished.");
    }

    @Override
    public void reopen(Appointment appointment) {
        appointment.setState(new NotFinishedState());
    }

    @Override
    public boolean asBoolean() {
        return true;
    }

    @Override
    public String getName() {
        return "Finished";
    }
}