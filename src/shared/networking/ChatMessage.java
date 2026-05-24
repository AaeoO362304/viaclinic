package shared.networking;

/**
 * One chat message sent between a patient and a doctor. It holds who sent it,
 * who it is for, the sender's name (to show in the chat), and the text. There is
 * also a type so the server can tell a normal message apart from the first
 * "register" message a client sends when it connects.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class ChatMessage {

    /** Message type: the first message a client sends to say who it is. */
    public static final String REGISTER = "REGISTER";
    /** Message type: a normal chat message from one user to another. */
    public static final String MESSAGE = "MESSAGE";
    /** Message type: a request from a client to load the saved history of a chat. */
    public static final String HISTORY = "HISTORY";

    /** The type of this message (REGISTER, MESSAGE or HISTORY). */
    private String type;
    /** The user id of the sender. */
    private int fromId;
    /** The name of the sender, shown in the chat. */
    private String fromName;
    /** The user id of the receiver. */
    private int toId;
    /** The text of the message. */
    private String text;
    /** The patient id of the chat this message belongs to. */
    private int patientId;
    /** The doctor id of the chat this message belongs to. */
    private int doctorId;

    /**
     * Creates a new chat message.
     *
     * @param type the type
     * @param fromId the user id of the sender
     * @param fromName the name of the sender
     * @param toId the user id of the receiver
     * @param text the text
     */
    public ChatMessage(String type, int fromId, String fromName, int toId, String text) {
        this.type = type;
        this.fromId = fromId;
        this.fromName = fromName;
        this.toId = toId;
        this.text = text;
    }

    /**
     * Sets the patient and doctor ids that say which chat this message belongs to.
     *
     * @param patientId the identifier of the patient
     * @param doctorId the identifier of the doctor
     */
    public void setChatParticipants(int patientId, int doctorId) {
        this.patientId = patientId;
        this.doctorId = doctorId;
    }

    /**
     * Returns the patient id of the chat.
     *
     * @return the patient id
     */
    public int getPatientId() {
        return patientId;
    }

    /**
     * Returns the doctor id of the chat.
     *
     * @return the doctor id
     */
    public int getDoctorId() {
        return doctorId;
    }

    /**
     * Returns the type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the sender id.
     *
     * @return the sender id
     */
    public int getFromId() {
        return fromId;
    }

    /**
     * Returns the sender name.
     *
     * @return the sender name
     */
    public String getFromName() {
        return fromName;
    }

    /**
     * Returns the receiver id.
     *
     * @return the receiver id
     */
    public int getToId() {
        return toId;
    }

    /**
     * Returns the text.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }
}
