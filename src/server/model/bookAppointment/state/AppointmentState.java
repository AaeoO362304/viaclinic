package server.model.bookAppointment.state;

import server.model.bookAppointment.Appointment;

public interface AppointmentState {
    void finish(Appointment appointment);
    void reopen(Appointment appointment);
    boolean asBoolean();
    String getName();
}