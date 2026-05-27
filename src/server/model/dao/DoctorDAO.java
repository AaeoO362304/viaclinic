package server.model.dao;

import server.database.DatabaseConnection;
import server.model.Doctor;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Handles reading and writing doctor rows in the database.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class DoctorDAO {

    /** The instance. */
    private static DoctorDAO instance;

    /**
     * Creates a new {@code DoctorDAO} instance.
     */
    private DoctorDAO() {}

    /**
     * Returns the single shared instance of {@code DoctorDAO}.
     *
     * @return the instance
     */
    public static synchronized DoctorDAO getInstance()
    {
        if (instance == null)
        {
            instance = new DoctorDAO();
        }
        return instance;
    }

    /**
     * Creates a new doctor.
     *
     * @param doctor the doctor
     * @return the resulting doctor
     * @throws SQLException if the operation cannot be completed
     */
    public Doctor createDoctor(Doctor doctor) throws SQLException
    {
        String userSql = """
            INSERT INTO users
            (first_name, last_name, day_of_birth, gender, phone_num, email, password, username)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;

        String doctorSql = """
            INSERT INTO doctor
            (doctor_id, specialization)
            VALUES (?, ?)
            """;

        try (Connection connection = DatabaseConnection.getConnection())
        {
            connection.setAutoCommit(false);

            try
            {
                PreparedStatement userStatement = connection.prepareStatement(
                        userSql,
                        Statement.RETURN_GENERATED_KEYS
                );

                userStatement.setString(1, doctor.getFirstName());
                userStatement.setString(2, doctor.getLastName());
                userStatement.setObject(3, doctor.getDayOfBirth());
                userStatement.setString(4, doctor.getGender());
                userStatement.setString(5, doctor.getPhoneNum());
                userStatement.setString(6, doctor.getEmail());
                userStatement.setString(7, doctor.getPassword());
                userStatement.setString(8, doctor.getUserName());

                userStatement.executeUpdate();

                ResultSet rs = userStatement.getGeneratedKeys();

                int generatedUserId = -1;

                if (rs.next())
                {
                    generatedUserId = rs.getInt(1);
                }

                PreparedStatement patientStatement = connection.prepareStatement(doctorSql);

                patientStatement.setInt(1, generatedUserId);
                patientStatement.setString(2, doctor.getSpecialization());

                patientStatement.executeUpdate();

                connection.commit();

                doctor.setDoctorID(generatedUserId);
                return doctor;
            }
            catch (Exception e)
            {
                connection.rollback();
                throw e;
            }
        }
    }

    /**
     * Returns the doctor by username.
     *
     * @param username the username
     * @return the doctor by username
     * @throws SQLException if the operation cannot be completed
     */
    public Doctor getDoctorByUsername(String username) throws SQLException
    {
        String sql = """
            SELECT
                d.doctor_id,
                d.specialization,
                u.first_name,
                u.last_name,
                u.day_of_birth,
                u.gender,
                u.phone_num,
                u.email,
                u.password,
                u.username
            FROM doctor d
            JOIN users u ON d.doctor_id = u.id
            WHERE u.username = ?
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
            {
                return extractDoctor(resultSet);
            }
        }

        return null;
    }

    /**
     * Returns the doctor by id.
     *
     * @param doctorId the id of the doctor
     * @return the doctor by id
     * @throws SQLException if the operation cannot be completed
     */
    public Doctor getDoctorById(int doctorId) throws SQLException
    {
        String sql = """
            SELECT
                d.doctor_id,
                d.specialization,
                u.first_name,
                u.last_name,
                u.email,
                u.phone_num,
                u.username,
                u.password,
                u.gender,
                u.day_of_birth
            FROM doctor d
            JOIN users u ON d.doctor_id = u.id
            WHERE d.doctor_id = ?
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, doctorId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
            {
                return extractDoctor(resultSet);
            }
        }

        return null;
    }

    /**
     * Returns all doctors.
     *
     * @return the all doctors
     * @throws SQLException if the operation cannot be completed
     */
    public ArrayList<Doctor> getAllDoctors() throws SQLException
    {
        ArrayList<Doctor> doctors = new ArrayList<>();

        String sql = """
            SELECT
                d.doctor_id,
                d.specialization,
                u.first_name,
                u.last_name,
                u.email,
                u.phone_num,
                u.username,
                u.password,
                u.gender,
                u.day_of_birth
            FROM doctor d
            JOIN users u ON d.doctor_id = u.id
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery())
        {
            while (resultSet.next())
            {
                doctors.add(extractDoctor(resultSet));
            }
        }

        return doctors;
    }

    /**
     * Reads one doctor from the database result.
     *
     * @param resultSet the result set
     * @return the resulting doctor
     * @throws SQLException if the operation cannot be completed
     */
    private Doctor extractDoctor(ResultSet resultSet) throws SQLException
    {
        int id = resultSet.getInt("doctor_id");

        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String email = resultSet.getString("email");
        String phoneNumber = resultSet.getString("phone_num");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        String gender = resultSet.getString("gender");
        String specialization = resultSet.getString("specialization");

        LocalDate dayOfBirth = resultSet.getObject("day_of_birth", LocalDate.class);

        Doctor doctor = new Doctor(
                firstName,
                lastName,
                password,
                email,
                gender,
                phoneNumber,
                dayOfBirth,
                username,
                specialization
        );

        doctor.setDoctorID(id);

        return doctor;
    }

}
