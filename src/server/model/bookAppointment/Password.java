package server.model.bookAppointment;

/**
 * Stores a password and checks that it is valid.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class Password
{
    /** The password. */
    private String password;

    /**
     * Creates a new {@code Password} initialised with the given password.
     *
     * @param password the password
     */
    public Password(String password)
    {
        String message = Password.isLegal(password);
        if (message != null)
        {
            throw new IllegalArgumentException(message);
        }
        this.password = password;
    }

    /**
     * Indicates whether legal password.
     *
     * @param password the password
     * @return {@code true} if legal password, otherwise {@code false}
     */
    public static boolean isLegalPassword(String password)
    {
        return isLegal(password) == null;
    }

    /**
     * Checks whether the value is allowed.
     *
     * @param password the password
     * @return the resulting string
     */
    private static String isLegal(String password)
    {
        if (password == null || password.length() < 6)
        {
            return "Password must have at least 6 characters";
        }
        int lower = 0;
        int upper = 0;
        int digit = 0;
        int special = 0;
        for (int i=0; i<password.length(); i++)
        {
            char ch = password.charAt(i);
            if (Character.isDigit(ch))
            {
                digit++;
            }
            else if (Character.isLowerCase(ch) && Character.isLetter(ch))
            {
                lower++;
            }
            else if (Character.isUpperCase(ch) && Character.isLetter(ch))
            {
                upper++;
            }
            else if (ch == '_' || ch == '-')
            {
                special++;
            }
        }
        if (lower + upper + digit + special < password.length())
        {
            return "Password may only contain letters, digits, hyphens and underscore characters";
        }
        if (lower == 0 || upper == 0 || digit == 0)
        {
            return "Password must contain at least one uppercase letter, at least one lowercase letter and at least one digit";
        }

        return null;
    }

    /**
     * Returns the password.
     *
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Returns this object as a String.
     *
     * @return the resulting string
     */
    @Override public String toString()
    {
        return password;
    }

}
