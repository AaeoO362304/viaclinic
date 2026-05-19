package client;

import server.model.bookAppointment.UserHandler;
import server.model.bookAppointment.UserHandlerManager;
import client.view.login.ViewHandler;
import client.viewModel.login.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class MyApplication extends Application{
    public void start(Stage primaryStage)
    {
        UserHandler model = new UserHandlerManager();
        ViewModelFactory viewModelFactory = new ViewModelFactory(model);
        ViewHandler view = new ViewHandler(viewModelFactory);
        view.start(primaryStage);
    }
}
