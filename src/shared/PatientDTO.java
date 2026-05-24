package shared;

import java.time.LocalDate;

/**
 * Holds the patient data that is sent between the server and the client.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class PatientDTO {
    /** The patient ID. */
    private int patientID;
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
    /** The CPR. */
    private String CPR;
    /** The gender. */
    private String gender;
    /** The password. */
    private String password;
    /** The medical notes. */
    private String medicalNotes;
    /** The last visit. */
    private LocalDate lastVisit;
    /** The day of birth. */
    private LocalDate dayOfBirth;
    /** The age. */
    private int age;

    /**
     * Creates a new {@code PatientDTO} instance.
     */
    public PatientDTO() {}

    /**
     * Creates a new {@code PatientDTO} initialised with the given patient ID, first name, last name, email, phone number, user name, CPR, gender, password, medical notes, last visit, day of birth, age.
     *
     * @param patientID the identifier of the patient
     * @param firstName the first name
     * @param lastName the last name
     * @param email the email
     * @param phoneNumber the phone number
     * @param userName the user name
     * @param CPR the CPR
     * @param gender the gender
     * @param password the password
     * @param medicalNotes the medical notes
     * @param lastVisit the last visit
     * @param dayOfBirth the day of birth
     * @param age the age
     */
    public PatientDTO(int patientID, String firstName, String lastName, String email,
                      String phoneNumber, String userName, String CPR, String gender,
                      String password, String medicalNotes, LocalDate lastVisit,
                      LocalDate dayOfBirth, int age) {
        this.patientID = patientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.CPR = CPR;
        this.gender = gender;
        this.password = password;
        this.medicalNotes = medicalNotes;
        this.lastVisit = lastVisit;
        this.dayOfBirth = dayOfBirth;
        this.age = age;
    }



    /**
     * Returns the patient ID.
     *
     * @return the patient ID
     */
    public int getPatientID() { return patientID; }
    /**
     * Returns the day of birth.
     *
     * @return the day of birth
     */
    public LocalDate getDayOfBirth() { return dayOfBirth; }
    /**
     * Returns the last visit.
     *
     * @return the last visit
     */
    public LocalDate getLastVisit() { return lastVisit; }
    /**
     * Returns the CPR.
     *
     * @return the CPR
     */
    public String getCPR() { return CPR; }
    /**
     * Returns the email.
     *
     * @return the email
     */
    public String getEmail() { return email; }
    /**
     * Returns the first name.
     *
     * @return the first name
     */
    public String getFirstName() { return firstName; }
    /**
     * Returns the gender.
     *
     * @return the gender
     */
    public String getGender() { return gender; }
    /**
     * Returns the last name.
     *
     * @return the last name
     */
    public String getLastName() { return lastName; }
    /**
     * Returns the medical notes.
     *
     * @return the medical notes
     */
    public String getMedicalNotes() { return medicalNotes; }
    /**
     * Returns the password.
     *
     * @return the password
     */
    public String getPassword() { return password; }
    /**
     * Returns the phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() { return phoneNumber; }
    /**
     * Returns the user name.
     *
     * @return the user name
     */
    public String getUserName() { return userName; }
    /**
     * Returns the age.
     *
     * @return the age
     */
    public int getAge() { return age; }

    /**
     * Sets the CPR.
     *
     * @param CPR the CPR
     */
    public void setCPR(String CPR) { this.CPR = CPR; }
    /**
     * Sets the day of birth.
     *
     * @param dayOfBirth the day of birth
     */
    public void setDayOfBirth(LocalDate dayOfBirth) { this.dayOfBirth = dayOfBirth; }
    /**
     * Sets the email.
     *
     * @param email the email
     */
    public void setEmail(String email) { this.email = email; }
    /**
     * Sets the first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) { this.firstName = firstName; }
    /**
     * Sets the gender.
     *
     * @param gender the gender
     */
    public void setGender(String gender) { this.gender = gender; }
    /**
     * Sets the last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) { this.lastName = lastName; }
    /**
     * Sets the last visit.
     *
     * @param lastVisit the last visit
     */
    public void setLastVisit(LocalDate lastVisit) { this.lastVisit = lastVisit; }
    /**
     * Sets the medical notes.
     *
     * @param medicalNotes the medical notes
     */
    public void setMedicalNotes(String medicalNotes) { this.medicalNotes = medicalNotes; }
    /**
     * Sets the password.
     *
     * @param password the password
     */
    public void setPassword(String password) { this.password = password; }
    /**
     * Sets the patient ID.
     *
     * @param patientID the identifier of the patient
     */
    public void setPatientID(int patientID) { this.patientID = patientID; }
    /**
     * Sets the phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    /**
     * Sets the user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) { this.userName = userName; }
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
        return firstName+" "+lastName;
    }
}
