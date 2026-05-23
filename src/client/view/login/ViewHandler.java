package client.view.login;

import client.view.doctor.*;
import client.view.patient.BookAppointmentWindowController;
import client.view.patient.EditAppointmentWindowController;
import client.view.patient.MyAppointmentWindowController;
import client.view.patient.EditPatientWindowController;
import client.view.patient.PatientWindowController;
import client.view.receptionist.ReceptionistRegisteredPatientWindowController;
import client.view.receptionist.ReceptionistWindowController;
import client.viewModel.login.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import shared.AppointmentDTO;
import shared.PatientDTO;
import shared.SessionDTO;

import java.net.URL;

public class ViewHandler {
    private Scene currentScene;
    private Stage primaryStage;
    private final ViewModelFactory viewModelFactory;

    private RegisterWindowController registerWindowController;
    private LoginWindowController loginWindowController;
    private MainWindowController mainWindowController;
    private PatientWindowController patientWindowController;
    private DoctorWindowController doctorWindowController;
    private ReceptionistWindowController receptionistWindowController;
    private BookAppointmentWindowController bookAppointmentWindowController;
    private MyAppointmentWindowController myAppointmentWindowController;
    private EditAppointmentWindowController editAppointmentWindowController;
    private EditPatientWindowController editPatientWindowController;
    private TodayAppointmentsWindowController todayAppointmentsWindowController;
    private DoctorEditAppointmentWindowController doctorEditAppointmentWindowController;
    private RegisteredPatientWindowController registeredPatientWindowController;
    private ReceptionistRegisteredPatientWindowController receptionistRegisteredPatientWindowController;
    private client.view.doctor.ReceptionistEditPatientWindowController receptionistEditPatientWindowController;

    private SessionDTO currentSession;

    public ViewHandler(ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
        this.currentScene = new Scene(new Region());
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setResizable(false);
        openView("main");
    }

    public void openView(String id) {
        Region root;
        switch (id) {
            case "main" -> root = loadMainWindow("/client/view/login/MainWindow.fxml");
            case "register" -> root = loadRegisterWindow("/client/view/login/RegisterWindow.fxml");
            case "login" -> root = loadLoginWindow("/client/view/login/LoginWindow.fxml");
            case "patient" -> root = loadPatientWindow("/client/view/patient/PatientWindow.fxml");
            case "doctor" -> root = loadDoctorWindow("/client/view/doctor/DoctorWindow.fxml");
            case "receptionist" -> root = loadReceptionistWindow("/client/view/receptionist/ReceptionistWindow.fxml");
            case "book_appointment" -> root = loadBookAppointmentWindow("/client/view/patient/BookAppointmentWindow.fxml");
            case "my_appointments" -> root = loadMyAppointmentsWindow("/client/view/patient/MyAppointmentWindow.fxml");
            case "profile" -> root = loadEditPatientWindow("/client/view/patient/EditPatientWindow.fxml");
            case "today_appointments" -> root = loadTodayAppointmentsWindow("/client/view/doctor/TodayAppointmentsWindow.fxml");
            case "registered_patients" -> root = loadRegisteredPatientsWindow("/client/view/doctor/RegisteredPatientWindow.fxml");
            case "receptionist_registered_patients" -> root = loadReceptionistRegisteredPatientsWindow("/client/view/receptionist/ReceptionistRegisteredPatientWindow.fxml");
            // Not implemented yet. Returning null is better than crashing the application.
            case "chat",
                 "spontaneous_visit", "receptionist_book_appointment", "all_appointments"-> root = null;
            default -> {
                System.out.println("Unknown view id: " + id);
                return;
            }
        }

        if (root == null) {
            System.out.println("Could not load view: " + id);
            return;
        }

        currentScene.setRoot(root);
        String title = root.getUserData() == null ? "" : root.getUserData().toString();
        primaryStage.setTitle(title);
        primaryStage.setScene(currentScene);
        primaryStage.setWidth(root.getPrefWidth());
        primaryStage.setHeight(root.getPrefHeight());
        primaryStage.show();
    }

    public void openAppointmentEditWindow(AppointmentDTO appointment)
    {
        Region root = loadEditAppointmentWindow(
                "/client/view/patient/EditAppointmentWindow.fxml",
                appointment
        );

        if (root == null)
        {
            return;
        }

        currentScene.setRoot(root);
    }

