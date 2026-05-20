package server.model.bookAppointment;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class PatientDAO {
    private static PatientDAO instance;

    private PatientDAO() {}

    public static synchronized PatientDAO getInstance()
    {
        if (instance == null)
        {
            instance = new PatientDAO();
        }
        return instance;
    }

    private static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=viaclinic",
                "postgres", "362304");
    }

    public Patient createPatient(Patient patient) throws SQLException
    {
        String userSql = """
            INSERT INTO users
            (first_name, last_name, day_of_birth, gender, phone_num, email, password, username)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;

        String patientSql = """
            INSERT INTO patient
            (patient_id, last_visit, medical_notes, cpr)
            VALUES (?, ?, ?, ?)
            """;

        try (Connection connection = getConnection())
        {
            connection.setAutoCommit(false);

            try
            {
                PreparedStatement userStatement = connection.prepareStatement(
                        userSql,
                        Statement.RETURN_GENERATED_KEYS
                );

                userStatement.setString(1, patient.getFirstName());
                userStatement.setString(2, patient.getLastName());
                userStatement.setObject(3, patient.getDayOfBirth());
                userStatement.setString(4, patient.getGender());
                userStatement.setString(5, patient.getPhoneNum());
                userStatement.setString(6, patient.getEmail());
                userStatement.setString(7, patient.getPassword());
                userStatement.setString(8, patient.getUserName());

                userStatement.executeUpdate();

                ResultSet rs = userStatement.getGeneratedKeys();

                int generatedUserId = -1;

                if (rs.next())
                {
                    generatedUserId = rs.getInt(1);
                }

                PreparedStatement patientStatement = connection.prepareStatement(patientSql);

                patientStatement.setInt(1, generatedUserId);
                patientStatement.setTimestamp(2, null);
                patientStatement.setString(3, null);
                patientStatement.setString(4, patient.getCPR());

                patientStatement.executeUpdate();

                connection.commit();

                patient.setPatientId(generatedUserId);
                return patient;
            }
            catch (Exception e)
            {
                connection.rollback();
                throw e;
            }
        }
    }

    public Patient getPatientByUsername(String username) throws SQLException
    {
        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT u.id AS patient_id, u.first_name, u.last_name, u.day_of_birth, u.gender, u.phone_num, u.email, u.password, u.username, p.last_visit, p.medical_notes, p.cpr FROM users u JOIN patient p ON u.id = p.patient_id WHERE u.username = ?");
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                return extractPatient(resultSet);
            }
        }
        return null;
    }

    public Patient getPatientById(int patientId) throws SQLException
    {
        String sql = """
            SELECT
                p.patient_id,
                p.last_visit,
                p.medical_notes,
                p.cpr,
                u.first_name,
                u.last_name,
                u.day_of_birth,
                u.gender,
                u.phone_num,
                u.email,
                u.password,
                u.username
            FROM patient p
            JOIN users u ON p.patient_id = u.id
            WHERE p.patient_id = ?
            """;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, patientId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
            {
                return extractPatient(resultSet);
            }
        }

        return null;
    }

    public void updatePatient(String userName) throws SQLException {
        Patient patient = getPatientByUsername(userName);
    }

    public ArrayList<Patient> getAllPatients() throws SQLException
    {
        ArrayList<Patient> patients = new ArrayList<>();
        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Patient");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                patients.add(extractPatient(resultSet));
            }
        }
        return patients;
    }

    private Patient extractPatient(ResultSet resultSet) throws SQLException
    {
        int id = resultSet.getInt("patient_id");

        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String email = resultSet.getString("email");
        String phoneNumber = resultSet.getString("phone_num");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        String gender = resultSet.getString("gender");
        String cpr = resultSet.getString("cpr");
        String notes = resultSet.getString("medical_notes");

        LocalDate dayOfBirth = resultSet.getObject("day_of_birth", LocalDate.class);

        Timestamp lastVisitTimestamp = resultSet.getTimestamp("last_visit");
        LocalDate lastVisit = null;

        if (lastVisitTimestamp != null)
        {
            lastVisit = lastVisitTimestamp.toLocalDateTime().toLocalDate();
        }

        Patient patient = new Patient(
                firstName,
                lastName,
                password,
                email,
                gender,
                phoneNumber,
                dayOfBirth,
                username,
                cpr
        );

        patient.setPatientId(id);
        patient.setUserName(username);
        patient.setMedicalNotes(notes);
        patient.setLastVisit(lastVisit);

        return patient;
    }
}
