package client.view.doctor;

import client.view.login.ViewHandler;
import client.viewModel.login.DoctorViewModel;
import client.viewModel.login.MainViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import server.model.auth.Session;
import server.model.bookAppointment.DoctorDAO;

public class DoctorWindowController {

    @FXML private Button logOutButton;
    @FXML private Button closeButton;
    @FXML private Button chatButton;
    @FXML private Button patientsButton;
    @FXML private Button appointmentButton;

    @FXML private Label doctorLabel;

    private Region root;
    private DoctorViewModel viewModel;
    private ViewHandler viewHandler;
    private Session currentSession;


    public void init(ViewHandler viewHandler,
                     DoctorViewModel viewModel, Region root, Session currentSession){
        this.viewModel = viewModel;
        this.viewHandler = viewHandler;
        this.root = root;
        this.currentSession=currentSession;

        doctorLabel.setText("Doctor: "+currentSession.getDisplayName());
    }

    public void logOutButtonPressed() {
        viewModel.getLoginViewModel().getAuthService().logout();
        viewHandler.openView("main");
    }

    public void closeButtonPressed() {
        viewModel.getLoginViewModel().getAuthService().logout();
        viewHandler.closeView();
    }

    public void registeredPatientsButtonPressed() {
        viewHandler.openView("registered_patients");
    }

    public void todayAppointmentsButtonPressed() {
        viewHandler.openView("today_appointments");
    }

    public void chatButtonPressed() {
        viewHandler.openView("chat");
    }

    public void reset()
    {
        viewModel.clear();
    }

    public Region getRoot()
    {
        return root;
    }
}
