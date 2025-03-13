import java.util.ArrayList;
import java.util.List;

// Patient class
class Patient {
    private String name;
    private int age;
    private List<Doctor> doctors; // Stores doctors the patient consults

    public Patient(String name, int age) {
        this.name = name;
        this.age = age;
        this.doctors = new ArrayList<>();
    }

    // Method to add a doctor for consultation
    public void addDoctor(Doctor doctor) {
        if (!doctors.contains(doctor)) {
            doctors.add(doctor);
            doctor.addPatient(this); // Ensuring bidirectional association
        }
    }

    // Display patient's details
    public void displayInfo() {
        System.out.println("\nPatient: " + name + " | Age: " + age);
        System.out.println("Consulting Doctors:");
        if (doctors.isEmpty()) {
            System.out.println("   No doctors assigned.");
        } else {
            for (Doctor doctor : doctors) {
                System.out.println("   " + doctor.getName() + " (Specialization: " + doctor.getSpecialization() + ")");
            }
        }
    }

    public String getName() {
        return name;
    }
}

// Doctor class
class Doctor {
    private String name;
    private String specialization;
    private List<Patient> patients; // Stores patients under this doctor

    public Doctor(String name, String specialization) {
        this.name = name;
        this.specialization = specialization;
        this.patients = new ArrayList<>();
    }

    // Method to add a patient
    public void addPatient(Patient patient) {
        if (!patients.contains(patient)) {
            patients.add(patient);
            patient.addDoctor(this); // Ensuring bidirectional association
        }
    }

    // Consultation method (Interaction)
    public void consult(Patient patient) {
        if (patients.contains(patient)) {
            System.out.println("\nDoctor " + name + " is consulting Patient " + patient.getName() + ".");
        } else {
            System.out.println("\nPatient " + patient.getName() + " is not assigned to Doctor " + name + " yet.");
        }
    }

    // Display doctor's details
    public void displayInfo() {
        System.out.println("\nDoctor: " + name + " | Specialization: " + specialization);
        System.out.println("Patients Under Care:");
        if (patients.isEmpty()) {
            System.out.println("   No patients assigned.");
        } else {
            for (Patient patient : patients) {
                System.out.println("   " + patient.getName());
            }
        }
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }
}

// Hospital class (Manages doctors and patients)
class Hospital {
    private String hospitalName;
    private List<Doctor> doctors;
    private List<Patient> patients;

    public Hospital(String hospitalName) {
        this.hospitalName = hospitalName;
        this.doctors = new ArrayList<>();
        this.patients = new ArrayList<>();
    }

    // Add a Doctor
    public void addDoctor(String name, String specialization) {
        doctors.add(new Doctor(name, specialization));
    }

    // Add a Patient
    public void addPatient(String name, int age) {
        patients.add(new Patient(name, age));
    }

    // Get Doctor by name
    public Doctor getDoctorByName(String name) {
        for (Doctor doctor : doctors) {
            if (doctor.getName().equalsIgnoreCase(name)) {
                return doctor;
            }
        }
        return null;
    }

    // Get Patient by name
    public Patient getPatientByName(String name) {
        for (Patient patient : patients) {
            if (patient.getName().equalsIgnoreCase(name)) {
                return patient;
            }
        }
        return null;
    }

    // Display all Doctors
    public void displayDoctors() {
        System.out.println("\nHospital: " + hospitalName);
        System.out.println("Doctors:");
        if (doctors.isEmpty()) {
            System.out.println("   No doctors available.");
        } else {
            for (Doctor doctor : doctors) {
                doctor.displayInfo();
            }
        }
    }

    // Display all Patients
    public void displayPatients() {
        System.out.println("\nHospital: " + hospitalName);
        System.out.println("Patients:");
        if (patients.isEmpty()) {
            System.out.println("   No patients available.");
        } else {
            for (Patient patient : patients) {
                patient.displayInfo();
            }
        }
    }
}

// Main class
public class HospitalManagement {
    public static void main(String[] args) {
        // Create Hospital
        Hospital hospital = new Hospital("City Hospital");

        // Add Doctors
        hospital.addDoctor("Dr. Aditi Sharma", "Cardiologist");
        hospital.addDoctor("Dr. Rajesh Verma", "Orthopedic");

        // Add Patients
        hospital.addPatient("Rohit Mehta", 35);
        hospital.addPatient("Neha Singh", 42);

        // Retrieve Doctor and Patient Objects
        Doctor doctor1 = hospital.getDoctorByName("Dr. Aditi Sharma");
        Doctor doctor2 = hospital.getDoctorByName("Dr. Rajesh Verma");

        Patient patient1 = hospital.getPatientByName("Rohit Mehta");
        Patient patient2 = hospital.getPatientByName("Neha Singh");

        // Establish Doctor-Patient Relationship
        if (doctor1 != null && patient1 != null) patient1.addDoctor(doctor1);
        if (doctor1 != null && patient2 != null) patient2.addDoctor(doctor1);
        if (doctor2 != null && patient1 != null) patient1.addDoctor(doctor2);

        // Display Hospital Details
        hospital.displayDoctors();
        hospital.displayPatients();

        // Consultation Example
        if (doctor1 != null && patient1 != null) doctor1.consult(patient1);
        if (doctor2 != null && patient2 != null) doctor2.consult(patient2);
    }
}
