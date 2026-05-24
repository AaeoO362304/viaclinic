package client.viewModel.doctor;

import client.model.ClinicClient;
import client.viewModel.login.PatientViewModel;
import shared.PatientDTO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * View model for the Registered Patient screen.
 * Stores the data shown on screen and has the methods the controller calls.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class RegisteredPatientViewModel {
    /** The client. */
    private final ClinicClient client;
    /** The patient view model. */
    private final PatientViewModel patientViewModel;

    /**
     * Creates a new {@code RegisteredPatientViewModel} initialised with the given client, patient view model.
     *
     * @param client the client
     * @param patientViewModel the patient view model
     */
    public RegisteredPatientViewModel(ClinicClient client, PatientViewModel patientViewModel) {
        this.client = client;
        this.patientViewModel = patientViewModel;
    }

    /**
     * Returns all patients.
     *
     * @return the all patients
     * @throws Exception if the operation cannot be completed
     */
    public ArrayList<PatientDTO> getAllPatients() throws Exception {
        return client.getAllPatients();
    }

   /**
    * Deletes the patient.
    *
    * @param patientId the identifier of the patient
    * @throws Exception if the operation cannot be completed
    */
   public void deletePatient(int patientId) throws Exception {
        client.deletePatient(patientId);
   }

}
