package client.viewModel.receptionist;

import client.model.ClinicClient;
import client.viewModel.login.PatientViewModel;
import shared.PatientDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReceptionistRegisteredPatientViewModel {
    private final ClinicClient client;
    private final PatientViewModel patientViewModel;

    public ReceptionistRegisteredPatientViewModel(ClinicClient client, PatientViewModel patientViewModel) {
        this.client = client;
        this.patientViewModel = patientViewModel;
    }

    public ArrayList<PatientDTO> getAllPatients() throws Exception {
        return client.getAllPatients();
    }

    public void deletePatient(int patientId) throws Exception {
        client.deletePatient(patientId);
    }

}
