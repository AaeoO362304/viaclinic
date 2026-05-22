package client.view.patient;


import client.view.login.ViewHandler;
import client.viewModel.login.CreateAccountViewModel;
import client.viewModel.patient.EditPatientViewModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import shared.PatientDTO;

import java.sql.SQLException;
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
                     EditPatientViewModel viewModel, Region root) throws Exception
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

        setEditable(false);

        confirmButton.setVisible(false);
        editButton.setVisible(true);

        viewModel.loadPatient();
        birthdayPicker.setValue(viewModel.getDayOfBirth());
    }

    private void setEditable(boolean editable)
    {
        firstNameField.setEditable(editable);
        lastNameField.setEditable(editable);
        passwordField.setEditable(editable);
        eMailField.setEditable(editable);
        phoneNumberField.setEditable(editable);
        cprNumberField.setEditable(editable);

        usernameTextField.setEditable(false);

        genderChoiceBox.setDisable(!editable);
        birthdayPicker.setDisable(!editable);
    }

    public void confirmButtonPressed() {
        viewModel.setDayOfBirth(birthdayPicker.getValue());

        boolean updated = viewModel.updatePatient();

        if (updated)
        {
            viewHandler.openView("patient");
        }
    }

    public void editButtonPressed()
    {
        editButton.setVisible(false);
        confirmButton.setVisible(true);

        setEditable(true);
    }

    public void cancelButtonPressed()
    {
        viewModel.loadPatient();
        birthdayPicker.setValue(viewModel.getDayOfBirth());

        setEditable(false);

        confirmButton.setVisible(false);
        editButton.setVisible(true);

        viewHandler.openView("patient");
    }

    public void reset()
    {
        viewModel.loadPatient();
        birthdayPicker.setValue(viewModel.getDayOfBirth());

        setEditable(false);

        confirmButton.setVisible(false);
        editButton.setVisible(true);
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
            confirmButtonPressed();
        }
    }

}
