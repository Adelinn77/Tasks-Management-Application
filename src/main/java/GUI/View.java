package GUI;

import BusinessLogic.Controller;
import BusinessLogic.Utility;
import DataAcces.Model;
import DataModel.ComplexTask;
import DataModel.Employee;
import DataModel.SimpleTask;
import DataModel.Task;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

public class View extends JFrame {
    private JMenuBar menuBar = new JMenuBar();
    private JButton employeesButton = new JButton("View Employees");
    private JButton tasksButton = new JButton("View Tasks");
    private JButton addEmployeeButton = new JButton("Add Employee");
    private JButton addTaskButton = new JButton("Add Task");
    private JButton assignTaskButton = new JButton("Assign Task");
    private JButton modifyTaskButton = new JButton("Modify Tasks Status");

    private JButton simpleTaskButton = new JButton("Add simple task");
    private JButton complexTaskButton = new JButton("Add complex task");
    private JButton simpleLeafTaskButton = new JButton("+ ST");
    private JButton complexTreeTaskButton = new JButton("+ CT");
    private JButton filterEmployeesButton = new JButton("Filter by work duration");
    private JPanel contentPane = new JPanel();
    private Controller controller = new Controller(this);

    public static Stack<ComplexTask> taskHistory = new Stack<>();

    public View(String name) {
        super(name);
        this.prepareGui();
    }

    public void clearTaskHistory() {
        taskHistory.clear();
    }

    public void prepareGui() {
        this.setSize(1200, 700);
        contentPane.setLayout(new BorderLayout());
        this.setContentPane(contentPane);
        this.setMenuBar(menuBar);
        this.simpleTaskButton.addActionListener(this.controller);
        this.simpleLeafTaskButton.addActionListener(this.controller);
        this.complexTaskButton.addActionListener(this.controller);
        this.complexTreeTaskButton.addActionListener(this.controller);
        this.modifyTaskButton.addActionListener(this.controller);
        this.filterEmployeesButton.addActionListener(this.controller);

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
        this.addTaskButton.addActionListener(this.controller);

        this.customizeButton(assignTaskButton);
        this.assignTaskButton.addActionListener(this.controller);

        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(employeesButton);
        menuBar.add(addEmployeeButton);
        menuBar.add(tasksButton);
        menuBar.add(addTaskButton);
        menuBar.add(assignTaskButton);
        menuBar.add(Box.createHorizontalGlue());
    }

    public void customizeButton(JButton button) {
        button.setBackground(new Color(200, 162, 200));
        button.setPreferredSize(new Dimension(200,150));
        button.setFont(new Font("Segoe UI", Font.BOLD, 20));
    }

    public void customizeModifyStatusButton(JButton button) {
        button.setBackground(new Color(200, 162, 200));
        button.setPreferredSize(new Dimension(270,50));
        button.setFont(new Font("Segoe UI", Font.BOLD, 20));
    }

    public void customizeFilterEmployeesButton(JButton button) {
        button.setBackground(new Color(200, 162, 200));
        button.setPreferredSize(new Dimension(270,50));
        button.setFont(new Font("Segoe UI", Font.BOLD, 20));
    }

    public void customizeSideButton(JButton button) {
        button.setBackground(new Color(200, 162, 200));
        button.setPreferredSize(new Dimension(250,80));
        button.setFont(new Font("Segoe UI", Font.BOLD, 20));
    }

    public void customizeTreeButton(JButton button) {
        button.setBackground(new Color(200, 162, 200));
        button.setPreferredSize(new Dimension(80,80));
        button.setFont(new Font("Segoe UI", Font.BOLD, 20));
    }

    public void customizeBackButton(JButton button) {
        button.setBackground(new Color(200, 162, 200));
        button.setPreferredSize(new Dimension(120,50));
        button.setFont(new Font("Segoe UI", Font.BOLD, 20));
    }

    public void customizeCloseButton(JButton button) {
        button.setBackground(new Color(200, 162, 200));
        button.setPreferredSize(new Dimension(100,40));
        button.setFont(new Font("Segoe UI", Font.BOLD, 20));
    }

