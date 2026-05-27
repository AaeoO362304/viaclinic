import client.model.ClinicClient;
import client.viewModel.login.LoginViewModel;
import org.junit.jupiter.api.Test;
import shared.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for {@link LoginViewModel}.
 *
 * <p>Methodology: ZOMBIE (Zero, One, Many, Boundary, Interface, Exceptions).<br>
 * Each test is tagged in its Javadoc with the applicable ZOMBIE letter(s).
 *
 * <p>Black-box tests treat {@code LoginViewModel} as a closed box and verify
 * only observable inputs and outputs.<br>
 * White-box tests are aware of the internal branching inside the class.
 *
 * <p>No mocking framework is used; behaviour is controlled through a
 * hand-written {@link FakeClinicClient} inner class.
 */
public class LoginViewModelTest {

    // ------------------------------------------------------------------ fake

    /**
     * In-process stub for {@link ClinicClient}.
     * Configure fields before use; inspect them afterwards.
     */
    private static class FakeClinicClient implements ClinicClient {
        SessionDTO loginResult;
        Exception loginException;
        Exception logoutException;
        boolean logoutCalled;

        @Override
        public SessionDTO login(String username, String password) throws Exception {
            if (loginException != null) throw loginException;
            return loginResult;
        }

        @Override
        public void logout() throws Exception {
            logoutCalled = true;
            if (logoutException != null) throw logoutException;
        }

        @Override public PatientDTO createPatient(PatientDTO p) throws Exception { throw new UnsupportedOperationException(); }
        @Override public PatientDTO updatePatient(PatientDTO p) throws Exception { throw new UnsupportedOperationException(); }
        @Override public DoctorDTO createDoctor(DoctorDTO d) throws Exception { throw new UnsupportedOperationException(); }
        @Override public ReceptionistDTO createReceptionist(ReceptionistDTO r) throws Exception { throw new UnsupportedOperationException(); }
        @Override public ArrayList<PatientDTO> getAllPatients() throws Exception { throw new UnsupportedOperationException(); }
        @Override public ArrayList<DoctorDTO> getAllDoctors() throws Exception { throw new UnsupportedOperationException(); }
        @Override public ArrayList<ReceptionistDTO> getAllReceptionists() throws Exception { throw new UnsupportedOperationException(); }
        @Override public ArrayList<AppointmentDTO> getAllAppointments() throws Exception { throw new UnsupportedOperationException(); }
        @Override public PatientDTO getPatientById(int id) throws Exception { throw new UnsupportedOperationException(); }
        @Override public void deletePatient(int id) throws Exception { throw new UnsupportedOperationException(); }
        @Override public AppointmentDTO createAppointment(int pid, int did, LocalDateTime dt, boolean s, String n) throws Exception { throw new UnsupportedOperationException(); }
        @Override public AppointmentDTO updateAppointment(int aid, int did, LocalDateTime dt) throws Exception { throw new UnsupportedOperationException(); }
        @Override public void deleteAppointment(int aid) throws SQLException { throw new UnsupportedOperationException(); }
        @Override public AppointmentDTO finishAppointment(int aid, String n) throws Exception { throw new UnsupportedOperationException(); }
        @Override public ArrayList<AppointmentDTO> getAppointmentsByPatientId(int pid) throws Exception { throw new UnsupportedOperationException(); }
        @Override public ArrayList<AppointmentDTO> getAppointmentsByDoctorId(int did) throws Exception { throw new UnsupportedOperationException(); }
    }

    // ------------------------------------------------------------ helper

    private static SessionDTO session(int userId, RoleDTO role) {
        return new SessionDTO(userId, "Test", "User", "testuser", role);
    }

    // ============================================================ BLACK-BOX TESTS
    // Verify observable input/output behaviour without knowing how the class works inside.

    /**
     * [O – One] Valid credentials for a PATIENT return {@code RoleDTO.PATIENT}.
     */
    @Test
    void blackBox_login_validPatientCredentials_returnsPatientRole() throws Exception {
        FakeClinicClient client = new FakeClinicClient();
        client.loginResult = session(1, RoleDTO.PATIENT);
        LoginViewModel vm = new LoginViewModel(client);

        vm.getUsernameProperty().set("harryp");
        vm.getPasswordProperty().set("Password123");

        assertEquals(RoleDTO.PATIENT, vm.login());
    }

