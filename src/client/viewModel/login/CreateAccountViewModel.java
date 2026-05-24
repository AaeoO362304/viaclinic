package client.viewModel.login;

import client.model.ClinicClient;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.PatientDTO;

import java.time.LocalDate;

/**
 * View model for the Create Account screen.
 * Stores the data shown on screen and has the methods the controller calls.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class CreateAccountViewModel {
    /** The client. */
    private final ClinicClient client;
    /** The first name. */
    private final StringProperty firstName;
    /** The last name. */
    private final StringProperty lastName;
    /** The password. */
    private final StringProperty password;
    /** The email. */
    private final StringProperty email;
    /** The error. */
    private final StringProperty error;
    /** The gender. */
    private final StringProperty gender;
    /** The phone num. */
    private final StringProperty phoneNum;
    /** The CPR. */
    private final StringProperty CPR;
    /** The user name. */
    private final StringProperty userName;
    /** The day of birth. */
    private LocalDate dayOfBirth;

    /**
     * Creates a new {@code CreateAccountViewModel} initialised with the given client.
     *
     * @param client the client
     */
    public CreateAccountViewModel(ClinicClient client) {
        this.client = client;
        this.firstName = new SimpleStringProperty("");
        this.lastName = new SimpleStringProperty("");
        this.password = new SimpleStringProperty("");
        this.email = new SimpleStringProperty("");
        this.error = new SimpleStringProperty("");
        this.gender = new SimpleStringProperty("");
        this.phoneNum = new SimpleStringProperty("");
        this.CPR = new SimpleStringProperty("");
        this.userName = new SimpleStringProperty("");
    }

    /**
     * Clears the values in this view model.
     */
    public void clear() {
        firstName.set("");
        lastName.set("");
        password.set("");
        email.set("");
        error.set("");
        gender.set("");
        phoneNum.set("");
        CPR.set("");
        userName.set("");
        dayOfBirth = null;
    }

    /**
     * Creates a new patient.
     *
     * @return {@code true} if the operation succeeded, otherwise {@code false}
     */
    public boolean createPatient() {
        error.set("");

        try {
            if (firstName.get() == null || firstName.get().isBlank()) { error.set("Please enter your first name."); return false; }
            if (lastName.get() == null || lastName.get().isBlank()) { error.set("Please enter your last name."); return false; }
            if (gender.get() == null || gender.get().isBlank()) { error.set("Please choose a gender."); return false; }
            if (dayOfBirth == null) { error.set("Please enter your birthday."); return false; }
            if (phoneNum.get() == null || phoneNum.get().isBlank()) { error.set("Please enter your phone number."); return false; }
            if (email.get() == null || email.get().isBlank()) { error.set("Please enter your email."); return false; }
            if (password.get() == null || password.get().isBlank()) { error.set("Please enter your password."); return false; }
            if (CPR.get() == null || CPR.get().isBlank()) { error.set("Please enter your CPR number."); return false; }
            if (userName.get() == null || userName.get().isBlank()) { error.set("Please enter your username."); return false; }

            PatientDTO patient = new PatientDTO(
                    0,
                    firstName.get(),
                    lastName.get(),
                    email.get(),
                    phoneNum.get(),
                    userName.get(),
                    CPR.get(),
                    gender.get(),
                    password.get(),
                    "",
                    null,
                    dayOfBirth,
                    0
            );

            client.createPatient(patient);
            return true;
        }
        catch (Exception e) {
            error.set(e.getMessage() == null ? "Something went wrong." : e.getMessage());
            return false;
        }
    }

    /**
     * Returns the first name property used for data binding.
     *
     * @return the first name property
     */
    public StringProperty getFirstNameProperty() { return firstName; }
    /**
     * Returns the last name property used for data binding.
     *
     * @return the last name property
     */
    public StringProperty getLastNameProperty() { return lastName; }
    /**
     * Returns the password property used for data binding.
     *
     * @return the password property
     */
    public StringProperty getPasswordProperty() { return password; }
    /**
     * Returns the email property used for data binding.
     *
     * @return the email property
     */
    public StringProperty getEmailProperty() { return email; }
    /**
     * Returns the gender property used for data binding.
     *
     * @return the gender property
     */
    public StringProperty getGenderProperty() { return gender; }
    /**
     * Returns the phone num property used for data binding.
     *
     * @return the phone num property
     */
    public StringProperty getPhoneNumProperty() { return phoneNum; }
    /**
     * Returns the user name property used for data binding.
     *
     * @return the user name property
     */
    public StringProperty getUserNameProperty() { return userName; }
    /**
     * Returns the CPR property used for data binding.
     *
     * @return the CPR property
     */
    public StringProperty getCPRProperty() { return CPR; }
    /**
     * Returns the error property used for data binding.
     *
     * @return the error property
     */
    public StringProperty getErrorProperty() { return error; }
    /**
     * Sets the day of birth.
     *
     * @param dayOfBirth the day of birth
     */
    public void setDayOfBirth(LocalDate dayOfBirth) { this.dayOfBirth = dayOfBirth; }
}
