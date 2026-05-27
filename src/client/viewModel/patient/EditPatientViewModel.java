package client.viewModel.patient;

import client.model.ClinicClient;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.PatientDTO;
import shared.SessionDTO;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * View model for the Edit Patient screen.
 * Stores the data shown on screen and has the methods the controller calls.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class EditPatientViewModel {
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
    /** The patient id. */
    private int patientId;

    /** The patient view model. */
    private PatientViewModel patientViewModel;

    /**
     * Creates a new {@code EditPatientViewModel} initialised with the given client, patient view model.
     *
     * @param client the client
     * @param patientViewModel the patient view model
     */
    public EditPatientViewModel(ClinicClient client, PatientViewModel patientViewModel) {
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
        this.patientViewModel=patientViewModel;
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
     * Updates the patient.
     *
     * @return {@code true} if the operation succeeded, otherwise {@code false}
     */
    public boolean updatePatient() {
        error.set("");

        try {
            if (firstName.get() == null || firstName.get().isBlank()) { error.set("Please enter your first name."); return false; }
            if (lastName.get() == null || lastName.get().isBlank()) { error.set("Please enter your last name."); return false; }
            if (gender.get() == null || gender.get().isBlank()) { error.set("Please choose a gender."); return false; }
            if (phoneNum.get() == null || phoneNum.get().isBlank()) { error.set("Please enter your phone number."); return false; }
            if (email.get() == null || email.get().isBlank()) { error.set("Please enter your email."); return false; }
            if (password.get() == null || password.get().isBlank()) { error.set("Please enter your password."); return false; }
            if (CPR.get() == null || CPR.get().isBlank()) { error.set("Please enter your CPR number."); return false; }
            if (patientId <= 0)
            {
                SessionDTO session = patientViewModel.getLoginViewModel().getCurrentSession();
                patientId = session.getUserId();
            }

            PatientDTO patient = new PatientDTO(
                    patientId,
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

            client.updatePatient(patient);
            return true;
        }
        catch (Exception e) {
            error.set(e.getMessage() == null ? "Something went wrong." : e.getMessage());
            return false;
        }
    }

    /**
     * Deletes the patient.
     *
     * @param patientId the id of the patient
     * @throws Exception if the operation cannot be completed
     */
    public void deletePatient(int patientId) throws Exception {
        client.deletePatient(patientId);
    }

    /**
     * Returns the patient by id.
     *
     * @param patientId the id of the patient
     * @return the patient by id
     * @throws Exception if the operation cannot be completed
     */
    public PatientDTO getPatientById(int patientId) throws Exception {
        SessionDTO session = patientViewModel.getLoginViewModel().getCurrentSession();
        if (session == null || session.getUserId() <= 0) {
            return new PatientDTO();
        }
        return client.getPatientById(patientId);
    }

    /**
     * Loads the patient into the view.
     */
    public void loadPatient()
    {
        error.set("");

        try
        {
            SessionDTO session = patientViewModel.getLoginViewModel().getCurrentSession();

            if (session == null || session.getUserId() <= 0)
            {
                error.set("No patient is logged in.");
                return;
            }

            int patientId = session.getUserId();

            PatientDTO patient = client.getPatientById(patientId);

            firstName.set(patient.getFirstName());
            lastName.set(patient.getLastName());
            email.set(patient.getEmail());
            phoneNum.set(patient.getPhoneNumber());
            userName.set(patient.getUserName());
            CPR.set(patient.getCPR());
            gender.set(patient.getGender());
            password.set(patient.getPassword());
            dayOfBirth = patient.getDayOfBirth();
        }
        catch (Exception e)
        {
            error.set(e.getMessage() == null ? "Could not load patient data." : e.getMessage());
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
     * Returns the username property used for data binding.
     *
     * @return the username property
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
     * Returns the day of birth.
     *
     * @return the day of birth
     */
    public LocalDate getDayOfBirth() {return dayOfBirth;}
    /**
     * Sets the day of birth.
     *
     * @param dayOfBirth the day of birth
     */
    public void setDayOfBirth(LocalDate dayOfBirth) { this.dayOfBirth = dayOfBirth; }

    /**
     * Returns the patient view model.
     *
     * @return the patient view model
     */
    public PatientViewModel getPatientViewModel() {
        return patientViewModel;
    }
}
