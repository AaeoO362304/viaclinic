package client.viewModel.doctor;

import client.model.ClinicClient;
import client.viewModel.login.PatientViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.PatientDTO;
import shared.SessionDTO;

import java.time.LocalDate;

public class ReceptionistEditPatientViewModel {
    private final ClinicClient client;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty password;
    private final StringProperty email;
    private final StringProperty error;
    private final StringProperty gender;
    private final StringProperty phoneNum;
    private final StringProperty CPR;
    private final StringProperty userName;
    private LocalDate dayOfBirth;
    private int patientId;

    private PatientViewModel patientViewModel;

    public ReceptionistEditPatientViewModel(ClinicClient client, PatientViewModel patientViewModel) {
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

    public void deletePatient(int patientId) throws Exception {
        client.deletePatient(patientId);
    }

    public PatientDTO getPatientById(int patientId) throws Exception {
        SessionDTO session = patientViewModel.getLoginViewModel().getCurrentSession();
        if (session == null || session.getUserId() <= 0) {
            return new PatientDTO();
        }
        return client.getPatientById(patientId);
    }

    public void loadPatient(PatientDTO patient)
    {
        error.set("");

        try
        {
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

    public StringProperty getFirstNameProperty() { return firstName; }
    public StringProperty getLastNameProperty() { return lastName; }
    public StringProperty getPasswordProperty() { return password; }
    public StringProperty getEmailProperty() { return email; }
    public StringProperty getGenderProperty() { return gender; }
    public StringProperty getPhoneNumProperty() { return phoneNum; }
    public StringProperty getUserNameProperty() { return userName; }
    public StringProperty getCPRProperty() { return CPR; }
    public StringProperty getErrorProperty() { return error; }
    public LocalDate getDayOfBirth() {return dayOfBirth;}
    public void setDayOfBirth(LocalDate dayOfBirth) { this.dayOfBirth = dayOfBirth; }

    public PatientViewModel getPatientViewModel() {
        return patientViewModel;
    }
}
