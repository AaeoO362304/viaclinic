package client.view.login;

import client.viewModel.login.CreateAccountViewModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

import java.time.LocalDate;


public class RegisterWindowController {

    @FXML private Text firstName;
    @FXML private Text lastName;
    @FXML private Text gender;
    @FXML private Text birthday;
    @FXML private Text phoneNumber;
    @FXML private Text eMail;
    @FXML private Text password;
    @FXML private Text cprNumber;

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField phoneNumberField;
    @FXML private TextField eMailField;
    @FXML private PasswordField passwordField;
    @FXML private TextField cprNumberField;
    @FXML private TextField usernameTextField;

    @FXML private DatePicker birthdayPicker;

    @FXML private ComboBox<String> genderChoiceBox;

    @FXML private Button registerButton;
    @FXML private Button cancelButton;

    @FXML private Label errorLabel;

    private Region root;
    private CreateAccountViewModel viewModel;
    private ViewHandler viewHandler;

    public RegisterWindowController() {}

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

    @FXML private void cancelButtonPressed() {viewHandler.openView("main");}

    public void reset()
    {
        viewModel.clear();
    }

    public Region getRoot()
    {
        return root;
    }

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
