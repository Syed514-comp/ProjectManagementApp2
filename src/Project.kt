import java.awt.*
import javax.swing.*
    class Project {
        fun addProject(frame: JFrame, projectPanel: JPanel) {
            val projectName = JOptionPane.showInputDialog(frame, "Enter project name:")

            if (projectName != null && projectName.isNotEmpty()) {
                val projectInfoPanel = JPanel()
                projectInfoPanel.layout = FlowLayout(FlowLayout.LEFT)
                val projectNameLabel = JLabel(projectName)
                projectInfoPanel.maximumSize = Dimension(1350, 100)
                projectInfoPanel.background = Color.WHITE
                projectInfoPanel.add(projectNameLabel)

                projectInfoPanel.addMouseListener(object : java.awt.event.MouseAdapter() {
                    override fun mouseClicked(e: java.awt.event.MouseEvent?) {
                        val task = Task()
                        task.showTasks(projectName)
                    }
                })

                projectPanel.add(projectInfoPanel)
                projectPanel.add(Box.createRigidArea(Dimension(0, 5)))
                frame.revalidate()
                frame.repaint()

                JOptionPane.showMessageDialog(frame, "Added Project: $projectName")
            } else {
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