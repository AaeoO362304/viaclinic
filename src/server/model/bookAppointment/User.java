package server.model.bookAppointment;

import java.time.LocalDate;
import java.time.Period;

/**
 * Parent class for all users (patient, doctor, receptionist) with the data they share.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public abstract class User
{
  /** The gender. */
  private String gender, phoneNum, firstName, lastName, userName;
  /** The password. */
  private Password password;
  /** The email. */
  private Email email;
  /** The day of birth. */
  private LocalDate dayOfBirth;
  /** The user id. */
  private int userId;

  /**
   * Creates a new {@code User} initialised with the given password, email.
   *
   * @param password the password
   * @param email the email
   */
  public User(Password password, Email email) {
      this.password=password;
      this.email=email;
  }

  /**
   * Creates a new {@code User} initialised with the given first name, last name, password, email, gender, phone num, date of birth, user name.
   *
   * @param firstName the first name
   * @param lastName the last name
   * @param password the password
   * @param email the email
   * @param gender the gender
   * @param phoneNum the phone num
   * @param dateOfBirth the date of birth
   * @param userName the user name
   */
  public User(String firstName, String lastName, String password, String email,
              String gender, String phoneNum, LocalDate dateOfBirth, String userName)
  {
      this.password = new Password(password);
      this.email = new Email(email);
    this.firstName = firstName;
    this.lastName = lastName;
    this.gender = gender;
    this.phoneNum = phoneNum;
    this.dayOfBirth = dateOfBirth;
    this.userName=userName;
  }

  /**
   * Creates a new {@code User} initialised with the given first name, last name, password, email, gender, phone num, day of birth, user name.
   *
   * @param firstName the first name
   * @param lastName the last name
   * @param password the password
   * @param email the email
   * @param gender the gender
   * @param phoneNum the phone num
   * @param dayOfBirth the day of birth
   * @param userName the user name
   */
  public User(String firstName, String lastName, Password password, Email email, String gender, String phoneNum, LocalDate dayOfBirth, String userName)
  {
    this.firstName=firstName;
    this.lastName=lastName;
    this.password=password;
    this.email=email;
    this.gender=gender;
    this.phoneNum=phoneNum;
    this.dayOfBirth=dayOfBirth;
    this.userName=userName;
  }


  /**
   * Returns the age.
   *
   * @return the age
   */
  public int getAge()
  {
    if (dayOfBirth == null)
    {
      return 0;
    }

    return Period.between(dayOfBirth, LocalDate.now()).getYears();
  }

  /**
   * Returns the email.
   *
   * @return the email
   */
  public String getEmail() {
    return email.toString() ;
  }

  /**
   * Returns the day of birth.
   *
   * @return the day of birth
   */
  public LocalDate getDayOfBirth() {
    return dayOfBirth;
  }

  /**
   * Returns the password.
   *
   * @return the password
   */
  public String getPassword() {
    return password.toString() ;
  }

  /**
   * Returns the first name.
   *
   * @return the first name
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Returns the gender.
   *
   * @return the gender
   */
  public String getGender() {
    return gender;
  }

  /**
   * Returns the last name.
   *
   * @return the last name
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Returns the phone num.
   *
   * @return the phone num
   */
  public String getPhoneNum() {
    return phoneNum;
  }

  /**
   * Returns the user name.
   *
   * @return the user name
   */
  public String getUserName() {
    return userName;
  }

  /**
   * Updates the patient's name.
   *
   * @param firstName new first name
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Updates the patient's name.
   *
   * @param lastName new last name
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Updates the patient's phone number.
   *
   * @param password the new phone number
   */
  public void setPhone(Password password) {
    this.password = password;
  }

  /**
   * Updates the patient's email address.
   *
   * @param email the new email address
   */
  public void setEmail(Email email) {
    this.email = email;
  }

  /**
   * Updates the patient's date of birth.
   *
   * @param dayOfBirth the new date of birth
   */
  public void setDayOfBirth(LocalDate dayOfBirth) {
    this.dayOfBirth = dayOfBirth;
  }

    /**
     * Sets the user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Sets the gender.
     *
     * @param gender the gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Sets the password.
     *
     * @param password the password
     */
    public void setPassword(Password password) {
        this.password = password;
    }

    /**
     * Sets the phone num.
     *
     * @param phoneNum the phone num
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    /**
     * Sets the user id.
     *
     * @param userId the identifier of the user
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Returns the user id.
     *
     * @return the user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Returns this object as a String.
     *
     * @return the resulting string
     */
    public String toString() {
      return userName;
    }
}
