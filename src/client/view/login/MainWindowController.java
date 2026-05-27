package client.view.login;

import client.view.ViewHandler;
import client.viewModel.MainViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;


/**
 * Controller for the Main Window view.
 * Reacts to button clicks and other input in this window and calls the view model to do the work.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class MainWindowController {

    /** The register button. */
    @FXML private Button registerButton;
    @FXML private Button loginButton;
    @FXML private Button closeButton;

    /** The root. */
    private Region root;
    /** The view model. */
    private MainViewModel viewModel;
    /** The view handler. */
    private ViewHandler viewHandler;

    /**
     * Creates a new {@code MainWindowController} instance.
     */
    public MainWindowController() {

    }

    /**
     * Sets up the controller with its view handler, view model and root.
     *
     * @param viewHandler the view handler
     * @param viewModel the view model
     * @param root the root
     */
    public void init(ViewHandler viewHandler,
                     MainViewModel viewModel, Region root){
        this.viewModel = viewModel;
        this.viewHandler = viewHandler;
        this.root = root;
    }

    /**
     * Handles the register button being pressed in the view.
     */
    @FXML private void registerButtonPressed() {viewHandler.openView("register");}

    /**
     * Handles the login button being pressed in the view.
     */
    @FXML private void loginButtonPressed() {viewHandler.openView("login");}

    /**
     * Handles the close button being pressed in the view.
     */
    @FXML
    private void closeButtonPressed() {viewHandler.closeView();}

    /**
     * Returns the root of this window.
     *
     * @return the root
     */
    public Region getRoot()
    {
        return root;
    }


}
