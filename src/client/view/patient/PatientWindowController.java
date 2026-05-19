package client.view.patient;

import client.view.login.ViewHandler;
import client.viewModel.login.DoctorViewModel;
import client.viewModel.login.PatientViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import server.model.auth.LoginAuthenticator;
import server.model.auth.LoginProxy;
import server.model.auth.Session;
import server.model.bookAppointment.Patient;

public class PatientWindowController {

    @FXML private Button logOutButton;
    @FXML private Button closeButton;
    @FXML private Button chatButton;
    @FXML private Button bookButton;
    @FXML private Button appointmentButton;
    @FXML private Button spontaneousButton;
    @FXML private Button profileButton;

    @FXML private Label patientLabel;

    private Region root;
    private PatientViewModel viewModel;
    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler,
                     PatientViewModel viewModel, Region root, Session currentSession){
        this.viewModel = viewModel;
        this.viewHandler = viewHandler;
        this.root = root;

        patientLabel.setText("Patient: " + currentSession.getDisplayName());
    }

    public void logOutButtonPressed() {
        viewModel.getLoginViewModel().getAuthService().logout();
        viewHandler.openView("main");
    }

    public void closeButtonPressed() {
        viewHandler.closeView();
    }

    public void myAppointmentsButtonPressed() {
        viewHandler.openView("my_appointments");
    }

    public void bookAppointmentButtonPressed() {
        viewHandler.openView("book_appointment");
    }

    public void spontaneousVisitButtonPressed() {
        viewHandler.openView("spontaneous_visit");
    }

    public void profileButtonPressed() {
        viewHandler.openView("profile");
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
