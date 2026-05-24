package server.model.bookAppointment;

import java.util.ArrayList;

/**
 * A list of patients kept in memory.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class PatientList
{
  /** The patients. */
  private ArrayList<Patient> patients;

  /**
   * Creates a new {@code PatientList} instance.
   */
  public PatientList() {
    patients = new ArrayList<Patient>();
  }

  /**
   * Returns the patient.
   *
   * @param index the index
   * @return the patient
   */
  public Patient getPatient(int index) {
    return patients.get(index);
  }

  /**
   * Adds the patient.
   *
   * @param patient the patient
   */
  public void addPatient(Patient patient) {patients.add(patient);}

  /**
   * Returns the patient by name.
   *
   * @param name the name
   * @return the patient by name
   */
  public Patient getPatientByName(String name) {
    for(int i=0; i<patients.size();i++) {
      if (name.equals(patients.get(i).getFirstName())) return patients.get(i);
    }
    return null;
  }

  /**
   * Returns how many items are in the list.
   *
   * @return the resulting int
   */
  public int size() {
    return patients.size();
  }

}
