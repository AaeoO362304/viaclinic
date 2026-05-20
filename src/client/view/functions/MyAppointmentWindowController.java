package client.view.functions;

import client.view.login.ViewHandler;
import client.viewModel.appointment.MyAppointmentViewModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import server.model.bookAppointment.Appointment;
import server.model.bookAppointment.AppointmentDAO;
import server.model.bookAppointment.Doctor;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MyAppointmentWindowController {

    @FXML private Button cancelButton;

    @FXML private TableView<Appointment> appointmentTable;

    @FXML private TableColumn<Appointment, LocalDateTime> appointmentDateColumn;
    @FXML private TableColumn<Appointment, Doctor> doctorColumn;
    @FXML private TableColumn<Appointment, Boolean> statusColumn;
    @FXML private TableColumn<Appointment, String> notesColumn;

    private Region root;
    private ViewHandler viewHandler;
    private MyAppointmentViewModel viewModel;

    public MyAppointmentWindowController() {

    }

    public void init(ViewHandler viewHandler, MyAppointmentViewModel viewModel, Region root) throws SQLException
    {
        this.viewModel = viewModel;
        this.viewHandler = viewHandler;
        this.root = root;

        appointmentDateColumn.setCellValueFactory(
                new PropertyValueFactory<>("date")
        );

        doctorColumn.setCellValueFactory(
                new PropertyValueFactory<>("doctor")
        );

        statusColumn.setCellValueFactory(
                new PropertyValueFactory<>("status")
        );

        notesColumn.setCellValueFactory(
                new PropertyValueFactory<>("notes")
        );

        loadAppointments();
    }

    private void loadAppointments()
    {
        try
        {
            ArrayList<Appointment> appointments =
                    viewModel.getAllAppointmentsByPatientId();

            if (appointments == null)
            {
                appointments = new ArrayList<>();
            }

            appointmentTable.setItems(
                    FXCollections.observableArrayList(appointments)
            );
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void cancelButtonPressed() {
        viewHandler.openView("patient");
    }

    public Region getRoot() {
        return root;
    }

    public void reset()
    {
        loadAppointments();
    }
}
