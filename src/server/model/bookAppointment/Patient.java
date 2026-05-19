package server.model.bookAppointment;

import java.time.LocalDate;

/**
 * A patient is uniquely identified by an {@code id} . Before the id is assigned the id is
 * {@code null}.
 */
public class Patient extends User {

  /** Unique identifier assigned by the repository; {@code null} until assigned. */
  private String medicalNotes, CPR;
  private LocalDate lastVisit;
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

  public Patient(String firstName, String lastName, String password, String email, String gender, String phoneNum, LocalDate dayOfBirth, String userName, String medicalNotes, LocalDate lastVisit, String CPR) {
      super(firstName, lastName, password, email, gender, phoneNum, dayOfBirth, userName);
      this.medicalNotes=medicalNotes;
      this.lastVisit=lastVisit;
      this.CPR=CPR;
  }

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

  public int getPatientID() {return patientID; }

    public String getUsername() { return super.getUserName(); }

    public LocalDate getLastVisit() {
        return lastVisit;
    }

    public String getMedicalNotes() {
        return medicalNotes;
    }

    public String getCPR() {
        return CPR;
    }

    public void setPatientId(int patientId) {
      this.patientID=patientId;
    }

    public void setCPR(String CPR) {
        this.CPR = CPR;
    }

    public void setMedicalNotes(String medicalNotes) {
        this.medicalNotes = medicalNotes;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public void setLastVisit(LocalDate lastVisit) {
        this.lastVisit = lastVisit;
    }

    @Override
    public void setDayOfBirth(LocalDate dayOfBirth) {
        super.setDayOfBirth(dayOfBirth);
    }

    @Override
    public void setEmail(Email email) {
        super.setEmail(email);
    }

    @Override
    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        super.setLastName(lastName);
    }

    @Override
    public void setPhone(Password password) {
        super.setPhone(password);
    }

    @Override
    public void setGender(String gender) {
        super.setGender(gender);
    }

    @Override
    public void setPassword(Password password) {
        super.setPassword(password);
    }

    @Override
    public void setPhoneNum(String phoneNum) {
        super.setPhoneNum(phoneNum);
    }

    @Override
    public void setUserName(String userName) {
        super.setUserName(userName);
    }

    @Override
    public String getGender() {
        return super.getGender();
    }

    @Override
    public String getPhoneNum() {
        return super.getPhoneNum();
    }

    @Override
    public String getUserName() {
        return super.getUserName();
    }

}