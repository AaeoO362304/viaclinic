package client.view.doctor;

import client.view.login.ViewHandler;
import client.viewModel.login.DoctorViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import shared.SessionDTO;

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
    private SessionDTO currentSession;


    public void init(ViewHandler viewHandler,
                     DoctorViewModel viewModel, Region root, SessionDTO currentSession){
        this.viewModel = viewModel;
        this.viewHandler = viewHandler;
        this.root = root;
        this.currentSession=currentSession;

        setLabel(currentSession);
    }

    public void logOutButtonPressed() {
        viewModel.getLoginViewModel().logout();
        viewHandler.setCurrentSession(null);
        viewHandler.openView("main");
    }

    public void closeButtonPressed() {
        viewModel.getLoginViewModel().logout();
        viewHandler.setCurrentSession(null);
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

    private void setLabel(SessionDTO session)
    {
        doctorLabel.setText("Doctor: " + (session == null ? "" : session.getDisplayName()));
    }

    public void reset()
    {
        viewModel.clear();
        setLabel(viewHandler.getCurrentSession());
    }

    public Region getRoot()
    {
        return root;
    }
}
