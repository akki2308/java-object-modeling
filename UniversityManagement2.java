import java.util.ArrayList;
import java.util.List;

// Professor class (Aggregation: A course has professors, but they exist independently)
class Professor {
    private String name;
    private String department;

    public Professor(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }
}

// Course class (Aggregates Professors & Associates Students)
class Course {
    private String courseName;
    private List<Student> students;
    private List<Professor> professors;

    public Course(String courseName) {
        this.courseName = courseName;
        this.students = new ArrayList<>();
        this.professors = new ArrayList<>();
    }

    // Enroll student in course
    public void enrollStudent(Student student) {
        students.add(student);
        System.out.println(student.getName() + " enrolled in " + courseName);
    }

    // Assign professor to course
    public void assignProfessor(Professor professor) {
        professors.add(professor);
        System.out.println(professor.getName() + " assigned to " + courseName);
    }

    // Display course details
    public void displayCourseDetails() {
        System.out.println("\nCourse: " + courseName);
        System.out.println("Professors:");
        for (Professor prof : professors) {
            System.out.println("   - " + prof.getName() + " (" + prof.getDepartment() + ")");
        }
        System.out.println("Enrolled Students:");
        for (Student student : students) {
            System.out.println("   - " + student.getName());
        }
    }
}

// Student class (Association: A student can enroll in multiple courses)
class Student {
    private String name;
    private List<Course> enrolledCourses;

    public Student(String name) {
        this.name = name;
        this.enrolledCourses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    // Enroll in a course
    public void enrollCourse(Course course) {
        enrolledCourses.add(course);
        course.enrollStudent(this);
    }

    // View enrolled courses
    public void viewCourses() {
        System.out.println("\nStudent: " + name);
        System.out.println("Enrolled Courses:");
        for (Course course : enrolledCourses) {
            System.out.println("   - " + course);
        }
    }
}

// University System (Manages Students, Professors, and Courses)
class UniversityManagement2 {
    public static void main(String[] args) {
        // Create Professors
        Professor prof1 = new Professor("Dr. Smith", "Computer Science");
        Professor prof2 = new Professor("Dr. Johnson", "Mathematics");

        // Create Courses
        Course course1 = new Course("Data Structures");
        Course course2 = new Course("Linear Algebra");

        // Assign Professors to Courses
        course1.assignProfessor(prof1);
        course2.assignProfessor(prof2);

        // Create Students
        Student student1 = new Student("Alice");
        Student student2 = new Student("Bob");

        // Students Enrolling in Courses
        student1.enrollCourse(course1);
        student2.enrollCourse(course1);
        student1.enrollCourse(course2);

        // Display Course Details
        course1.displayCourseDetails();
        course2.displayCourseDetails();

        // View Student Enrollments
        student1.viewCourses();
        student2.viewCourses();
    }
}
