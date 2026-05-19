package server.model.bookAppointment;

import java.util.ArrayList;

public class PatientList
{
  private ArrayList<Patient> patients;

  public PatientList() {
    patients = new ArrayList<Patient>();
  }

  public Patient getPatient(int index) {
    return patients.get(index);
  }

  public void addPatient(Patient patient) {patients.add(patient);}

  public Patient getPatientByName(String name) {
    for(int i=0; i<patients.size();i++) {
      if (name.equals(patients.get(i).getFirstName())) return patients.get(i);
    }
    return null;
  }

  public int size() {
    return patients.size();
  }

}
