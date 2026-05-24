package server.networking;

import shared.networking.ChatMessage;
import server.model.chat.ChatDAO;
import shared.ChatMessageDTO;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The chat server. It listens on its own port (separate from the main clinic
 * server) and starts a new ChatClientHandler thread for every user that connects.
 * It keeps a list of the users who are online right now and, when a message comes
 * in, sends it on to the chosen receiver if that user is online. Messages are not
 * saved anywhere, so chat history is only live while users are connected.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class ChatServer {

    /** The port the chat server listens on. */
    public static final int PORT = 2911;

    /** The users who are online now, kept as user id to their handler. */
    private final Map<Integer, ChatClientHandler> online = new ConcurrentHashMap<>();

    /**
     * Opens the chat server socket and keeps accepting users until the program
     * is stopped. Each user is handled on its own thread.
     *
     * @throws Exception if the server socket cannot be opened
     */
    public void start() throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("ChatServer listening on port " + PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                ChatClientHandler handler = new ChatClientHandler(socket, this);
                Thread thread = new Thread(handler);
                thread.setDaemon(true);
                thread.start();
            }
        }
    }

    /**
     * Adds a user to the list of online users.
     *
     * @param userId the identifier of the user
     * @param handler the handler for that user
     */
    public void register(int userId, ChatClientHandler handler) {
        online.put(userId, handler);
    }

    /**
     * Removes a user from the list of online users.
     *
     * @param userId the identifier of the user
     */
    public void unregister(int userId) {
        online.remove(userId);
    }

    /**
     * Saves the message in the database and then sends it to the receiver chosen
     * in the message, but only if that user is online. The sender also gets a copy
     * back so it shows in their own chat.
     *
     * @param msg the message to send
     */
    public void route(ChatMessage msg) {
        // save the message to the database first
        try {
            int chatId = ChatDAO.getInstance().getOrCreateChat(msg.getPatientId(), msg.getDoctorId());
            ChatDAO.getInstance().saveMessage(chatId, msg.getFromName(), msg.getText());
        } catch (Exception e) {
            System.out.println("Could not save chat message: " + e.getMessage());
        }
        // then deliver it live to whoever is online
        ChatClientHandler receiver = online.get(msg.getToId());
        if (receiver != null) {
            receiver.send(msg);
        }
        ChatClientHandler sender = online.get(msg.getFromId());
        if (sender != null && msg.getFromId() != msg.getToId()) {
            sender.send(msg);
        }
    }

    /**
     * Returns the saved messages of the chat between one patient and one doctor.
     *
     * @param patientId the identifier of the patient
     * @param doctorId the identifier of the doctor
     * @return the saved messages, oldest first
     */
    public ArrayList<ChatMessageDTO> getHistory(int patientId, int doctorId) {
        try {
            int chatId = ChatDAO.getInstance().getOrCreateChat(patientId, doctorId);
            return ChatDAO.getInstance().getMessages(chatId);
        } catch (Exception e) {
            System.out.println("Could not load chat history: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Starts the chat server program.
     *
     * @param args not used
     * @throws Exception if the server cannot start
     */
    public static void main(String[] args) throws Exception {
        new ChatServer().start();
    }
}
