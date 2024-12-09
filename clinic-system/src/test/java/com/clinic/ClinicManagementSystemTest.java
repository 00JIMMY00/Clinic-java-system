package com.clinic;

import com.clinic.model.Appointment;
import com.clinic.model.Doctor;
import com.clinic.model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class ClinicManagementSystemTest {
    private ClinicManagementSystem cms;
    private Doctor testDoctor;
    private Patient testPatient;
    private Appointment testAppointment;

    @BeforeEach
    void setUp() {
        cms = new ClinicManagementSystem();
        
        // Create test doctor
        testDoctor = new Doctor(1, "John", "Smith", "General Practitioner",
                "123-456-7890", "john.smith@clinic.com", "MD12345");
        
        // Create test patient
        testPatient = new Patient(1, "Jane", "Doe", LocalDate.of(1990, 1, 1),
                "098-765-4321", "jane.doe@email.com", "123 Main St");
        
        // Create test appointment
        testAppointment = new Appointment(1, testPatient, testDoctor,
                LocalDateTime.now().plusDays(1), "SCHEDULED", "Regular checkup");
    }

    @Test
    void testAddAndGetDoctor() {
        cms.addDoctor(testDoctor);
        Doctor retrievedDoctor = cms.getDoctor(1);
        
        assertNotNull(retrievedDoctor);
        assertEquals(testDoctor.getId(), retrievedDoctor.getId());
        assertEquals(testDoctor.getFirstName(), retrievedDoctor.getFirstName());
        assertEquals(testDoctor.getLastName(), retrievedDoctor.getLastName());
    }

    @Test
    void testAddAndGetPatient() {
        cms.addPatient(testPatient);
        Patient retrievedPatient = cms.getPatient(1);
        
        assertNotNull(retrievedPatient);
        assertEquals(testPatient.getId(), retrievedPatient.getId());
        assertEquals(testPatient.getFirstName(), retrievedPatient.getFirstName());
        assertEquals(testPatient.getLastName(), retrievedPatient.getLastName());
    }

    @Test
    void testScheduleAppointment() {
        cms.addDoctor(testDoctor);
        cms.addPatient(testPatient);
        cms.scheduleAppointment(testAppointment);
        
        var appointments = cms.getAllAppointments();
        assertFalse(appointments.isEmpty());
        assertEquals(1, appointments.size());
        
        Appointment retrievedAppointment = appointments.get(0);
        assertEquals(testAppointment.getId(), retrievedAppointment.getId());
        assertEquals(testAppointment.getPatient().getId(), retrievedAppointment.getPatient().getId());
        assertEquals(testAppointment.getDoctor().getId(), retrievedAppointment.getDoctor().getId());
    }

    @Test
    void testGetPatientAppointments() {
        cms.addDoctor(testDoctor);
        cms.addPatient(testPatient);
        cms.scheduleAppointment(testAppointment);
        
        var patientAppointments = cms.getPatientAppointments(testPatient.getId());
        assertFalse(patientAppointments.isEmpty());
        assertEquals(1, patientAppointments.size());
        assertEquals(testPatient.getId(), patientAppointments.get(0).getPatient().getId());
    }

    @Test
    void testGetDoctorAppointments() {
        cms.addDoctor(testDoctor);
        cms.addPatient(testPatient);
        cms.scheduleAppointment(testAppointment);
        
        var doctorAppointments = cms.getDoctorAppointments(testDoctor.getId());
        assertFalse(doctorAppointments.isEmpty());
        assertEquals(1, doctorAppointments.size());
        assertEquals(testDoctor.getId(), doctorAppointments.get(0).getDoctor().getId());
    }

    @Test
    void testGetNonExistentDoctor() {
        Doctor retrievedDoctor = cms.getDoctor(999);
        assertNull(retrievedDoctor);
    }

    @Test
    void testGetNonExistentPatient() {
        Patient retrievedPatient = cms.getPatient(999);
        assertNull(retrievedPatient);
    }
}
