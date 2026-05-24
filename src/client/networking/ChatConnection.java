package client.networking;

import com.google.gson.Gson;
import shared.networking.ChatMessage;
import shared.networking.JsonUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

/**
 * The client side of the chat. It opens a second socket to the chat server (on a
 * different port from the normal clinic calls), tells the server who the user is,
 * and then starts a background thread that listens for messages coming in. When a
 * message arrives it is handed to a listener so the chat window can show it.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class ChatConnection {

    /** The host the client connects to (the local machine). */
    private static final String HOST = "localhost";
    /** The port the chat server listens on. */
    private static final int PORT = 2911;

    /** The socket connection to the chat server. */
    private final Socket socket;
    /** Reader for messages coming from the server. */
    private final BufferedReader in;
    /** Writer for messages going to the server. */
    private final BufferedWriter out;
    /** The shared Gson instance for converting to and from JSON. */
    private final Gson gson = JsonUtil.gson();
    /** The user id of the logged-in user. */
    private final int userId;
    /** The name of the logged-in user. */
    private final String userName;

    /**
     * Connects to the chat server and registers the user.
     *
     * @param userId the identifier of the user
     * @param userName the name of the user
     * @throws Exception if the connection cannot be opened
     */
    public ChatConnection(int userId, String userName) throws Exception {
        this.userId = userId;
        this.userName = userName;
        this.socket = new Socket(HOST, PORT);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
        // first message tells the server who we are
        send(new ChatMessage(ChatMessage.REGISTER, userId, userName, userId, ""));
    }

    /**
     * Starts the background thread that listens for incoming messages.
     *
     * @param onMessage what to do with each message that arrives
     */
    public void startListening(Consumer<ChatMessage> onMessage) {
        Thread listener = new Thread(() -> {
            try {
                String line;
                while ((line = in.readLine()) != null) {
                    ChatMessage msg = gson.fromJson(line, ChatMessage.class);
                    onMessage.accept(msg);
                }
            } catch (Exception e) {
                System.out.println("Chat listener stopped: " + e.getMessage());
            }
        });
        listener.setDaemon(true);
        listener.start();
    }

    /**
     * Sends a chat message to the chosen receiver. The patient and doctor ids say
     * which saved chat the message belongs to.
     *
     * @param toId the identifier of the receiver
     * @param patientId the identifier of the patient in this chat
     * @param doctorId the identifier of the doctor in this chat
     * @param text the text to send
     */
    public void sendMessage(int toId, int patientId, int doctorId, String text) {
        ChatMessage msg = new ChatMessage(ChatMessage.MESSAGE, userId, userName, toId, text);
        msg.setChatParticipants(patientId, doctorId);
        send(msg);
    }

    /**
     * Asks the server to send back the saved history of one patient-doctor chat.
     * The old messages arrive through the normal listener like new messages.
     *
     * @param patientId the identifier of the patient in this chat
     * @param doctorId the identifier of the doctor in this chat
     */
    public void requestHistory(int patientId, int doctorId) {
        ChatMessage msg = new ChatMessage(ChatMessage.HISTORY, userId, userName, userId, "");
        msg.setChatParticipants(patientId, doctorId);
        send(msg);
    }

    /**
     * Sends one message object over the socket.
     *
     * @param msg the message to send
     */
    private synchronized void send(ChatMessage msg) {
        try {
            out.write(gson.toJson(msg));
            out.newLine();
            out.flush();
        } catch (Exception e) {
            System.out.println("Could not send chat message: " + e.getMessage());
        }
    }

    /**
     * Closes the chat connection.
     */
    public void close() {
        try {
            socket.close();
        } catch (Exception ignored) {
        }
    }
}
