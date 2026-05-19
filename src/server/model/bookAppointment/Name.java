package server.model.bookAppointment;

public class Name
{
    private String name;
    private Email email;

    public Name(String name, Email email)
    {
        if (name == null || name.length() < 3)
        {
            throw new IllegalArgumentException(
                    "Username has to have at least 3 characters");
        } this.name = name;
        this.email = email;
    }

    public Name(Email email)
    {
        this(email + "", email);
    }

    public Name(String userName, String email)
    {
        this(userName, new Email(email));
    }

    public Name(String email)
    {
        this(email + "", new Email(email));
    }

    public String getName()
    {
        return name;
    }

    public Email getEmail()
    {
        return email;
    }

    @Override public String toString()
    {
        return name + " (" + email + ')';
    }
}
