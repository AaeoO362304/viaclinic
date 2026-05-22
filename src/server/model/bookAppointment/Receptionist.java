package server.model.bookAppointment;

import java.time.LocalDate;

public class Receptionist extends User {
    private int receptionistID;

    public Receptionist(String firstName, String lastName, String password, String email,
                        String gender, String phoneNum, LocalDate dayOfBirth, String userName) {
        super(firstName, lastName, password, email, gender, phoneNum, dayOfBirth, userName);
    }

    public Receptionist(int receptionistID, String firstName, String lastName, String password,
                        String email, String gender, String phoneNum, LocalDate dayOfBirth,
                        String userName) {
        super(firstName, lastName, password, email, gender, phoneNum, dayOfBirth, userName);
        this.receptionistID = receptionistID;
    }

    public int getReceptionistID() { return receptionistID; }
    public void setReceptionistID(int receptionistID) { this.receptionistID = receptionistID; }
}
