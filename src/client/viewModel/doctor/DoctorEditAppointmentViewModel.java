package client.viewModel.doctor;

import client.model.ClinicClient;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import server.model.bookAppointment.Appointment;
import server.model.bookAppointment.AppointmentDAO;
import shared.AppointmentDTO;

public class DoctorEditAppointmentViewModel {

    private final ClinicClient client;
    private final StringProperty notes;
    private final BooleanProperty status;
    private AppointmentDTO appointment;

    public DoctorEditAppointmentViewModel(ClinicClient client, AppointmentDTO appointment) {
        this.client = client;
        this.appointment=appointment;
        this.notes = new SimpleStringProperty("");
        this.status = new SimpleBooleanProperty(false);
    }

    public void setAppointment(AppointmentDTO appointment) {
        this.appointment = appointment;

        if (appointment != null) {
            notes.set(appointment.getNotes() == null ? "" : appointment.getNotes());
            status.set(appointment.isStatus());
        }
    }

    public boolean finishAppointment()
    {
        try
        {
            if (appointment == null)
            {
                throw new IllegalStateException("No appointment selected.");
            }

            client.finishAppointment(
                    appointment.getId(),
                    notes.get()
            );

            status.set(true);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public StringProperty getNotesProperty() {
        return notes;
    }

    public BooleanProperty getStatusProperty() {
        return status;
    }

    public void clear() {
        notes.set("");
        status.set(false);
    }

    public void setNotes(String notes) {
        this.notes.set(notes);
    }
}