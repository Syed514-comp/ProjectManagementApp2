// Import necessary Swing and AWT libraries
import javax.swing.*;
import java.awt.*;

// Main class definition
public class Test {
    // Main method, entry point for the program
    public static void main(String[] args) {
        // Create a JFrame (window) with the title "Home Page" and set its size and default close operation
        JFrame frame = new JFrame("Home Page");
        frame.setSize(1800, 1600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JPanel to act as the main content pane with a null layout
        JPanel contentPane = new JPanel(null);
        frame.setContentPane(contentPane);

        // Create a JLabel with text "All Projects:", left alignment, and a bold Times New Roman font
        JLabel label1 = new JLabel("All Projects: ", JLabel.LEFT);
        label1.setFont(new Font("Times New Roman", Font.BOLD, 40));
        label1.setOpaque(true); // Make the label opaque
        label1.setBounds(160, 6, 300, 50); // Set the bounds (position and size) of the label

        // Create another JPanel for displaying projects with a vertical BoxLayout and a light gray background
        JPanel projectPanel = new JPanel();
        projectPanel.setLayout(new BoxLayout(projectPanel, BoxLayout.Y_AXIS));
        projectPanel.setBackground(Color.LIGHT_GRAY);
        projectPanel.setBounds(160, 70, 1350, 1150); // Set the bounds for the project panel

        // Create "Add Project" button with specified bounds
        JButton button1 = new JButton("Add Project");
        button1.setBounds(2, 70, 150, 60);

        // Create "Remove Project" button with specified bounds
        JButton button2 = new JButton("Remove Project");
        button2.setBounds(2, 150, 150, 60);

        // Create an instance of the Project class
        Project project = new Project();

        // Adding ActionListener to the "Add Project" button
        button1.addActionListener(e -> {
            // Call the non-static method addProject from the instance of the Project class
            project.addProject(frame, projectPanel);
        });

        // Adding ActionListener to the "Remove Project" button
        button2.addActionListener(e -> {
            // Call the non-static method removeProject from the instance of the Project class
            project.removeProject(frame, projectPanel);
        });

        // Add components to the content pane and set the Z order for button1 and button2
        contentPane.add(label1);
        contentPane.add(projectPanel);
        contentPane.add(button1);
        contentPane.add(button2);

        contentPane.setComponentZOrder(button1, 0);
        contentPane.setComponentZOrder(button2, 1);

        // Make the frame visible
        frame.setVisible(true);
    }
}