    public void openDoctorEditAppointmentWindow(AppointmentDTO appointment)
    {
        Region root = loadDoctorEditAppointmentWindow(
                "/client/view/doctor/DoctorEditAppointmentWindow.fxml",
                appointment
        );

        if (root == null)
        {
            return;
        }

        currentScene.setRoot(root);
    }

    public void openPatientEditWindow(PatientDTO patient)
    {
        Region root = loadReceptionistPatientEditWindow(
                "/client/view/receptionist/ReceptionistEditPatientWindow.fxml",
                patient
        );

        if (root==null)
        {
            return;
        }

        currentScene.setRoot(root);
    }

    public void closeView() {
        primaryStage.close();
    }

    public void setCurrentSession(SessionDTO currentSession) {
        this.currentSession = currentSession;
    }

    public SessionDTO getCurrentSession() {
        return currentSession;
    }

    private URL getFXMLUrl(String fxmlFile) {
        URL url = getClass().getResource(fxmlFile);
        if (url == null) {
            System.err.println("FXML file not found: " + fxmlFile);
        }
        return url;
    }

    private Region loadRegisterWindow(String fxmlFile) {
        if (registerWindowController == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getFXMLUrl(fxmlFile));
                Region root = loader.load();
                registerWindowController = loader.getController();
                registerWindowController.init(this, viewModelFactory.getCreateAccountViewModel(), root);
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        else {
            registerWindowController.reset();
        }
        return registerWindowController == null ? null : registerWindowController.getRoot();
    }

    private Region loadLoginWindow(String fxmlFile) {
        if (loginWindowController == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getFXMLUrl(fxmlFile));
                Region root = loader.load();
                loginWindowController = loader.getController();
                loginWindowController.init(this, viewModelFactory.getLoginViewModel(), root);
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        else {
            loginWindowController.reset();
        }
        return loginWindowController == null ? null : loginWindowController.getRoot();
    }

    private Region loadMainWindow(String fxmlFile) {
        if (mainWindowController == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getFXMLUrl(fxmlFile));
                Region root = loader.load();
                mainWindowController = loader.getController();
                mainWindowController.init(this, viewModelFactory.getMainViewModel(), root);
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return mainWindowController == null ? null : mainWindowController.getRoot();
    }

    private Region loadPatientWindow(String fxmlFile) {
        if (patientWindowController == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getFXMLUrl(fxmlFile));
                Region root = loader.load();
                patientWindowController = loader.getController();
                patientWindowController.init(this, viewModelFactory.getPatientViewModel(), root, currentSession);
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        else {
            patientWindowController.reset();
        }
        return patientWindowController == null ? null : patientWindowController.getRoot();
    }

    private Region loadDoctorWindow(String fxmlFile) {
        if (doctorWindowController == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getFXMLUrl(fxmlFile));
                Region root = loader.load();
                doctorWindowController = loader.getController();
                doctorWindowController.init(this, viewModelFactory.getDoctorViewModel(), root, currentSession);
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        else {
            doctorWindowController.reset();
        }
        return doctorWindowController == null ? null : doctorWindowController.getRoot();
    }

    private Region loadReceptionistWindow(String fxmlFile) {
        if (receptionistWindowController == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getFXMLUrl(fxmlFile));
                Region root = loader.load();
                receptionistWindowController = loader.getController();
                receptionistWindowController.init(this, viewModelFactory.getReceptionistViewModel(), root, currentSession);
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        else {
            receptionistWindowController.reset();
        }
        return receptionistWindowController == null ? null : receptionistWindowController.getRoot();
    }

    private Region loadBookAppointmentWindow(String fxmlFile) {
        if (bookAppointmentWindowController == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getFXMLUrl(fxmlFile));
                Region root = loader.load();
                bookAppointmentWindowController = loader.getController();
                bookAppointmentWindowController.init(this, viewModelFactory.getBookAppointmentViewModel(), root);
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        else {
            bookAppointmentWindowController.reset();
        }
        return bookAppointmentWindowController == null ? null : bookAppointmentWindowController.getRoot();
    }

