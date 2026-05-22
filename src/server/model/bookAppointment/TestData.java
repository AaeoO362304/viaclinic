package server.model.bookAppointment;

import java.time.LocalDate;

public class TestData {
    public static void main(String[] args) {
        try
        {

            PatientDAO databasePatientDAO = PatientDAO.getInstance();

            String firstName="Mourice";
            String lastName="Kühn";
            String password = "362304aA";
            String email = "a362304@via.dk";
            String gender = "Male";
            String phoneNumber = "01637754384";
            LocalDate dayOfBirth = LocalDate.of(2005, 8, 1);
            String userName = "aaeoo";
            String CPR = "010805-3123";

            Patient patient = new Patient(
                    firstName,
                    lastName,
                    password,
                    email,
                    gender,
                    phoneNumber,
                    dayOfBirth,
                    userName,
                    CPR
            );

            databasePatientDAO.createPatient(patient);

            DoctorDAO databaseDoctorDAO = DoctorDAO.getInstance();

            String doctorFirstName = "Mourice";
            String doctorLastName = "Kühn";
            String doctorPassword = "362304aA";
            String doctorEmail = "aA362304@via.dk";
            String doctorGender = "Male";
            String doctorPhoneNumber = "0163775434";
            LocalDate doctorDayOfBirth = LocalDate.of(2005, 8, 1);
            String doctorUserName = "aaee";
            String specialization = "brain";

            Doctor doctor = new Doctor(
                    doctorFirstName,
                    doctorLastName,
                    doctorPassword,
                    doctorEmail,
                    doctorGender,
                    doctorPhoneNumber,
                    doctorDayOfBirth,
                    doctorUserName,
                    specialization
            );

            databaseDoctorDAO.createDoctor(doctor);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
