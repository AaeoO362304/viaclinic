package shared;

import java.time.LocalDate;

public class PatientDTO {
    private int patientID;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String userName;
    private String CPR;
    private String gender;
    private String password;
    private String medicalNotes;
    private LocalDate lastVisit;
    private LocalDate dayOfBirth;
    private int age;

    public PatientDTO() {}

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



    public int getPatientID() { return patientID; }
    public LocalDate getDayOfBirth() { return dayOfBirth; }
    public LocalDate getLastVisit() { return lastVisit; }
    public String getCPR() { return CPR; }
    public String getEmail() { return email; }
    public String getFirstName() { return firstName; }
    public String getGender() { return gender; }
    public String getLastName() { return lastName; }
    public String getMedicalNotes() { return medicalNotes; }
    public String getPassword() { return password; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getUserName() { return userName; }
    public int getAge() { return age; }

    public void setCPR(String CPR) { this.CPR = CPR; }
    public void setDayOfBirth(LocalDate dayOfBirth) { this.dayOfBirth = dayOfBirth; }
    public void setEmail(String email) { this.email = email; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setGender(String gender) { this.gender = gender; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setLastVisit(LocalDate lastVisit) { this.lastVisit = lastVisit; }
    public void setMedicalNotes(String medicalNotes) { this.medicalNotes = medicalNotes; }
    public void setPassword(String password) { this.password = password; }
    public void setPatientID(int patientID) { this.patientID = patientID; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setUserName(String userName) { this.userName = userName; }
    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return firstName+" "+lastName;
    }
}