    /**
     * [M – Many] Valid credentials for a DOCTOR return {@code RoleDTO.DOCTOR}.
     */
    @Test
    void blackBox_login_validDoctorCredentials_returnsDoctorRole() throws Exception {
        FakeClinicClient client = new FakeClinicClient();
        client.loginResult = session(2, RoleDTO.DOCTOR);
        LoginViewModel vm = new LoginViewModel(client);

        vm.getUsernameProperty().set("drsmith");
        vm.getPasswordProperty().set("Secret99");

        assertEquals(RoleDTO.DOCTOR, vm.login());
    }

    /**
     * [M] Valid credentials for a RECEPTIONIST return {@code RoleDTO.RECEPTIONIST}.
     */
    @Test
    void blackBox_login_validReceptionistCredentials_returnsReceptionistRole() throws Exception {
        FakeClinicClient client = new FakeClinicClient();
        client.loginResult = session(3, RoleDTO.RECEPTIONIST);
        LoginViewModel vm = new LoginViewModel(client);

        vm.getUsernameProperty().set("rec01");
        vm.getPasswordProperty().set("Desk2024");

        assertEquals(RoleDTO.RECEPTIONIST, vm.login());
    }

    /**
     * [O] After a successful login the session is stored and accessible.
     */
    @Test
    void blackBox_login_validCredentials_storesSession() throws Exception {
        FakeClinicClient client = new FakeClinicClient();
        SessionDTO expected = session(1, RoleDTO.PATIENT);
        client.loginResult = expected;
        LoginViewModel vm = new LoginViewModel(client);

        vm.getUsernameProperty().set("harryp");
        vm.getPasswordProperty().set("Password123");
        vm.login();

        assertSame(expected, vm.getCurrentSession());
    }

    /**
     * [O] A successful login leaves the error property empty.
     */
    @Test
    void blackBox_login_validCredentials_errorRemainsEmpty() throws Exception {
        FakeClinicClient client = new FakeClinicClient();
        client.loginResult = session(1, RoleDTO.PATIENT);
        LoginViewModel vm = new LoginViewModel(client);

        vm.getUsernameProperty().set("harryp");
        vm.getPasswordProperty().set("Password123");
        vm.login();

        assertEquals("", vm.getErrorProperty().get());
    }

    /**
     * [E – Exceptions] When the client throws, {@code login()} returns {@code null}.
     */
    @Test
    void blackBox_login_clientThrowsException_returnsNull() throws Exception {
        FakeClinicClient client = new FakeClinicClient();
        client.loginException = new Exception("Invalid username or password");
        LoginViewModel vm = new LoginViewModel(client);

        vm.getUsernameProperty().set("wrong");
        vm.getPasswordProperty().set("bad");

        assertNull(vm.login());
    }

    /**
     * [E] When the client throws, the error property contains the exception message.
     */
    @Test
    void blackBox_login_clientThrowsException_setsErrorMessage() throws Exception {
        FakeClinicClient client = new FakeClinicClient();
        client.loginException = new Exception("Invalid username or password");
        LoginViewModel vm = new LoginViewModel(client);

        vm.getUsernameProperty().set("wrong");
        vm.getPasswordProperty().set("bad");
        vm.login();

        assertEquals("Invalid username or password", vm.getErrorProperty().get());
    }

    /**
     * [Z – Zero] Empty username and password — client rejects them and error is set.
     */
    @Test
    void blackBox_login_emptyCredentials_setsError() throws Exception {
        FakeClinicClient client = new FakeClinicClient();
        client.loginException = new Exception("Username and password required");
        LoginViewModel vm = new LoginViewModel(client);

        // username and password default to "" — no explicit set needed
        vm.login();

        assertFalse(vm.getErrorProperty().get().isEmpty());
    }

    /**
     * [O] After a successful login, {@code logout()} clears the stored session.
     */
    @Test
    void blackBox_logout_afterLogin_sessionBecomesNull() throws Exception {
        FakeClinicClient client = new FakeClinicClient();
        client.loginResult = session(1, RoleDTO.PATIENT);
        LoginViewModel vm = new LoginViewModel(client);

        vm.getUsernameProperty().set("harryp");
        vm.getPasswordProperty().set("Password123");
        vm.login();

        vm.logout();

        assertNull(vm.getCurrentSession());
    }

