package server.service;

import server.model.auth.Role;
import server.model.auth.Session;
import server.model.bookAppointment.*;
import shared.*;

/**
 * Helper class that converts between the server's objects and the shared DTOs.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class DTOMapper {
    /**
     * Performs the to role DTO operation.
     *
     * @param role the role
     * @return the resulting role DTO
     */
    public static RoleDTO toRoleDTO(Role role) {
        if (role == null) return null;
        return switch (role) {
            case PATIENT -> RoleDTO.PATIENT;
            case DOCTOR -> RoleDTO.DOCTOR;
            case RECEPTIONIST -> RoleDTO.RECEPTIONIST;
        };
    }

    /**
     * Performs the to session DTO operation.
     *
     * @param session the session
     * @return the resulting session DTO
     */
    public static SessionDTO toSessionDTO(Session session) {
        if (session == null || session.getUser() == null) return null;

        int userId = 0;
        if (session.getUser() instanceof Patient patient) {
            userId = patient.getPatientID();
        }
        else if (session.getUser() instanceof Doctor doctor) {
            userId = doctor.getDoctorID();
        }
        else if (session.getUser() instanceof Receptionist receptionist) {
            userId = receptionist.getReceptionistID();
        }

        return new SessionDTO(
                userId,
                session.getUser().getFirstName(),
                session.getUser().getLastName(),
                session.getUser().getUserName(),
                toRoleDTO(session.getRole())
        );
    }

    /**
     * Performs the to patient DTO operation.
     *
     * @param patient the patient
     * @return the resulting patient DTO
     */
    public static PatientDTO toPatientDTO(Patient patient) {
        if (patient == null) return null;
        return new PatientDTO(
                patient.getPatientID(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getEmail(),
                patient.getPhoneNum(),
                patient.getUserName(),
                patient.getCPR(),
                patient.getGender(),
                patient.getPassword(),
                patient.getMedicalNotes(),
                patient.getLastVisit(),
                patient.getDayOfBirth(),
                patient.getAge()
        );
    }

    /**
     * Converts the DTO into a Patient.
     *
     * @param dto the dto
     * @return the resulting patient
     */
    public static Patient toPatient(PatientDTO dto) {
        if (dto == null) return null;
        Patient patient = new Patient(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getPassword(),
                dto.getEmail(),
                dto.getGender(),
                dto.getPhoneNumber(),
                dto.getDayOfBirth(),
                dto.getUserName(),
                dto.getCPR()
        );
        patient.setPatientID(dto.getPatientID());
        patient.setMedicalNotes(dto.getMedicalNotes());
        patient.setLastVisit(dto.getLastVisit());
        return patient;
    }

    /**
     * Performs the to doctor DTO operation.
     *
     * @param doctor the doctor
     * @return the resulting doctor DTO
     */
    public static DoctorDTO toDoctorDTO(Doctor doctor) {
        if (doctor == null) return null;
        return new DoctorDTO(
                doctor.getDoctorID(),
                doctor.getFirstName(),
                doctor.getLastName(),
                doctor.getEmail(),
                doctor.getPhoneNum(),
                doctor.getUserName(),
                doctor.getPassword(),
                doctor.getGender(),
                doctor.getSpecialization(),
                doctor.getDayOfBirth(),
                doctor.getAge()
        );
    }

    /**
     * Converts the DTO into a Doctor.
     *
     * @param dto the dto
     * @return the resulting doctor
     */
    public static Doctor toDoctor(DoctorDTO dto) {
        if (dto == null) return null;
        Doctor doctor = new Doctor(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getPassword(),
                dto.getEmail(),
                dto.getGender(),
                dto.getPhoneNumber(),
                dto.getDayOfBirth(),
                dto.getUserName(),
                dto.getSpecialization()
        );
        doctor.setDoctorID(dto.getDoctorID());
        return doctor;
    }

    /**
     * Performs the to receptionist DTO operation.
     *
     * @param receptionist the receptionist
     * @return the resulting receptionist DTO
     */
    public static ReceptionistDTO toReceptionistDTO(Receptionist receptionist) {
        if (receptionist == null) return null;
        return new ReceptionistDTO(
                receptionist.getReceptionistID(),
                receptionist.getFirstName(),
                receptionist.getLastName(),
                receptionist.getEmail(),
                receptionist.getPhoneNum(),
                receptionist.getUserName(),
                receptionist.getPassword(),
                receptionist.getGender(),
                receptionist.getDayOfBirth(),
                receptionist.getAge()
        );
    }

    /**
     * Converts the DTO into a Receptionist.
     *
     * @param dto the dto
     * @return the resulting receptionist
     */
    public static Receptionist toReceptionist(ReceptionistDTO dto) {
        if (dto == null) return null;
        Receptionist receptionist = new Receptionist(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getPassword(),
                dto.getEmail(),
                dto.getGender(),
                dto.getPhoneNumber(),
                dto.getDayOfBirth(),
                dto.getUserName()
        );
        receptionist.setReceptionistID(dto.getReceptionistID());
        return receptionist;
    }

    /**
     * Performs the to appointment DTO operation.
     *
     * @param appointment the appointment
     * @return the resulting appointment DTO
     */
    public static AppointmentDTO toAppointmentDTO(Appointment appointment) {
        if (appointment == null) return null;
        return new AppointmentDTO(
                appointment.getAppointmentID(),
                appointment.getDate(),
                toDoctorDTO(appointment.getDoctor()),
                toPatientDTO(appointment.getPatient()),
                appointment.isStatus(),
                appointment.getNotes()
        );
    }
}
