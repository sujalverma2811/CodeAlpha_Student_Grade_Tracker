import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StudentGradeTrackerGUI {
    private static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Student Grade Tracker");
        frame.setSize(400, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(7, 2));

        // Input fields
        JTextField nameField = new JTextField();
        JTextField englishField = new JTextField();
        JTextField hindiField = new JTextField();
        JTextField mathField = new JTextField();

        JButton addButton = new JButton("Add Student");
        JButton saveButton = new JButton("Save Report");

        // Add components to frame
        frame.add(new JLabel("Student Name:"));
        frame.add(nameField);
        frame.add(new JLabel("English Grade:"));
        frame.add(englishField);
        frame.add(new JLabel("Hindi Grade:"));
        frame.add(hindiField);
        frame.add(new JLabel("Math Grade:"));
        frame.add(mathField);
        frame.add(addButton);
        frame.add(saveButton);

        JTextArea outputArea = new JTextArea();
        JScrollPane scroll = new JScrollPane(outputArea);
        frame.add(new JLabel("Summary:"));
        frame.add(scroll);

        frame.setVisible(true);

        // Add student button action
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            try {
                int eng = Integer.parseInt(englishField.getText());
                int hin = Integer.parseInt(hindiField.getText());
                int math = Integer.parseInt(mathField.getText());

                Student s = new Student(name);
                s.addGrade(eng);
                s.addGrade(hin);
                s.addGrade(math);
                students.add(s);

                String summary = "Name: " + s.name + "\n"
                        + "Grades: " + s.getGrades() + "\n"
                        + String.format("Average: %.2f\n", s.getAverage())
                        + "Highest: " + s.getHighest() + "\n"
                        + "Lowest: " + s.getLowest() + "\n"
                        + "-----------------------\n";

                outputArea.append(summary);

                nameField.setText("");
                englishField.setText("");
                hindiField.setText("");
                mathField.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter valid grades!");
            }
        });

        // Save report button action
        saveButton.addActionListener(e -> {
            try {
                FileWriter writer = new FileWriter("report.txt");
                for (Student s : students) {
                    String summary = "Name: " + s.name + "\n"
                            + "Grades: " + s.getGrades() + "\n"
                            + String.format("Average: %.2f\n", s.getAverage())
                            + "Highest: " + s.getHighest() + "\n"
                            + "Lowest: " + s.getLowest() + "\n"
                            + "-----------------------\n";
                    writer.write(summary);
                }
                writer.close();
                JOptionPane.showMessageDialog(frame, "✅ Report saved to report.txt");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "❌ Error saving file.");
            }
        });
    }
}
