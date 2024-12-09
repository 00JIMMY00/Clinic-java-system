package com.clinic;

import com.clinic.model.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class ClinicManagementSystem {
    private List<Patient> patients;
    private List<Doctor> doctors;
    private List<Appointment> appointments;

    public ClinicManagementSystem() {
        this.patients = new ArrayList<>();
        this.doctors = new ArrayList<>();
        this.appointments = new ArrayList<>();
    }

    // Patient Management
    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public Patient getPatient(int id) {
        return patients.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Doctor Management
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public Doctor getDoctor(int id) {
        return doctors.stream()
                .filter(d -> d.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Appointment Management
    public void scheduleAppointment(Appointment appointment) {
        // Add validation logic here
        appointments.add(appointment);
    }

    public List<Appointment> getPatientAppointments(int patientId) {
        return appointments.stream()
                .filter(a -> a.getPatient().getId() == patientId)
                .toList();
    }

    public List<Appointment> getDoctorAppointments(int doctorId) {
        return appointments.stream()
                .filter(a -> a.getDoctor().getId() == doctorId)
                .toList();
    }

    // Getters for all lists
    public List<Patient> getAllPatients() {
        return new ArrayList<>(patients);
    }

    public List<Doctor> getAllDoctors() {
        return new ArrayList<>(doctors);
    }

    public List<Appointment> getAllAppointments() {
        return new ArrayList<>(appointments);
    }

    // Main method with example usage
    public static void main(String[] args) {
        ClinicManagementSystem cms = new ClinicManagementSystem();

        // Example usage
        Doctor doctor = new Doctor(1, "John", "Smith", "General Practitioner", 
                                 "123-456-7890", "john.smith@clinic.com", "MD12345");
        cms.addDoctor(doctor);

        Patient patient = new Patient(1, "Jane", "Doe", java.time.LocalDate.of(1990, 1, 1),
                                    "098-765-4321", "jane.doe@email.com", "123 Main St");
        cms.addPatient(patient);

        Appointment appointment = new Appointment(1, patient, doctor, 
                                               LocalDateTime.now().plusDays(1), 
                                               "SCHEDULED", "Regular checkup");
        cms.scheduleAppointment(appointment);

        // Print all appointments
        System.out.println("All appointments:");
        cms.getAllAppointments().forEach(System.out::println);
    }
}
