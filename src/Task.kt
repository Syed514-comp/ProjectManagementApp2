import javax.swing.*
import java.awt.*
import java.util.UUID

class Task {
    // Map to store task assignments for each project
    private val taskAssignments = mutableMapOf<String, MutableList<String>>()

    fun showTasks(projectName: String) {
        val taskWindow = JFrame("$projectName Tasks")
        taskWindow.defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
        taskWindow.size = Dimension(1800, 1600)

        val contentPane = JPanel(null)
        taskWindow.contentPane = contentPane

        val label1 = JLabel("All Tasks: ", JLabel.LEFT)
        label1.font = Font("Times New Roman", Font.BOLD, 40)
        label1.isOpaque = true
        label1.bounds = Rectangle(160, 6, 300, 50)

        val taskPanel = JPanel()
        taskPanel.layout = BoxLayout(taskPanel, BoxLayout.Y_AXIS)
        taskPanel.background = Color.LIGHT_GRAY
        taskPanel.bounds = Rectangle(160, 70, 600, 460)

        val addTaskButton = JButton("Add Task")
        addTaskButton.bounds = Rectangle(2, 70, 150, 60)

        val removeTaskButton = JButton("Remove Task")
        removeTaskButton.bounds = Rectangle(2, 150, 150, 60)

        addTaskButton.addActionListener {
            val taskName = JOptionPane.showInputDialog(null, "Enter task name:")
            val taskDuration = JOptionPane.showInputDialog(null, "Enter task duration (in hours):")
            val assignee = JOptionPane.showInputDialog(null, "Assign task to (Person's name):")

            if (taskName != null && taskName.isNotEmpty() && taskDuration != null && taskDuration.isNotEmpty() && assignee != null && assignee.isNotEmpty()) {
                val duration = Integer.parseInt(taskDuration)

                val taskId = UUID.randomUUID().toString() // Generate a unique ID for each task
                val newTaskLabel = JLabel("$taskName (Duration: $duration hours) - Assigned to: $assignee")
                newTaskLabel.size = Dimension(800, 30)
                newTaskLabel.background = Color.WHITE
                newTaskLabel.isOpaque = true
                newTaskLabel.name = taskId // Store the unique ID as the label's name

                newTaskLabel.addMouseListener(object : java.awt.event.MouseAdapter() {
                    override fun mouseClicked(evt: java.awt.event.MouseEvent?) {
                        val confirm = JOptionPane.showConfirmDialog(null, "Is this task complete?")
                        if (confirm == JOptionPane.YES_OPTION) {
                            taskPanel.remove(newTaskLabel)
                            taskPanel.revalidate()
                            taskPanel.repaint()
                            taskAssignments[projectName]?.remove("$assignee: $taskName")
                        }
                    }
                })

                taskAssignments.computeIfAbsent(projectName) { mutableListOf() }.add("$assignee: $taskName")
                taskPanel.add(newTaskLabel)
                taskPanel.add(Box.createRigidArea(Dimension(0, 5)))
                taskPanel.revalidate()
                taskPanel.repaint()
            } else {
                JOptionPane.showMessageDialog(null, "Task name, duration, or assignee cannot be empty.")
            }
        }


        contentPane.add(label1)
        contentPane.add(addTaskButton)
        contentPane.add(taskPanel)

        contentPane.setComponentZOrder(addTaskButton, 0)

        taskWindow.isVisible = true
    }
}
