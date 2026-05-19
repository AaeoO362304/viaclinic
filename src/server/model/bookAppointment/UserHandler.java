package server.model.bookAppointment;

import client.observer.UnnamedPropertyChangeSubject;

import java.time.LocalDate;

public interface UserHandler extends UnnamedPropertyChangeSubject
{
public void addPatient(String firstName, String lastName, String gender, String phoneNum, String email, String password, LocalDate dateOfBirth, String CPR, String UserName)
        throws IllegalArgumentException, IllegalStateException;

public Patient getPatient(String firstName);
public Doctor getDoctor(String userName);
public Receptionist getReceptionist(String userName);

public int getPatientCount();
}