    private Region loadMyAppointmentsWindow(String fxmlFile) {
        if (myAppointmentWindowController == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getFXMLUrl(fxmlFile));
                Region root = loader.load();
                myAppointmentWindowController = loader.getController();
                myAppointmentWindowController.init(this, viewModelFactory.getMyAppointmentViewModel(), root);
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        else {
            myAppointmentWindowController.reset();
        }
        return myAppointmentWindowController == null ? null : myAppointmentWindowController.getRoot();
    }

    private Region loadEditAppointmentWindow(String fxmlFile, AppointmentDTO appointment)
    {
        if (editAppointmentWindowController == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getFXMLUrl(fxmlFile));
                Region root = loader.load();
                editAppointmentWindowController = loader.getController();
                editAppointmentWindowController.init(this, viewModelFactory.getEditAppointmentViewModel(), root, appointment);
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        else {
            editAppointmentWindowController.reset();
        }
        return editAppointmentWindowController == null ? null : editAppointmentWindowController.getRoot();
    }

    private Region loadEditPatientWindow(String fxmlFile)
    {
        if (editPatientWindowController == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getFXMLUrl(fxmlFile));
                Region root = loader.load();
                editPatientWindowController = loader.getController();
                editPatientWindowController.init(this, viewModelFactory.getEditPatientViewModel(), root);
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        else {
            editPatientWindowController.reset();
        }
        return editPatientWindowController == null ? null : editPatientWindowController.getRoot();
    }

    private Region loadTodayAppointmentsWindow(String fxmlFile) {
        if (todayAppointmentsWindowController == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getFXMLUrl(fxmlFile));
                Region root = loader.load();
                todayAppointmentsWindowController = loader.getController();
                todayAppointmentsWindowController.init(this, viewModelFactory.getTodayAppointmentsViewModel(), root);
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        else {
            todayAppointmentsWindowController.reset();
        }
        return todayAppointmentsWindowController == null ? null : todayAppointmentsWindowController.getRoot();
    }

    private Region loadDoctorEditAppointmentWindow(String fxmlFile, AppointmentDTO appointment)
    {
        if (doctorEditAppointmentWindowController == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getFXMLUrl(fxmlFile));
                Region root = loader.load();
                doctorEditAppointmentWindowController = loader.getController();
                doctorEditAppointmentWindowController.init(this, viewModelFactory.getDoctorEditAppointmentViewModel(), root, appointment);
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        else {
            doctorEditAppointmentWindowController.reset();
        }
        return doctorEditAppointmentWindowController == null ? null : doctorEditAppointmentWindowController.getRoot();
    }

    private Region loadRegisteredPatientsWindow(String fxmlFile)
    {
        if (registeredPatientWindowController == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getFXMLUrl(fxmlFile));
                Region root = loader.load();
                registeredPatientWindowController = loader.getController();
                registeredPatientWindowController.init(this, viewModelFactory.getRegisteredPatientViewModel(), root);
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        else {
            registeredPatientWindowController.reset();
        }
        return registeredPatientWindowController == null ? null : registeredPatientWindowController.getRoot();
    }

    private Region loadReceptionistPatientEditWindow(String fxmlFile, PatientDTO patient)
    {
        if (receptionistEditPatientWindowController == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getFXMLUrl(fxmlFile));
                Region root = loader.load();
                receptionistEditPatientWindowController = loader.getController();
                receptionistEditPatientWindowController.init(this, viewModelFactory.getReceptionistEditPatientViewModel(), root, patient);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        else {
            receptionistEditPatientWindowController.reset();
        }
        return receptionistEditPatientWindowController == null ? null : receptionistEditPatientWindowController.getRoot();
    }

    private Region loadReceptionistRegisteredPatientsWindow(String fxmlFile)
    {
        if (receptionistRegisteredPatientWindowController == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getFXMLUrl(fxmlFile));
                Region root = loader.load();
                receptionistRegisteredPatientWindowController = loader.getController();
                receptionistRegisteredPatientWindowController.init(this, viewModelFactory.getReceptionistRegisteredPatientViewModel(), root);
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        else {
            receptionistRegisteredPatientWindowController.reset();
        }
        return receptionistRegisteredPatientWindowController == null ? null : receptionistRegisteredPatientWindowController.getRoot();
    }
}