    public void customizeTable(JTable table) {
        table.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 22));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        List<TableColumn> columns = Collections.list(table.getColumnModel().getColumns());
        for (TableColumn column : columns) {
            column.setCellRenderer(centerRenderer);
        }

    }

    public void displayEmployeesTable() {
        String[] columnNames = {"ID", "Name", "Work Duration","Tasks", "Completed Tasks", "Uncompleted Tasks"};
        int noEmployees = 0;
        List<Employee> employees = Model.loadEmployees();
        if(employees != null) noEmployees = employees.size();
        else {
            employees = new ArrayList<>();
        };
        Object[][] data = new Object[noEmployees][6];

        HashMap <String, Map<String, Integer>> statusTasks = Utility.statusTasksForEmmployees(Model.loadTasksManagement());
        int i = 0;
        for (Employee employee : employees) {
            data[i][0] = employee.getIdEmployee();
            data[i][1] = employee.getName();
            data[i][2] = Model.loadTasksManagement().calculateEmployeeWorkDuration(employee.getIdEmployee());
            data[i][3] = "[*]";
            data[i][4] = statusTasks.get(employee.getName()).get("Completed");
            data[i++][5] = statusTasks.get(employee.getName()).get("Uncompleted");
        }

        JTable table = new JTable(new DefaultTableModel(data, columnNames));
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(400, 300));
        this.customizeTable(table);
        this.addViewEmployeesTableEvents(table, employees);
        this.customizeFilterEmployeesButton(filterEmployeesButton);

        contentPane.removeAll();
        contentPane.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.add(filterEmployeesButton, BorderLayout.CENTER);

        contentPane.add(panel, BorderLayout.NORTH);
        contentPane.add(tableScrollPane, BorderLayout.CENTER);
        this.setContentPane(contentPane);
    }

    public void displayTasksTable() {
        String[] columnNames = {"ID","Type", "Status", "Duration", "Details"};
        int noTasks = 0;
        List<Task> tasks = flattenTasks(Model.loadTasks());
        if(tasks != null) noTasks = tasks.size();
        else {
            tasks = new ArrayList<>();
        }
        Object[][] data = new Object[noTasks][5];
        int i = 0;
        for (Task task : tasks) {
            if(task != null){
                data[i][0] = task.getIdTask();
                data[i][1] = (task instanceof SimpleTask) ? "simple" : "complex";
                data[i][2] = task.getStatusTask();
                data[i][3] = task.estimateDuration();
                data[i++][4] = (task instanceof SimpleTask) ? "" : "[*]";;

            }
        }

        JTable table = new JTable(new DefaultTableModel(data, columnNames));
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(400, 300));
        this.customizeTable(table);
        this.addViewTasksTableEvents(table, tasks);

        contentPane.removeAll();
        contentPane.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        customizeModifyStatusButton(modifyTaskButton);
        panel.add(modifyTaskButton);
        contentPane.add(panel, BorderLayout.NORTH);
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
            Model.loadEmployees();
            Employee employee = new Employee(value1);
            Model.addEmployee(employee);
            Model.addEmployeeInTaskManagement(employee);
        }
    }

    public void openAssignTaskDialog() {
        JTextField field1 = new JTextField();
        JTextField field2 = new JTextField();
        Object[] message = {
                "Enter employee's ID:", field1,
                "Enter task's ID:", field2,
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Assign task to employee", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String value1 = field1.getText();
            String value2 = field2.getText();
            Model.loadTasksManagement().assignTaskToEmployee(Integer.parseInt(value1), Model.findTaskById(Integer.parseInt(value2)));
        }
        else {
            return;
        }
    }


    public void openAddTaskWindow() {
        JPanel panel = new JPanel();
        Model.loadTasks();

        panel.add(simpleTaskButton);
        panel.add(complexTaskButton);

        this.customizeSideButton(simpleTaskButton);
        this.customizeSideButton(complexTaskButton);
        contentPane.removeAll();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(panel, BorderLayout.CENTER);
        this.setContentPane(contentPane);
    }

    public void openSimpleTaskDialog() {
        JTextField field1 = new JTextField();
        JTextField field2 = new JTextField();
        Object[] message = {
                "Enter start hour:", field1,
                "Enter end hour:", field2,
        };
        int option = JOptionPane.showConfirmDialog(null, message, "New Simple Task", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String value1 = field1.getText();
            String value2 = field2.getText();
            SimpleTask simpleTask = new SimpleTask(Integer.parseInt(value1), Integer.parseInt(value2));
            Model.addTask(simpleTask);
        }
    }

    public void openComplexTaskWindow() {

        ComplexTask complexTask = new ComplexTask();
        Model.addTask(complexTask);
        taskHistory.push(complexTask);

        JPanel panel = new JPanel();
        JLabel taskList = new JLabel("The list of tasks for the complex task: ");
        taskList.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panel.add(taskList);

        this.customizeTreeButton(simpleLeafTaskButton);
        this.customizeTreeButton(complexTreeTaskButton);
        panel.add(simpleLeafTaskButton);
        panel.add(complexTreeTaskButton);

        contentPane.removeAll();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(panel, BorderLayout.NORTH);

        this.setContentPane(contentPane);
    }

    public void openSimpleLeafTaskDialog(ComplexTask complexTask) {
        JTextField field1 = new JTextField();
        JTextField field2 = new JTextField();
        Object[] message = {
                "Enter start hour:", field1,
                "Enter end hour:", field2,
        };
        int option = JOptionPane.showConfirmDialog(null, message, "New Simple Task", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String value1 = field1.getText();
            String value2 = field2.getText();
            SimpleTask simpleTask = new SimpleTask(Integer.parseInt(value1), Integer.parseInt(value2));
            complexTask.getTasks().add(simpleTask);
            Model.saveTasks();

            this.displayTasksForComplexTask(complexTask);
        }
    }

    public void displayTasksForComplexTask(ComplexTask complexTask) {
        String[] columnNames = {"ID","Type", "Status", "Duration", "", ""};

        Object[][] data = new Object[complexTask.getTasks().size()][7];
        int i = 0;
        for (Task task : complexTask.getTasks()) {
            data[i][0] = task.getIdTask();
            data[i][1] = (task instanceof SimpleTask) ? "simple" : "complex";
            data[i][2] = task.getStatusTask();
            data[i][3] = task.estimateDuration();
            data[i][4] = (task instanceof ComplexTask) ? "+ST" : "";
            data[i++][5] = (task instanceof ComplexTask) ? "+CT" : "";
        }

        JTable table = new JTable(new DefaultTableModel(data, columnNames));
        this.customizeTable(table);
        this.addComplexTaskTableEvents(table, complexTask);
        JScrollPane tableScrollPane = new JScrollPane(table);

        contentPane.removeAll();
        JPanel panel = this.createTableForCT();
        contentPane.add(panel, BorderLayout.NORTH);
        contentPane.add(tableScrollPane, BorderLayout.CENTER);
        this.setContentPane(contentPane);
        contentPane.revalidate();
        contentPane.repaint();
    }

    public JPanel createTableForCT() {
        JPanel panel = new JPanel(new FlowLayout());
        JLabel taskList = new JLabel("The list of tasks for the complex task: ");
        taskList.setFont(new Font("Segoe UI", Font.BOLD, 18));

        panel.add(taskList);
        if(taskHistory.size() == 1) {
            panel.add(simpleLeafTaskButton);
            panel.add(complexTreeTaskButton);
        }

        if (taskHistory.size() > 1) {
            JButton backButton = new JButton("<- Back");
            this.customizeBackButton(backButton);
            backButton.addActionListener(e -> {
                taskHistory.pop();
                displayTasksForComplexTask(taskHistory.peek());
            });
            panel.add(backButton);
        }

        return panel;
    }

    public void addComplexTaskInTree(ComplexTask parentComplexTask, ComplexTask childComplexTask) {
        parentComplexTask.getTasks().add(childComplexTask);
        Model.saveTasks();
        taskHistory.push(childComplexTask);
        this.displayTasksForComplexTask(childComplexTask);
    }

    public void addComplexTaskTableEvents(JTable table, ComplexTask complexTask) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());

                if (col == 4) {
                    Task selectedTask = complexTask.getTasks().get(row);
                    if (selectedTask instanceof ComplexTask) {
                        taskHistory.push((ComplexTask) selectedTask);
                        openSimpleLeafTaskDialog((ComplexTask)selectedTask);
                    }
                }
                if (col == 5) {
                    Task selectedTask = complexTask.getTasks().get(row);
                    if (selectedTask instanceof ComplexTask) {
                        taskHistory.push((ComplexTask)selectedTask);
                        ComplexTask childComplexTask = new ComplexTask();
                        addComplexTaskInTree((ComplexTask)selectedTask, childComplexTask);
                    }
                }
            }
        });
    }

    public static List<Task> flattenTasks(List<Task> roots) {
        List<Task> all = new ArrayList<>();
        if(roots != null) {
            for (Task task : roots) {
                collect(task, all);
            }
        }
        return all;
    }

    private static void collect(Task task, List<Task> all) {
        all.add(task);
        if (task instanceof ComplexTask complex) {
            for (Task sub : complex.getTasks()) {
                collect(sub, all);
            }
        }
    }

    public void addViewTasksTableEvents(JTable table, List<Task> tasks) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());
                if (col == 4) {
                    if(!tasks.isEmpty()){
                        Task selectedTask = tasks.get(row);
                        if (selectedTask instanceof ComplexTask) {
                            viewTasksForComplexTask((ComplexTask)selectedTask);
                        }
                    }
                }
            }
        });
    }

    public void addViewEmployeesTableEvents(JTable table, List<Employee> employees) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());
                if (col == 3) {
                    if(!employees.isEmpty()){
                        Employee selectedEmployee = employees.get(row);
                        viewTasksForEmployee(selectedEmployee);
                    }
                }
            }
        });
    }

    public void viewTasksForEmployee(Employee employee) {
        List<Task> tasksOfEmployee = Model.loadTasksManagement().getTasksOfEmployee(employee);
        List<Task> tasks = flattenTasks(tasksOfEmployee);

        JDialog dialog = new JDialog(this, "Tasks for employee: " + employee.getName(), true);
        dialog.setSize(500, 300);
        dialog.setLayout(new BorderLayout());

        String[] columnNames = {"ID", "Type", "Status", "Duration"};
        Object[][] data = new Object[tasks.size()][4];

        int i = 0;
        for (Task task : tasks) {
            data[i][0] = task.getIdTask();
            data[i][1] = (task instanceof SimpleTask) ? "simple" : "complex";
            data[i][2] = task.getStatusTask();
            data[i++][3] = task.estimateDuration();
        }

        JTable tasksTable = new JTable(new DefaultTableModel(data, columnNames)) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        List<TableColumn> columns = Collections.list(tasksTable.getColumnModel().getColumns());
        for (TableColumn column : columns) {
            column.setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(tasksTable);
        dialog.add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        customizeCloseButton(closeButton);
        closeButton.addActionListener(e -> dialog.dispose());

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(closeButton);
        dialog.add(bottomPanel, BorderLayout.SOUTH);

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    public void viewTasksForComplexTask(ComplexTask complexTask) {
        JDialog dialog = new JDialog(this, "Subtasks for Complex Task " + complexTask.getIdTask(), true);
        dialog.setSize(500, 300);
        dialog.setLayout(new BorderLayout());

        String[] columnNames = {"ID", "Type", "Status", "Duration"};
        Object[][] data = new Object[complexTask.getTasks().size()][4];

        int i = 0;
        for (Task subtask : complexTask.getTasks()) {
            data[i][0] = subtask.getIdTask();
            data[i][1] = (subtask instanceof SimpleTask) ? "simple" : "complex";
            data[i][2] = subtask.getStatusTask();
            data[i++][3] = subtask.estimateDuration();
        }

        JTable subtaskTable = new JTable(new DefaultTableModel(data, columnNames)) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        List<TableColumn> columns = Collections.list(subtaskTable.getColumnModel().getColumns());
        for (TableColumn column : columns) {
            column.setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(subtaskTable);
        dialog.add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        customizeCloseButton(closeButton);
        closeButton.addActionListener(e -> dialog.dispose());

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(closeButton);
        dialog.add(bottomPanel, BorderLayout.SOUTH);

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    public void openModifyTaskStatusDialog(){
        JTextField field1 = new JTextField();
        Object[] message = {
                "Enter task's id:", field1,
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Modify status", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String value1 = field1.getText();
            Model.loadTasksManagement().modifyTaskStatus(1, Integer.parseInt(value1));
        }
    }

    public void filterEmployeesDialog(){
        HashMap<Integer, ArrayList<Employee>> greatEmployees = Utility.over40Hours(Model.loadTasksManagement());

        JDialog dialog = new JDialog(this, "All employees with a work duration greater than 40 hours", true);
        dialog.setSize(500, 300);
        dialog.setLayout(new BorderLayout());

        String[] columnNames = {"ID", "Name", "Work Duration"};
        int totalEmployees = 0;
        for (ArrayList<Employee> list : greatEmployees.values()) {
            totalEmployees += list.size();
        }

        Object[][] data = new Object[totalEmployees][3];

        int i = 0;
        for (Integer wd : greatEmployees.keySet()) {
            for(Employee e : greatEmployees.get(wd)) {
                data[i][0] = e.getIdEmployee();
                data[i][1] = e.getName();
                data[i++][2] = wd;
            }
        }

        JTable greatEmployeesTable = new JTable(new DefaultTableModel(data, columnNames)) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        List<TableColumn> columns = Collections.list(greatEmployeesTable.getColumnModel().getColumns());
        for (TableColumn column : columns) {
            column.setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(greatEmployeesTable);
        dialog.add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        customizeCloseButton(closeButton);
        closeButton.addActionListener(e -> dialog.dispose());

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(closeButton);
        dialog.add(bottomPanel, BorderLayout.SOUTH);

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
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

    public JButton getAssignTaskButton() {
        return assignTaskButton;
    }

    public JButton getSimpleTaskButton() {
        return simpleTaskButton;
    }

    public JButton getComplexTaskButton() {
        return complexTaskButton;
    }

    public JButton getSimpleLeafTaskButton() {
        return simpleLeafTaskButton;
    }

    public JButton getComplexTreeTaskButton() {
        return complexTreeTaskButton;
    }

    public JButton getModifyTaskButton() {
        return modifyTaskButton;
    }

    public JButton getFilterEmployeesButton() {
        return filterEmployeesButton;
    }
}

