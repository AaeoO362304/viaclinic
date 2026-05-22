package server.model.bookAppointment;

import java.time.LocalDate;

public class Doctor extends User {
    private String specialization;
    private int doctorID;

    public Doctor(String firstName, String lastName, String password, String email,
                  String gender, String phoneNum, LocalDate dayOfBirth,
                  String userName, String specialization) {
        super(firstName, lastName, password, email, gender, phoneNum, dayOfBirth, userName);
        this.specialization = specialization;
    }

    public Doctor(int doctorID, String firstName, String lastName, String password, String email,
                  String gender, String phoneNum, LocalDate dayOfBirth,
                  String userName, String specialization) {
        super(firstName, lastName, password, email, gender, phoneNum, dayOfBirth, userName);
        this.doctorID = doctorID;
        this.specialization = specialization;
    }

    public int getDoctorID() { return doctorID; }
    public void setDoctorID(int doctorID) { this.doctorID = doctorID; }
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + (specialization == null ? "" : " - " + specialization);
    }
}
