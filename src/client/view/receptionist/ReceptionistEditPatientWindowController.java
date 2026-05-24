package client.view.receptionist;

import client.view.login.ViewHandler;
import client.viewModel.receptionist.ReceptionistEditPatientViewModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import shared.PatientDTO;

/**
 * Controller for the Receptionist Edit Patient Window view.
 * Reacts to button clicks and other input in this window and calls the view model to do the work.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class ReceptionistEditPatientWindowController {
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

    /** The edit button. */
    @FXML private Button editButton;
    @FXML private Button confirmButton;
    @FXML private Button cancelButton;
    @FXML private Button deleteButton;

    /** The error label. */
    @FXML private Label errorLabel;

    /** The root. */
    private Region root;
    /** The view model. */
    private ReceptionistEditPatientViewModel viewModel;
    /** The view handler. */
    private ViewHandler viewHandler;
    /** The patient. */
    private PatientDTO patient;

    /**
     * Sets up the controller with its view handler, view model and root.
     *
     * @param viewHandler the view handler
     * @param viewModel the view model
     * @param root the root
     * @param patient the patient
     * @throws Exception if the operation cannot be completed
     */
    public void init(ViewHandler viewHandler,
                     ReceptionistEditPatientViewModel viewModel, Region root, PatientDTO patient) throws Exception
    {
        this.viewModel = viewModel;
        this.viewHandler = viewHandler;
        this.root = root;
        this.patient=patient;

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
        birthdayPicker.setDisable(true);
        confirmButton.setVisible(false);
        editButton.setVisible(true);

        viewModel.loadPatient(patient);
        birthdayPicker.setValue(viewModel.getDayOfBirth());
    }

    /**
     * Sets the editable.
     *
     * @param editable the editable
     */
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
    }

    /**
     * Handles the confirm button being pressed in the view.
     */
    public void confirmButtonPressed() {
        viewModel.setDayOfBirth(birthdayPicker.getValue());

        boolean updated = viewModel.updatePatient(patient.getPatientID());

        if (updated)
        {
            viewHandler.openView("receptionist");
        }
    }

    /**
     * Handles the edit button being pressed in the view.
     */
    public void editButtonPressed()
    {
        editButton.setVisible(false);
        confirmButton.setVisible(true);

        setEditable(true);
    }

    /**
     * Handles the cancel button being pressed in the view.
     */
    public void cancelButtonPressed()
    {
        viewModel.loadPatient(patient);
        birthdayPicker.setValue(viewModel.getDayOfBirth());

        setEditable(false);

        confirmButton.setVisible(false);
        editButton.setVisible(true);

        viewHandler.openView("receptionist");
    }

    /**
     * Clears the input fields and puts the window back to the start.
     */
    public void reset()
    {
        viewModel.loadPatient(patient);
        birthdayPicker.setValue(viewModel.getDayOfBirth());

        setEditable(false);

        confirmButton.setVisible(false);
        editButton.setVisible(true);
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
            confirmButtonPressed();
        }
    }
}
