package shared;

import java.time.LocalDate;

/**
 * Holds the receptionist data that is sent between the server and the client.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class ReceptionistDTO {
    /** The receptionist ID. */
    private int receptionistID;
    /** The first name. */
    private String firstName;
    /** The last name. */
    private String lastName;
    /** The email. */
    private String email;
    /** The phone number. */
    private String phoneNumber;
    /** The user name. */
    private String userName;
    /** The password. */
    private String password;
    /** The gender. */
    private String gender;
    /** The day of birth. */
    private LocalDate dayOfBirth;
    /** The age. */
    private int age;

    /**
     * Creates a new {@code ReceptionistDTO} instance.
     */
    public ReceptionistDTO() {}

    /**
     * Creates a new {@code ReceptionistDTO} initialised with the given receptionist ID, first name, last name, email, phone number, user name, password, gender, day of birth, age.
     *
     * @param receptionistID the identifier of the receptionist
     * @param firstName the first name
     * @param lastName the last name
     * @param email the email
     * @param phoneNumber the phone number
     * @param userName the user name
     * @param password the password
     * @param gender the gender
     * @param dayOfBirth the day of birth
     * @param age the age
     */
    public ReceptionistDTO(int receptionistID, String firstName, String lastName,
                           String email, String phoneNumber, String userName,
                           String password, String gender, LocalDate dayOfBirth,
                           int age) {
        this.receptionistID = receptionistID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.password = password;
        this.gender = gender;
        this.dayOfBirth = dayOfBirth;
        this.age = age;
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
    /**
     * Returns the first name.
     *
     * @return the first name
     */
    public String getFirstName() { return firstName; }
    /**
     * Sets the first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) { this.firstName = firstName; }
    /**
     * Returns the last name.
     *
     * @return the last name
     */
    public String getLastName() { return lastName; }
    /**
     * Sets the last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) { this.lastName = lastName; }
    /**
     * Returns the email.
     *
     * @return the email
     */
    public String getEmail() { return email; }
    /**
     * Sets the email.
     *
     * @param email the email
     */
    public void setEmail(String email) { this.email = email; }
    /**
     * Returns the phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() { return phoneNumber; }
    /**
     * Sets the phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    /**
     * Returns the user name.
     *
     * @return the user name
     */
    public String getUserName() { return userName; }
    /**
     * Sets the user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) { this.userName = userName; }
    /**
     * Returns the password.
     *
     * @return the password
     */
    public String getPassword() { return password; }
    /**
     * Sets the password.
     *
     * @param password the password
     */
    public void setPassword(String password) { this.password = password; }
    /**
     * Returns the gender.
     *
     * @return the gender
     */
    public String getGender() { return gender; }
    /**
     * Sets the gender.
     *
     * @param gender the gender
     */
    public void setGender(String gender) { this.gender = gender; }
    /**
     * Returns the day of birth.
     *
     * @return the day of birth
     */
    public LocalDate getDayOfBirth() { return dayOfBirth; }
    /**
     * Sets the day of birth.
     *
     * @param dayOfBirth the day of birth
     */
    public void setDayOfBirth(LocalDate dayOfBirth) { this.dayOfBirth = dayOfBirth; }
    /**
     * Returns the age.
     *
     * @return the age
     */
    public int getAge() { return age; }
    /**
     * Sets the age.
     *
     * @param age the age
     */
    public void setAge(int age) { this.age = age; }

    /**
     * Returns this object as a String.
     *
     * @return the resulting string
     */
    @Override
    public String toString() {
        return ((firstName == null ? "" : firstName) + " " + (lastName == null ? "" : lastName)).trim();
    }
}
