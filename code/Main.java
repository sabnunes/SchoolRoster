public class Main {
    public static void main(String[] args) {
        // Create instances of Name
        Name studentName = new Name("John", "Doe");
        Name teacherName = new Name("Jane", "Smith");

        // Create instances of Date
        Date studentDOB = new Date(14, 3, 2005);
        Date teacherDOB = new Date(23, 6, 1985);

        // Create instances of Person (Student and Teacher)
        Student student = null;
        Teacher teacher = null;

        try {
            student = new Student(studentDOB, studentName, "A12345678");
            teacher = new Teacher(teacherDOB, teacherName, "COMPUTER SCIENCE");
        } catch (IllegalPersonException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        // Assuming School class has a method to add students and teachers
        School school = new School();

        if (student != null) {
            school.register(student);
        }
        if (teacher != null) {
            school.register(teacher);
        }

        // Print out the ages and years of each person
        school.printAgesAndYears();

        // Assuming there are toString() methods implemented in the Person, Student, and Teacher classes
        System.out.println(student);
        System.out.println(teacher);
    }
}
