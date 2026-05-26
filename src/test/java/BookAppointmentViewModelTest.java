
import client.model.ClinicClient;
import client.viewModel.appointment.BookAppointmentViewModel;
import client.viewModel.login.DoctorViewModel;
import client.viewModel.login.LoginViewModel;
import client.viewModel.login.PatientViewModel;
import org.junit.jupiter.api.Test;
import shared.DoctorDTO;
import shared.SessionDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for BookAppointmentViewModel.
 * These tests check appointment validation
 * and appointment creation behavior.
 */
public class BookAppointmentViewModelTest {

  /**
   * Tests creating appointment without selecting date.
   * Expected: error message and no appointment creation.
   */
  @Test
  void createWithoutDateSetsError() throws Exception {

    // Arrange

    // Create mock objects
    ClinicClient client = mock(ClinicClient.class);
    PatientViewModel patientVM = mock(PatientViewModel.class);
    DoctorViewModel doctorVM = mock(DoctorViewModel.class);

    // Create object under test
    BookAppointmentViewModel vm =
        new BookAppointmentViewModel(client, patientVM, doctorVM);

    // Act

    // Try creating appointment
    boolean result = vm.create();

    // Assert

    // Appointment creation should fail
    assertFalse(result);

    // Correct error message should appear
    assertEquals(
        "Please select a date.",
        vm.getErrorProperty().get()
    );

    // Verify client method was never called
    verify(client, never()).createAppointment(
        anyInt(),
        anyInt(),
        any(LocalDateTime.class),
        anyBoolean(),
        anyString()
    );
  }

  /**
   * Tests creating appointment with past date.
   * Expected: error message and no appointment creation.
   */
  @Test
  void createWithPastDateSetsError() throws Exception {

    // Arrange

    // Create mock objects
    ClinicClient client = mock(ClinicClient.class);
    PatientViewModel patientVM = mock(PatientViewModel.class);
    DoctorViewModel doctorVM = mock(DoctorViewModel.class);

    // Create object under test
    BookAppointmentViewModel vm =
        new BookAppointmentViewModel(client, patientVM, doctorVM);

    // Set invalid past date
    vm.setDate(LocalDate.now().minusDays(1));

    // Act

    // Try creating appointment
    boolean result = vm.create();

    // Assert

    // Appointment creation should fail
    assertFalse(result);

    // Correct error message should appear
    assertEquals(
        "Select a date from today or the future.",
        vm.getErrorProperty().get()
    );

    // Verify client method was never called
    verify(client, never()).createAppointment(
        anyInt(),
        anyInt(),
        any(LocalDateTime.class),
        anyBoolean(),
        anyString()
    );
  }

  /**
   * Tests creating appointment without selecting doctor.
   * Expected: error message and no appointment creation.
   */
  @Test
  void createWithoutDoctorSetsError() throws Exception {

    // Arrange

    // Create mock objects
    ClinicClient client = mock(ClinicClient.class);
    PatientViewModel patientVM = mock(PatientViewModel.class);
    DoctorViewModel doctorVM = mock(DoctorViewModel.class);

    // Create object under test
    BookAppointmentViewModel vm =
        new BookAppointmentViewModel(client, patientVM, doctorVM);

    // Set valid future date
    vm.setDate(LocalDate.now().plusDays(1));

    // Act

    // Try creating appointment
    boolean result = vm.create();

    // Assert

    // Appointment creation should fail
    assertFalse(result);

    // Correct error message should appear
    assertEquals(
        "Please select a doctor.",
        vm.getErrorProperty().get()
    );

    // Verify client method was never called
    verify(client, never()).createAppointment(
        anyInt(),
        anyInt(),
        any(LocalDateTime.class),
        anyBoolean(),
        anyString()
    );
  }

  /**
   * Tests successful appointment creation.
   * Expected: appointment is created successfully.
   */
  @Test
  void createValidAppointmentCallsClient() throws Exception {

    // Arrange

    // Create mock objects
    ClinicClient client = mock(ClinicClient.class);
    PatientViewModel patientVM = mock(PatientViewModel.class);
    DoctorViewModel doctorVM = mock(DoctorViewModel.class);
    LoginViewModel loginVM = mock(LoginViewModel.class);

    SessionDTO session = mock(SessionDTO.class);
    DoctorDTO doctor = mock(DoctorDTO.class);

    // Define mock behavior
    when(patientVM.getLoginViewModel()).thenReturn(loginVM);

    when(loginVM.getCurrentSession()).thenReturn(session);

    when(session.getUserId()).thenReturn(1);

    when(doctor.getDoctorID()).thenReturn(2);

    // Create object under test
    BookAppointmentViewModel vm =
        new BookAppointmentViewModel(client, patientVM, doctorVM);

    // Set valid appointment data
    vm.setDate(LocalDate.now().plusDays(1));
    vm.setDoctor(doctor);
    vm.getTimeProperty().set("10:30");
    vm.getNotesProperty().set("Headache");

    // Act

    // Create appointment
    boolean result = vm.create();

    // Assert

    // Appointment creation should succeed
    assertTrue(result);

    // No error should exist
    assertEquals("", vm.getErrorProperty().get());

    // Verify correct client method call
    verify(client).createAppointment(
        eq(1),
        eq(2),
        any(LocalDateTime.class),
        eq(false),
        eq("Headache")
    );
  }
}