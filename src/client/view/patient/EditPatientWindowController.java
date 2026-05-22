package client.view.patient;


import client.view.login.ViewHandler;
import client.viewModel.login.CreateAccountViewModel;
import client.viewModel.patient.EditPatientViewModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import shared.PatientDTO;

import java.util.ArrayList;

public class EditPatientWindowController {
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

    @FXML private Button editButton;
    @FXML private Button confirmButton;
    @FXML private Button cancelButton;

    @FXML private Label errorLabel;

    private Region root;
    private EditPatientViewModel viewModel;
    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler,
                     EditPatientViewModel viewModel, Region root)
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

        firstNameField.setEditable(false);
        lastNameField.setEditable(false);
        passwordField.setEditable(false);
        eMailField.setEditable(false);
        phoneNumberField.setEditable(false);
        cprNumberField.setEditable(false);
        usernameTextField.setEditable(false);

        genderChoiceBox.setEditable(false);
        birthdayPicker.setEditable(false);

        confirmButton.setVisible(false);

        PatientDTO patient = viewModel.getPatientById(viewModel.getPatientViewModel().getLoginViewModel().getCurrentSession().getUserId()) ;

        firstNameField.setText(patient.getFirstName());
        lastNameField.setText(patient.getLastName());
        passwordField.setText(patient.getPassword());
        eMailField.setText(patient.getEmail());
        phoneNumberField.setText(patient.getPhoneNumber());
        cprNumberField.setText(patient.getCPR());
        usernameTextField.setText(patient.getUserName());

        genderChoiceBox.setValue(patient.getGender());
        birthdayPicker.setValue(patient.getDayOfBirth());


    }

    public void editButtonPressed() {
        editButton.setVisible(false);
        confirmButton.setVisible(true);

        firstNameField.setEditable(true);
        lastNameField.setEditable(true);
        passwordField.setEditable(true);
        eMailField.setEditable(true);
        phoneNumberField.setEditable(true);
        cprNumberField.setEditable(true);
        usernameTextField.setEditable(true);

        genderChoiceBox.setEditable(true);
        birthdayPicker.setEditable(true);
    }

    public void cancelButtonPressed() {
        firstNameField.setEditable(false);
        lastNameField.setEditable(false);
        passwordField.setEditable(false);
        eMailField.setEditable(false);
        phoneNumberField.setEditable(false);
        cprNumberField.setEditable(false);
        usernameTextField.setEditable(false);

        genderChoiceBox.setEditable(false);
        birthdayPicker.setEditable(false);

        confirmButton.setVisible(false);
        editButton.setVisible(true);

        viewHandler.openView("patient");
    }

}
