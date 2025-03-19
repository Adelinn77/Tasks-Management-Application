package DataAcces;
import DataModel.Employee;
import DataModel.Task;
import BusinessLogic.TasksManagement;
import GUI.View;

import java.io.*;
import java.util.*;

public class Model {
    private static final String EMPLOYEES_FILE = "employees.txt";
    private static final String TASKS_FILE = "tasks.txt";
    private static final String TM_FILE = "management.txt";
    private static List<Task> rootTasks = new ArrayList<>();

    public Model() {}

    public static void saveTasks() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(TASKS_FILE))) {
            out.writeObject(rootTasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Task> loadTasks() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(TASKS_FILE))) {
            rootTasks = (List<Task>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            rootTasks = new ArrayList<>();
        }
        resetIdTasks();
        return rootTasks;
    }

    public static void addTask(Task task) {
        if (!rootTasks.contains(task)) {
            rootTasks.add(task);
        }
        saveTasks();
    }

    public static void saveEmployees(List<Employee> employees) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(EMPLOYEES_FILE))) {
            oos.writeObject(employees);
        } catch (IOException e) {
            System.out.println("error saving employees");
        }
    }

    public static List<Employee> loadEmployees() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(EMPLOYEES_FILE))) {
            List<Employee> employees = (List<Employee>) ois.readObject();
            if (employees == null) {
                employees = new ArrayList<>();
            }
            int maxId = 0;
            for (Employee employee : employees) {
                if (employee.getIdEmployee() > maxId) {
                    maxId = employee.getIdEmployee();
                }
            }
            Employee.setId(maxId);
            System.out.println("ID: " + maxId);
            return employees;
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    public static void addEmployee(Employee employee) {
        List<Employee> employees = Model.loadEmployees();
        if(employees == null) {
            employees = new ArrayList<>();
        }
        employees.add(employee);
        saveEmployees(employees);
    }

    public static void resetIdTasks() {
        int maxId = 0;
        for (Task task : View.flattenTasks(rootTasks)) {
            if (task.getIdTask() > maxId) {
                maxId = task.getIdTask();
            }
        }
        Task.setId(maxId);
    }

    public static void saveTaskManagement(TasksManagement tasksManagement) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TM_FILE))) {
            oos.writeObject(tasksManagement);
        } catch (IOException e) {
            System.out.println("error saving task management");
        }
    }

    public static TasksManagement loadTasksManagement() {
        File file = new File(TM_FILE);

        if (!file.exists() || file.length() == 0) {
            return new TasksManagement();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof TasksManagement tm) {
                return tm;
            } else {
                return new TasksManagement();
            }
        } catch (IOException | ClassNotFoundException e) {
            return new TasksManagement();
        }
    }


    public static void addEmployeeInTaskManagement(Employee employee) {
        TasksManagement tasksManagement = loadTasksManagement();
        if (tasksManagement == null) {
            tasksManagement = new TasksManagement();
        }
        tasksManagement.getTasks().put(employee, new ArrayList<>());
        saveTaskManagement(tasksManagement);
    }

    public static Employee findEmployeeById(int id) {
        if(Model.loadEmployees() != null) {
            for(Employee employee : Model.loadEmployees()) {
                if (employee.getIdEmployee() == id) {
                    return employee;
                }
            }
        }
        return null;
    }

    public static void addTaskToEmployee(int employeeId, Task task) {
        TasksManagement tasksManagement = loadTasksManagement();
        if (tasksManagement == null) {
            tasksManagement = new TasksManagement();
        }
        Employee employee = findEmployeeById(employeeId);
        if(employee == null) {
            System.out.println("employee not found");
        }
        else if(tasksManagement.getTasks().containsKey(employee)) {
           tasksManagement.getTasks().get(employee).add(task);
        }
        saveTaskManagement(tasksManagement);
    }

    public static Task findTaskById(int id) {
        List<Task> tasks = View.flattenTasks(Model.loadTasks());

        for(Task task : tasks) {
            if (task.getIdTask() == id) {
                return task;
            }
        }

        return null;
    }

    public static void changeStatusForTask(Task t) {
        if(t.getStatusTask().equals("Uncompleted")){
            System.out.println("now completed");
            t.setStatusTask("Completed");
            modifyInTM(t, "Completed");
        }
        else if(t.getStatusTask().equals("Completed")){
            t.setStatusTask("Uncompleted");
            modifyInTM(t, "Uncompleted");
        }
        System.out.println("modific 6:" + t.getIdTask());
        Model.saveTasks();
    }

    public static void modifyInTM(Task t, String newStatus) {
        TasksManagement tasksManagement = loadTasksManagement();
        for(Employee employee : tasksManagement.getTasks().keySet()) {
            for(Task task : View.flattenTasks(tasksManagement.getTasks().get(employee))) {
                if(task.getIdTask() == t.getIdTask()) {
                    System.out.println("Am gasit task-ul cu id-ul " + t.getIdTask());
                    task.setStatusTask(newStatus);
                }
            }
        }
        saveTaskManagement(tasksManagement);
        saveTasks();
    }
}
