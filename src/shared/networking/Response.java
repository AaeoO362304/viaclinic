package shared.networking;

import com.google.gson.JsonElement;

/**
 * One response that the server sends back to the client over the socket.
 * If the call worked, success is true and result holds the returned value as JSON.
 * If it failed, success is false and error holds the error message.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class Response {
    /** True if the call worked, false if there was an error. */
    private boolean success;
    /** The returned value as raw JSON (null for void methods or on error). */
    private JsonElement result;
    /** The error message when success is false. */
    private String error;

    /**
     * Builds a success response.
     *
     * @param result the returned value as JSON
     * @return the response
     */
    public static Response ok(JsonElement result) {
        Response r = new Response();
        r.success = true;
        r.result = result;
        return r;
    }

    /**
     * Builds an error response.
     *
     * @param error the error message
     * @return the response
     */
    public static Response fail(String error) {
        Response r = new Response();
        r.success = false;
        r.error = error;
        return r;
    }

    /**
     * Indicates whether the call worked.
     *
     * @return true if the call worked, otherwise false
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Returns the result value as JSON.
     *
     * @return the result
     */
    public JsonElement getResult() {
        return result;
    }

    /**
     * Returns the error message.
     *
     * @return the error message
     */
    public String getError() {
        return error;
    }
}
