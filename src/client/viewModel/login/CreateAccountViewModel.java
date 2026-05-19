package client.viewModel.login;

import server.model.bookAppointment.Patient;
import server.model.bookAppointment.PatientDAO;
import server.model.bookAppointment.UserHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class CreateAccountViewModel {
    private UserHandler handler;
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty password;
    private StringProperty email;
    private StringProperty error;
    private StringProperty gender;
    private StringProperty phoneNum;
    private StringProperty CPR;
    private LocalDate dayOfBirth;
    private StringProperty userName;

    public CreateAccountViewModel(UserHandler handler)
    {
        this.handler=handler;
        this.firstName=new SimpleStringProperty();
        this.lastName=new SimpleStringProperty();
        this.password=new SimpleStringProperty();
        this.email=new SimpleStringProperty();
        this.error=new SimpleStringProperty();
        this.gender=new SimpleStringProperty();
        this.phoneNum=new SimpleStringProperty();
        this.CPR= new SimpleStringProperty();
        this.userName=new SimpleStringProperty();
    }

    public void clear()
    {
        firstName.set("");
        lastName.set("");
        password.set("");
        email.set("");
        error.set("");
        gender.set("");
        phoneNum.set("");
        CPR.set("");
        userName.set("");
        dayOfBirth=null;
    }

    public boolean createPatient()
    {
        error.set("");

        try
        {
            if (firstName.get() == null || firstName.get().isBlank())
            {
                error.set("Please enter your first name.");
                return false;
            }

            if (lastName.get() == null || lastName.get().isBlank())
            {
                error.set("Please enter your last name.");
                return false;
            }

            if (gender.get() == null || gender.get().isBlank())
            {
                error.set("Please choose a gender.");
                return false;
            }

            if (dayOfBirth == null)
            {
                error.set("Please enter your birthday.");
                return false;
            }

            if (phoneNum.get() == null || phoneNum.get().isBlank())
            {
                error.set("Please enter your phone number.");
                return false;
            }

            if (email.get() == null || email.get().isBlank())
            {
                error.set("Please enter your email.");
                return false;
            }

            if (password.get() == null || password.get().isBlank())
            {
                error.set("Please enter your password.");
                return false;
            }

            if (CPR.get() == null || CPR.get().isBlank())
            {
                error.set("Please enter your CPR number.");
                return false;
            }

            if (userName.get() == null || userName.get().isBlank())
            {
                error.set("Please enter your Username number.");
                return false;
            }

            PatientDAO patientDAO = PatientDAO.getInstance();

            Patient patient = new Patient(firstName.get(),
                    lastName.get(),
                    password.get(),
                    email.get(),
                    gender.get(),
                    phoneNum.get(),
                    dayOfBirth,
                    userName.get(),
                    CPR.get());

            patientDAO.createPatient(patient);

            return true;
        }
        catch (Exception e)
        {
            error.set(e.getMessage() == null ? "Something went wrong." : e.getMessage());
            return false;
        }
    }

    public StringProperty getFirstNameProperty() {return firstName;}

    public StringProperty getLastNameProperty() {return lastName;}

    public StringProperty getPasswordProperty() {return password;}

    public StringProperty getEmailProperty()
    {
        return email;
    }

    public StringProperty getGenderProperty()
    {
        return gender;
    }

    public StringProperty getPhoneNumProperty()
    {
        return phoneNum;
    }

    public StringProperty getUserNameProperty() {
        return userName;
    }

    public StringProperty getCPRProperty()
    {
        return CPR;
    }

    public StringProperty getErrorProperty()
    {
        return error;
    }

    public void setDayOfBirth(LocalDate dayOfBirth)
    {
        this.dayOfBirth = dayOfBirth;
    }
}
