package client.view.doctor;

import client.view.login.ViewHandler;
import client.viewModel.appointment.EditAppointmentViewModel;
import client.viewModel.doctor.DoctorEditAppointmentViewModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import shared.AppointmentDTO;
import shared.DoctorDTO;

import java.util.ArrayList;

public class DoctorEditAppointmentWindowController {
    @FXML private Button cancelButton;
    @FXML private Button confirmButton;
    @FXML private ComboBox<Boolean> statusComboBox;
    @FXML private TextArea notes;


    private Region root;
    private ViewHandler viewHandler;
    private DoctorEditAppointmentViewModel viewModel;;
    private int appointmentId;

    public void init(ViewHandler viewHandler,
                     DoctorEditAppointmentViewModel viewModel,
                     Region root,
                     AppointmentDTO appointment)
    {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;
        statusComboBox.setItems(FXCollections.observableArrayList(true, false));

        viewModel.setAppointment(appointment);
    }

    @FXML
    public void confirmButtonPressed()
    {
        viewModel.setNotes(notes.getText());

        boolean finished = viewModel.finishAppointment();

        if (finished)
        {
            viewHandler.openView("today_appointments");
        }
    }

    @FXML
    public void cancelButtonPressed() {
        viewHandler.openView("doctor");
    }

    public void reset() {
        viewModel.clear();
        statusComboBox.getSelectionModel().clearSelection();
    }

    public Region getRoot() { return root; }
}
