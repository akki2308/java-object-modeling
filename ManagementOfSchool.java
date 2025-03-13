import java.util.ArrayList;
import java.util.List;

// Course class (Many-to-Many Association with Student)
class Course {
    private String courseName;
    private List<Student> enrolledStudents;

    public Course(String courseName) {
        this.courseName = courseName;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getCourseName() {
        return courseName;
    }

    public void enrollStudent(Student student) {
        enrolledStudents.add(student);
    }

    public void displayEnrolledStudents() {
        System.out.println("\nCourse: " + courseName + " - Enrolled Students:");
        if (enrolledStudents.isEmpty()) {
            System.out.println("   No students enrolled.");
        } else {
            for (int i = 0; i < enrolledStudents.size(); i++) {
                System.out.println("   " + enrolledStudents.get(i).getName());
            }
        }
    }
}

// Student class (Can exist independently, Associated with Course)
class Student {
    private String name;
    private int studentID;
    private List<Course> enrolledCourses;

    public Student(String name, int studentID) {
        this.name = name;
        this.studentID = studentID;
        this.enrolledCourses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void enrollInCourse(Course course) {
        enrolledCourses.add(course);
        course.enrollStudent(this); // Association: Linking student and course
    }

    public void displayEnrolledCourses() {
        System.out.println("\nStudent: " + name + " - Enrolled Courses:");
        if (enrolledCourses.isEmpty()) {
            System.out.println("   Not enrolled in any course.");
        } else {
            for (int i = 0; i < enrolledCourses.size(); i++) {
                System.out.println("   " + enrolledCourses.get(i).getCourseName());
            }
        }
    }
}

// School class (Aggregation: Has multiple Students)
class School {
    private String schoolName;
    private List<Student> students;

    public School(String schoolName) {
        this.schoolName = schoolName;
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void displayStudents() {
        System.out.println("\nSchool: " + schoolName + " - Students List:");
        if (students.isEmpty()) {
            System.out.println("   No students enrolled.");
        } else {
            for (int i = 0; i < students.size(); i++) {
                System.out.println("   " + students.get(i).getName());
            }
        }
    }
}

// Main class to test the implementation
public class ManagementOfSchool {
    public static void main(String[] args) {
        // Creating a School
        School mySchool = new School("Greenwood High");

        // Creating Students
        Student student1 = new Student("Aryan Sharma", 101);
        Student student2 = new Student("Sneha Verma", 102);
        Student student3 = new Student("Rohan Gupta", 103);

        // Adding students to the school (Aggregation)
        mySchool.addStudent(student1);
        mySchool.addStudent(student2);
        mySchool.addStudent(student3);

        // Creating Courses
        Course math = new Course("Mathematics");
        Course science = new Course("Science");
        Course history = new Course("History");

        // Enrolling students in courses (Association)
        student1.enrollInCourse(math);
        student1.enrollInCourse(science);

        student2.enrollInCourse(math);
        student2.enrollInCourse(history);

        student3.enrollInCourse(science);

        // Display school students
        mySchool.displayStudents();

        // Display each student's enrolled courses
        student1.displayEnrolledCourses();
        student2.displayEnrolledCourses();
        student3.displayEnrolledCourses();

        // Display enrolled students in each course
        math.displayEnrolledStudents();
        science.displayEnrolledStudents();
        history.displayEnrolledStudents();
    }
}
