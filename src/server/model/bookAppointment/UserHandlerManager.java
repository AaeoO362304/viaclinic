package server.model.bookAppointment;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;

public class UserHandlerManager implements UserHandler{
        private PatientList patientList;
        private DoctorList doctorList;
        private ReceptionistList receptionistList;
        private PropertyChangeSupport property;
        private LocalDate dateOfBirth;

  public UserHandlerManager()
        {
            patientList = new PatientList();
            doctorList = new DoctorList();
            receptionistList = new ReceptionistList();
            property = new PropertyChangeSupport(this);
        }

        @Override public void addPatient(String firstName, String lastName, String gender, String phoneNum, String email, String password, LocalDate dateOfBirth, String CPR, String userName)
      throws IllegalArgumentException, IllegalStateException
        {
            Patient patient = new Patient(firstName, lastName, password, email, gender, phoneNum, dateOfBirth, userName, CPR);
            patientList.addPatient(patient);
            property.firePropertyChange("Patient", null, patient);
        }

        @Override public Patient getPatient(String firstName)
        {
            return patientList.getPatientByName(firstName);
        }

        @Override public Doctor getDoctor(String userName) { return doctorList.getDoctorByUserName(userName); }

        @Override public Receptionist getReceptionist(String userName) { return receptionistList.getReceptionistByUserName(userName); }

        @Override public int getPatientCount()
        {
            return patientList.size();
        }

        public void addListener(PropertyChangeListener listener)
        {
            property.addPropertyChangeListener(listener);
        }

        public void removeListener(PropertyChangeListener listener)
        {
            property.removePropertyChangeListener(listener);
        }
    }

