
import client.model.ClinicClient;
import client.viewModel.login.LoginViewModel;
import org.junit.jupiter.api.Test;
import shared.RoleDTO;
import shared.SessionDTO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for LoginViewModel.
 * These tests check login functionality,
 * error handling and clearing of fields.
 */
public class LoginViewModelTest {

  /**
   * Tests that valid login credentials
   * return the correct role and session.
   */
  @Test
  void loginWithValidCredentialsReturnsRole() throws Exception {

    // Arrange

    // Create mock ClinicClient
    ClinicClient client = mock(ClinicClient.class);

    // Create LoginViewModel object
    LoginViewModel vm = new LoginViewModel(client);

    // Mock session object
    SessionDTO session = mock(SessionDTO.class);

    // Define mock behavior
    when(session.getRole()).thenReturn(RoleDTO.PATIENT);

    when(client.login("harryp", "Password123"))
        .thenReturn(session);

    // Set username and password
    vm.getUsernameProperty().set("harryp");
    vm.getPasswordProperty().set("Password123");

    // Act

    // Try to log in
    RoleDTO result = vm.login();

    // Assert

    // Check returned role
    assertEquals(RoleDTO.PATIENT, result);

    // Check session was stored
    assertEquals(session, vm.getCurrentSession());

    // Check no error message exists
    assertEquals("", vm.getErrorProperty().get());

    // Verify login method was called correctly
    verify(client).login("harryp", "Password123");
  }

  /**
   * Tests that invalid login credentials
   * return null and show an error message.
   */
  @Test
  void loginWithInvalidCredentialsSetsError() throws Exception {

    // Arrange

    // Create mock ClinicClient
    ClinicClient client = mock(ClinicClient.class);

    // Create LoginViewModel object
    LoginViewModel vm = new LoginViewModel(client);

    // Define mock behavior for invalid login
    when(client.login("wrong", "bad"))
        .thenThrow(new Exception("Invalid username or password"));

    // Set invalid credentials
    vm.getUsernameProperty().set("wrong");
    vm.getPasswordProperty().set("bad");

    // Act

    // Try to log in
    RoleDTO result = vm.login();

    // Assert

    // Check login failed
    assertNull(result);

    // Check correct error message
    assertEquals(
        "Invalid username or password",
        vm.getErrorProperty().get()
    );

    // Verify login method was called
    verify(client).login("wrong", "bad");
  }
  /**
   * Tests that logout()
   * clears the current session.
   */
  @Test
  void logoutClearsCurrentSession() throws Exception {

    // Arrange

    // Create mock ClinicClient
    ClinicClient client = mock(ClinicClient.class);

    // Create LoginViewModel object
    LoginViewModel vm = new LoginViewModel(client);

    // Mock session object
    SessionDTO session = mock(SessionDTO.class);

    // Define mock behavior
    when(session.getRole()).thenReturn(RoleDTO.PATIENT);

    when(client.login("harryp", "Password123"))
        .thenReturn(session);

    // Log in first
    vm.getUsernameProperty().set("harryp");
    vm.getPasswordProperty().set("Password123");

    vm.login();

    // Act

    // Log out
    vm.logout();

    // Assert

    // Check session is cleared
    assertNull(vm.getCurrentSession());

    // Verify logout method was called
    verify(client).logout();
  }
  /**
   * Tests that clear()
   * removes username, password and error text.
   */
  @Test
  void clearRemovesUsernamePasswordAndError() {

    // Arrange

    // Create mock ClinicClient
    ClinicClient client = mock(ClinicClient.class);

    // Create LoginViewModel object
    LoginViewModel vm = new LoginViewModel(client);

    // Set values before clearing
    vm.getUsernameProperty().set("harryp");
    vm.getPasswordProperty().set("Password123");
    vm.getErrorProperty().set("Error");

    // Act

    // Clear all fields
    vm.clear();

    // Assert

    // Check all fields are empty
    assertEquals("", vm.getUsernameProperty().get());

    assertEquals("", vm.getPasswordProperty().get());

    assertEquals("", vm.getErrorProperty().get());
  }
}