    /**
     * [M] {@code clear()} resets username, password, and error to empty strings.
     */
    @Test
    void blackBox_clear_resetsAllThreeProperties() {
        FakeClinicClient client = new FakeClinicClient();
        LoginViewModel vm = new LoginViewModel(client);

        vm.getUsernameProperty().set("harryp");
        vm.getPasswordProperty().set("Password123");
        vm.getErrorProperty().set("Some error");

        vm.clear();

        assertAll(
            () -> assertEquals("", vm.getUsernameProperty().get()),
            () -> assertEquals("", vm.getPasswordProperty().get()),
            () -> assertEquals("", vm.getErrorProperty().get())
        );
    }

    // ============================================================ WHITE-BOX TESTS
    // These tests know the internal branching in LoginViewModel and target specific paths.

    /**
     * [B – Boundary] When the client returns {@code null}, {@code login()} returns
     * {@code null} without throwing a NullPointerException.
     * White-box: {@code return currentSession == null ? null : currentSession.getRole()}.
     */
    @Test
    void whitebox_login_clientReturnsNull_returnsNull() throws Exception {
        FakeClinicClient client = new FakeClinicClient();
        client.loginResult = null;
        LoginViewModel vm = new LoginViewModel(client);

        vm.getUsernameProperty().set("harryp");
        vm.getPasswordProperty().set("Password123");

        assertNull(vm.login());
    }

    /**
     * [B, E] When the thrown exception carries a {@code null} message the error
     * is set to the fallback {@code "Login failed."}.
     * White-box: {@code e.getMessage() == null ? "Login failed." : e.getMessage()}.
     */
    @Test
    void whitebox_login_nullMessageException_setsFallbackError() throws Exception {
        FakeClinicClient client = new FakeClinicClient();
        client.loginException = new Exception((String) null);
        LoginViewModel vm = new LoginViewModel(client);

        vm.login();

        assertEquals("Login failed.", vm.getErrorProperty().get());
    }

    /**
     * [I – Interface] The error property is cleared at the start of every
     * {@code login()} call, so a stale message never survives across attempts.
     * White-box: {@code error.set("")} is the first statement inside {@code login()}.
     */
    @Test
    void whitebox_login_staleErrorIsOverwrittenOnNextAttempt() throws Exception {
        FakeClinicClient client = new FakeClinicClient();
        client.loginException = new Exception("New error");
        LoginViewModel vm = new LoginViewModel(client);

        vm.getErrorProperty().set("Old error");
        vm.login();

        assertEquals("New error", vm.getErrorProperty().get());
    }

    /**
     * [E] When {@code logout()} throws, the session is still set to {@code null}.
     * White-box: {@code currentSession = null} is outside the try-catch block.
     */
    @Test
    void whitebox_logout_clientThrows_sessionStillCleared() throws Exception {
        FakeClinicClient client = new FakeClinicClient();
        client.loginResult = session(1, RoleDTO.PATIENT);
        LoginViewModel vm = new LoginViewModel(client);

        vm.getUsernameProperty().set("harryp");
        vm.getPasswordProperty().set("Password123");
        vm.login();

        client.logoutException = new Exception("Logout failed on server");
        vm.logout();

        assertNull(vm.getCurrentSession());
    }

    /**
     * [E] When {@code logout()} throws, the error property records the message.
     * White-box: the catch block calls {@code error.set(e.getMessage())}.
     */
    @Test
    void whitebox_logout_clientThrows_setsError() throws Exception {
        FakeClinicClient client = new FakeClinicClient();
        client.loginResult = session(1, RoleDTO.PATIENT);
        LoginViewModel vm = new LoginViewModel(client);

        vm.getUsernameProperty().set("harryp");
        vm.getPasswordProperty().set("Password123");
        vm.login();

        client.logoutException = new Exception("Logout failed on server");
        vm.logout();

        assertEquals("Logout failed on server", vm.getErrorProperty().get());
    }
}
