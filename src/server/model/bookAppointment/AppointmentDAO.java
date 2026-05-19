package server.model.bookAppointment;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AppointmentDAO {
    private static AppointmentDAO instance;

    private AppointmentDAO() throws SQLException
    {
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    public static synchronized  AppointmentDAO getInstance() throws SQLException
    {
        if (instance==null)
        {
            instance = new AppointmentDAO();
        }
        return instance;
    }

    private static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=viaclinic",
                "postgres", "362304");
    }

    public Appointment create(Patient patient, Doctor doctor, LocalDateTime date, boolean status, String notes) throws SQLException {
        try (Connection connection = getConnection())
        {
            PreparedStatement statement= connection.prepareStatement("INSERT INTO Appointment(patient_id,doctor_id,appointment_date,status,notes) VALUES(?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            LocalDateTime localDateTime = date;
            statement.setInt(1, patient.getPatientID());
            statement.setInt(2, doctor.getDoctorID());
            statement.setTimestamp(3, Timestamp.valueOf(localDateTime));
            statement.setBoolean(4, status);
            statement.setString(5, notes);

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            int generatedID=-1;
            if (rs.next())
            {
                generatedID=rs.getInt(1);
            }
            Appointment appointment = new Appointment(patient, doctor, date);
            appointment.setAppointmentID(generatedID);
            return appointment;
        }
    }

    public ArrayList<Appointment> getAppointmentsByPatientId(int patientID) throws SQLException {
        ArrayList<Appointment> appointments = new ArrayList<>();

        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT appointment_id, patient_id, doctor_id, appointment_date, status, notes FROM Appointment WHERE patient_id = ?");
            statement.setInt(1, patientID);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int appointmentID = rs.getInt("appointment_id");
                Timestamp timestamp = rs.getTimestamp("appointment_date");
                int doctorId = rs.getInt("doctor_id");
                boolean status = rs.getBoolean("status");
                String notes = rs.getString("notes");
                LocalDateTime date = timestamp.toLocalDateTime();

                Doctor doctor = DoctorDAO.getInstance().getDoctorById(doctorId);
                Patient patient = PatientDAO.getInstance().getPatientById(patientID);
                Appointment appointment = new Appointment(patient, doctor, date, status, notes);
                Patient patient1 = PatientDAO.getInstance().getPatientById(patientID);
                appointment.setPatient(patient1);
                appointment.setAppointmentID(appointmentID);
                appointments.add(appointment);
            }
        }

        return appointments;
    }

    public ArrayList<Appointment> getAppointmentsByDoctorId(int doctorID) throws SQLException {
        ArrayList<Appointment> appointments = new ArrayList<>();

        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT appointment_id, patient_id, doctor_id, appointment_date, status, notes FROM Appointment WHERE doctor_id = ?");
            statement.setInt(1, doctorID);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int appointmentID = rs.getInt("appointment_id");
                Timestamp timestamp = rs.getTimestamp("appointment_date");
                int patientId = rs.getInt("patient_id");
                boolean status = rs.getBoolean("status");
                String notes = rs.getString("notes");
                LocalDateTime date = timestamp.toLocalDateTime();

                Doctor doctor = DoctorDAO.getInstance().getDoctorById(doctorID);
                Patient patient = PatientDAO.getInstance().getPatientById(patientId);
                Appointment appointment = new Appointment(patient, doctor, date, status, notes);
                Doctor doctor1 = DoctorDAO.getInstance().getDoctorById(doctorID);
                appointment.setDoctor(doctor1);
                appointment.setAppointmentID(appointmentID);
                appointments.add(appointment);
            }
        }

        return appointments;
    }


    public boolean deleteAppointment(int appointmentId) throws SQLException
    {
        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM Appointment WHERE appointment_id = ?");
            statement.setInt(1, appointmentId);
            int affectedRows = statement.executeUpdate();
            return affectedRows == 1;
        }
    }

    public Appointment updateAppointment(int appointmentId, LocalDateTime date, int newDoctorId) throws SQLException {
        String sql = "UPDATE Appointment SET date = ?, doctor_id=? WHERE appointment_id = ?";

        try (Connection connection = getConnection();
        PreparedStatement statement = getConnection().prepareStatement(sql))
        {
            statement.setTimestamp(1, Timestamp.valueOf(date));
            statement.setInt(2, newDoctorId);
            statement.setInt(3, appointmentId);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated>0)
            {
                String fetchSql = "SELECT patient_id, doctor_id, appointment_date, status, notes FROM Appointment WHERE appointment_id = ?";
                try (PreparedStatement fetchStatement = connection.prepareStatement(fetchSql))
                {
                    fetchStatement.setInt(1, appointmentId);
                    ResultSet rs = fetchStatement.executeQuery();
                    if (rs.next())
                    {
                        Timestamp timestamp = rs.getTimestamp("date");
                        int patientId = rs.getInt("patient_id");
                        int doctorId = rs.getInt("doctor_id");
                        boolean status = rs.getBoolean("status");
                        String notes = rs.getString("notes");

                        Doctor doctor = DoctorDAO.getInstance().getDoctorById(doctorId);
                        Patient patient = PatientDAO.getInstance().getPatientById(patientId);

                        Appointment updatedAppointment = new Appointment(patient, doctor, date, status, notes);
                        updatedAppointment.setPatient(patient);
                        updatedAppointment.setAppointmentID(appointmentId);
                        return updatedAppointment;
                    }
                }
            }
            return null;
        }
    }

    }
