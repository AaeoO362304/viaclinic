package server.networking;

import com.google.gson.Gson;
import shared.networking.ChatMessage;
import shared.networking.JsonUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Handles the chat connection of one user on its own thread. The first message it
 * reads is a REGISTER message that tells the server which user this socket belongs
 * to. After that it reads chat messages and asks the chat server to send each one
 * to the chosen receiver. A new ChatClientHandler is started for every user that
 * connects, so many users can chat at the same time.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class ChatClientHandler implements Runnable {

    /** The socket connected to this one user. */
    private final Socket socket;
    /** The chat server that keeps track of everyone and routes messages. */
    private final ChatServer server;
    /** The shared Gson instance for converting to and from JSON. */
    private final Gson gson = JsonUtil.gson();
    /** Writer for sending messages to this user. */
    private BufferedWriter out;
    /** The user id of the user on this connection (set after REGISTER). */
    private int userId = -1;

    /**
     * Creates a new chat handler for one user.
     *
     * @param socket the socket connected to the user
     * @param server the chat server
     */
    public ChatClientHandler(Socket socket, ChatServer server) {
        this.socket = socket;
        this.server = server;
    }

    /**
     * Reads messages from this user until they disconnect, routing each one.
     */
    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            out = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));

            String line;
            while ((line = in.readLine()) != null) {
                ChatMessage msg = gson.fromJson(line, ChatMessage.class);
                if (ChatMessage.REGISTER.equals(msg.getType())) {
                    userId = msg.getFromId();
                    server.register(userId, this);
                    System.out.println("Chat: user " + userId + " connected");
                } else if (ChatMessage.HISTORY.equals(msg.getType())) {
                    // client wants the saved history of this patient-doctor chat
                    for (shared.ChatMessageDTO saved :
                            server.getHistory(msg.getPatientId(), msg.getDoctorId())) {
                        ChatMessage old = new ChatMessage(ChatMessage.MESSAGE,
                                0, saved.getSender(), userId, saved.getText());
                        send(old);
                    }
                } else {
                    // a normal message: save it and send it to the chosen receiver
                    server.route(msg);
                }
            }
        } catch (Exception e) {
            System.out.println("Chat connection error: " + e.getMessage());
        } finally {
            server.unregister(userId);
            try {
                socket.close();
            } catch (Exception ignored) {
            }
            System.out.println("Chat: user " + userId + " disconnected");
        }
    }

    /**
     * Sends one message to this user over the socket.
     *
     * @param msg the message to send
     */
    public synchronized void send(ChatMessage msg) {
        try {
            out.write(gson.toJson(msg));
            out.newLine();
            out.flush();
        } catch (Exception e) {
            System.out.println("Could not send chat message to user " + userId + ": " + e.getMessage());
        }
    }
}
