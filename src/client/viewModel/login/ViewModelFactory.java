package client.viewModel.login;

import client.viewModel.appointment.BookAppointmentViewModel;
import client.viewModel.appointment.MyAppointmentViewModel;
import server.model.bookAppointment.UserHandler;

public class ViewModelFactory {
    private CreateAccountViewModel createAccountViewModel;
    private LoginViewModel loginViewModel;
    private MainViewModel mainViewModel;
    private PatientViewModel patientViewModel;
    private DoctorViewModel doctorViewModel;
    private ReceptionistViewModel receptionistViewModel;
    private BookAppointmentViewModel bookAppointmentViewModel;
    private MyAppointmentViewModel myAppointmentViewModel;

    public ViewModelFactory(UserHandler handler)
    {
        createAccountViewModel=new CreateAccountViewModel(handler);
        loginViewModel = new LoginViewModel(handler);
        mainViewModel = new MainViewModel();
        patientViewModel = new PatientViewModel(handler, loginViewModel);
        doctorViewModel = new DoctorViewModel(handler, loginViewModel);
        receptionistViewModel = new ReceptionistViewModel(handler, loginViewModel);
        bookAppointmentViewModel = new BookAppointmentViewModel(handler, patientViewModel, doctorViewModel);
        myAppointmentViewModel = new MyAppointmentViewModel(handler, patientViewModel);
    }

    public CreateAccountViewModel getCreateAccountViewModel() {
        return createAccountViewModel;
    }

    public LoginViewModel getLoginViewModel() {
        return loginViewModel;
    }

    public DoctorViewModel getDoctorViewModel() {
        return doctorViewModel;
    }

    public MainViewModel getMainViewModel() {
        return mainViewModel;
    }

    public PatientViewModel getPatientViewModel() {
        return patientViewModel;
    }

    public ReceptionistViewModel getReceptionistViewModel() {
        return receptionistViewModel;
    }

    public BookAppointmentViewModel getBookAppointmentViewModel() {
        return bookAppointmentViewModel;
    }

    public MyAppointmentViewModel getMyAppointmentViewModel() {
        return myAppointmentViewModel;
    }
}
