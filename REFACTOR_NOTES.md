# DTO / client-server refactor notes

This version continues the refactor toward a cleaner architecture:

```text
JavaFX client
→ ViewModel
→ client.model.ClinicClient
→ client.model.ClinicClientProxy
→ server.service.ClinicService
→ DAO
→ PostgreSQL
```

## What changed in this version

### Shared DTOs

The client now uses DTOs instead of server model objects for the core flows:

- `PatientDTO`
- `DoctorDTO`
- `ReceptionistDTO`
- `AppointmentDTO`
- `SessionDTO`
- `RoleDTO`

### Server service layer

The service layer now covers:

- login/logout
- create patient/doctor/receptionist
- get all patients/doctors/receptionists
- create appointment
- get appointments by patient
- get appointments by doctor

### DAOs stay server-side

The DAOs are still in `server.model.bookAppointment` and are called by `ClinicServiceImpl`, not by the JavaFX controllers.

### Receptionist support

Added `ReceptionistDAO` and `ReceptionistDTO`. `LoginAuthenticator` now checks patient, doctor, and receptionist users.

### Safer ViewHandler

`ViewHandler` now uses absolute FXML paths and returns `null` safely when a not-yet-implemented view is opened instead of crashing with `Location is not set` or `Scene root cannot be null`.

### Appointment flow

Booking and MyAppointments use DTOs:

- `BookAppointmentWindowController` uses `ComboBox<DoctorDTO>`.
- `MyAppointmentWindowController` uses `TableView<AppointmentDTO>`.

## Still not a full socket/RMI architecture

`ClinicClientProxy` currently calls `ClinicServiceImpl` in-process. This keeps the project easy to run while still separating client and server responsibilities. Later you can replace only `ClinicClientProxy` with socket/RMI communication without changing the controllers/viewmodels again.

## Not implemented views

The following buttons/views still point to placeholders:

- chat
- registered patients
- today appointments
- profile
- spontaneous visit
- receptionist book appointment
- all appointments

They will not crash the app, but they print `Could not load view: ...` until real FXML/controllers are added.
