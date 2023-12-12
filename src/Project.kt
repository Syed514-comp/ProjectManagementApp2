// Import necessary classes from the javax.swing and java.awt packages
import javax.swing.*
import java.awt.*

// Define a Kotlin class named Project
class Project {
    // Function to add a new project to the GUI
    fun addProject(frame: JFrame, projectPanel: JPanel) {
        // Show an input dialog to get the project name from the user
        val projectName = JOptionPane.showInputDialog(frame, "Enter project name:")

        // Check if a valid project name is provided
        if (projectName != null && projectName.isNotEmpty()) {
            // Create a JButton with the project name
            val button3 = JButton(projectName)

            // Set preferred size and background color for the button
            button3.preferredSize = Dimension(280, 30)
            button3.background = Color.WHITE

            // Add the button to the projectPanel and create a space between buttons
            projectPanel.add(button3)
            projectPanel.add(Box.createRigidArea(Dimension(0, 5)))

            // Update the frame layout
            frame.revalidate()
            frame.repaint()

            // Show a message dialog indicating that the project has been added
            JOptionPane.showMessageDialog(frame, "Added Project: $projectName")

            // Add an ActionListener to the button to show tasks when clicked
            button3.addActionListener {
                val task = Task()
                task.showTasks(projectName)
            }
        } else {
            // Show a message dialog if the project name is not provided
            JOptionPane.showMessageDialog(frame, "Project not added.")
        }
    }

    // Function to remove a project from the GUI
    fun removeProject(frame: JFrame, projectPanel: JPanel) {
        // Check if there are any projects in the projectPanel
        if (projectPanel.components.isNotEmpty()) {
            // Collect project names from the existing buttons
            val projectNames = mutableListOf<String>()
            for (component in projectPanel.components) {
                if (component is JButton) {
                    projectNames.add(component.text)
                }
            }

            // Show an input dialog to select a project for removal
            val selectedProject = JOptionPane.showInputDialog(
                frame, "Select project to remove:",
                "Remove Project", JOptionPane.PLAIN_MESSAGE, null, projectNames.toTypedArray(), projectNames[0]
            )

            // Check if a project is selected for removal
            if (selectedProject != null) {
                // Iterate through components to find the selected project button
                for (component in projectPanel.components) {
                    if (component is JButton && component.text == selectedProject) {
                        // Confirm removal with a dialog and remove the project if confirmed
                        val confirm = JOptionPane.showConfirmDialog(frame, "Do you want to remove this project?")
                        if (confirm == JOptionPane.YES_OPTION) {
                            projectPanel.remove(component)
                            projectPanel.revalidate()
                            projectPanel.repaint()
                            break
                        }
                    }
                }
            }
        } else {
            // Show a message dialog if there are no projects to remove
            JOptionPane.showMessageDialog(frame, "No projects to remove.")
        }
    }
}
