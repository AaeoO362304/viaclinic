package client.view.receptionist;

import client.view.login.ViewHandler;
import client.viewModel.login.ReceptionistViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import shared.SessionDTO;

/**
 * Controller for the Receptionist Window view.
 * Reacts to button clicks and other input in this window and calls the view model to do the work.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class ReceptionistWindowController {

    /** The log out button. */
    @FXML private Button logOutButton;
    @FXML private Button closeButton;
    @FXML private Button bookButton;
    @FXML private Button patientsButton;
    @FXML private Button appointmentButton;
    @FXML private Button allAppointmentsButton;

    /** The receptionist label. */
    @FXML private Label receptionistLabel;

    /** The root. */
    private Region root;
    /** The view model. */
    private ReceptionistViewModel viewModel;
    /** The view handler. */
    private ViewHandler viewHandler;

    /**
     * Sets up the controller with its view handler, view model and root.
     *
     * @param viewHandler the view handler
     * @param viewModel the view model
     * @param root the root
     * @param currentSession the current session
     */
    public void init(ViewHandler viewHandler,
                     ReceptionistViewModel viewModel, Region root, SessionDTO currentSession){
        this.viewModel = viewModel;
        this.viewHandler = viewHandler;
        this.root = root;

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
     * Handles the book appointment button being pressed in the view.
     */
    public void bookAppointmentButtonPressed() {
        viewHandler.openView("receptionist_book_appointment");
    }

    /**
     * Handles the today appointments button being pressed in the view.
     */
    public void todayAppointmentsButtonPressed() {
        viewHandler.openView("receptionist_today_appointments");
    }

    /**
     * Handles the all appointments button being pressed in the view.
     */
    public void allAppointmentsButtonPressed() {
        viewHandler.openView("all_appointments");
    }

    /**
     * Handles the registered patients button being pressed in the view.
     */
    public void registeredPatientsButtonPressed() {
        viewHandler.openView("receptionist_registered_patients");
    }

    /**
     * Sets the label.
     *
     * @param session the session
     */
    private void setLabel(SessionDTO session)
    {
        receptionistLabel.setText("Receptionist: " + (session == null ? "" : session.getDisplayName()));
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
