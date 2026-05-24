package client.view.login;

import client.view.doctor.*;
import client.view.patient.*;
import client.view.receptionist.*;
import client.viewModel.login.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import shared.AppointmentDTO;
import shared.PatientDTO;
import shared.SessionDTO;

import java.net.URL;

/**
 * Loads the FXML windows and switches between them.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class ViewHandler {
    /** The current scene. */
    private Scene currentScene;
    /** The primary stage. */
    private Stage primaryStage;
    /** The view model factory. */
    private final ViewModelFactory viewModelFactory;

    /** The register window controller. */
    private RegisterWindowController registerWindowController;
    /** The login window controller. */
    private LoginWindowController loginWindowController;
    /** The main window controller. */
    private MainWindowController mainWindowController;
    /** The patient window controller. */
    private PatientWindowController patientWindowController;
    /** The doctor window controller. */
    private DoctorWindowController doctorWindowController;
    /** The receptionist window controller. */
    private ReceptionistWindowController receptionistWindowController;
    /** The book appointment window controller. */
    private BookAppointmentWindowController bookAppointmentWindowController;
    /** The my appointment window controller. */
    private MyAppointmentWindowController myAppointmentWindowController;
    /** The edit appointment window controller. */
    private EditAppointmentWindowController editAppointmentWindowController;
    /** The edit patient window controller. */
    private EditPatientWindowController editPatientWindowController;
    /** The today appointments window controller. */
    private TodayAppointmentsWindowController todayAppointmentsWindowController;
    /** The doctor edit appointment window controller. */
    private DoctorEditAppointmentWindowController doctorEditAppointmentWindowController;
    /** The registered patient window controller. */
    private RegisteredPatientWindowController registeredPatientWindowController;
    /** The receptionist registered patient window controller. */
    private ReceptionistRegisteredPatientWindowController receptionistRegisteredPatientWindowController;
    /** The receptionist edit patient window controller. */
    private ReceptionistEditPatientWindowController receptionistEditPatientWindowController;
    /** The receptionist today appointments window controller. */
    private ReceptionistTodaysAppointmentsWindowController receptionistTodayAppointmentsWindowController;
    /** The receptionist all appointments window controller. */
    private ReceptionistAllAppointmentsWindowController receptionistAllAppointmentsWindowController;
    /** The receptionist edit appointment window controller. */
    private ReceptionistEditAppointmentWindowController receptionistEditAppointmentWindowController;
    /** The receptionist book appointments window controller. */
    private ReceptionistBookAppointmentWindowController receptionistBookAppointmentsWindowController;
    /** The spontaneous appointment window controller. */
    private SpontaneousAppointmentWindowController spontaneousAppointmentWindowController;

    /** The current session. */
    private SessionDTO currentSession;

    /**
     * Creates a new {@code ViewHandler} initialised with the given view model factory.
     *
     * @param viewModelFactory the view model factory
     */
    public ViewHandler(ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
        this.currentScene = new Scene(new Region());
    }

    /**
     * Starts the application and shows the primary stage.
     *
     * @param primaryStage the primary stage
     */
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setResizable(false);
        openView("main");
    }

    /**
     * Opens the view.
     *
     * @param id the identifier of the entity
     */
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
            case "receptionist_today_appointments" -> root = loadReceptionistTodayAppointmentsWindow("/client/view/receptionist/ReceptionistTodaysAppointmentsWindow.fxml");
            case "all_appointments" -> root = loadReceptionistAllAppointmentsWindow("/client/view/receptionist/ReceptionistAllAppointmentsWindow.fxml");
            case "receptionist_book_appointment" -> root = loadReceptionistBookAppointmentWindow("/client/view/receptionist/ReceptionistBookAppointmentWindow.fxml");
            case "spontaneous_visit" -> root = loadSpontaneousAppointmentWindow("/client/view/patient/SpontaneousAppointmentWindow.fxml");
            // Not implemented yet. Returning null is better than crashing the application.
            case "chat" -> {
                openChat();
                return;
            }
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

    /**
     * Opens the appointment edit window.
     *
     * @param appointment the appointment
     */
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

    /**
     * Opens the doctor edit appointment window.
     *
     * @param appointment the appointment
     */
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

    /**
     * Opens the receptionist edit appointment window.
     *
     * @param appointment the appointment
     */
    public void openReceptionistEditAppointmentWindow(AppointmentDTO appointment)
    {
        Region root = loadReceptionistEditAppointmentsWindow(
                "/client/view/receptionist/ReceptionistEditAppointmentWindow.fxml",
                appointment
        );

        if (root==null)
        {
            return;
        }

        currentScene.setRoot(root);
    }

    /**
     * Opens the receptionist edit window.
     *
     * @param patient the patient
     */
    public void openReceptionistEditWindow(PatientDTO patient) {
        Region root = loadReceptionistEditPatientWindow(
                "/client/view/receptionist/ReceptionistEditPatientWindow.fxml",
                patient
        );

        if (root==null)
        {
            return;
        }

        currentScene.setRoot(root);
    }

    /**
     * Closes the window.
     */
    public void closeView() {
        primaryStage.close();
    }

    /**
     * Sets the current session.
     *
     * @param currentSession the current session
     */
    public void setCurrentSession(SessionDTO currentSession) {
        this.currentSession = currentSession;
    }

    /**
     * Returns the current session.
     *
     * @return the current session
     */
    public SessionDTO getCurrentSession() {
        return currentSession;
    }

    /**
     * Opens the chat window in its own stage for the logged-in user.
     */
    private void openChat() {
        if (currentSession == null) {
            System.out.println("Cannot open chat: no user is logged in.");
            return;
        }
        try {
            new client.view.chat.ChatWindow(currentSession, viewModelFactory.getClient()).show();
        } catch (Exception e) {
            System.out.println("Could not open chat: " + e.getMessage());
        }
    }

    /**
     * Returns the FXML url.
     *
     * @param fxmlFile the fxml file
     * @return the FXML url
     */
    private URL getFXMLUrl(String fxmlFile) {
        URL url = getClass().getResource(fxmlFile);
        if (url == null) {
            System.err.println("FXML file not found: " + fxmlFile);
        }
        return url;
    }

    /**
     * Loads the register window into the view.
     *
     * @param fxmlFile the fxml file
     * @return the resulting region
     */
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

    /**
     * Loads the login window into the view.
     *
     * @param fxmlFile the fxml file
     * @return the resulting region
     */
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

    /**
     * Loads the main window into the view.
     *
     * @param fxmlFile the fxml file
     * @return the resulting region
     */
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

    /**
     * Loads the patient window into the view.
     *
     * @param fxmlFile the fxml file
     * @return the resulting region
     */
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

    /**
     * Loads the doctor window into the view.
     *
     * @param fxmlFile the fxml file
     * @return the resulting region
     */
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

    /**
     * Loads the receptionist window into the view.
     *
     * @param fxmlFile the fxml file
     * @return the resulting region
     */
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

    /**
     * Loads the book appointment window into the view.
     *
     * @param fxmlFile the fxml file
     * @return the resulting region
     */
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

    /**
     * Loads the my appointments window into the view.
     *
     * @param fxmlFile the fxml file
     * @return the resulting region
     */
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

    /**
     * Loads the edit appointment window into the view.
     *
     * @param fxmlFile the fxml file
     * @param appointment the appointment
     * @return the resulting region
     */
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

    /**
     * Loads the edit patient window into the view.
     *
     * @param fxmlFile the fxml file
     * @return the resulting region
     */
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

    /**
     * Loads the today appointments window into the view.
     *
     * @param fxmlFile the fxml file
     * @return the resulting region
     */
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

    /**
     * Loads the doctor edit appointment window into the view.
     *
     * @param fxmlFile the fxml file
     * @param appointment the appointment
     * @return the resulting region
     */
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

    /**
     * Loads the registered patients window into the view.
     *
     * @param fxmlFile the fxml file
     * @return the resulting region
     */
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

    /**
     * Loads the receptionist patient edit window into the view.
     *
     * @param fxmlFile the fxml file
     * @param patient the patient
     * @return the resulting region
     */
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

    /**
     * Loads the receptionist registered patients window into the view.
     *
     * @param fxmlFile the fxml file
     * @return the resulting region
     */
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

    /**
     * Loads the receptionist today appointments window into the view.
     *
     * @param fxmlFile the fxml file
     * @return the resulting region
     */
    private Region loadReceptionistTodayAppointmentsWindow(String fxmlFile)
    {
        if (receptionistTodayAppointmentsWindowController == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getFXMLUrl(fxmlFile));
                Region root = loader.load();
                receptionistTodayAppointmentsWindowController = loader.getController();
                receptionistTodayAppointmentsWindowController.init(this, viewModelFactory.getReceptionistTodayAppointmentsViewModel(), root);
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        else {
            receptionistTodayAppointmentsWindowController.reset();
        }
        return receptionistTodayAppointmentsWindowController == null ? null : receptionistTodayAppointmentsWindowController.getRoot();
    }

    /**
     * Loads the receptionist all appointments window into the view.
     *
     * @param fxmlFile the fxml file
     * @return the resulting region
     */
    private Region loadReceptionistAllAppointmentsWindow(String fxmlFile)
    {
        if (receptionistAllAppointmentsWindowController == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getFXMLUrl(fxmlFile));
                Region root = loader.load();
                receptionistAllAppointmentsWindowController = loader.getController();
                receptionistAllAppointmentsWindowController.init(this, viewModelFactory.getReceptionistAllAppointmentsViewModel(), root);
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        else {
            receptionistAllAppointmentsWindowController.reset();
        }
        return receptionistAllAppointmentsWindowController == null ? null : receptionistAllAppointmentsWindowController.getRoot();
    }

    /**
     * Loads the receptionist edit appointments window into the view.
     *
     * @param fxmlFile the fxml file
     * @param appointment the appointment
     * @return the resulting region
     */
    private Region loadReceptionistEditAppointmentsWindow(String fxmlFile, AppointmentDTO appointment)
    {
        if (receptionistEditAppointmentWindowController == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getFXMLUrl(fxmlFile));
                Region root = loader.load();
                receptionistEditAppointmentWindowController = loader.getController();
                receptionistEditAppointmentWindowController.init(this, viewModelFactory.getReceptionistEditAppointmentViewModel(), root, appointment);
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        else {
            receptionistEditAppointmentWindowController.reset();
        }
        return receptionistEditAppointmentWindowController == null ? null : receptionistEditAppointmentWindowController.getRoot();
    }

    /**
     * Loads the receptionist edit patient window into the view.
     *
     * @param fxmlFile the fxml file
     * @param patient the patient
     * @return the resulting region
     */
    private Region loadReceptionistEditPatientWindow(String fxmlFile, PatientDTO patient)
    {
        if (receptionistEditPatientWindowController == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getFXMLUrl(fxmlFile));
                Region root = loader.load();
                receptionistEditPatientWindowController = loader.getController();
                receptionistEditPatientWindowController.init(this, viewModelFactory.getReceptionistEditPatientViewModel(), root, patient);
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        else {
            receptionistEditPatientWindowController.reset();
        }
        return receptionistEditPatientWindowController == null ? null : receptionistEditPatientWindowController.getRoot();
    }

    /**
     * Loads the receptionist book appointment window into the view.
     *
     * @param fxmlFile the fxml file
     * @return the resulting region
     */
    private Region loadReceptionistBookAppointmentWindow(String fxmlFile) {
        if (receptionistBookAppointmentsWindowController == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getFXMLUrl(fxmlFile));
                Region root = loader.load();
                receptionistBookAppointmentsWindowController = loader.getController();
                receptionistBookAppointmentsWindowController.init(this, viewModelFactory.getReceptionistBookAppointmentViewModel(), root);
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        else {
            receptionistBookAppointmentsWindowController.reset();
        }
        return receptionistBookAppointmentsWindowController == null ? null : receptionistBookAppointmentsWindowController.getRoot();
    }

    /**
     * Loads the spontaneous appointment window into the view.
     *
     * @param fxmlFile the fxml file
     * @return the resulting region
     */
    private Region loadSpontaneousAppointmentWindow(String fxmlFile) {
        if (spontaneousAppointmentWindowController == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getFXMLUrl(fxmlFile));
                Region root = loader.load();
                spontaneousAppointmentWindowController = loader.getController();
                spontaneousAppointmentWindowController.init(this, viewModelFactory.getSpontaneousAppointmentViewModel(), root);
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        else {
            spontaneousAppointmentWindowController.reset();
        }
        return spontaneousAppointmentWindowController == null ? null : spontaneousAppointmentWindowController.getRoot();
    }

}
