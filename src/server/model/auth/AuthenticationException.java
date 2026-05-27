package server.model.auth;

/**
 * Error thrown when a user's login fails.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class AuthenticationException extends Exception
{
    /**
     * Creates a new {@code AuthenticationException} initialised with the given message.
     *
     * @param message the message
     */
    public AuthenticationException(String message)
    {
        super(message);
    }

    /**
     * Creates a new {@code AuthenticationException} initialised with the given message, cause.
     *
     * @param message the message
     * @param cause the cause
     */
    public AuthenticationException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
