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
import server.model.auth.Role;
import server.model.auth.Session;

public class LoginWindowController {
    @FXML private Text idText;
    @FXML private Text passwordText;

    @FXML private TextField idTextField;
    @FXML private PasswordField passwordTextField;

    @FXML private Button loginButton;
    @FXML private Button cancelButton;

    @FXML private Label errorLabel;

    @FXML private AnchorPane patientPane;

    private Region root;
    private LoginViewModel viewModel;
    private ViewHandler viewHandler;

    public LoginWindowController() {
    }

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

    @FXML
    private void loginButtonPressed()
    {
        Role role = viewModel.login();

        if (role!=null)
        {
            viewHandler.setCurrentSession(viewModel.getCurrentSession());

            if (role == Role.PATIENT)
            {
                viewHandler.openView("patient");
            }
            else if (role == Role.DOCTOR)
            {
                viewHandler.openView("doctor");
            }
            else if (role == Role.RECEPTIONIST)
            {
                viewHandler.openView("receptionist");
            }
        }
    }

    @FXML
    private void cancelButtonPressed()
    {
        viewHandler.openView("main");
    }

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

    public void reset()
    {
        viewModel.clear();
    }

    public Region getRoot()
    {
        return root;
    }
}
