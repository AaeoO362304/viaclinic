package server.database;

public class DbTest {
    public static void main(String[] args) {
        try (var conn = DatabaseConnection.getConnection()) {
            System.out.println("CONNECTED: " + conn.getCatalog());

            try (var st = conn.prepareStatement("SELECT current_schema()")) {
                var rs = st.executeQuery(); rs.next();
                System.out.println("current_schema = " + rs.getString(1));
            }
            try (var st = conn.prepareStatement("SELECT COUNT(*) FROM users")) {
                var rs = st.executeQuery(); rs.next();
                System.out.println("users rows = " + rs.getInt(1));
            }
            try (var st = conn.prepareStatement(
                    "SELECT u.id, u.username FROM users u JOIN patient p ON u.id = p.patient_id")) {
                var rs = st.executeQuery();
                int n = 0; while (rs.next()) n++;
                System.out.println("login join returned " + n + " patient rows");
            }
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }
}