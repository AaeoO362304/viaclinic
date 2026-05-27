package server.model;

import java.time.LocalDate;

/**
 * A doctor user.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class Doctor extends User {
    /** The specialization. */
    private String specialization;
    /** The doctor ID. */
    private int doctorID;

    /**
     * Creates a new {@code Doctor} initialised with the given first name, last name, password, email, gender, phone num, day of birth, user name, specialization.
     *
     * @param firstName the first name
     * @param lastName the last name
     * @param password the password
     * @param email the email
     * @param gender the gender
     * @param phoneNum the phone num
     * @param dayOfBirth the day of birth
     * @param userName the username
     * @param specialization the specialization
     */
    public Doctor(String firstName, String lastName, String password, String email,
                  String gender, String phoneNum, LocalDate dayOfBirth,
                  String userName, String specialization) {
        super(firstName, lastName, password, email, gender, phoneNum, dayOfBirth, userName);
        this.specialization = specialization;
    }

    /**
     * Creates a new {@code Doctor} initialised with the given doctor ID, first name, last name, password, email, gender, phone num, day of birth, user name, specialization.
     *
     * @param doctorID the identifier of the doctor
     * @param firstName the first name
     * @param lastName the last name
     * @param password the password
     * @param email the email
     * @param gender the gender
     * @param phoneNum the phone num
     * @param dayOfBirth the day of birth
     * @param userName the username
     * @param specialization the specialization
     */
    public Doctor(int doctorID, String firstName, String lastName, String password, String email,
                  String gender, String phoneNum, LocalDate dayOfBirth,
                  String userName, String specialization) {
        super(firstName, lastName, password, email, gender, phoneNum, dayOfBirth, userName);
        this.doctorID = doctorID;
        this.specialization = specialization;
    }

    /**
     * Returns the doctor ID.
     *
     * @return the doctor ID
     */
    public int getDoctorID() { return doctorID; }
    /**
     * Sets the doctor ID.
     *
     * @param doctorID the id of the doctor
     */
    public void setDoctorID(int doctorID) { this.doctorID = doctorID; }
    /**
     * Returns the specialization.
     *
     * @return the specialization
     */
    public String getSpecialization() { return specialization; }
    /**
     * Sets the specialization.
     *
     * @param specialization the specialization
     */
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    /**
     * Returns this object as a String.
     *
     * @return the resulting string
     */
    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + (specialization == null ? "" : " - " + specialization);
    }
}
