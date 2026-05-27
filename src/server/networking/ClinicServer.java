package server.networking;

import server.service.ClinicService;
import server.service.ClinicServiceImpl;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * The server program. It opens a ServerSocket on a fixed port and waits for clients
 * to connect. For every client that connects it starts a new ClientHandler on its own
 * thread, so many clients can use the system at the same time.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class ClinicServer {

    /** The port the server listens on. */
    public static final int PORT = 2910;

    /** The clinic service shared by all client handlers. */
    private final ClinicService service;

    /**
     * Creates a new server with its own clinic service.
     */
    public ClinicServer() {
        this.service = new ClinicServiceImpl();
    }

    /**
     * Opens the server socket and keeps accepting clients until the program is stopped.
     * Each accepted client is handled on its own thread.
     *
     * @throws Exception if the server socket cannot be opened
     */
    public void start() throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("ClinicServer listening on port " + PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                ClientHandler handler = new ClientHandler(socket, service);
                Thread thread = new Thread(handler);
                thread.start();
            }
        }
    }

    /**
     * Starts the server program.
     *
     * @param args not used
     * @throws Exception if the server cannot start
     */
    public static void main(String[] args) throws Exception {
        new ClinicServer().start();
    }
}
