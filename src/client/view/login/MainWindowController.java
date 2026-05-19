package client.view.login;

import client.viewModel.login.CreateAccountViewModel;
import client.viewModel.login.MainViewModel;
import com.sun.tools.javac.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;

import javax.swing.*;

public class MainWindowController {

    @FXML private Button registerButton;
    @FXML private Button loginButton;
    @FXML private Button closeButton;

    private Region root;
    private MainViewModel viewModel;
    private ViewHandler viewHandler;

    public MainWindowController() {

    }

    public void init(ViewHandler viewHandler,
                     MainViewModel viewModel, Region root){
        this.viewModel = viewModel;
        this.viewHandler = viewHandler;
        this.root = root;
    }

    @FXML private void registerButtonPressed() {viewHandler.openView("register");}

    @FXML private void loginButtonPressed() {viewHandler.openView("login");}

    @FXML
    private void closeButtonPressed() {viewHandler.closeView();}

    public Region getRoot()
    {
        return root;
    }


}
