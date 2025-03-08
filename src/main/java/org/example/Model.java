package org.example;
import java.util.*;

public class Model {
    private List<Employee> employees;
    private List<Task> tasks;

    public Model() {
        employees = new ArrayList<>();
        employees.add(new Employee("John Doe"));
        employees.add(new Employee("Jane Smith"));
        employees.add(new Employee("Michael Brown"));

        tasks = new ArrayList<>();
        tasks.add(new SimpleTask(12, 14));
        tasks.add(new SimpleTask(17, 24));
        tasks.add(new SimpleTask(8, 11));
        tasks.add(new ComplexTask());
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
