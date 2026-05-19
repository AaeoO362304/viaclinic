package client.view.functions;

import client.view.login.ViewHandler;
import client.viewModel.appointment.BookAppointmentViewModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import server.model.bookAppointment.*;

import java.sql.SQLException;

public class BookAppointmentWindowController {

    @FXML private Button cancelButton;
    @FXML private Button confirmButton;

    @FXML private DatePicker datePicker;

    @FXML private ComboBox<String> timeComboBox;
    @FXML private ComboBox<Doctor> doctorComboBox;

    @FXML private Label errorLabel;

    private Region root;
    private ViewHandler viewHandler;
    private BookAppointmentViewModel bookAppointmentViewModel;

    public BookAppointmentWindowController() {}

    public void init(ViewHandler viewHandler,
                     BookAppointmentViewModel bookAppointmentViewModel,
                     Region root) throws SQLException {
        this.viewHandler = viewHandler;
        this.bookAppointmentViewModel = bookAppointmentViewModel;
        this.root = root;

        errorLabel.textProperty().bind(bookAppointmentViewModel.getErrorProperty());

        DoctorDAO doctorDAO = DoctorDAO.getInstance();

        doctorComboBox.setItems(
                FXCollections.observableArrayList(doctorDAO.getAllDoctors())
        );

        timeComboBox.setItems(
                FXCollections.observableArrayList(
                        "10:00", "11:00", "12:00",
                        "13:00", "14:00", "15:00",
                        "16:00", "17:00", "18:00"
                )
        );
    }

    @FXML
    public void confirmButtonPressed() {
        bookAppointmentViewModel.setDate(datePicker.getValue());
        bookAppointmentViewModel.setDoctor(doctorComboBox.getValue());
        bookAppointmentViewModel.getTimeProperty().set(timeComboBox.getValue());

        boolean created = bookAppointmentViewModel.create();

        if (created) {
            viewHandler.openView("patient");
        }
    }

    @FXML
    public void cancelButtonPressed() {
        viewHandler.openView("patient");
    }

    public void reset() {
        bookAppointmentViewModel.clear();

        datePicker.setValue(null);
        doctorComboBox.getSelectionModel().clearSelection();
        timeComboBox.getSelectionModel().clearSelection();
    }

    public Region getRoot() {
        return root;
    }

    @FXML
    private void onEnter(ActionEvent actionEvent) {
        if (actionEvent.getSource() == datePicker) {
            doctorComboBox.requestFocus();
        }
        else if (actionEvent.getSource() == doctorComboBox) {
            timeComboBox.requestFocus();
        }
        else {
            confirmButtonPressed();
        }
    }
}