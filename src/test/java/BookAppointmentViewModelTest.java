import client.model.ClinicClient;
import client.viewModel.appointment.BookAppointmentViewModel;
import client.viewModel.doctor.DoctorViewModel;
import client.viewModel.login.LoginViewModel;
import client.viewModel.patient.PatientViewModel;
import org.junit.jupiter.api.Test;
import shared.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for {@link BookAppointmentViewModel}.
 *
 * <p>Methodology: ZOMBIE (Zero, One, Many, Boundary, Interface, Exceptions).<br>
 * Each test is tagged in its Javadoc with the applicable ZOMBIE letter(s).
 *
 * <p>Black-box tests verify observable inputs and outputs only.<br>
 * White-box tests target specific branches inside {@code create()} and {@code clear()}.
 *
 * <p>No mocking framework is used; a hand-written {@link FakeClinicClient}
 * handles both login (session setup) and appointment creation.
 */
public class BookAppointmentViewModelTest {

    // ------------------------------------------------------------------ fake

    /**
     * In-process stub for {@link ClinicClient}.
     * Supports {@code login()} for session setup and {@code createAppointment()}
     * for appointment verification. All other methods are unused.
     */
    private static class FakeClinicClient implements ClinicClient {
        SessionDTO loginResult;
        Exception createAppointmentException;
        boolean createAppointmentCalled;
        int lastPatientId;
        int lastDoctorId;
        boolean lastStatus;
        String lastNotes;

        @Override
        public SessionDTO login(String username, String password) throws Exception {
            return loginResult;
        }

        @Override public void logout() throws Exception {}

        @Override
        public AppointmentDTO createAppointment(int patientId, int doctorId,
                LocalDateTime date, boolean status, String notes) throws Exception {
            createAppointmentCalled = true;
            lastPatientId = patientId;
            lastDoctorId = doctorId;
            lastStatus = status;
            lastNotes = notes;
            if (createAppointmentException != null) throw createAppointmentException;
            return null;
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
        @Override public AppointmentDTO updateAppointment(int aid, int did, LocalDateTime dt) throws Exception { throw new UnsupportedOperationException(); }
        @Override public void deleteAppointment(int aid) throws SQLException { throw new UnsupportedOperationException(); }
        @Override public AppointmentDTO finishAppointment(int aid, String n) throws Exception { throw new UnsupportedOperationException(); }
        @Override public ArrayList<AppointmentDTO> getAppointmentsByPatientId(int pid) throws Exception { throw new UnsupportedOperationException(); }
        @Override public ArrayList<AppointmentDTO> getAppointmentsByDoctorId(int did) throws Exception { throw new UnsupportedOperationException(); }
    }

    // ------------------------------------------------------------ helpers

    /** Returns a {@link DoctorDTO} with the given ID and nothing else set. */
    private static DoctorDTO doctor(int id) {
        DoctorDTO d = new DoctorDTO();
        d.setDoctorID(id);
        return d;
    }

    /**
     * Builds a {@link BookAppointmentViewModel} whose patient has an authenticated
     * session with the given {@code userId}.
     * The same {@code client} is used for both the login call and appointment creation.
     */
    private static BookAppointmentViewModel vmWithSession(FakeClinicClient client,
                                                          int userId) throws Exception {
        client.loginResult = new SessionDTO(userId, "Harry", "Potter", "harryp", RoleDTO.PATIENT);
        LoginViewModel loginVM = new LoginViewModel(client);
        loginVM.getUsernameProperty().set("harryp");
        loginVM.getPasswordProperty().set("Password123");
        loginVM.login();

        PatientViewModel patientVM = new PatientViewModel(loginVM);
        DoctorViewModel doctorVM = new DoctorViewModel(loginVM);
        return new BookAppointmentViewModel(client, patientVM, doctorVM);
    }

    /**
     * Builds a {@link BookAppointmentViewModel} where no patient has logged in
     * ({@code getCurrentSession()} returns {@code null}).
     */
    private static BookAppointmentViewModel vmWithNoSession(FakeClinicClient client) {
        LoginViewModel loginVM = new LoginViewModel(client);
        PatientViewModel patientVM = new PatientViewModel(loginVM);
        DoctorViewModel doctorVM = new DoctorViewModel(loginVM);
        return new BookAppointmentViewModel(client, patientVM, doctorVM);
    }

    // ============================================================ BLACK-BOX TESTS
    // These tests treat BookAppointmentViewModel as a closed box.

    /**
     * [Z – Zero] No date selected — {@code create()} returns {@code false}.
     */
    @Test
    void blackBox_create_noDate_returnsFalse() throws Exception {
        assertFalse(vmWithNoSession(new FakeClinicClient()).create());
    }

    /**
     * [Z] No date selected — error property shows the date prompt.
     */
    @Test
    void blackBox_create_noDate_setsDateError() throws Exception {
        BookAppointmentViewModel vm = vmWithNoSession(new FakeClinicClient());

        vm.create();

        assertEquals("Please select a date.", vm.getErrorProperty().get());
    }

