package server.model.bookAppointment;

import java.util.ArrayList;

/**
 * A list of receptionists kept in memory.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class ReceptionistList {

    /** The receptionists. */
    private ArrayList<Receptionist> receptionists;

    /**
     * Creates a new {@code ReceptionistList} instance.
     */
    public ReceptionistList() {
        receptionists = new ArrayList<>();
    }

    /**
     * Returns the receptionist.
     *
     * @param index the index
     * @return the receptionist
     */
    public Receptionist getReceptionist(int index) {
        return receptionists.get(index);
    }

    /**
     * Adds the receptionist.
     *
     * @param receptionist the receptionist
     */
    public void addReceptionist(Receptionist receptionist) {receptionists.add(receptionist);}

    /**
     * Returns the receptionist by user name.
     *
     * @param userName the user name
     * @return the receptionist by user name
     */
    public Receptionist getReceptionistByUserName(String userName) {
        for(int i=0; i<receptionists.size();i++) {
            if (userName.equals(receptionists.get(i).getUserName())) return receptionists.get(i);
        }
        return null;
    }

    /**
     * Returns how many items are in the list.
     *
     * @return the resulting int
     */
    public int size() {
        return receptionists.size();
    }
}
