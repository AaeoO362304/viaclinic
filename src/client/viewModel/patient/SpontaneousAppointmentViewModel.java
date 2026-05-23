package client.viewModel.patient;

import client.model.ClinicClient;
import client.viewModel.login.DoctorViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.AppointmentDTO;
import shared.DoctorDTO;

import java.util.ArrayList;

public class SpontaneousAppointmentViewModel {
    private final ClinicClient client;
    private final StringProperty empty;

    public SpontaneousAppointmentViewModel(ClinicClient client) {
        this.client=client;
        empty = new SimpleStringProperty();
    }

    public void clear() {
        empty.set("");
    }

    public ArrayList<DoctorDTO> getAllDoctors() throws Exception {
        return client.getAllDoctors();
    }

    public ArrayList<AppointmentDTO> getAllAppointments() throws Exception {
        return client.getAllAppointments();
    }

    public StringProperty getEmptyProperty() {return empty; }

}
