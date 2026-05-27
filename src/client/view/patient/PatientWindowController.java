package client.view.patient;

import client.view.ViewHandler;
import client.viewModel.patient.PatientViewModel;
import shared.SessionDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * Controller for the Patient Window view.
 * Reacts to button clicks and other input in this window and calls the view model to do the work.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class PatientWindowController {

    /** The log out button. */
    @FXML private Button logOutButton;
    @FXML private Button closeButton;
    @FXML private Button chatButton;
    @FXML private Button bookButton;
    @FXML private Button appointmentButton;
    @FXML private Button spontaneousButton;
    @FXML private Button profileButton;

    /** The patient label. */
    @FXML private Label patientLabel;

    /** The root. */
    private Region root;
    /** The view model. */
    private PatientViewModel viewModel;
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
                     PatientViewModel viewModel, Region root, SessionDTO currentSession){
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
        viewHandler.closeView();
    }

    /**
     * Handles the my appointments button being pressed in the view.
     */
    public void myAppointmentsButtonPressed() {
        viewHandler.openView("my_appointments");
    }

    /**
     * Handles the book appointment button being pressed in the view.
     */
    public void bookAppointmentButtonPressed() {
        viewHandler.openView("book_appointment");
    }

    /**
     * Handles the spontaneous visit button being pressed in the view.
     */
    public void spontaneousVisitButtonPressed() {
        viewHandler.openView("spontaneous_visit");
    }

    /**
     * Handles the profile button being pressed in the view.
     */
    public void profileButtonPressed() {
        viewHandler.openView("profile");
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
        patientLabel.setText("Patient: " + (session == null ? "" : session.getDisplayName()));
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
