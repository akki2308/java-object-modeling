import java.util.ArrayList;
import java.util.List;

// Faculty class (Aggregation: Can exist independently of a University)
class Faculty {
    private String name;
    private String specialization;

    public Faculty(String name, String specialization) {
        this.name = name;
        this.specialization = specialization;
    }

    public void displayInfo() {
        System.out.println("Faculty: " + name + " | Specialization: " + specialization);
    }
}

// Department class (Composition: Exists only within a University)
class Department {
    private String departmentName;

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public void displayInfo() {
        System.out.println("Department: " + departmentName);
    }
}

// University class (Contains Departments via Composition & Faculty via Aggregation)
class University {
    private String universityName;
    private List<Department> departments; // Composition: Departments exist only inside a University
    private List<Faculty> facultyMembers; // Aggregation: Faculty can exist outside the University

    public University(String universityName) {
        this.universityName = universityName;
        this.departments = new ArrayList<>();
        this.facultyMembers = new ArrayList<>();
    }

    // Adding a Department (Composition)
    public void addDepartment(String departmentName) {
        departments.add(new Department(departmentName));
    }

    // Adding a Faculty Member (Aggregation)
    public void addFaculty(Faculty faculty) {
        facultyMembers.add(faculty);
    }

    // Display University Details
    public void displayUniversityDetails() {
        System.out.println("\nUniversity: " + universityName);
        System.out.println("Departments:");
        if (departments.isEmpty()) {
            System.out.println("   No departments available.");
        } else {
            for (Department dept : departments) {
                dept.displayInfo();
            }
        }

        System.out.println("\nFaculty Members:");
        if (facultyMembers.isEmpty()) {
            System.out.println("   No faculty members available.");
        } else {
            for (Faculty faculty : facultyMembers) {
                faculty.displayInfo();
            }
        }
    }

    // Deleting University (Composition: Deletes all Departments)
    public void deleteUniversity() {
        System.out.println("\nDeleting University: " + universityName);
        departments.clear(); // Deleting all departments (Composition)
        System.out.println("All departments have been deleted.");
    }
}

// Main class to test the implementation
public class UniversityManagement {
    public static void main(String[] args) {
        // Creating Faculty members (They can exist independently)
        Faculty faculty1 = new Faculty("Dr. Ramesh Kumar", "Computer Science");
        Faculty faculty2 = new Faculty("Dr. Meera Sharma", "Physics");

        // Creating a University
        University myUniversity = new University("Tech University");

        // Adding Departments (Composition)
        myUniversity.addDepartment("Computer Science");
        myUniversity.addDepartment("Physics");
        myUniversity.addDepartment("Mathematics");

        // Adding Faculty Members (Aggregation)
        myUniversity.addFaculty(faculty1);
        myUniversity.addFaculty(faculty2);

        // Display University Details
        myUniversity.displayUniversityDetails();

        // Deleting the University (Composition effect)
        myUniversity.deleteUniversity();

        // Faculty members should still exist (Aggregation effect)
        System.out.println("\nFaculty members still exist after deleting the university:");
        faculty1.displayInfo();
        faculty2.displayInfo();
    }
}
