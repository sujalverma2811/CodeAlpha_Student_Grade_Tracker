import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int numStudents = sc.nextInt();
        sc.nextLine(); // consume newline

        for (int i = 0; i < numStudents; i++) {
            System.out.println("\nEnter details for Student #" + (i + 1));

            System.out.print("Enter student name: ");
            String name = sc.nextLine();

            Student student = new Student(name);

            System.out.print("Enter grade for English: ");
            int english = sc.nextInt();

            System.out.print("Enter grade for Hindi: ");
            int hindi = sc.nextInt();

            System.out.print("Enter grade for Math: ");
            int math = sc.nextInt();

            student.addGrade(english);
            student.addGrade(hindi);
            student.addGrade(math);

            students.add(student);
            sc.nextLine(); // clear buffer
        }

        // ✅ Print and write summary to file
        try {
            FileWriter writer = new FileWriter("report.txt");

            System.out.println("\n--- Summary Report ---");

            for (Student s : students) {
                String summary = "Name: " + s.name + "\n"
                        + "Grades: " + s.getGrades() + "\n"
                        + String.format("Average: %.2f\n", s.getAverage())
                        + "Highest: " + s.getHighest() + "\n"
                        + "Lowest: " + s.getLowest() + "\n"
                        + "-----------------------\n";

                System.out.print(summary);     // print to console
                writer.write(summary);         // write to file
            }

            writer.close();
            System.out.println("\n✅ Report saved to report.txt");

        } catch (IOException e) {
            System.out.println("❌ Error writing to file: " + e.getMessage());
        }

        sc.close();
    }
}
