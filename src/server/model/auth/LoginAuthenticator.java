package server.model.auth;

import server.model.bookAppointment.*;

import java.sql.SQLException;
import java.util.Objects;

public class LoginAuthenticator implements LoginService {
    private final PatientDAO patientDAO;
    private final DoctorDAO doctorDAO;
    private final ReceptionistDAO receptionistDAO;

    private Session currentSession;

    public LoginAuthenticator() {
        this.patientDAO = PatientDAO.getInstance();
        this.doctorDAO = DoctorDAO.getInstance();
        this.receptionistDAO = ReceptionistDAO.getInstance();
    }

    @Override
    public Session login(String username, String password) throws AuthenticationException {
        try {
            Patient patient = patientDAO.getPatientByUsername(username);
            if (patient != null && Objects.equals(password, patient.getPassword())) {
                currentSession = new Session(patient, Role.PATIENT);
                return currentSession;
            }

            Doctor doctor = doctorDAO.getDoctorByUsername(username);
            if (doctor != null && Objects.equals(password, doctor.getPassword())) {
                currentSession = new Session(doctor, Role.DOCTOR);
                return currentSession;
            }

            Receptionist receptionist = receptionistDAO.getReceptionistByUsername(username);
            if (receptionist != null && Objects.equals(password, receptionist.getPassword())) {
                currentSession = new Session(receptionist, Role.RECEPTIONIST);
                return currentSession;
            }

            throw new AuthenticationException("Invalid username or password.");
        }
        catch (SQLException e) {
            throw new AuthenticationException("Could not reach the database. Please try again.", e);
        }
    }

    @Override
    public void logout() { currentSession = null; }

    @Override
    public Session getCurrentSession() { return currentSession; }

    @Override
    public boolean isLoggedIn() { return currentSession != null; }

    @Override
    public boolean hasRole(Role role) { return currentSession != null && currentSession.getRole() == role; }
}
