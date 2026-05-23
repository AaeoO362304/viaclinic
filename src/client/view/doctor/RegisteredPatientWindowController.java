package client.view.doctor;

import client.view.login.ViewHandler;
import client.viewModel.doctor.RegisteredPatientViewModel;
import client.viewModel.doctor.TodayAppointmentsViewModel;
import client.viewModel.login.DoctorViewModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import server.model.bookAppointment.PatientDAO;
import shared.AppointmentDTO;
import shared.PatientDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class RegisteredPatientWindowController {
    @FXML private TableColumn<PatientDTO, String> firstNameColumn;
    @FXML private TableColumn<PatientDTO, String> lastNameColumn;
    @FXML private TableColumn<PatientDTO, String> genderColumn;
    @FXML private TableColumn<PatientDTO, String> phoneNumberColumn;
    @FXML private TableColumn<PatientDTO, String> eMailColumn;
    @FXML private TableColumn<PatientDTO, String> passwordColumn;
    @FXML private TableColumn<PatientDTO, String> userNameColumn;
    @FXML private TableColumn<PatientDTO, String> cprColumn;
    @FXML private TableColumn<PatientDTO, String> medicalNotesColumn;
    @FXML private TableColumn<PatientDTO, LocalDate> dayOfBirthColumn;
    @FXML private TableColumn<PatientDTO, LocalDate> lastVisitColumn;
    @FXML private TableView<PatientDTO> patientTable;

    @FXML private Button cancelButton;

    private Region root;
    private RegisteredPatientViewModel viewModel;
    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler, RegisteredPatientViewModel viewModel, Region root) throws Exception {
        this.viewModel = viewModel;
        this.viewHandler = viewHandler;
        this.root = root;

        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        dayOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("dayOfBirth"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        eMailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        lastVisitColumn.setCellValueFactory(new PropertyValueFactory<>("lastVisit"));
        medicalNotesColumn.setCellValueFactory(new PropertyValueFactory<>("medicalNotes"));
        cprColumn.setCellValueFactory(new PropertyValueFactory<>("CPR"));

        loadPatients();
    }

    private void loadPatients() {
        try {
            ArrayList<PatientDTO> patients = viewModel.getAllPatients();
            patientTable.setItems(FXCollections.observableArrayList(patients));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancelButtonPressed() {
        viewHandler.openView("doctor"); }

    public Region getRoot() { return root; }

    public void reset() { loadPatients(); }
}
