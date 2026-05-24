package server.model.bookAppointment;

import java.time.LocalDate;

/**
 * Legacy in-memory model interface from the early MVVM version.
 * New client code should prefer client.model.ClinicClient and DTOs.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public interface UserHandler {
    /**
     * Adds the patient.
     *
     * @param firstName the first name
     * @param lastName the last name
     * @param gender the gender
     * @param phoneNum the phone num
     * @param email the email
     * @param password the password
     * @param dateOfBirth the date of birth
     * @param CPR the CPR
     * @param userName the user name
     * @throws IllegalArgumentException if the operation cannot be completed
     * @throws IllegalStateException if the operation cannot be completed
     */
    void addPatient(String firstName, String lastName, String gender, String phoneNum,
                    String email, String password, LocalDate dateOfBirth, String CPR,
                    String userName) throws IllegalArgumentException, IllegalStateException;

    /**
     * Returns the patient.
     *
     * @param firstName the first name
     * @return the patient
     */
    Patient getPatient(String firstName);
    /**
     * Returns the doctor.
     *
     * @param userName the user name
     * @return the doctor
     */
    Doctor getDoctor(String userName);
    /**
     * Returns the receptionist.
     *
     * @param userName the user name
     * @return the receptionist
     */
    Receptionist getReceptionist(String userName);
    /**
     * Returns the patient count.
     *
     * @return the patient count
     */
    int getPatientCount();
}
