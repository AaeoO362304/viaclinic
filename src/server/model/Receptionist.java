package server.model;

import java.time.LocalDate;

/**
 * A receptionist user.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class Receptionist extends User {
    /** The receptionist ID. */
    private int receptionistID;

    /**
     * Creates a new {@code Receptionist} initialised with the given first name, last name, password, email, gender, phone num, day of birth, user name.
     *
     * @param firstName the first name
     * @param lastName the last name
     * @param password the password
     * @param email the email
     * @param gender the gender
     * @param phoneNum the phone num
     * @param dayOfBirth the day of birth
     * @param userName the username
     */
    public Receptionist(String firstName, String lastName, String password, String email,
                        String gender, String phoneNum, LocalDate dayOfBirth, String userName) {
        super(firstName, lastName, password, email, gender, phoneNum, dayOfBirth, userName);
    }

    /**
     * Creates a new {@code Receptionist} initialised with the given receptionist ID, first name, last name, password, email, gender, phone num, day of birth, user name.
     *
     * @param receptionistID the identifier of the receptionist
     * @param firstName the first name
     * @param lastName the last name
     * @param password the password
     * @param email the email
     * @param gender the gender
     * @param phoneNum the phone num
     * @param dayOfBirth the day of birth
     * @param userName the username
     */
    public Receptionist(int receptionistID, String firstName, String lastName, String password,
                        String email, String gender, String phoneNum, LocalDate dayOfBirth,
                        String userName) {
        super(firstName, lastName, password, email, gender, phoneNum, dayOfBirth, userName);
        this.receptionistID = receptionistID;
    }

    /**
     * Returns the receptionist ID.
     *
     * @return the receptionist ID
     */
    public int getReceptionistID() { return receptionistID; }
    /**
     * Sets the receptionist ID.
     *
     * @param receptionistID the identifier of the receptionist
     */
    public void setReceptionistID(int receptionistID) { this.receptionistID = receptionistID; }
}
