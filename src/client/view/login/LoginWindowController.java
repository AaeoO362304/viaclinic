package client.view.login;

import client.viewModel.login.LoginViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import shared.RoleDTO;

/**
 * Controller for the Login Window view.
 * Reacts to button clicks and other input in this window and calls the view model to do the work.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class LoginWindowController {
    /** The id text. */
    @FXML private Text idText;
    @FXML private Text passwordText;

    /** The id text field. */
    @FXML private TextField idTextField;
    @FXML private PasswordField passwordTextField;

    /** The login button. */
    @FXML private Button loginButton;
    @FXML private Button cancelButton;

    /** The error label. */
    @FXML private Label errorLabel;

    /** The patient pane. */
    @FXML private AnchorPane patientPane;

    /** The root. */
    private Region root;
    /** The view model. */
    private LoginViewModel viewModel;
    /** The view handler. */
    private ViewHandler viewHandler;

    /**
     * Creates a new {@code LoginWindowController} instance.
     */
    public LoginWindowController() {
    }

    /**
     * Sets up the controller with its view handler, view model and root.
     *
     * @param viewHandler the view handler
     * @param viewModel the view model
     * @param root the root
     */
    public void init(ViewHandler viewHandler,
                     LoginViewModel viewModel, Region root)
    {
        this.viewModel = viewModel;
        this.viewHandler = viewHandler;
        this.root = root;


        idTextField.textProperty().bindBidirectional(viewModel.getUsernameProperty());
        passwordTextField.textProperty().bindBidirectional(viewModel.getPasswordProperty());
        errorLabel.textProperty().bind(viewModel.getErrorProperty());
    }

    /**
     * Handles the login button being pressed in the view.
     */
    @FXML
    private void loginButtonPressed()
    {
        RoleDTO role = viewModel.login();

        if (role!=null)
        {
            viewHandler.setCurrentSession(viewModel.getCurrentSession());

            if (role == RoleDTO.PATIENT)
            {
                viewHandler.openView("patient");
            }
            else if (role == RoleDTO.DOCTOR)
            {
                viewHandler.openView("doctor");
            }
            else if (role == RoleDTO.RECEPTIONIST)
            {
                viewHandler.openView("receptionist");
            }
        }
    }

    /**
     * Handles the cancel button being pressed in the view.
     */
    @FXML
    private void cancelButtonPressed()
    {
        viewHandler.openView("main");
    }

    /**
     * Handles the Enter key being pressed.
     *
     * @param actionEvent the action event
     */
    @FXML
    private void onEnter(ActionEvent actionEvent)
    {
        if (actionEvent.getSource() == idTextField)
        {
            passwordTextField.requestFocus();
        }
        else
        {
            loginButtonPressed();
        }
    }

    /**
     * Clears the input fields and puts the window back to the start.
     */
    public void reset()
    {
        viewModel.clear();
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
