package server.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection
{
    private static final String URL =
            "jdbc:postgresql://localhost:5432/postgres?currentSchema=viaclinic";

    private static final String USER = "postgres";
    private static final String PASSWORD = "362304";

    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
