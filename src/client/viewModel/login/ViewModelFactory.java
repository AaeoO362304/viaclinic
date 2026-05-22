package client.viewModel.login;

import client.model.ClinicClient;
import client.viewModel.appointment.BookAppointmentViewModel;
import client.viewModel.appointment.EditAppointmentViewModel;
import client.viewModel.appointment.MyAppointmentViewModel;
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
}
