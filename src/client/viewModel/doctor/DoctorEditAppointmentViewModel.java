package client.viewModel.doctor;

import client.model.ClinicClient;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.AppointmentDTO;

/**
 * View model for the Doctor Edit Appointment screen.
 * Stores the data shown on screen and has the methods the controller calls.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class DoctorEditAppointmentViewModel {

    /** The client. */
    private final ClinicClient client;
    /** The notes. */
    private final StringProperty notes;
    /** The status. */
    private final BooleanProperty status;
    /** The appointment. */
    private AppointmentDTO appointment;

    /**
     * Creates a new {@code DoctorEditAppointmentViewModel} initialised with the given client, appointment.
     *
     * @param client the client
     * @param appointment the appointment
     */
    public DoctorEditAppointmentViewModel(ClinicClient client, AppointmentDTO appointment) {
        this.client = client;
        this.appointment=appointment;
        this.notes = new SimpleStringProperty("");
        this.status = new SimpleBooleanProperty(false);
    }

    /**
     * Sets the appointment.
     *
     * @param appointment the appointment
     */
    public void setAppointment(AppointmentDTO appointment) {
        this.appointment = appointment;

        if (appointment != null) {
            notes.set(appointment.getNotes() == null ? "" : appointment.getNotes());
            status.set(appointment.isStatus());
        }
    }

    /**
     * Finishes the appointment.
     *
     * @return {@code true} if the operation succeeded, otherwise {@code false}
     */
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

    /**
     * Returns the notes property used for data binding.
     *
     * @return the notes property
     */
    public StringProperty getNotesProperty() {
        return notes;
    }

    /**
     * Returns the status property used for data binding.
     *
     * @return the status property
     */
    public BooleanProperty getStatusProperty() {
        return status;
    }

    /**
     * Clears the values in this view model.
     */
    public void clear() {
        notes.set("");
        status.set(false);
    }

    /**
     * Sets the notes.
     *
     * @param notes the notes
     */
    public void setNotes(String notes) {
        this.notes.set(notes);
    }
}