package client.view.doctor;

import client.view.login.ViewHandler;
import client.viewModel.login.DoctorViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import shared.SessionDTO;

/**
 * Controller for the Doctor Window view.
 * Reacts to button clicks and other input in this window and calls the view model to do the work.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class DoctorWindowController {

    /** The log out button. */
    @FXML private Button logOutButton;
    @FXML private Button closeButton;
    @FXML private Button chatButton;
    @FXML private Button patientsButton;
    @FXML private Button appointmentButton;

    /** The doctor label. */
    @FXML private Label doctorLabel;

    /** The root. */
    private Region root;
    /** The view model. */
    private DoctorViewModel viewModel;
    /** The view handler. */
    private ViewHandler viewHandler;
    /** The current session. */
    private SessionDTO currentSession;


    /**
     * Sets up the controller with its view handler, view model and root.
     *
     * @param viewHandler the view handler
     * @param viewModel the view model
     * @param root the root
     * @param currentSession the current session
     */
    public void init(ViewHandler viewHandler,
                     DoctorViewModel viewModel, Region root, SessionDTO currentSession){
        this.viewModel = viewModel;
        this.viewHandler = viewHandler;
        this.root = root;
        this.currentSession=currentSession;

        setLabel(currentSession);
    }

    /**
     * Handles the log out button being pressed in the view.
     */
    public void logOutButtonPressed() {
        viewModel.getLoginViewModel().logout();
        viewHandler.setCurrentSession(null);
        viewHandler.openView("main");
    }

    /**
     * Handles the close button being pressed in the view.
     */
    public void closeButtonPressed() {
        viewModel.getLoginViewModel().logout();
        viewHandler.setCurrentSession(null);
        viewHandler.closeView();
    }

    /**
     * Handles the registered patients button being pressed in the view.
     */
    public void registeredPatientsButtonPressed() {
        viewHandler.openView("registered_patients");
    }

    /**
     * Handles the today appointments button being pressed in the view.
     */
    public void todayAppointmentsButtonPressed() {
        viewHandler.openView("today_appointments");
    }

    /**
     * Handles the chat button being pressed in the view.
     */
    public void chatButtonPressed() {
        viewHandler.openView("chat");
    }

    /**
     * Sets the label.
     *
     * @param session the session
     */
    private void setLabel(SessionDTO session)
    {
        doctorLabel.setText("Doctor: " + (session == null ? "" : session.getDisplayName()));
    }

    /**
     * Clears the input fields and puts the window back to the start.
     */
    public void reset()
    {
        viewModel.clear();
        setLabel(viewHandler.getCurrentSession());
    }

    /**
     * Returns the root of this window.
     *
     * @return the root
     */
    public Region getRoot()
    {
        return root;
    }
}
