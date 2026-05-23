package client.view.patient;

import client.view.login.RegisterWindowController;
import client.view.login.ViewHandler;
import client.viewModel.login.DoctorViewModel;
import client.viewModel.login.PatientViewModel;
import client.viewModel.patient.SpontaneousAppointmentViewModel;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import shared.AppointmentDTO;
import shared.DoctorDTO;
import shared.SessionDTO;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class SpontaneousAppointmentWindowController {
    @FXML private Button cancelButton;

    @FXML private Label emptyLabel;

    private ViewHandler viewHandler;
    private SpontaneousAppointmentViewModel viewModel;
    private Region root;

    public void init(ViewHandler viewHandler, SpontaneousAppointmentViewModel viewModel,
                     Region root) throws Exception {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;

        ArrayList<DoctorDTO> doctors = viewModel.getAllDoctors();
        ArrayList<AppointmentDTO> appointments = viewModel.getAllAppointments();
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        int x = 0;

        for (int i=0; i<appointments.size(); i++) {
            if (appointments.get(i).getDate().toLocalDate().equals(date) && appointments.get(i).getDate().getHour()==time.getHour()) {
                x++;
            }
        }

        if (doctors.size()-x>=0) {
            emptyLabel.setText(doctors.size()-x+" free slot(s) for this hour.");
        } else emptyLabel.setText("Overbooked.");


    }

    public void cancelButtonPressed() {
        viewHandler.openView("patient");
    }

    public Region getRoot() {
        return root;
    }

    public void reset() {
        viewModel.clear();
    }

}