    /**
     * [B – Boundary] Yesterday (past date) — {@code create()} returns {@code false}.
     */
    @Test
    void blackBox_create_pastDate_returnsFalse() throws Exception {
        BookAppointmentViewModel vm = vmWithNoSession(new FakeClinicClient());
        vm.setDate(LocalDate.now().minusDays(1));

        assertFalse(vm.create());
    }

    /**
     * [B] Yesterday — error property shows the past-date message.
     */
    @Test
    void blackBox_create_pastDate_setsPastDateError() throws Exception {
        BookAppointmentViewModel vm = vmWithNoSession(new FakeClinicClient());
        vm.setDate(LocalDate.now().minusDays(1));

        vm.create();

        assertEquals("Select a date from today or the future.", vm.getErrorProperty().get());
    }

    /**
     * [O – One] Future date set but no doctor — {@code create()} returns {@code false}.
     */
    @Test
    void blackBox_create_futureDateNoDoctor_returnsFalse() throws Exception {
        BookAppointmentViewModel vm = vmWithNoSession(new FakeClinicClient());
        vm.setDate(LocalDate.now().plusDays(1));

        assertFalse(vm.create());
    }

    /**
     * [O] Future date but no doctor — error property shows the doctor prompt.
     */
    @Test
    void blackBox_create_futureDateNoDoctor_setsDoctorError() throws Exception {
        BookAppointmentViewModel vm = vmWithNoSession(new FakeClinicClient());
        vm.setDate(LocalDate.now().plusDays(1));

        vm.create();

        assertEquals("Please select a doctor.", vm.getErrorProperty().get());
    }

    /**
     * [O] Date and doctor set but no time — {@code create()} returns {@code false}.
     */
    @Test
    void blackBox_create_dateAndDoctorNoTime_returnsFalse() throws Exception {
        BookAppointmentViewModel vm = vmWithNoSession(new FakeClinicClient());
        vm.setDate(LocalDate.now().plusDays(1));
        vm.setDoctor(doctor(5));

        assertFalse(vm.create());
    }

    /**
     * [O] Date and doctor set but no time — error property shows the time prompt.
     */
    @Test
    void blackBox_create_dateAndDoctorNoTime_setsTimeError() throws Exception {
        BookAppointmentViewModel vm = vmWithNoSession(new FakeClinicClient());
        vm.setDate(LocalDate.now().plusDays(1));
        vm.setDoctor(doctor(5));

        vm.create();

        assertEquals("Please select a time for your appointment.", vm.getErrorProperty().get());
    }

    /**
     * [M – Many] All required fields provided — {@code create()} returns {@code true}.
     */
    @Test
    void blackBox_create_allValidFields_returnsTrue() throws Exception {
        FakeClinicClient client = new FakeClinicClient();
        BookAppointmentViewModel vm = vmWithSession(client, 1);

        vm.setDate(LocalDate.now().plusDays(1));
        vm.setDoctor(doctor(2));
        vm.getTimeProperty().set("10:30");
        vm.getNotesProperty().set("Headache");

        assertTrue(vm.create());
    }

    /**
     * [M] All fields valid — error property is empty after creation.
     */
    @Test
    void blackBox_create_allValidFields_errorIsEmpty() throws Exception {
        FakeClinicClient client = new FakeClinicClient();
        BookAppointmentViewModel vm = vmWithSession(client, 1);

        vm.setDate(LocalDate.now().plusDays(1));
        vm.setDoctor(doctor(2));
        vm.getTimeProperty().set("10:30");
        vm.getNotesProperty().set("Headache");
        vm.create();

        assertEquals("", vm.getErrorProperty().get());
    }

    /**
     * [M, I – Interface] Valid appointment — the client is called with the correct
     * patient ID, doctor ID, notes, and status.
     */
    @Test
    void blackBox_create_allValidFields_callsClientWithCorrectArgs() throws Exception {
        FakeClinicClient client = new FakeClinicClient();
        BookAppointmentViewModel vm = vmWithSession(client, 1);

        vm.setDate(LocalDate.now().plusDays(1));
        vm.setDoctor(doctor(2));
        vm.getTimeProperty().set("10:30");
        vm.getNotesProperty().set("Headache");
        vm.create();

        assertTrue(client.createAppointmentCalled);
        assertEquals(1, client.lastPatientId);
        assertEquals(2, client.lastDoctorId);
        assertEquals("Headache", client.lastNotes);
        assertFalse(client.lastStatus);
    }

    // ============================================================ WHITE-BOX TESTS
    // These tests know the internal branching inside create() and clear().

    /**
     * [B] Today's date passes the date check.
     * White-box: the guard is {@code date.isBefore(LocalDate.now())}, so today itself
     * is accepted (it is not strictly before today).
     */
    @Test
    void whitebox_create_todayDate_passesDateValidation() throws Exception {
        BookAppointmentViewModel vm = vmWithNoSession(new FakeClinicClient());
        vm.setDate(LocalDate.now());

        vm.create();

        assertNotEquals("Select a date from today or the future.", vm.getErrorProperty().get());
    }

