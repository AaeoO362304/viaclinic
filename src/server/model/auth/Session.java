package server.model.auth;

import server.model.bookAppointment.User;

public class Session
{
    private final User user;
    private final Role role;

    public Session(User user, Role role)
    {
        this.user = user;
        this.role = role;
    }

    public User getUser()
    {
        return user;
    }

    public Role getRole()
    {
        return role;
    }

    public String getDisplayName()
    {
        if (user == null) return "";
        return user.getFirstName() + " " + user.getLastName();
    }
}
