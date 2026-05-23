package client.viewModel.doctor;

import client.model.ClinicClient;
import client.viewModel.login.PatientViewModel;
import shared.PatientDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class RegisteredPatientViewModel {
    private final ClinicClient client;
    private final PatientViewModel patientViewModel;

    public RegisteredPatientViewModel(ClinicClient client, PatientViewModel patientViewModel) {
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
