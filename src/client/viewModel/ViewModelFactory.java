package client.viewModel;

import client.model.ClinicClient;
import client.viewModel.appointment.BookAppointmentViewModel;
import client.viewModel.appointment.EditAppointmentViewModel;
import client.viewModel.appointment.MyAppointmentViewModel;
import client.viewModel.doctor.*;
import client.viewModel.login.*;
import client.viewModel.patient.EditPatientViewModel;
import client.viewModel.patient.PatientViewModel;
import client.viewModel.patient.SpontaneousAppointmentViewModel;
import client.viewModel.receptionist.*;
import client.viewModel.receptionist.ReceptionistRegisteredPatientViewModel;
import shared.AppointmentDTO;

/**
 * Creates the view models and keeps one copy of each.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class ViewModelFactory {
    /** The client, kept so windows like the chat window can use it. */
    private final ClinicClient client;
    /** The create account view model. */
    private final CreateAccountViewModel createAccountViewModel;
    /** The login view model. */
    private final LoginViewModel loginViewModel;
    /** The main view model. */
    private final MainViewModel mainViewModel;
    /** The patient view model. */
    private final PatientViewModel patientViewModel;
    /** The doctor view model. */
    private final DoctorViewModel doctorViewModel;
    /** The receptionist view model. */
    private final ReceptionistViewModel receptionistViewModel;
    /** The book appointment view model. */
    private final BookAppointmentViewModel bookAppointmentViewModel;
    /** The my appointment view model. */
    private final MyAppointmentViewModel myAppointmentViewModel;
    /** The edit appointment view model. */
    private final EditAppointmentViewModel editAppointmentViewModel;
    /** The edit patient view model. */
    private final EditPatientViewModel editPatientViewModel;
    /** The today appointments view model. */
    private final TodayAppointmentsViewModel todayAppointmentsViewModel;
    /** The doctor edit appointment view model. */
    private final DoctorEditAppointmentViewModel doctorEditAppointmentViewModel;
    /** The registered patient view model. */
    private final RegisteredPatientViewModel registeredPatientViewModel;
    /** The receptionist registered patient view model. */
    private final ReceptionistRegisteredPatientViewModel receptionistRegisteredPatientViewModel;
    /** The receptionist edit patient view model. */
    private final ReceptionistEditPatientViewModel receptionistEditPatientViewModel;
    /** The receptionist today appointments view model. */
    private final ReceptionistTodayAppointmentsViewModel receptionistTodayAppointmentsViewModel;
    /** The receptionist all appointments view model. */
    private final ReceptionistAllAppointmentsViewModel receptionistAllAppointmentsViewModel;
    /** The receptionist edit appointment view model. */
    private final ReceptionistEditAppointmentViewModel receptionistEditAppointmentViewModel;
    /** The receptionist book appointment view model. */
    private final ReceptionistBookAppointmentViewModel receptionistBookAppointmentViewModel;
    /** The spontaneous appointment view model. */
    private final SpontaneousAppointmentViewModel spontaneousAppointmentViewModel;

    /**
     * Creates a new {@code ViewModelFactory} initialised with the given client.
     *
     * @param client the client
     */
    public ViewModelFactory(ClinicClient client) {
        this.client = client;
        createAccountViewModel = new CreateAccountViewModel(client);
        loginViewModel = new LoginViewModel(client);
        mainViewModel = new MainViewModel();
        patientViewModel = new PatientViewModel(loginViewModel);
        doctorViewModel = new DoctorViewModel(loginViewModel);
        receptionistViewModel = new ReceptionistViewModel(loginViewModel);
        bookAppointmentViewModel = new BookAppointmentViewModel(client, patientViewModel, doctorViewModel);
        myAppointmentViewModel = new MyAppointmentViewModel(client, patientViewModel);
        editAppointmentViewModel = new EditAppointmentViewModel(client, new AppointmentDTO());
        editPatientViewModel = new EditPatientViewModel(client, patientViewModel);
        todayAppointmentsViewModel = new TodayAppointmentsViewModel(client, doctorViewModel);
        doctorEditAppointmentViewModel = new DoctorEditAppointmentViewModel(client, new AppointmentDTO());
        registeredPatientViewModel = new RegisteredPatientViewModel(client, patientViewModel);
        receptionistRegisteredPatientViewModel = new ReceptionistRegisteredPatientViewModel(client, patientViewModel);
        receptionistEditPatientViewModel = new ReceptionistEditPatientViewModel(client, patientViewModel);
        receptionistTodayAppointmentsViewModel = new ReceptionistTodayAppointmentsViewModel(client);
        receptionistAllAppointmentsViewModel = new ReceptionistAllAppointmentsViewModel(client);
        receptionistEditAppointmentViewModel = new ReceptionistEditAppointmentViewModel(client, new AppointmentDTO());
        receptionistBookAppointmentViewModel = new ReceptionistBookAppointmentViewModel(client, patientViewModel, doctorViewModel);
        spontaneousAppointmentViewModel = new SpontaneousAppointmentViewModel(client);
    }

    /**
     * Returns the create account view model.
     *
     * @return the create account view model
     */
    public CreateAccountViewModel getCreateAccountViewModel() { return createAccountViewModel; }
    /**
     * Returns the login view model.
     *
     * @return the login view model
     */
    public LoginViewModel getLoginViewModel() { return loginViewModel; }
    /**
     * Returns the doctor view model.
     *
     * @return the doctor view model
     */
    public DoctorViewModel getDoctorViewModel() { return doctorViewModel; }
    /**
     * Returns the main view model.
     *
     * @return the main view model
     */
    public MainViewModel getMainViewModel() { return mainViewModel; }
    /**
     * Returns the patient view model.
     *
     * @return the patient view model
     */
    public PatientViewModel getPatientViewModel() { return patientViewModel; }
    /**
     * Returns the receptionist view model.
     *
     * @return the receptionist view model
     */
    public ReceptionistViewModel getReceptionistViewModel() { return receptionistViewModel; }
    /**
     * Returns the book appointment view model.
     *
     * @return the book appointment view model
     */
    public BookAppointmentViewModel getBookAppointmentViewModel() { return bookAppointmentViewModel; }
    /**
     * Returns the MyAppointment view model.
     *
     * @return the MyAppointment view model
     */
    public MyAppointmentViewModel getMyAppointmentViewModel() { return myAppointmentViewModel; }
    /**
     * Returns the edit appointment view model.
     *
     * @return the edit appointment view model
     */
    public EditAppointmentViewModel getEditAppointmentViewModel() { return editAppointmentViewModel; }
    /**
     * Returns the edit patient view model.
     *
     * @return the edit patient view model
     */
    public EditPatientViewModel getEditPatientViewModel() {
        return editPatientViewModel;
    }
    /**
     * Returns the today appointments view model.
     *
     * @return the today appointments view model
     */
    public TodayAppointmentsViewModel getTodayAppointmentsViewModel() {
        return todayAppointmentsViewModel;
    }
    /**
     * Returns the doctor edit appointment view model.
     *
     * @return the doctor edit appointment view model
     */
    public DoctorEditAppointmentViewModel getDoctorEditAppointmentViewModel() {
        return doctorEditAppointmentViewModel;
    }
    /**
     * Returns the registered patient view model.
     *
     * @return the registered patient view model
     */
    public RegisteredPatientViewModel getRegisteredPatientViewModel() {
        return registeredPatientViewModel;
    }
    /**
     * Returns the receptionist registered patient view model.
     *
     * @return the receptionist registered patient view model
     */
    public ReceptionistRegisteredPatientViewModel getReceptionistRegisteredPatientViewModel() {return receptionistRegisteredPatientViewModel;}
    /**
     * Returns the receptionist edit patient view model.
     *
     * @return the receptionist edit patient view model
     */
    public ReceptionistEditPatientViewModel getReceptionistEditPatientViewModel() {return receptionistEditPatientViewModel;}
    /**
     * Returns the receptionist today appointments view model.
     *
     * @return the receptionist today appointments view model
     */
    public ReceptionistTodayAppointmentsViewModel getReceptionistTodayAppointmentsViewModel() {return receptionistTodayAppointmentsViewModel;}
    /**
     * Returns the receptionist all appointments view model.
     *
     * @return the receptionist all appointments view model
     */
    public ReceptionistAllAppointmentsViewModel getReceptionistAllAppointmentsViewModel() {return receptionistAllAppointmentsViewModel;}
    /**
     * Returns the receptionist edit appointment view model.
     *
     * @return the receptionist edit appointment view model
     */
    public ReceptionistEditAppointmentViewModel getReceptionistEditAppointmentViewModel() {return receptionistEditAppointmentViewModel;}
    /**
     * Returns the receptionist book appointment view model.
     *
     * @return the receptionist book appointment view model
     */
    public ReceptionistBookAppointmentViewModel getReceptionistBookAppointmentViewModel() {return receptionistBookAppointmentViewModel;}
    /**
     * Returns the spontaneous appointment view model.
     *
     * @return the spontaneous appointment view model
     */
    public SpontaneousAppointmentViewModel getSpontaneousAppointmentViewModel() {return spontaneousAppointmentViewModel;}

    /**
     * Returns the client.
     *
     * @return the client
     */
    public ClinicClient getClient() {
        return client;
    }
}
