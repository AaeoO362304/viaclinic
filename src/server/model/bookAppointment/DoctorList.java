package server.model.bookAppointment;

import java.util.ArrayList;

/**
 * A list of doctors kept in memory.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class DoctorList {

    /** The doctors. */
    private ArrayList<Doctor> doctors;

    /**
     * Creates a new {@code DoctorList} instance.
     */
    public DoctorList() {
        doctors = new ArrayList<>();
    }

    /**
     * Returns the doctor.
     *
     * @param index the index
     * @return the doctor
     */
    public Doctor getDoctor(int index) {
        return doctors.get(index);
    }

    /**
     * Adds the doctor.
     *
     * @param doctor the doctor
     */
    public void addDoctor(Doctor doctor) {doctors.add(doctor);}

    /**
     * Returns the doctor by user name.
     *
     * @param userName the user name
     * @return the doctor by user name
     */
    public Doctor getDoctorByUserName(String userName) {
        for(int i=0; i<doctors.size();i++) {
            if (userName.equals(doctors.get(i).getUserName())) return doctors.get(i);
        }
        return null;
    }

    /**
     * Returns how many items are in the list.
     *
     * @return the resulting int
     */
    public int size() {
        return doctors.size();
    }
}
