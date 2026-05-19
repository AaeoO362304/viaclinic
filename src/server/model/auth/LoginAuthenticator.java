package server.model.auth;

import client.view.login.ViewHandler;
import server.model.bookAppointment.Doctor;
import server.model.bookAppointment.DoctorDAO;
import server.model.bookAppointment.Patient;
import server.model.bookAppointment.PatientDAO;

import java.sql.SQLException;

public class LoginAuthenticator implements LoginService
{
    private final PatientDAO patientDAO;
    private final DoctorDAO doctorDAO;

    private Session currentSession;

    public LoginAuthenticator()
    {
        this.patientDAO = PatientDAO.getInstance();
        this.doctorDAO = DoctorDAO.getInstance();
    }

    @Override
    public Session login(String username, String password) throws AuthenticationException
    {
        try
        {
            Patient patient = patientDAO.getPatientByUsername(username);
            if (patient != null && password.equals(patient.getPassword()))
            {
                currentSession = new Session(patient, Role.PATIENT);
                return currentSession;
            }

            Doctor doctor = doctorDAO.getDoctorByUsername(username);
            if (doctor != null && password.equals(doctor.getPassword()))
            {
                currentSession = new Session(doctor, Role.DOCTOR);
                return currentSession;
            }

            throw new AuthenticationException("Invalid username or password.");
        }
        catch (SQLException e)
        {
            throw new AuthenticationException("Could not reach the database. Please try again.", e);
        }
    }

    @Override
    public void logout()
    {
        currentSession = null;
    }

    @Override
    public Session getCurrentSession()
    {
        return currentSession;
    }

    @Override
    public boolean isLoggedIn()
    {
        return currentSession != null;
    }

    @Override
    public boolean hasRole(Role role)
    {
        return currentSession != null && currentSession.getRole() == role;
    }
}
