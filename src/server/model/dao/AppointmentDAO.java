package server.model.dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import server.database.DatabaseConnection;
import server.model.Appointment;
import server.model.Doctor;
import server.model.Patient;

import javax.xml.crypto.Data;

/**
 * Handles reading and writing appointment rows in the database.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class AppointmentDAO {
    /** The instance. */
    private static AppointmentDAO instance;

    /**
     * Creates a new {@code AppointmentDAO} instance.
     *
     * @throws SQLException if the operation cannot be completed
     */
    private AppointmentDAO() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    /**
     * Returns the single shared instance of {@code AppointmentDAO}.
     *
     * @return the instance
     * @throws SQLException if the operation cannot be completed
     */
    public static synchronized AppointmentDAO getInstance() throws SQLException {
        if (instance == null) {
            instance = new AppointmentDAO();
        }
        return instance;
    }

    /**
     * Creates a new record.
     *
     * @param patient the patient
     * @param doctor the doctor
     * @param date the date
     * @param status the status
     * @param notes the notes
     * @return the resulting appointment
     * @throws SQLException if the operation cannot be completed
     */
    public Appointment create(Patient patient, Doctor doctor, LocalDateTime date, boolean status, String notes) throws SQLException {
        String sql = """
            INSERT INTO appointment(patient_id, doctor_id, appointment_date, status, notes)
            VALUES (?, ?, ?, ?, ?)
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, patient.getPatientID());
            statement.setInt(2, doctor.getDoctorID());
            statement.setTimestamp(3, Timestamp.valueOf(date));
            statement.setBoolean(4, status);
            statement.setString(5, notes);
            statement.executeUpdate();

            int generatedID = -1;
            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    generatedID = rs.getInt(1);
                }
            }

            Appointment appointment = new Appointment(patient, doctor, date, status, notes);
            appointment.setAppointmentID(generatedID);
            return appointment;
        }
    }

    /**
     * Returns the appointments by patient id.
     *
     * @param patientID the identifier of the patient
     * @return the appointments by patient id
     * @throws SQLException if the operation cannot be completed
     */
    public ArrayList<Appointment> getAppointmentsByPatientId(int patientID) throws SQLException {
        ArrayList<Appointment> appointments = new ArrayList<>();
        String sql = """
            SELECT appointment_id, patient_id, doctor_id, appointment_date, status, notes
            FROM appointment
            WHERE patient_id = ?
            ORDER BY appointment_date
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, patientID);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    appointments.add(extractAppointment(rs));
                }
            }
        }
        return appointments;
    }

    /**
     * Returns all appointments.
     *
     * @return the all appointments
     * @throws SQLException if the operation cannot be completed
     */
    public ArrayList<Appointment> getAllAppointments() throws SQLException {
        ArrayList<Appointment> appointments = new ArrayList<>();
        String sql = """
                SELECT *
                FROM appointment
                ORDER BY appointment_date
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    appointments.add(extractAppointment(rs));
                }
            }
        }
        return appointments;
    }

    /**
     * Returns the appointments by doctor id.
     *
     * @param doctorID the id of the doctor
     * @return the appointments by doctor id
     * @throws SQLException if the operation cannot be completed
     */
    public ArrayList<Appointment> getAppointmentsByDoctorId(int doctorID) throws SQLException {
        ArrayList<Appointment> appointments = new ArrayList<>();
        String sql = """
            SELECT appointment_id, patient_id, doctor_id, appointment_date, status, notes
            FROM appointment
            WHERE doctor_id = ?
            ORDER BY appointment_date
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, doctorID);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    appointments.add(extractAppointment(rs));
                }
            }
        }
        return appointments;
    }

    /**
     * Deletes the appointment.
     *
     * @param appointmentId the id of the appointment
     * @return {@code true} if the operation succeeded, otherwise {@code false}
     * @throws SQLException if the operation cannot be completed
     */
    public boolean deleteAppointment(int appointmentId) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM appointment WHERE appointment_id = ?")) {
            statement.setInt(1, appointmentId);
            return statement.executeUpdate() == 1;
        }
    }

    /**
     * Updates the appointment.
     *
     * @param appointmentId the id of the appointment
     * @param date the date
     * @param newDoctorId the id of the new doctor
     * @return the resulting appointment
     * @throws SQLException if the operation cannot be completed
     */
    public Appointment updateAppointment(int appointmentId, LocalDateTime date, int newDoctorId) throws SQLException {
        String sql = "UPDATE appointment SET appointment_date = ?, doctor_id = ? WHERE appointment_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setTimestamp(1, Timestamp.valueOf(date));
            statement.setInt(2, newDoctorId);
            statement.setInt(3, appointmentId);

            if (statement.executeUpdate() > 0) {
                return getAppointmentById(appointmentId);
            }
            return null;
        }
    }

    /**
     * Finishes the appointment.
     *
     * @param appointmentId the id of the appointment
     * @param status the status
     * @param notes the notes
     * @return the resulting appointment
     * @throws SQLException if the operation cannot be completed
     */
    public Appointment finishAppointment(int appointmentId, boolean status, String notes) throws SQLException {
        String appointmentSql = "UPDATE appointment SET status = ?, notes = ? WHERE appointment_id = ?";

        String patientSql = "UPDATE patient SET last_visit = ?, medical_notes = ? WHERE patient_id = ?";

        try (Connection connection = DatabaseConnection.getConnection())
        {
            connection.setAutoCommit(false);
            Appointment appointment = getAppointmentById(appointmentId);
            try
            {
                PreparedStatement appointmentStatement = connection.prepareStatement(appointmentSql);

                appointmentStatement.setBoolean(1, status);
                appointmentStatement.setString(2, notes);
                appointmentStatement.setInt(3, appointmentId);

                appointmentStatement.executeUpdate();

                PreparedStatement patientStatement = connection.prepareStatement(patientSql);

                patientStatement.setDate(1, Date.valueOf(appointment.getDate().toLocalDate()));
                patientStatement.setString(2, appointment.getPatient().getMedicalNotes()+" | "+notes);
                patientStatement.setInt(3, appointment.getPatient().getPatientID());

                patientStatement.executeUpdate();

                connection.commit();

                return appointment;
            }
            catch (Exception e)
            {
                connection.rollback();
                throw e;
            }
        }
    }

    /**
     * Returns the appointment by id.
     *
     * @param appointmentId the id of the appointment
     * @return the appointment by id
     * @throws SQLException if the operation cannot be completed
     */
    public Appointment getAppointmentById(int appointmentId) throws SQLException {
        String sql = """
            SELECT appointment_id, patient_id, doctor_id, appointment_date, status, notes
            FROM appointment
            WHERE appointment_id = ?
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, appointmentId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return extractAppointment(rs);
                }
            }
        }
        return null;
    }

    /**
     * Reads one appointment from the database result.
     *
     * @param rs the ResultSet
     * @return the resulting appointment
     * @throws SQLException if the operation cannot be completed
     */
    private Appointment extractAppointment(ResultSet rs) throws SQLException {
        int appointmentID = rs.getInt("appointment_id");
        int patientId = rs.getInt("patient_id");
        int doctorId = rs.getInt("doctor_id");
        Timestamp timestamp = rs.getTimestamp("appointment_date");
        LocalDateTime date = timestamp == null ? null : timestamp.toLocalDateTime();
        boolean status = rs.getBoolean("status");
        String notes = rs.getString("notes");

        Patient patient = PatientDAO.getInstance().getPatientById(patientId);
        Doctor doctor = DoctorDAO.getInstance().getDoctorById(doctorId);

        Appointment appointment = new Appointment(patient, doctor, date, status, notes);
        appointment.setAppointmentID(appointmentID);
        return appointment;
    }
}
