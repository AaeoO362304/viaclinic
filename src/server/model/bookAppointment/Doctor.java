package server.model.bookAppointment;

import java.time.LocalDate;

public class Doctor extends User
{
  private String specialization;
  private int doctorID;

  public Doctor(String firstName, String lastName, String gender, String phoneNum, String email, String password, LocalDate dayOfBirth, String userName, String specialization) {
    super(firstName, lastName, gender, phoneNum, email, password, dayOfBirth, userName);
    this.specialization=specialization;
  }

  public Doctor(int doctorID, String firstName, String lastName, String gender, String phoneNum, String email, String password, LocalDate dayOfBirth, String userName, String specialization) {
      super(firstName, lastName, gender, phoneNum, email, password, dayOfBirth, userName);
      this.specialization=specialization;
      this.doctorID=doctorID;
  }

  public int getDoctorID() {return doctorID;}

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString()
    {
        return getFirstName() + " " + getLastName();
    }
}
