package client.viewModel.login;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import server.model.bookAppointment.UserHandler;

public class ReceptionistViewModel {

    private UserHandler handler;
    private StringProperty name;
    private LoginViewModel loginViewModel;

    public ReceptionistViewModel(UserHandler handler, LoginViewModel loginViewModel) {
        this.handler=handler;
        this.name = new SimpleStringProperty();
        this.loginViewModel=loginViewModel;
    }

    public void clear() {
        name.set("");
    }

    public StringProperty getNameProperty() {
        return name;
    }

    public LoginViewModel getLoginViewModel() {
        return loginViewModel;
    }
}
