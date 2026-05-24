package server.networking;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import shared.*;
import shared.networking.JsonUtil;
import shared.networking.Request;
import shared.networking.Response;
import server.service.ClinicService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Handles one connected client on its own thread. It reads JSON requests line by
 * line, calls the matching method on the clinic service, and sends a JSON response
 * back. A new ClientHandler is created and started for every client that connects,
 * which is how the server serves several clients at the same time.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class ClientHandler implements Runnable {

    /** The socket connected to this one client. */
    private final Socket socket;
    /** The clinic service that does the real work. */
    private final ClinicService service;
    /** The shared Gson instance for converting to and from JSON. */
    private final Gson gson = JsonUtil.gson();

    /**
     * Creates a new handler for one client.
     *
     * @param socket the socket connected to the client
     * @param service the clinic service to call
     */
    public ClientHandler(Socket socket, ClinicService service) {
        this.socket = socket;
        this.service = service;
    }

    /**
     * Reads requests from the client until it disconnects, answering each one.
     */
    @Override
    public void run() {
        System.out.println("Client connected: " + socket.getRemoteSocketAddress());
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             BufferedWriter out = new BufferedWriter(
                     new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8))) {

            String line;
            while ((line = in.readLine()) != null) {
                Response response;
                try {
                    Request request = gson.fromJson(line, Request.class);
                    response = handle(request);
                } catch (Exception e) {
                    response = Response.fail(e.getMessage() == null ? e.toString() : e.getMessage());
                }
                out.write(gson.toJson(response));
                out.newLine();
                out.flush();
            }
        } catch (Exception e) {
            System.out.println("Connection error: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (Exception ignored) {
            }
            System.out.println("Client disconnected: " + socket.getRemoteSocketAddress());
        }
    }

    /**
     * Looks at the method name in the request and calls the matching service method.
     *
     * @param request the request from the client
     * @return the response to send back
     * @throws Exception if the service call fails
     */
    private Response handle(Request request) throws Exception {
        List<JsonElement> a = request.getArgs();
        switch (request.getMethod()) {
            case "login":
                return Response.ok(toJson(service.login(str(a, 0), str(a, 1))));
            case "logout":
                service.logout();
                return Response.ok(JsonNull.INSTANCE);
            case "createPatient":
                return Response.ok(toJson(service.createPatient(arg(a, 0, PatientDTO.class))));
            case "updatePatient":
                return Response.ok(toJson(service.updatePatient(arg(a, 0, PatientDTO.class))));
            case "createDoctor":
                return Response.ok(toJson(service.createDoctor(arg(a, 0, DoctorDTO.class))));
            case "createReceptionist":
                return Response.ok(toJson(service.createReceptionist(arg(a, 0, ReceptionistDTO.class))));
            case "getAllPatients":
                return Response.ok(toJson(service.getAllPatients()));
            case "getAllDoctors":
                return Response.ok(toJson(service.getAllDoctors()));
            case "getAllReceptionists":
                return Response.ok(toJson(service.getAllReceptionists()));
            case "getAllAppointments":
                return Response.ok(toJson(service.getAllAppointments()));
            case "getPatientById":
                return Response.ok(toJson(service.getPatientById(integer(a, 0))));
            case "deletePatient":
                service.deletePatient(integer(a, 0));
                return Response.ok(JsonNull.INSTANCE);
            case "createAppointment":
                return Response.ok(toJson(service.createAppointment(
                        integer(a, 0), integer(a, 1),
                        arg(a, 2, LocalDateTime.class), bool(a, 3), str(a, 4))));
            case "updateAppointment":
                return Response.ok(toJson(service.updateAppointment(
                        integer(a, 0), integer(a, 1), arg(a, 2, LocalDateTime.class))));
            case "deleteAppointment":
                service.deleteAppointment(integer(a, 0));
                return Response.ok(JsonNull.INSTANCE);
            case "finishAppointment":
                return Response.ok(toJson(service.finishAppointment(integer(a, 0), str(a, 1))));
            case "getAppointmentsByPatientId":
                return Response.ok(toJson(service.getAppointmentsByPatientId(integer(a, 0))));
            case "getAppointmentsByDoctorId":
                return Response.ok(toJson(service.getAppointmentsByDoctorId(integer(a, 0))));
            default:
                return Response.fail("Unknown method: " + request.getMethod());
        }
    }

    /**
     * Converts a returned value into JSON.
     *
     * @param value the value to convert
     * @return the value as JSON
     */
    private JsonElement toJson(Object value) {
        return gson.toJsonTree(value);
    }

    /**
     * Reads one argument and converts it from JSON into the wanted type.
     *
     * @param args the list of arguments
     * @param index the position of the argument
     * @param type the class to convert into
     * @param <T> the type to return
     * @return the converted argument
     */
    private <T> T arg(List<JsonElement> args, int index, Class<T> type) {
        return gson.fromJson(args.get(index), type);
    }

    /**
     * Reads one argument as a String.
     *
     * @param args the list of arguments
     * @param index the position of the argument
     * @return the argument as a String
     */
    private String str(List<JsonElement> args, int index) {
        JsonElement e = args.get(index);
        return e.isJsonNull() ? null : e.getAsString();
    }

    /**
     * Reads one argument as an int.
     *
     * @param args the list of arguments
     * @param index the position of the argument
     * @return the argument as an int
     */
    private int integer(List<JsonElement> args, int index) {
        return args.get(index).getAsInt();
    }

    /**
     * Reads one argument as a boolean.
     *
     * @param args the list of arguments
     * @param index the position of the argument
     * @return the argument as a boolean
     */
    private boolean bool(List<JsonElement> args, int index) {
        return args.get(index).getAsBoolean();
    }
}
