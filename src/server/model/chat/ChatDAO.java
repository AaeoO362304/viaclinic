package server.model.chat;

import server.database.DatabaseConnection;
import shared.ChatMessageDTO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Handles reading and writing the chat and message rows in the database.
 * A chat is one patient together with one doctor. Each message belongs to a chat
 * and stores the sender name, the date, and the text.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class ChatDAO {
    /** The instance. */
    private static ChatDAO instance;

    /**
     * Creates a new {@code ChatDAO} instance.
     *
     * @throws SQLException if the operation cannot be completed
     */
    private ChatDAO() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    /**
     * Returns the single shared instance of {@code ChatDAO}.
     *
     * @return the instance
     * @throws SQLException if the operation cannot be completed
     */
    public static synchronized ChatDAO getInstance() throws SQLException {
        if (instance == null) {
            instance = new ChatDAO();
        }
        return instance;
    }

    /**
     * Finds the chat for one patient and one doctor, or creates it if there is
     * none yet, and returns its chat id.
     *
     * @param patientId the identifier of the patient
     * @param doctorId the identifier of the doctor
     * @return the chat id
     * @throws SQLException if the operation cannot be completed
     */
    public int getOrCreateChat(int patientId, int doctorId) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            // try to find an existing chat for this patient and doctor
            try (PreparedStatement find = conn.prepareStatement(
                    "SELECT chat_id FROM chat WHERE patient_id = ? AND doctor_id = ?")) {
                find.setInt(1, patientId);
                find.setInt(2, doctorId);
                ResultSet rs = find.executeQuery();
                if (rs.next()) {
                    return rs.getInt("chat_id");
                }
            }
            // none found: create a new chat
            try (PreparedStatement insert = conn.prepareStatement(
                    "INSERT INTO chat (patient_id, doctor_id) VALUES (?, ?) RETURNING chat_id")) {
                insert.setInt(1, patientId);
                insert.setInt(2, doctorId);
                ResultSet rs = insert.executeQuery();
                rs.next();
                return rs.getInt("chat_id");
            }
        }
    }

    /**
     * Saves one message in the database.
     *
     * @param chatId the identifier of the chat
     * @param sender the name of the sender
     * @param text the text
     * @throws SQLException if the operation cannot be completed
     */
    public void saveMessage(int chatId, String sender, String text) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement st = conn.prepareStatement(
                     "INSERT INTO message (chat_id, sender, message_date, text) VALUES (?, ?, ?, ?)")) {
            st.setInt(1, chatId);
            st.setString(2, sender);
            st.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            st.setString(4, text);
            st.executeUpdate();
        }
    }

    /**
     * Returns all messages of one chat, oldest first.
     *
     * @param chatId the identifier of the chat
     * @return the messages
     * @throws SQLException if the operation cannot be completed
     */
    public ArrayList<ChatMessageDTO> getMessages(int chatId) throws SQLException {
        ArrayList<ChatMessageDTO> messages = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement st = conn.prepareStatement(
                     "SELECT chat_id, sender, message_date, text FROM message "
                             + "WHERE chat_id = ? ORDER BY message_date")) {
            st.setInt(1, chatId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Timestamp ts = rs.getTimestamp("message_date");
                messages.add(new ChatMessageDTO(
                        rs.getInt("chat_id"),
                        rs.getString("sender"),
                        ts == null ? null : ts.toLocalDateTime(),
                        rs.getString("text")));
            }
        }
        return messages;
    }
}
