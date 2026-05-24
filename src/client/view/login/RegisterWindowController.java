package client.view.login;

import client.viewModel.login.CreateAccountViewModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

import java.time.LocalDate;


/**
 * Controller for the Register Window view.
 * Reacts to button clicks and other input in this window and calls the view model to do the work.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class RegisterWindowController {

    /** The first name. */
    @FXML private Text firstName;
    @FXML private Text lastName;
    @FXML private Text gender;
    @FXML private Text birthday;
    @FXML private Text phoneNumber;
    @FXML private Text eMail;
    @FXML private Text password;
    @FXML private Text cprNumber;

    /** The first name field. */
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField phoneNumberField;
    @FXML private TextField eMailField;
    @FXML private PasswordField passwordField;
    @FXML private TextField cprNumberField;
    @FXML private TextField usernameTextField;

    /** The birthday picker. */
    @FXML private DatePicker birthdayPicker;

    /** The gender choice box. */
    @FXML private ComboBox<String> genderChoiceBox;

    /** The register button. */
    @FXML private Button registerButton;
    @FXML private Button cancelButton;

    /** The error label. */
    @FXML private Label errorLabel;

    /** The root. */
    private Region root;
    /** The view model. */
    private CreateAccountViewModel viewModel;
    /** The view handler. */
    private ViewHandler viewHandler;

    /**
     * Creates a new {@code RegisterWindowController} instance.
     */
    public RegisterWindowController() {}

    /**
     * Sets up the controller with its view handler, view model and root.
     *
     * @param viewHandler the view handler
     * @param viewModel the view model
     * @param root the root
     */
    public void init(ViewHandler viewHandler,
                     CreateAccountViewModel viewModel, Region root)
    {
        this.viewModel = viewModel;
        this.viewHandler = viewHandler;
        this.root = root;

        firstNameField.textProperty().bindBidirectional(viewModel.getFirstNameProperty());
        lastNameField.textProperty().bindBidirectional(viewModel.getLastNameProperty());
        passwordField.textProperty().bindBidirectional(viewModel.getPasswordProperty());
        eMailField.textProperty().bindBidirectional(viewModel.getEmailProperty());
        phoneNumberField.textProperty().bindBidirectional(viewModel.getPhoneNumProperty());
        cprNumberField.textProperty().bindBidirectional(viewModel.getCPRProperty());
        usernameTextField.textProperty().bindBidirectional(viewModel.getUserNameProperty());

        genderChoiceBox.setItems(FXCollections.observableArrayList("Male", "Female", "Divers"));
        genderChoiceBox.valueProperty().bindBidirectional(viewModel.getGenderProperty());

        errorLabel.textProperty().bind(viewModel.getErrorProperty());
    }

    /**
     * Handles the register button being pressed in the view.
     */
    @FXML
    private void registerButtonPressed()
    {
        viewModel.setDayOfBirth(birthdayPicker.getValue());

        boolean created = viewModel.createPatient();

        if (created)
        {
            viewHandler.openView("main");
        }
    }

    /**
     * Handles the cancel button being pressed in the view.
     */
    @FXML private void cancelButtonPressed() {viewHandler.openView("main");}

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

    /**
     * Handles the Enter key being pressed.
     *
     * @param actionEvent the action event
     */
    @FXML private void onEnter(ActionEvent actionEvent)
    {
        if (actionEvent.getSource() == firstNameField)
        {
            passwordField.requestFocus();
        }
        else if (actionEvent.getSource() == passwordField)
        {
            eMailField.requestFocus();
        }
        else
        {
            registerButtonPressed();
        }
    }


}
