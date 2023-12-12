import javax.swing.*;
import java.awt.*;

public class Test {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Home Page");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel(null);
        frame.setContentPane(contentPane);

        JLabel label1 = new JLabel("All Projects: ", JLabel.LEFT);
        label1.setFont(new Font("Times New Roman", Font.BOLD, 40));
        label1.setOpaque(true);
        label1.setBounds(160, 6, 300, 50);

        JPanel projectPanel = new JPanel();
        projectPanel.setLayout(new BoxLayout(projectPanel, BoxLayout.Y_AXIS));
        projectPanel.setBackground(Color.LIGHT_GRAY);
        projectPanel.setBounds(160, 70, 600, 460);

        JButton button1 = new JButton("Add Project");
        button1.setBounds(2, 70, 150, 60);

        JButton button2 = new JButton("Remove Project");
        button2.setBounds(2, 150, 150, 60);


        Project project = new Project();

        // Adding ActionListener to the button1
        button1.addActionListener(e -> {
            // Call the non-static method addProject from the instance
            project.addProject(frame, projectPanel);
        });

        // Adding ActionListener to the button2
        button2.addActionListener(e -> {
            // Call the non-static method removeProject from the instance
            project.removeProject(frame, projectPanel);
        });

        contentPane.add(label1);
        contentPane.add(projectPanel);
        contentPane.add(button1);
        contentPane.add(button2);

        contentPane.setComponentZOrder(button1, 0);
        contentPane.setComponentZOrder(button2, 1);

        frame.setVisible(true);
    }
}
