package client;

import client.model.ClinicClient;
import client.model.ClinicClientProxy;
import client.view.login.ViewHandler;
import client.viewModel.login.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class MyApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        ClinicClient client = new ClinicClientProxy();
        ViewModelFactory viewModelFactory = new ViewModelFactory(client);
        ViewHandler view = new ViewHandler(viewModelFactory);
        view.start(primaryStage);
    }
}
