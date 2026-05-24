package server.model.bookAppointment;

/**
 * Stores a user name and its email and checks that they are valid.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class Name
{
    /** The name. */
    private String name;
    /** The email. */
    private Email email;

    /**
     * Creates a new {@code Name} initialised with the given name, email.
     *
     * @param name the name
     * @param email the email
     */
    public Name(String name, Email email)
    {
        if (name == null || name.length() < 3)
        {
            throw new IllegalArgumentException(
                    "Username has to have at least 3 characters");
        } this.name = name;
        this.email = email;
    }

    /**
     * Creates a new {@code Name} initialised with the given email.
     *
     * @param email the email
     */
    public Name(Email email)
    {
        this(email + "", email);
    }

    /**
     * Creates a new {@code Name} initialised with the given user name, email.
     *
     * @param userName the user name
     * @param email the email
     */
    public Name(String userName, String email)
    {
        this(userName, new Email(email));
    }

    /**
     * Creates a new {@code Name} initialised with the given email.
     *
     * @param email the email
     */
    public Name(String email)
    {
        this(email + "", new Email(email));
    }

    /**
     * Returns the name.
     *
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the email.
     *
     * @return the email
     */
    public Email getEmail()
    {
        return email;
    }

    /**
     * Returns this object as a String.
     *
     * @return the resulting string
     */
    @Override public String toString()
    {
        return name + " (" + email + ')';
    }
}
