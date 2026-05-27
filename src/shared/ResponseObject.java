package shared;

/**
 * Carries the result, a success flag and a message back to the client.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class ResponseObject {
    /** The success. */
    private boolean success;
    /** The message. */
    private String message;
    /** The data. */
    private Object data;

    /**
     * Creates a new {@code ResponseObject} instance.
     */
    public ResponseObject() {}

    /**
     * Creates a new {@code ResponseObject} initialised with the given success, message, data.
     *
     * @param success the success
     * @param message the message
     * @param data the data
     */
    public ResponseObject(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    /**
     * Indicates whether success.
     *
     * @return {@code true} if success, otherwise {@code false}
     */
    public boolean isSuccess() { return success; }
    /**
     * Sets the success.
     *
     * @param success the success
     */
    public void setSuccess(boolean success) { this.success = success; }
    /**
     * Returns the message.
     *
     * @return the message
     */
    public String getMessage() { return message; }
    /**
     * Sets the message.
     *
     * @param message the message
     */
    public void setMessage(String message) { this.message = message; }
    /**
     * Returns the data.
     *
     * @return the data
     */
    public Object getData() { return data; }
    /**
     * Sets the data.
     *
     * @param data the data
     */
    public void setData(Object data) { this.data = data; }
}
