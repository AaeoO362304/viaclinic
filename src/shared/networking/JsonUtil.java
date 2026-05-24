package shared.networking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Creates the single shared Gson object used by both the client and the server.
 * It adds support for LocalDate and LocalDateTime, which Gson cannot convert on its own.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public final class JsonUtil {

    /** The one Gson instance shared by the whole program. */
    private static final Gson GSON = build();

    /** Private constructor so nobody makes an instance of this helper class. */
    private JsonUtil() {}

    /**
     * Returns the shared Gson instance.
     *
     * @return the Gson instance
     */
    public static Gson gson() {
        return GSON;
    }

    /**
     * Builds the Gson instance and registers the date adapters.
     *
     * @return the configured Gson instance
     */
    private static Gson build() {
        GsonBuilder builder = new GsonBuilder();

        // LocalDateTime <-> ISO string (e.g. "2026-05-23T10:00")
        builder.registerTypeAdapter(LocalDateTime.class,
                (JsonSerializer<LocalDateTime>) (src, type, ctx) ->
                        src == null ? null : new JsonPrimitive(src.toString()));
        builder.registerTypeAdapter(LocalDateTime.class,
                (JsonDeserializer<LocalDateTime>) (json, type, ctx) ->
                        json == null || json.isJsonNull() ? null : LocalDateTime.parse(json.getAsString()));

        // LocalDate <-> ISO string (e.g. "2026-05-23")
        builder.registerTypeAdapter(LocalDate.class,
                (JsonSerializer<LocalDate>) (src, type, ctx) ->
                        src == null ? null : new JsonPrimitive(src.toString()));
        builder.registerTypeAdapter(LocalDate.class,
                (JsonDeserializer<LocalDate>) (json, type, ctx) ->
                        json == null || json.isJsonNull() ? null : LocalDate.parse(json.getAsString()));

        return builder.create();
    }
}
