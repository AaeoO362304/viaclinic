package server.model.bookAppointment;

import java.time.LocalDate;
import java.time.Period;

public abstract class User
{
  private String gender, phoneNum, firstName, lastName, userName;
  private Password password;
  private Email email;
  private LocalDate dayOfBirth;
  private int userId;

  public User(Password password, Email email) {
      this.password=password;
      this.email=email;
  }

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


  public int getAge()
  {
    if (dayOfBirth == null)
    {
      return 0;
    }

    return Period.between(dayOfBirth, LocalDate.now()).getYears();
  }

  public String getEmail() {
    return email.toString() ;
  }

  public LocalDate getDayOfBirth() {
    return dayOfBirth;
  }

  public String getPassword() {
    return password.toString() ;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getGender() {
    return gender;
  }

  public String getLastName() {
    return lastName;
  }

  public String getPhoneNum() {
    return phoneNum;
  }

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

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public String toString() {
      return userName;
    }
}
