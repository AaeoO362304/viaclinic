package client.networking;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import client.model.ClinicClient;
import shared.*;
import shared.networking.JsonUtil;
import shared.networking.Request;
import shared.networking.Response;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Client-side version of the clinic client that talks to the server over a socket.
 * It connects to the server on localhost, and for every method it turns the call into
 * a JSON request, sends it, waits for the JSON response, and turns it back into objects.
 * Because it implements the same ClinicClient interface, it can be used in place of the
 * old in-process proxy without changing the view models.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class SocketClinicClient implements ClinicClient {

    /** The host the client connects to (the local machine). */
    private static final String HOST = "localhost";
    /** The port the server listens on. */
    private static final int PORT = 2910;

    /** The socket connection to the server. */
    private final Socket socket;
    /** Reader for responses coming from the server. */
    private final BufferedReader in;
    /** Writer for requests going to the server. */
    private final BufferedWriter out;
    /** The shared Gson instance for converting to and from JSON. */
    private final Gson gson = JsonUtil.gson();

    /**
     * Connects to the server on localhost.
     *
     * @throws Exception if the connection cannot be opened
     */
    public SocketClinicClient() throws Exception {
        this.socket = new Socket(HOST, PORT);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
    }

    /**
     * Sends one request to the server and returns the raw JSON result.
     *
     * @param method the name of the method to call
     * @param args the arguments for the method
     * @return the result value as JSON
     * @throws Exception if the call fails or the server returns an error
     */
    private synchronized JsonElement call(String method, Object... args) throws Exception {
        List<JsonElement> jsonArgs = Arrays.stream(args)
                .map(gson::toJsonTree)
                .collect(Collectors.toList());

        out.write(gson.toJson(new Request(method, jsonArgs)));
        out.newLine();
        out.flush();

        String line = in.readLine();
        if (line == null) {
            throw new Exception("Server closed the connection.");
        }
        Response response = gson.fromJson(line, Response.class);
        if (!response.isSuccess()) {
            throw new Exception(response.getError());
        }
        return response.getResult();
    }

    /**
     * Converts a JSON result into one object of the given type.
     *
     * @param json the JSON to convert
     * @param type the class to convert into
     * @param <T> the type to return
     * @return the converted object
     */
    private <T> T one(JsonElement json, Class<T> type) {
        return gson.fromJson(json, type);
    }

    /**
     * Authenticates the user and starts a session.
     *
     * @param username the username
     * @param password the password
     * @return the resulting session
     * @throws Exception if the call fails
     */
    @Override
    public SessionDTO login(String username, String password) throws Exception {
        return one(call("login", username, password), SessionDTO.class);
    }

    /**
     * Logs the user out.
     *
     * @throws Exception if the call fails
     */
    @Override
    public void logout() throws Exception {
        call("logout");
    }

    /**
     * Creates a new patient.
     *
     * @param patient the patient
     * @return the resulting patient
     * @throws Exception if the call fails
     */
    @Override
    public PatientDTO createPatient(PatientDTO patient) throws Exception {
        return one(call("createPatient", patient), PatientDTO.class);
    }

    /**
     * Updates the patient.
     *
     * @param patientDTO the patient
     * @return the resulting patient
     * @throws Exception if the call fails
     */
    @Override
    public PatientDTO updatePatient(PatientDTO patientDTO) throws Exception {
        return one(call("updatePatient", patientDTO), PatientDTO.class);
    }

    /**
     * Creates a new doctor.
     *
     * @param doctor the doctor
     * @return the resulting doctor
     * @throws Exception if the call fails
     */
    @Override
    public DoctorDTO createDoctor(DoctorDTO doctor) throws Exception {
        return one(call("createDoctor", doctor), DoctorDTO.class);
    }

    /**
     * Creates a new receptionist.
     *
     * @param receptionist the receptionist
     * @return the resulting receptionist
     * @throws Exception if the call fails
     */
    @Override
    public ReceptionistDTO createReceptionist(ReceptionistDTO receptionist) throws Exception {
        return one(call("createReceptionist", receptionist), ReceptionistDTO.class);
    }

    /**
     * Returns all patients.
     *
     * @return all patients
     * @throws Exception if the call fails
     */
    @Override
    public ArrayList<PatientDTO> getAllPatients() throws Exception {
        return gson.fromJson(call("getAllPatients"), new TypeToken<ArrayList<PatientDTO>>() {}.getType());
    }

    /**
     * Returns all doctors.
     *
     * @return all doctors
     * @throws Exception if the call fails
     */
    @Override
    public ArrayList<DoctorDTO> getAllDoctors() throws Exception {
        return gson.fromJson(call("getAllDoctors"), new TypeToken<ArrayList<DoctorDTO>>() {}.getType());
    }

    /**
     * Returns all receptionists.
     *
     * @return all receptionists
     * @throws Exception if the call fails
     */
    @Override
    public ArrayList<ReceptionistDTO> getAllReceptionists() throws Exception {
        return gson.fromJson(call("getAllReceptionists"), new TypeToken<ArrayList<ReceptionistDTO>>() {}.getType());
    }

    /**
     * Returns all appointments.
     *
     * @return all appointments
     * @throws Exception if the call fails
     */
    @Override
    public ArrayList<AppointmentDTO> getAllAppointments() throws Exception {
        return gson.fromJson(call("getAllAppointments"), new TypeToken<ArrayList<AppointmentDTO>>() {}.getType());
    }

    /**
     * Returns the patient with the given id.
     *
     * @param patientId the identifier of the patient
     * @return the patient
     * @throws Exception if the call fails
     */
    @Override
    public PatientDTO getPatientById(int patientId) throws Exception {
        return one(call("getPatientById", patientId), PatientDTO.class);
    }

    /**
     * Deletes the patient.
     *
     * @param patientId the identifier of the patient
     * @throws Exception if the call fails
     */
    @Override
    public void deletePatient(int patientId) throws Exception {
        call("deletePatient", patientId);
    }

    /**
     * Creates a new appointment.
     *
     * @param patientId the identifier of the patient
     * @param doctorId the identifier of the doctor
     * @param date the date
     * @param status the status
     * @param notes the notes
     * @return the resulting appointment
     * @throws Exception if the call fails
     */
    @Override
    public AppointmentDTO createAppointment(int patientId, int doctorId, LocalDateTime date, boolean status, String notes) throws Exception {
        return one(call("createAppointment", patientId, doctorId, date, status, notes), AppointmentDTO.class);
    }

    /**
     * Updates the appointment.
     *
     * @param appointmentId the identifier of the appointment
     * @param doctorId the identifier of the doctor
     * @param date the date
     * @return the resulting appointment
     * @throws Exception if the call fails
     */
    @Override
    public AppointmentDTO updateAppointment(int appointmentId, int doctorId, LocalDateTime date) throws Exception {
        return one(call("updateAppointment", appointmentId, doctorId, date), AppointmentDTO.class);
    }

    /**
     * Deletes the appointment.
     *
     * @param appointmentId the identifier of the appointment
     */
    @Override
    public void deleteAppointment(int appointmentId) {
        try {
            call("deleteAppointment", appointmentId);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * Finishes the appointment.
     *
     * @param appointmentId the identifier of the appointment
     * @param notes the notes
     * @return the resulting appointment
     * @throws Exception if the call fails
     */
    @Override
    public AppointmentDTO finishAppointment(int appointmentId, String notes) throws Exception {
        return one(call("finishAppointment", appointmentId, notes), AppointmentDTO.class);
    }

    /**
     * Returns the appointments for one patient.
     *
     * @param patientId the identifier of the patient
     * @return the appointments
     * @throws Exception if the call fails
     */
    @Override
    public ArrayList<AppointmentDTO> getAppointmentsByPatientId(int patientId) throws Exception {
        return gson.fromJson(call("getAppointmentsByPatientId", patientId),
                new TypeToken<ArrayList<AppointmentDTO>>() {}.getType());
    }

    /**
     * Returns the appointments for one doctor.
     *
     * @param doctorId the identifier of the doctor
     * @return the appointments
     * @throws Exception if the call fails
     */
    @Override
    public ArrayList<AppointmentDTO> getAppointmentsByDoctorId(int doctorId) throws Exception {
        return gson.fromJson(call("getAppointmentsByDoctorId", doctorId),
                new TypeToken<ArrayList<AppointmentDTO>>() {}.getType());
    }
}
