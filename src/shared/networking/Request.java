package shared.networking;

import com.google.gson.JsonElement;

import java.util.List;

/**
 * One request that the client sends to the server over the socket.
 * It holds the name of the method to call and the list of arguments for it.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class Request {
    /** The name of the service method the client wants to call. */
    private String method;
    /** The arguments for the method, kept as raw JSON so any type fits. */
    private List<JsonElement> args;

    /**
     * Creates a new request.
     *
     * @param method the name of the method to call
     * @param args the arguments for the method
     */
    public Request(String method, List<JsonElement> args) {
        this.method = method;
        this.args = args;
    }

    /**
     * Returns the method name.
     *
     * @return the method name
     */
    public String getMethod() {
        return method;
    }

    /**
     * Returns the arguments.
     *
     * @return the arguments
     */
    public List<JsonElement> getArgs() {
        return args;
    }
}
