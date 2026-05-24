package client;

import client.model.ClinicClient;
import client.networking.SocketClinicClient;
import client.view.login.ViewHandler;
import client.viewModel.login.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Sets up the client, view models and view handler when the program starts.
 * The client now talks to the server over a socket on localhost, so the server
 * program ({@code server.networking.ClinicServer}) must be running first.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class MyApplication extends Application {
    /**
     * Starts the application and shows the primary stage.
     *
     * @param primaryStage the primary stage
     */
    @Override
    public void start(Stage primaryStage)
    {
        try
        {
            ClinicClient client = new SocketClinicClient();

            ViewModelFactory viewModelFactory = new ViewModelFactory(client);
            ViewHandler viewHandler = new ViewHandler(viewModelFactory);

            viewHandler.start(primaryStage);
        }
        catch (Exception e)
        {
            System.err.println("Could not connect to the server.");
            System.err.println("Start ClinicServer first. Reason: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
