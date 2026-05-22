package server.model.bookAppointment;

import java.time.LocalDate;

/**
 * Legacy in-memory model interface from the early MVVM version.
 * New client code should prefer client.model.ClinicClient and DTOs.
 */
public interface UserHandler {
    void addPatient(String firstName, String lastName, String gender, String phoneNum,
                    String email, String password, LocalDate dateOfBirth, String CPR,
                    String userName) throws IllegalArgumentException, IllegalStateException;

    Patient getPatient(String firstName);
    Doctor getDoctor(String userName);
    Receptionist getReceptionist(String userName);
    int getPatientCount();
}
