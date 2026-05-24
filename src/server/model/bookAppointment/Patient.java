package server.model.bookAppointment;

import java.time.LocalDate;

/**
 * A patient is uniquely identified by an {@code id} . Before the id is assigned the id is
 * {@code null}.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class Patient extends User {

  /** Unique identifier assigned by the repository; {@code null} until assigned. */
  private String medicalNotes, CPR;
  /** The last visit. */
  private LocalDate lastVisit;
  /** The patient ID. */
  private int patientID;

  /**
   * Creates a new, unregistered patient.
   *
   * @param firstName   first name of the patient
   * @param lastName    last name of the patient
   * @param password    password of the patient
   * @param email       contact email address
   * @param dayOfBirth date of birth
   */
  public Patient(String firstName, String lastName, String password, String email, String gender, String phoneNum, LocalDate dayOfBirth, String userName, String CPR) {
    super(firstName, lastName, password, email, gender, phoneNum, dayOfBirth, userName);
    this.CPR=CPR;
    this.medicalNotes=("");
    this.lastVisit=null;
  }

  /**
   * Creates a new {@code Patient} initialised with the given first name, last name, password, email, gender, phone num, day of birth, user name, medical notes, last visit, CPR.
   *
   * @param firstName the first name
   * @param lastName the last name
   * @param password the password
   * @param email the email
   * @param gender the gender
   * @param phoneNum the phone num
   * @param dayOfBirth the day of birth
   * @param userName the user name
   * @param medicalNotes the medical notes
   * @param lastVisit the last visit
   * @param CPR the CPR
   */
  public Patient(String firstName, String lastName, String password, String email, String gender, String phoneNum, LocalDate dayOfBirth, String userName, String medicalNotes, LocalDate lastVisit, String CPR) {
      super(firstName, lastName, password, email, gender, phoneNum, dayOfBirth, userName);
      this.medicalNotes=medicalNotes;
      this.lastVisit=lastVisit;
      this.CPR=CPR;
  }

  /**
   * Creates a new {@code Patient} initialised with the given patient ID, first name, last name, password, email, gender, phone num, day of birth, user name, medical notes, last visit, CPR.
   *
   * @param patientID the identifier of the patient
   * @param firstName the first name
   * @param lastName the last name
   * @param password the password
   * @param email the email
   * @param gender the gender
   * @param phoneNum the phone num
   * @param dayOfBirth the day of birth
   * @param userName the user name
   * @param medicalNotes the medical notes
   * @param lastVisit the last visit
   * @param CPR the CPR
   */
  public Patient(int patientID, String firstName, String lastName, String password, String email, String gender, String phoneNum, LocalDate dayOfBirth, String userName, String medicalNotes, LocalDate lastVisit,  String CPR) {
    super(firstName, lastName, password, email, gender, phoneNum, dayOfBirth, userName);
    this.patientID=patientID;
    this.medicalNotes=medicalNotes;
    this.lastVisit=lastVisit;
    this.CPR=CPR;
  }


  /**
   * Returns the patient's first name.
   *
   * @return the name
   */
  public String getFirstName() {
    return super.getFirstName();
  }

  /**
   * Returns the patient's last name.
   *
   * @return the name
   */
  public String getLastName() {
    return super.getLastName();
  }

  /**
   * Returns the patient's phone number.
   *
   * @return the phone number
   */
  public String getPassword() {
    return super.getPassword();
  }

  /**
   * Returns the patient's email address.
   *
   * @return the email address
   */
  public String getEmail() {
    return super.getEmail();
  }

  /**
   * Returns the patient's date of birth.
   *
   * @return the date of birth
   */
  public LocalDate getDayOfBirth() {
    return super.getDayOfBirth();
  }

  /**
   * Gets the patients age.
   *
   * @return the age in whole years, or {@code 0} if date of birth is {@code null}
   */
  public int getAge() {
    return super.getAge();
  }

  /**
   * Returns the patient ID.
   *
   * @return the patient ID
   */
  public int getPatientID() {return patientID; }

    /**
     * Returns the username.
     *
     * @return the username
     */
    public String getUsername() { return super.getUserName(); }

    /**
     * Returns the last visit.
     *
     * @return the last visit
     */
    public LocalDate getLastVisit() {
        return lastVisit;
    }

    /**
     * Returns the medical notes.
     *
     * @return the medical notes
     */
    public String getMedicalNotes() {
        return medicalNotes;
    }

    /**
     * Returns the CPR.
     *
     * @return the CPR
     */
    public String getCPR() {
        return CPR;
    }

    /**
     * Sets the patient id.
     *
     * @param patientId the identifier of the patient
     */
    public void setPatientId(int patientId) {
      this.patientID=patientId;
    }

    /**
     * Sets the CPR.
     *
     * @param CPR the CPR
     */
    public void setCPR(String CPR) {
        this.CPR = CPR;
    }

    /**
     * Sets the medical notes.
     *
     * @param medicalNotes the medical notes
     */
    public void setMedicalNotes(String medicalNotes) {
        this.medicalNotes = medicalNotes;
    }

    /**
     * Sets the patient ID.
     *
     * @param patientID the identifier of the patient
     */
    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    /**
     * Sets the last visit.
     *
     * @param lastVisit the last visit
     */
    public void setLastVisit(LocalDate lastVisit) {
        this.lastVisit = lastVisit;
    }

    /**
     * Sets the day of birth.
     *
     * @param dayOfBirth the day of birth
     */
    @Override
    public void setDayOfBirth(LocalDate dayOfBirth) {
        super.setDayOfBirth(dayOfBirth);
    }

    /**
     * Sets the email.
     *
     * @param email the email
     */
    @Override
    public void setEmail(Email email) {
        super.setEmail(email);
    }

    /**
     * Sets the first name.
     *
     * @param firstName the first name
     */
    @Override
    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    /**
     * Sets the last name.
     *
     * @param lastName the last name
     */
    @Override
    public void setLastName(String lastName) {
        super.setLastName(lastName);
    }

    /**
     * Sets the phone.
     *
     * @param password the password
     */
    @Override
    public void setPhone(Password password) {
        super.setPhone(password);
    }

    /**
     * Sets the gender.
     *
     * @param gender the gender
     */
    @Override
    public void setGender(String gender) {
        super.setGender(gender);
    }

    /**
     * Sets the password.
     *
     * @param password the password
     */
    @Override
    public void setPassword(Password password) {
        super.setPassword(password);
    }

    /**
     * Sets the phone num.
     *
     * @param phoneNum the phone num
     */
    @Override
    public void setPhoneNum(String phoneNum) {
        super.setPhoneNum(phoneNum);
    }

    /**
     * Sets the user name.
     *
     * @param userName the user name
     */
    @Override
    public void setUserName(String userName) {
        super.setUserName(userName);
    }

    /**
     * Returns the gender.
     *
     * @return the gender
     */
    @Override
    public String getGender() {
        return super.getGender();
    }

    /**
     * Returns the phone num.
     *
     * @return the phone num
     */
    @Override
    public String getPhoneNum() {
        return super.getPhoneNum();
    }

    /**
     * Returns the user name.
     *
     * @return the user name
     */
    @Override
    public String getUserName() {
        return super.getUserName();
    }

}