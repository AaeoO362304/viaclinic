package server.model.bookAppointment;

import java.time.LocalDate;

/**
 * Represents a receptionist actor in the clinic system.
 * The {@link #isLoggedIn()} flag is checked by services to enforce the
 * precondition that the receptionist must be logged into the system.
 */
public class Receptionist extends User {


  public Receptionist(String firstName, String lastName, String gender, String phoneNum, String email, String password, LocalDate dayOfBirth, String userName) {
    super(firstName, lastName, gender, phoneNum, email, password, dayOfBirth, userName);
  }

  /**
   * Returns the receptionist's unique identifier.
   *
   * @return the id
   */


  /**
   * Returns the receptionist's username.
   *
   * @return the username
   */


  /**
   * Indicates whether the receptionist is currently logged in.
   *
   * @return {@code true} if logged in, {@code false} otherwise
   */

}