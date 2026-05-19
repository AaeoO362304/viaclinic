package server.model.bookAppointment;

import java.util.ArrayList;

public class ReceptionistList {

    private ArrayList<Receptionist> receptionists;

    public ReceptionistList() {
        receptionists = new ArrayList<>();
    }

    public Receptionist getReceptionist(int index) {
        return receptionists.get(index);
    }

    public void addReceptionist(Receptionist receptionist) {receptionists.add(receptionist);}

    public Receptionist getReceptionistByUserName(String userName) {
        for(int i=0; i<receptionists.size();i++) {
            if (userName.equals(receptionists.get(i).getUserName())) return receptionists.get(i);
        }
        return null;
    }

    public int size() {
        return receptionists.size();
    }
}
