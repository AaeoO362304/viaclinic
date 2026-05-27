package shared;

import java.time.LocalDateTime;

/**
 * Holds one saved chat message that is sent between the server and the client.
 * It carries which chat it belongs to, the sender's name, the date, and the text.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class ChatMessageDTO {
    /** The chat this message belongs to. */
    private int chatId;
    /** The name of the sender. */
    private String sender;
    /** The date and time the message was sent. */
    private LocalDateTime messageDate;
    /** The text of the message. */
    private String text;

    /**
     * Creates a new chat message.
     *
     * @param chatId the id of the chat
     * @param sender the name of the sender
     * @param messageDate the date the message was sent
     * @param text the text
     */
    public ChatMessageDTO(int chatId, String sender, LocalDateTime messageDate, String text) {
        this.chatId = chatId;
        this.sender = sender;
        this.messageDate = messageDate;
        this.text = text;
    }

    /**
     * Returns the chat id.
     *
     * @return the chat id
     */
    public int getChatId() {
        return chatId;
    }

    /**
     * Returns the sender name.
     *
     * @return the sender name
     */
    public String getSender() {
        return sender;
    }

    /**
     * Returns the message date.
     *
     * @return the message date
     */
    public LocalDateTime getMessageDate() {
        return messageDate;
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
