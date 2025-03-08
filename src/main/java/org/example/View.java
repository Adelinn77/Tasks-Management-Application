package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class View extends JFrame {
    private JMenuBar menuBar = new JMenuBar();
    private JButton employeesButton = new JButton("View Employees");
    private JButton tasksButton = new JButton("View Tasks");
    private JButton addEmployeeButton = new JButton("Add Employee");
    private JButton addTaskButton = new JButton("Add Task");
    JPanel contentPane = new JPanel();
    Controller controller = new Controller(this);

    public View(String name) {
        super(name);
        this.prepareGui();
    }

    public void prepareGui() {
        this.setSize(1200, 700);
        contentPane.setLayout(new BorderLayout());
        this.setContentPane(contentPane);
        this.setMenuBar(menuBar);

        this.setJMenuBar(menuBar);
        this.setContentPane(contentPane);
    }

    public void setMenuBar(JMenuBar menuBar) {
        menuBar.setOpaque(true);
        menuBar.setBackground(new Color(173, 216, 230));
        menuBar.setPreferredSize(new Dimension(200,100));
        menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.X_AXIS));

        this.customizeButton(employeesButton);
        this.employeesButton.addActionListener(this.controller);

        this.customizeButton(tasksButton);
        this.tasksButton.addActionListener(this.controller);

        this.customizeButton(addEmployeeButton);
        this.addEmployeeButton.addActionListener(this.controller);

        this.customizeButton(addTaskButton);
        this.tasksButton.addActionListener(this.controller);

        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(employeesButton);
        menuBar.add(addEmployeeButton);
        menuBar.add(tasksButton);
        menuBar.add(addTaskButton);
        menuBar.add(Box.createHorizontalGlue());
    }

    public void customizeButton(JButton button) {
        button.setBackground(new Color(200, 162, 200));
        button.setPreferredSize(new Dimension(200,150));
        button.setFont(new Font("Arial", Font.BOLD, 20));
    }

    public void displayEmployeesTable(Model model) {
        String[] columnNames = {"ID", "Name"};
        Object[][] data = new Object[model.getEmployees().size()][2];

        int i = 0;
        for (Employee employee : model.getEmployees()) {
            System.out.println(employee.toString());
            data[i][0] = employee.getIdEmployee();
            data[i++][1] = employee.getName();
        }

        JTable table = new JTable(new DefaultTableModel(data, columnNames));
        JScrollPane tableScrollPane = new JScrollPane(table);

        tableScrollPane.setPreferredSize(new Dimension(400, 300));
        table.setFont(new Font("Arial", Font.PLAIN, 20));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 22)); // Header bigger

        contentPane.removeAll();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(tableScrollPane, BorderLayout.CENTER);
        this.setContentPane(contentPane);
    }

    public void displayTasksTable(Model model) {
        String[] columnNames = {"ID","Type", "Status", "Duration"};
        Object[][] data = new Object[model.getTasks().size()][4];
        int i = 0;
        for (Task task : model.getTasks()) {
            System.out.println(task.toString());
            data[i][0] = task.getIdTask();
            data[i][1] = (task instanceof SimpleTask) ? "simple" : "complex";
            data[i][2] = task.getStatusTask();
            data[i++][3] = task.estimateDuration();
        }
        JTable table = new JTable(new DefaultTableModel(data, columnNames));
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(400, 300));
        table.setFont(new Font("Arial", Font.PLAIN, 20));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 22));
        contentPane.removeAll();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(tableScrollPane, BorderLayout.CENTER);
        this.setContentPane(contentPane);
    }

    public void openAddEmployeeDialog() {
        JTextField field1 = new JTextField();

        Object[] message = {
                "Enter employee's name:", field1,
        };

        int option = JOptionPane.showConfirmDialog(null, message, "New Employee", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String value1 = field1.getText();
        } else {
            System.out.println("Cancelled");
        }
    }

    public void openAddTaskDialog() {
        JTextField field1 = new JTextField();
        Object[] message = {
                "Enter employee's name:", field1,
        };

        int option = JOptionPane.showConfirmDialog(null, message, "New Task", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String value1 = field1.getText();
        } else {
            System.out.println("Cancelled");
        }
    }

    public JButton getEmployeesButton() {
        return employeesButton;
    }

    public JButton getTasksButton() {
        return tasksButton;
    }

    public JButton getAddEmployeeButton() {
        return addEmployeeButton;
    }

    public JButton getAddTaskButton() {
        return addTaskButton;
    }
}

