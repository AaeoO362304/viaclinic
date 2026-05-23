package server.model.bookAppointment;

import server.database.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReceptionistDAO {
    private static ReceptionistDAO instance;

    private ReceptionistDAO() {}

    public static synchronized ReceptionistDAO getInstance() {
        if (instance == null) {
            instance = new ReceptionistDAO();
        }
        return instance;
    }

    public Receptionist createReceptionist(Receptionist receptionist) throws SQLException {
        String userSql = """
            INSERT INTO users
            (first_name, last_name, day_of_birth, gender, phone_num, email, password, username)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;

        String receptionistSql = """
            INSERT INTO receptionist
            (receptionist_id)
            VALUES (?)
            """;

        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement userStatement = connection.prepareStatement(userSql, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement receptionistStatement = connection.prepareStatement(receptionistSql)) {

                userStatement.setString(1, receptionist.getFirstName());
                userStatement.setString(2, receptionist.getLastName());
                userStatement.setObject(3, receptionist.getDayOfBirth());
                userStatement.setString(4, receptionist.getGender());
                userStatement.setString(5, receptionist.getPhoneNum());
                userStatement.setString(6, receptionist.getEmail());
                userStatement.setString(7, receptionist.getPassword());
                userStatement.setString(8, receptionist.getUserName());

                userStatement.executeUpdate();

                try (ResultSet rs = userStatement.getGeneratedKeys()) {
                    if (rs.next()) {
                        int generatedUserId = rs.getInt(1);
                        receptionistStatement.setInt(1, generatedUserId);
                        receptionistStatement.executeUpdate();
                        connection.commit();
                        receptionist.setReceptionistID(generatedUserId);
                        return receptionist;
                    }
                }

                connection.rollback();
                throw new SQLException("Could not create receptionist: no generated user id returned.");
            }
            catch (Exception e) {
                connection.rollback();
                throw e;
            }
        }
    }

    public Receptionist getReceptionistByUsername(String username) throws SQLException {
        String sql = """
            SELECT
                r.receptionist_id,
                u.first_name,
                u.last_name,
                u.day_of_birth,
                u.gender,
                u.phone_num,
                u.email,
                u.password,
                u.username
            FROM receptionist r
            JOIN users u ON r.receptionist_id = u.id
            WHERE u.username = ?
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractReceptionist(resultSet);
                }
            }
        }
        return null;
    }

    public Receptionist getReceptionistById(int receptionistId) throws SQLException {
        String sql = """
            SELECT
                r.receptionist_id,
                u.first_name,
                u.last_name,
                u.day_of_birth,
                u.gender,
                u.phone_num,
                u.email,
                u.password,
                u.username
            FROM receptionist r
            JOIN users u ON r.receptionist_id = u.id
            WHERE r.receptionist_id = ?
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, receptionistId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractReceptionist(resultSet);
                }
            }
        }
        return null;
    }

    public ArrayList<Receptionist> getAllReceptionists() throws SQLException {
        ArrayList<Receptionist> receptionists = new ArrayList<>();
        String sql = """
            SELECT
                r.receptionist_id,
                u.first_name,
                u.last_name,
                u.day_of_birth,
                u.gender,
                u.phone_num,
                u.email,
                u.password,
                u.username
            FROM receptionist r
            JOIN users u ON r.receptionist_id = u.id
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                receptionists.add(extractReceptionist(resultSet));
            }
        }
        return receptionists;
    }

    private Receptionist extractReceptionist(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("receptionist_id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String email = resultSet.getString("email");
        String phoneNumber = resultSet.getString("phone_num");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        String gender = resultSet.getString("gender");
        LocalDate dayOfBirth = resultSet.getObject("day_of_birth", LocalDate.class);

        return new Receptionist(id, firstName, lastName, password, email, gender, phoneNumber, dayOfBirth, username);
    }
}
