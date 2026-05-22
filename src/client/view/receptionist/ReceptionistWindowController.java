package client.view.receptionist;

import client.view.login.ViewHandler;
import client.viewModel.login.ReceptionistViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import shared.SessionDTO;

public class ReceptionistWindowController {

    @FXML private Button logOutButton;
    @FXML private Button closeButton;
    @FXML private Button bookButton;
    @FXML private Button patientsButton;
    @FXML private Button appointmentButton;
    @FXML private Button allAppointmentsButton;

    @FXML private Label receptionistLabel;

    private Region root;
    private ReceptionistViewModel viewModel;
    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler,
                     ReceptionistViewModel viewModel, Region root, SessionDTO currentSession){
        this.viewModel = viewModel;
        this.viewHandler = viewHandler;
        this.root = root;

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

    public void bookAppointmentButtonPressed() {
        viewHandler.openView("receptionist_book_appointment");
    }

    public void todayAppointmentsButtonPressed() {
        viewHandler.openView("today_appointments");
    }

    public void allAppointmentsButtonPressed() {
        viewHandler.openView("all_appointments");
    }

    public void registeredPatientsButtonPressed() {
        viewHandler.openView("registered_patients");
    }

    private void setLabel(SessionDTO session)
    {
        receptionistLabel.setText("Receptionist: " + (session == null ? "" : session.getDisplayName()));
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
