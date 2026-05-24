package server.model.bookAppointment;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;

/**
 * Keeps track of the user handlers.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class UserHandlerManager implements UserHandler{
        /** The patient list. */
        private PatientList patientList;
        /** The doctor list. */
        private DoctorList doctorList;
        /** The receptionist list. */
        private ReceptionistList receptionistList;
        /** The property. */
        private PropertyChangeSupport property;
        /** The date of birth. */
        private LocalDate dateOfBirth;

  /**
   * Creates a new {@code UserHandlerManager} instance.
   */
  public UserHandlerManager()
        {
            patientList = new PatientList();
            doctorList = new DoctorList();
            receptionistList = new ReceptionistList();
            property = new PropertyChangeSupport(this);
        }

        /**
         * Adds the patient.
         *
         * @param firstName the first name
         * @param lastName the last name
         * @param gender the gender
         * @param phoneNum the phone num
         * @param email the email
         * @param password the password
         * @param dateOfBirth the date of birth
         * @param CPR the CPR
         * @param userName the user name
         * @throws IllegalArgumentException if the operation cannot be completed
         * @throws IllegalStateException if the operation cannot be completed
         */
        @Override public void addPatient(String firstName, String lastName, String gender, String phoneNum, String email, String password, LocalDate dateOfBirth, String CPR, String userName)
      throws IllegalArgumentException, IllegalStateException
        {
            Patient patient = new Patient(firstName, lastName, password, email, gender, phoneNum, dateOfBirth, userName, CPR);
            patientList.addPatient(patient);
            property.firePropertyChange("Patient", null, patient);
        }

        /**
         * Returns the patient.
         *
         * @param firstName the first name
         * @return the patient
         */
        @Override public Patient getPatient(String firstName)
        {
            return patientList.getPatientByName(firstName);
        }

        /**
         * Returns the doctor.
         *
         * @param userName the user name
         * @return the doctor
         */
        @Override public Doctor getDoctor(String userName) { return doctorList.getDoctorByUserName(userName); }

        /**
         * Returns the receptionist.
         *
         * @param userName the user name
         * @return the receptionist
         */
        @Override public Receptionist getReceptionist(String userName) { return receptionistList.getReceptionistByUserName(userName); }

        /**
         * Returns the patient count.
         *
         * @return the patient count
         */
        @Override public int getPatientCount()
        {
            return patientList.size();
        }

        /**
         * Adds a listener.
         *
         * @param listener the listener
         */
        public void addListener(PropertyChangeListener listener)
        {
            property.addPropertyChangeListener(listener);
        }

        /**
         * Removes a listener.
         *
         * @param listener the listener
         */
        public void removeListener(PropertyChangeListener listener)
        {
            property.removePropertyChangeListener(listener);
        }
    }

