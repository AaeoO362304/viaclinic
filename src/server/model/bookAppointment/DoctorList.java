package server.model.bookAppointment;

import java.util.ArrayList;

public class DoctorList {

    private ArrayList<Doctor> doctors;

    public DoctorList() {
        doctors = new ArrayList<>();
    }

    public Doctor getDoctor(int index) {
        return doctors.get(index);
    }

    public void addDoctor(Doctor doctor) {doctors.add(doctor);}

    public Doctor getDoctorByUserName(String userName) {
        for(int i=0; i<doctors.size();i++) {
            if (userName.equals(doctors.get(i).getUserName())) return doctors.get(i);
        }
        return null;
    }

    public int size() {
        return doctors.size();
    }
}
