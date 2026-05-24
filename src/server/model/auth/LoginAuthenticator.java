package server.model.auth;

import server.model.bookAppointment.*;

import java.sql.SQLException;
import java.util.Objects;

/**
 * Checks a user's login against the patient, doctor and receptionist data.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class LoginAuthenticator implements LoginService {
    /** The patient DAO. */
    private final PatientDAO patientDAO;
    /** The doctor DAO. */
    private final DoctorDAO doctorDAO;
    /** The receptionist DAO. */
    private final ReceptionistDAO receptionistDAO;

    /** The current session. */
    private Session currentSession;

    /**
     * Creates a new {@code LoginAuthenticator} instance.
     */
    public LoginAuthenticator() {
        this.patientDAO = PatientDAO.getInstance();
        this.doctorDAO = DoctorDAO.getInstance();
        this.receptionistDAO = ReceptionistDAO.getInstance();
    }

    /**
     * Checks the login and starts a session.
     *
     * @param username the username
     * @param password the password
     * @return the resulting session
     * @throws AuthenticationException if the operation cannot be completed
     */
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

    /**
     * Logs the user out.
     */
    @Override
    public void logout() { currentSession = null; }

    /**
     * Returns the current session.
     *
     * @return the current session
     */
    @Override
    public Session getCurrentSession() { return currentSession; }

    /**
     * Indicates whether logged in.
     *
     * @return {@code true} if logged in, otherwise {@code false}
     */
    @Override
    public boolean isLoggedIn() { return currentSession != null; }

    /**
     * Indicates whether role.
     *
     * @param role the role
     * @return {@code true} if role, otherwise {@code false}
     */
    @Override
    public boolean hasRole(Role role) { return currentSession != null && currentSession.getRole() == role; }
}
