package shared;

import java.time.LocalDate;

public class ReceptionistDTO {
    private int receptionistID;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String userName;
    private String password;
    private String gender;
    private LocalDate dayOfBirth;
    private int age;

    public ReceptionistDTO() {}

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

    public int getReceptionistID() { return receptionistID; }
    public void setReceptionistID(int receptionistID) { this.receptionistID = receptionistID; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public LocalDate getDayOfBirth() { return dayOfBirth; }
    public void setDayOfBirth(LocalDate dayOfBirth) { this.dayOfBirth = dayOfBirth; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return ((firstName == null ? "" : firstName) + " " + (lastName == null ? "" : lastName)).trim();
    }
}
