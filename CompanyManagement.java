import java.util.ArrayList;
import java.util.List;

// Employee class (Exists only within a Department)
class Employee {
    private String name;
    private String position;

    public Employee(String name, String position) {
        this.name = name;
        this.position = position;
    }

    public void displayEmployee() {
        System.out.println("   Employee: " + name + " | Position: " + position);
    }
}

// Department class (Exists only within a Company)
class Department {
    private String departmentName;
    private List<Employee> employees;

    public Department(String departmentName) {
        this.departmentName = departmentName;
        this.employees = new ArrayList<>();
    }

    public void addEmployee(String name, String position) {
        employees.add(new Employee(name, position));
    }

    public void displayDepartment() {
        System.out.println("\nDepartment: " + departmentName);
        if (employees.isEmpty()) {
            System.out.println("   No employees in this department.");
        } else {
            for (int i = 0; i < employees.size(); i++) {
                employees.get(i).displayEmployee();
            }
        }
    }
}

// Company class (Owns Departments & Employees - Composition)
class Company {
    private String companyName;
    private List<Department> departments;

    public Company(String companyName) {
        this.companyName = companyName;
        this.departments = new ArrayList<>();
    }

    public void addDepartment(String departmentName) {
        departments.add(new Department(departmentName));
    }

    public Department getDepartment(String departmentName) {
        for (int i = 0; i < departments.size(); i++) {
            if (departments.get(i).departmentName.equals(departmentName)) {
                return departments.get(i);
            }
        }
        return null;
    }

    public void displayCompany() {
        System.out.println("\nCompany: " + companyName);
        if (departments.isEmpty()) {
            System.out.println("No departments available.");
        } else {
            for (int i = 0; i < departments.size(); i++) {
                departments.get(i).displayDepartment();
            }
        }
    }

    // Destructor simulation: When a company is deleted, all departments and employees are also deleted
    public void shutdownCompany() {
        System.out.println("\nCompany " + companyName + " is shutting down...");
        departments.clear();  // This deletes all departments and their employees
        System.out.println("All departments and employees have been removed.");
    }
}

// Main class to test composition
public class CompanyManagement {
    public static void main(String[] args) {
        // Creating a company
        Company myCompany = new Company("Tech Innovators");

        // Adding departments
        myCompany.addDepartment("Engineering");
        myCompany.addDepartment("Human Resources");

        // Adding employees to departments
        Department engineering = myCompany.getDepartment("Engineering");
        if (engineering != null) {
            engineering.addEmployee("Rahul Sharma", "Software Engineer");
            engineering.addEmployee("Priya Verma", "DevOps Engineer");
        }

        Department hr = myCompany.getDepartment("Human Resources");
        if (hr != null) {
            hr.addEmployee("Amit Patel", "HR Manager");
            hr.addEmployee("Sneha Gupta", "Recruiter");
        }

        // Display company details
        myCompany.displayCompany();

        // Deleting the company (Composition Example)
        myCompany.shutdownCompany();
    }
}
