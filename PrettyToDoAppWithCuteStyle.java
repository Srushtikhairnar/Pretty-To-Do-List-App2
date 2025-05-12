import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class PrettyToDoAppWithCuteStyle {
    private JFrame frame;
    private JPanel taskPanel;
    private JTextField taskInput;
    private java.util.List<TaskItem> tasks = new ArrayList<>();

    public PrettyToDoAppWithCuteStyle() {
        // 💕 Frame setup
        frame = new JFrame("🩷 Blush To-Do List");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(255, 228, 235)); // pastel pink

        // 💕 Input field
        taskInput = new JTextField();
        taskInput.setFont(new Font("Comic Sans MS", Font.PLAIN, 16)); // Cute font
        taskInput.setBackground(new Color(255, 240, 245));
        taskInput.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        // 💕 Buttons
        JButton addButton = new JButton("➕ Add");
        JButton deleteButton = new JButton("❌ Delete Selected");

        styleButton(addButton);
        styleButton(deleteButton);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        buttonPanel.setBackground(new Color(255, 228, 235));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        // 💕 Task list panel
        taskPanel = new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
        taskPanel.setBackground(new Color(255, 228, 235));

        JScrollPane scrollPane = new JScrollPane(taskPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(12);

        // ➕ Add task logic
        addButton.addActionListener(e -> {
            String text = taskInput.getText().trim();
            if (!text.isEmpty()) {
                addTask(text);
                taskInput.setText("");
            }
        });

        // ❌ Delete selected tasks
        deleteButton.addActionListener(e -> {
            tasks.removeIf(task -> {
                if (task.checkBox.isSelected()) {
                    taskPanel.remove(task.panel);
                    return true;
                }
                return false;
            });
            taskPanel.revalidate();
            taskPanel.repaint();
        });

        frame.add(taskInput, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void addTask(String taskText) {
        JCheckBox checkBox = new JCheckBox(taskText);
        checkBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        checkBox.setBackground(new Color(255, 228, 235));
        checkBox.setFocusable(false);

        JPanel singleTaskPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        singleTaskPanel.setBackground(new Color(255, 228, 235));
        singleTaskPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        singleTaskPanel.add(checkBox);

        TaskItem item = new TaskItem(checkBox, singleTaskPanel);
        tasks.add(item);
        taskPanel.add(singleTaskPanel);

        taskPanel.revalidate();
        taskPanel.repaint();
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        button.setBackground(new Color(255, 200, 221));
        button.setForeground(Color.DARK_GRAY);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(255, 160, 200), 2));
    }

    // Internal class for task tracking
    private static class TaskItem {
        JCheckBox checkBox;
        JPanel panel;

        TaskItem(JCheckBox checkBox, JPanel panel) {
            this.checkBox = checkBox;
            this.panel = panel;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PrettyToDoAppWithCuteStyle::new);
    }
}
