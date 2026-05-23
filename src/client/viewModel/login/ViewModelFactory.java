package client.viewModel.login;

import client.model.ClinicClient;
import client.viewModel.appointment.BookAppointmentViewModel;
import client.viewModel.appointment.EditAppointmentViewModel;
import client.viewModel.appointment.MyAppointmentViewModel;
import client.viewModel.doctor.*;
import client.viewModel.doctor.ReceptionistEditPatientViewModel;
import client.viewModel.doctor.ReceptionistRegisteredPatientViewModel;
import client.viewModel.patient.EditPatientViewModel;
import shared.AppointmentDTO;

public class ViewModelFactory {
    private final CreateAccountViewModel createAccountViewModel;
    private final LoginViewModel loginViewModel;
    private final MainViewModel mainViewModel;
    private final PatientViewModel patientViewModel;
    private final DoctorViewModel doctorViewModel;
    private final ReceptionistViewModel receptionistViewModel;
    private final BookAppointmentViewModel bookAppointmentViewModel;
    private final MyAppointmentViewModel myAppointmentViewModel;
    private final EditAppointmentViewModel editAppointmentViewModel;
    private final EditPatientViewModel editPatientViewModel;
    private final TodayAppointmentsViewModel todayAppointmentsViewModel;
    private final DoctorEditAppointmentViewModel doctorEditAppointmentViewModel;
    private final RegisteredPatientViewModel registeredPatientViewModel;
    private final ReceptionistRegisteredPatientViewModel receptionistRegisteredPatientViewModel;
    private final ReceptionistEditPatientViewModel receptionistEditPatientViewModel;

    public ViewModelFactory(ClinicClient client) {
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
    }

    public CreateAccountViewModel getCreateAccountViewModel() { return createAccountViewModel; }
    public LoginViewModel getLoginViewModel() { return loginViewModel; }
    public DoctorViewModel getDoctorViewModel() { return doctorViewModel; }
    public MainViewModel getMainViewModel() { return mainViewModel; }
    public PatientViewModel getPatientViewModel() { return patientViewModel; }
    public ReceptionistViewModel getReceptionistViewModel() { return receptionistViewModel; }
    public BookAppointmentViewModel getBookAppointmentViewModel() { return bookAppointmentViewModel; }
    public MyAppointmentViewModel getMyAppointmentViewModel() { return myAppointmentViewModel; }
    public EditAppointmentViewModel getEditAppointmentViewModel() { return editAppointmentViewModel; }
    public EditPatientViewModel getEditPatientViewModel() {
        return editPatientViewModel;
    }
    public TodayAppointmentsViewModel getTodayAppointmentsViewModel() {
        return todayAppointmentsViewModel;
    }
    public DoctorEditAppointmentViewModel getDoctorEditAppointmentViewModel() {
        return doctorEditAppointmentViewModel;
    }
    public RegisteredPatientViewModel getRegisteredPatientViewModel() {
        return registeredPatientViewModel;
    }
    public ReceptionistRegisteredPatientViewModel getReceptionistRegisteredPatientViewModel() {
        return receptionistRegisteredPatientViewModel;
    }
    public ReceptionistEditPatientViewModel getReceptionistEditPatientViewModel() {
        return receptionistEditPatientViewModel;
    }
}
