import java.util.ArrayList;
import java.util.Scanner;

class Patient {
    String name;
    int age;
    String contact;
    int dobYear;

    public Patient(String name, int age, String contact, int dobYear) {
        this.name = name;
        this.age = age;
        this.contact = contact;
        this.dobYear = dobYear;
    }

    @Override
    public String toString() {
        return "Patient [Name=" + name + ", Age=" + age + ", Contact=" + contact + ", DOB Year=" + dobYear + "]";
    }
}

class Doctor {
    String name;
    String specialty;
    String contact;
    int dobYear;

    public Doctor(String name, String specialty, String contact, int dobYear) {
        this.name = name;
        this.specialty = specialty;
        this.contact = contact;
        this.dobYear = dobYear;
    }

    @Override
    public String toString() {
        return "Doctor [Name=" + name + ", Specialty=" + specialty + ", Contact=" + contact + ", DOB Year=" + dobYear + "]";
    }
}

class Appointment {
    Patient patient;
    Doctor doctor;
    String date;

    public Appointment(Patient patient, Doctor doctor, String date) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Appointment [Patient=" + patient.name + ", Doctor=" + doctor.name + ", Date=" + date + "]";
    }
}

public class v1 {
    private static ArrayList<Patient> patients = new ArrayList<>();
    private static ArrayList<Doctor> doctors = new ArrayList<>();
    private static ArrayList<Appointment> appointments = new ArrayList<>();
    private static ArrayList<String> admins = new ArrayList<>();
    private static final String MAIN_ADMIN_ID = "922524104016"; // Main admin ID
    private static final String MAIN_ADMIN_NAME = "Balavignesh"; // Main admin name

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Default admin is the main admin
        admins.add(MAIN_ADMIN_ID);

        // Sample doctors
        doctors.add(new Doctor("Dr. Smith", "Cardiologist", "1234567890", 1970));
        doctors.add(new Doctor("Dr. Johnson", "Neurologist", "0987654321", 1980));

        while (true) {
            System.out.println("===== Hospital Management System =====");
            System.out.println("1. Register Patient");
            System.out.println("2. View Doctors");
            System.out.println("3. Make Appointment");
            System.out.println("4. View Appointments");
            System.out.println("5. Add Doctor (Admin only)");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    registerPatient(scanner);
                    break;
                case 2:
                    viewDoctors();
                    break;
                case 3:
                    makeAppointment(scanner);
                    break;
                case 4:
                    viewAppointments();
                    break;
                case 5:
                    addDoctor(scanner);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // Method to register a patient
    private static void registerPatient(Scanner scanner) {
        System.out.print("Enter patient name: ");
        String name = scanner.nextLine();
        System.out.print("Enter patient age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter patient contact (10 digits): ");
        String contact = scanner.nextLine();

        if (!isValidContact(contact)) {
            System.out.println("Invalid contact number! It must be 10 digits.");
            return;
        }

        System.out.print("Enter patient's year of birth (1900 to 2025): ");
        int dobYear = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (dobYear < 1900 || dobYear > 2025) {
            System.out.println("Invalid year of birth! It must be between 1900 and 2025.");
            return;
        }

        Patient patient = new Patient(name, age, contact, dobYear);
        patients.add(patient);
        System.out.println("Patient registered successfully.");
    }

    // Method to view doctors
    private static void viewDoctors() {
        if (doctors.isEmpty()) {
            System.out.println("No doctors available.");
            return;
        }

        System.out.println("Available Doctors:");
        for (Doctor doctor : doctors) {
            System.out.println(doctor);
        }
    }

    // Method to make an appointment
    private static void makeAppointment(Scanner scanner) {
        if (patients.isEmpty()) {
            System.out.println("No patients registered.");
            return;
        }

        System.out.println("Enter patient name for appointment: ");
        String patientName = scanner.nextLine();

        Patient patient = null;
        for (Patient p : patients) {
            if (p.name.equalsIgnoreCase(patientName)) {
                patient = p;
                break;
            }
        }

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        System.out.println("Available Doctors:");
        viewDoctors();

        System.out.print("Enter doctor name for appointment: ");
        String doctorName = scanner.nextLine();

        Doctor doctor = null;
        for (Doctor d : doctors) {
            if (d.name.equalsIgnoreCase(doctorName)) {
                doctor = d;
                break;
            }
        }

        if (doctor == null) {
            System.out.println("Doctor not found.");
            return;
        }

        System.out.print("Enter appointment date (DD-MM-YYYY): ");
        String date = scanner.nextLine();

        Appointment appointment = new Appointment(patient, doctor, date);
        appointments.add(appointment);
        System.out.println("Appointment booked successfully.");
    }

    // Method to view appointments
    private static void viewAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments available.");
            return;
        }

        System.out.println("Scheduled Appointments:");
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }

    // Method to add a doctor (Admin only)
    private static void addDoctor(Scanner scanner) {
        System.out.print("Enter admin ID: ");
        String adminId = scanner.nextLine();

        if (!isAdmin(adminId)) {
            System.out.println("Invalid Admin ID! Access denied.");
            return;  // Don't close the program, just return to the menu
        }

        System.out.print("Enter doctor's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter doctor's specialty: ");
        String specialty = scanner.nextLine();
        System.out.print("Enter doctor's contact (10 digits): ");
        String contact = scanner.nextLine();

        if (!isValidContact(contact)) {
            System.out.println("Invalid contact number! It must be 10 digits.");
            return;
        }

        System.out.print("Enter doctor's year of birth (1900 to 2025): ");
        int dobYear = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (dobYear < 1900 || dobYear > 2025) {
            System.out.println("Invalid year of birth! It must be between 1900 and 2025.");
            return;
        }

        Doctor doctor = new Doctor(name, specialty, contact, dobYear);
        doctors.add(doctor);
        System.out.println("Doctor added successfully.");

        // Add or remove admin functionality
        System.out.println("1. Add Admin");
        System.out.println("2. Remove Admin");
        System.out.print("Choose an option (1 or 2): ");
        int adminChoice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        if (adminChoice == 1) {
            System.out.print("Enter admin ID to add: ");
            String newAdminId = scanner.nextLine();
            if (!admins.contains(newAdminId)) {
                admins.add(newAdminId);
                System.out.println("Admin added successfully.");
            } else {
                System.out.println("This admin ID already exists.");
            }
        } else if (adminChoice == 2) {
            System.out.print("Enter admin ID to remove: ");
            String adminToRemove = scanner.nextLine();
            if (adminToRemove.equals(MAIN_ADMIN_ID)) {
                System.out.println("Cannot remove the main admin!");
            } else if (admins.contains(adminToRemove)) {
                admins.remove(adminToRemove);
                System.out.println("Admin removed successfully.");
            } else {
                System.out.println("Admin not found.");
            }
        } else {
            System.out.println("Invalid choice!");
        }
    }

    // Method to manage admins (Main admin only)
    private static boolean isAdmin(String adminId) {
        return admins.contains(adminId);
    }

    // Method to validate contact number (must be 10 digits)
    private static boolean isValidContact(String contact) {
        return contact.matches("\\d{10}");
    }
}
