package client.view.login;

import client.view.doctor.DoctorWindowController;
import client.view.functions.BookAppointmentWindowController;
import client.view.functions.MyAppointmentWindowController;
import client.view.patient.PatientWindowController;
import client.view.receptionist.ReceptionistWindowController;
import client.viewModel.login.CreateAccountViewModel;
import client.viewModel.login.LoginViewModel;
import client.viewModel.login.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import server.model.auth.LoginAuthenticator;
import server.model.auth.Session;

public class ViewHandler {
    private Scene currentScene;
    private Stage primaryStage;
    private ViewModelFactory viewModelFactory;
    private RegisterWindowController registerWindowController;
    private LoginWindowController loginWindowController;
    private MainWindowController mainWindowController;
    private PatientWindowController patientWindowController;
    private DoctorWindowController doctorWindowController;
    private ReceptionistWindowController receptionistWindowController;
    private BookAppointmentWindowController bookAppointmentWindowController;
    private MyAppointmentWindowController myAppointmentWindowController;
    private Session currentSession;

    public ViewHandler(ViewModelFactory viewModelFactory) {
        this.viewModelFactory=viewModelFactory;
        this.currentScene=new Scene(new Region());
    }

    public void start(Stage primaryStage)
    {
        this.primaryStage=primaryStage;
        this.primaryStage.setResizable(false);
        openView("main");
    }

    public void openView(String id) {
        Region root=null;
        switch(id)
        {
            case "main":
                root=loadMainWindow("MainWindow.fxml");
                break;
            case "register":
                root=loadRegisterWindow("RegisterWindow.fxml");
                break;
            case "login":
                root = loadLoginWindow("LoginWindow.fxml");
                break;
            case "patient":
                root = loadPatientWindow("/client/view/patient/PatientWindow.fxml");
                break;
            case "doctor":
                root = loadDoctorWindow("/client/view/doctor/DoctorWindow.fxml");
                break;
            case "receptionist":
                root = loadReceptionistWindow("/client/view/receptionist/ReceptionistWindow.fxml");
                break;
            case "chat":
                root = loadChatWindow("/client/view/functions/ChatWindow.fxml");
                break;
            case "registered_patients":
                root = loadRegisteredPatientsWindow("/client/view/functions/RegisteredPatientsWindow.fxml");
                break;
            case "today_appointments":
                root = loadTodaysAppointmentsWindow("/client/view/functions/TodayAppointmentsWindow.fxml");
                break;
            case "profile":
                root = loadProfileWindow("/client/view/functions/ProfileWindow.fxml");
                break;
            case "spontaneous_visit":
                root = loadSpontaneousVisitWindow("/client/view/functions/SpontaneousVisitWindow.fxml");
                break;
            case "book_appointment":
                root = loadBookAppointmentWindow("/client/view/functions/BookAppointmentWindow.fxml");
                break;
            case "receptionist_book_appointment":
                root = loadReceptionistBookAppointmentWindow("/client/view/functions/ReceptionistBookAppointmentWindow.fxml");
                break;
            case "my_appointments":
                root = loadMyAppointmentsWindow("/client/view/functions/MyAppointmentWindow.fxml");
                break;
            case "all_appointments":
                root = loadAllAppointmentsWindow("/client/view/functions/AllAppointmentsWindow.fxml");
                break;
            default:
                return;

        }
        currentScene.setRoot(root);
        String title = "";
        if(root.getUserData() != null)
        {
            title += root.getUserData();
        }
        primaryStage.setTitle(title);
        primaryStage.setScene(currentScene);
        primaryStage.setWidth(root.getPrefWidth());
        primaryStage.setHeight(root.getPrefHeight());
        primaryStage.show();
    }

    public void closeView() {primaryStage.close();}

    public void setCurrentSession(Session currentSession) {
        this.currentSession=currentSession;
    }

    public Session getCurrentSession()
    {
        return currentSession;
    }

    private Region loadRegisterWindow(String fxmlFile)
    {
        Region root = null;
        if (registerWindowController == null)
        {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                root = loader.load();
                registerWindowController = loader.getController();
                registerWindowController.init(this, viewModelFactory.getCreateAccountViewModel(), root);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            registerWindowController.reset();
        }
        return  registerWindowController.getRoot();
    }

    private Region loadLoginWindow(String fxmlFile)
    {
        Region root = null;
        if (loginWindowController == null)
        {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                root = loader.load();
                loginWindowController = loader.getController();
                loginWindowController.init(this, viewModelFactory.getLoginViewModel(), root);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            loginWindowController.reset();
        }
        return  loginWindowController.getRoot();
    }

    private Region loadMainWindow(String fxmlFile)
    {
        Region root = null;
        if (mainWindowController == null)
        {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                root = loader.load();
                mainWindowController = loader.getController();
                mainWindowController.init(this, viewModelFactory.getMainViewModel(), root);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return  mainWindowController.getRoot();
    }

    private Region loadPatientWindow(String fxmlFile)
    {
        Region root = null;
        if (patientWindowController == null)
        {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                root = loader.load();
                patientWindowController = loader.getController();
                patientWindowController.init(this, viewModelFactory.getPatientViewModel(), root, currentSession);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            patientWindowController.reset();
        }
        return  patientWindowController.getRoot();
    }

    private Region loadDoctorWindow(String fxmlFile)
    {
        Region root = null;
        if (doctorWindowController == null)
        {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                root = loader.load();
                doctorWindowController = loader.getController();
                doctorWindowController.init(this, viewModelFactory.getDoctorViewModel(), root, currentSession);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            doctorWindowController.reset();
        }
        return  doctorWindowController.getRoot();
    }

    private Region loadReceptionistWindow(String fxmlFile)
    {
        Region root = null;
        if (receptionistWindowController == null)
        {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                root = loader.load();
                receptionistWindowController = loader.getController();
                receptionistWindowController.init(this, viewModelFactory.getReceptionistViewModel(), root, currentSession);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            receptionistWindowController.reset();
        }
        return  receptionistWindowController.getRoot();
    }

    private Region loadChatWindow(String fxmlFile) {
        return null;
    }

    private Region loadRegisteredPatientsWindow(String fxmlFile) {
        return null;
    }

    private Region loadTodaysAppointmentsWindow(String fxmlFile) {
        return null;
    }

    private Region loadProfileWindow(String fxmlFile) {
        return null;
    }

    private Region loadSpontaneousVisitWindow(String fxmlFile) {
        return null;
    }

    private Region loadBookAppointmentWindow(String fxmlFile) {
        Region root = null;
        if (bookAppointmentWindowController == null)
        {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                root = loader.load();
                bookAppointmentWindowController = loader.getController();
                bookAppointmentWindowController.init(this, viewModelFactory.getBookAppointmentViewModel(), root);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            bookAppointmentWindowController.reset();
        }
        return  bookAppointmentWindowController.getRoot();
    }

    private Region loadReceptionistBookAppointmentWindow(String fxmlFile) {
        return null;
    }

    private Region loadMyAppointmentsWindow(String fxmlFile) {
        Region root = null;
        if (myAppointmentWindowController == null)
        {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                root = loader.load();
                myAppointmentWindowController = loader.getController();
                myAppointmentWindowController.init(this, viewModelFactory.getMyAppointmentViewModel(), root);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            myAppointmentWindowController.reset();
        }
        return  myAppointmentWindowController.getRoot();
    }

    private Region loadAllAppointmentsWindow(String fxmlFile) {
        return null;
    }
}