    /**
     * [Z] No session (patient never logged in) — {@code create()} returns {@code false}.
     * White-box: the session check occurs after date / doctor / time validation.
     */
    @Test
    void whitebox_create_nullSession_returnsFalse() throws Exception {
        BookAppointmentViewModel vm = vmWithNoSession(new FakeClinicClient());
        vm.setDate(LocalDate.now().plusDays(1));
        vm.setDoctor(doctor(3));
        vm.getTimeProperty().set("09:00");

        assertFalse(vm.create());
    }

    /**
     * [Z] No session — error shows the "no patient" message.
     * White-box: {@code error.set("No patient is logged in.")} when {@code session == null}.
     */
    @Test
    void whitebox_create_nullSession_setsNoPatientError() throws Exception {
        BookAppointmentViewModel vm = vmWithNoSession(new FakeClinicClient());
        vm.setDate(LocalDate.now().plusDays(1));
        vm.setDoctor(doctor(3));
        vm.getTimeProperty().set("09:00");
        vm.create();

        assertEquals("No patient is logged in.", vm.getErrorProperty().get());
    }

    /**
     * [B] {@code userId == 0} also triggers the "no patient" error.
     * White-box: condition is {@code session.getUserId() <= 0}, so 0 is rejected.
     */
    @Test
    void whitebox_create_userIdZero_setsNoPatientError() throws Exception {
        FakeClinicClient client = new FakeClinicClient();
        BookAppointmentViewModel vm = vmWithSession(client, 0);

        vm.setDate(LocalDate.now().plusDays(1));
        vm.setDoctor(doctor(3));
        vm.getTimeProperty().set("09:00");
        vm.create();

        assertEquals("No patient is logged in.", vm.getErrorProperty().get());
    }

    /**
     * [E – Exceptions] When {@code client.createAppointment()} throws,
     * {@code create()} returns {@code false}.
     */
    @Test
    void whitebox_create_clientThrows_returnsFalse() throws Exception {
        FakeClinicClient client = new FakeClinicClient();
        client.createAppointmentException = new Exception("Server error");
        BookAppointmentViewModel vm = vmWithSession(client, 1);

        vm.setDate(LocalDate.now().plusDays(1));
        vm.setDoctor(doctor(2));
        vm.getTimeProperty().set("10:30");

        assertFalse(vm.create());
    }

    /**
     * [E] When the client throws, the error property contains the exception message.
     */
    @Test
    void whitebox_create_clientThrows_setsExceptionMessage() throws Exception {
        FakeClinicClient client = new FakeClinicClient();
        client.createAppointmentException = new Exception("Server error");
        BookAppointmentViewModel vm = vmWithSession(client, 1);

        vm.setDate(LocalDate.now().plusDays(1));
        vm.setDoctor(doctor(2));
        vm.getTimeProperty().set("10:30");
        vm.create();

        assertEquals("Server error", vm.getErrorProperty().get());
    }

    /**
     * [E, B] A {@code null} exception message falls back to {@code "Something went wrong."}.
     * White-box: {@code e.getMessage() == null ? "Something went wrong." : e.getMessage()}.
     */
    @Test
    void whitebox_create_nullMessageException_setsFallbackError() throws Exception {
        FakeClinicClient client = new FakeClinicClient();
        client.createAppointmentException = new Exception((String) null);
        BookAppointmentViewModel vm = vmWithSession(client, 1);

        vm.setDate(LocalDate.now().plusDays(1));
        vm.setDoctor(doctor(2));
        vm.getTimeProperty().set("10:30");
        vm.create();

        assertEquals("Something went wrong.", vm.getErrorProperty().get());
    }

    /**
     * [I] The error property is cleared at the start of every {@code create()} call,
     * so stale messages never survive between attempts.
     * White-box: {@code error.set("")} is the first statement inside {@code create()}.
     */
    @Test
    void whitebox_create_clearsPreviousErrorBeforeValidation() throws Exception {
        BookAppointmentViewModel vm = vmWithNoSession(new FakeClinicClient());
        vm.getErrorProperty().set("Previous error");

        vm.create(); // fails with "Please select a date." because date is null

        assertEquals("Please select a date.", vm.getErrorProperty().get());
    }

    /**
     * [M] {@code clear()} resets time, notes, and error properties, and nulls the
     * date so that the next {@code create()} call requires a date again.
     */
    @Test
    void whitebox_clear_resetsAllFields() throws Exception {
        FakeClinicClient client = new FakeClinicClient();
        BookAppointmentViewModel vm = vmWithSession(client, 1);

        vm.setDate(LocalDate.now().plusDays(1));
        vm.setDoctor(doctor(2));
        vm.getTimeProperty().set("10:30");
        vm.getNotesProperty().set("Headache");
        vm.getErrorProperty().set("Some error");

        vm.clear();

        assertAll(
            () -> assertEquals("", vm.getTimeProperty().get()),
            () -> assertEquals("", vm.getNotesProperty().get()),
            () -> assertEquals("", vm.getErrorProperty().get())
        );

        // date was also cleared — create() must fail with the "no date" error
        vm.create();
        assertEquals("Please select a date.", vm.getErrorProperty().get());
    }
}
