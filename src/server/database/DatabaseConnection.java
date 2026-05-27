package server.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Opens connections to the PostgreSQL database.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class DatabaseConnection
{
    /** Constant holding the URL. */
    private static final String URL =
            "jdbc:postgresql://localhost:5432/postgres?currentSchema=viaclinic";

    /** Constant holding the USER. */
    private static final String USER = "postgres";
    /** Constant holding the PASSWORD. */
    private static final String PASSWORD = "325689";

    /**
     * Returns the connection.
     *
     * @return the connection
     * @throws SQLException if the operation cannot be completed
     */
    public static Connection getConnection() throws SQLException
    {
        System.out.println(DriverManager.getConnection(URL, USER, PASSWORD));
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
