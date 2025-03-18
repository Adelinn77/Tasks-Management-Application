package org.example;

import DataAcces.Model;
import GUI.View;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Model.resetIdTasks();
        View frame = new View("Task Management GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


//        Map<Employee, List<Task>> tasks = new HashMap<>();
//        Employee e1 = new Employee("JOHN");
//        Employee e2 = new Employee("JACK");
//        Employee e3 = new Employee("RYAN");
//        Employee e4 = new Employee("PHIL");
//        Employee e5 = new Employee("FINLAY");
//
//        SimpleTask t1 = new SimpleTask(0, 24);
//        SimpleTask t2 = new SimpleTask(0, 24);
//        SimpleTask t3 = new SimpleTask(0, 24);
//        SimpleTask t4 = new SimpleTask(0, 24);
//        SimpleTask t5 = new SimpleTask(0, 24);
//        SimpleTask t6 = new SimpleTask(0, 24);
//        SimpleTask t7 = new SimpleTask(0, 24);
//        SimpleTask t8 = new SimpleTask(0, 24);
//        SimpleTask t9 = new SimpleTask(0, 24);
//        SimpleTask t10 = new SimpleTask(0, 24);
//        SimpleTask t11 = new SimpleTask(0, 24);
//
//        List<Task> tasks1 = new ArrayList<>();
//        tasks1.add(t1);
//        tasks1.add(t2);
//        tasks1.add(t3);
//        tasks1.add(t4);
//
//        List<Task> tasks2 = new ArrayList<>();
//        tasks2.add(t5);
//        tasks2.add(t6);
//        tasks2.add(t7);
//        tasks2.add(t8);
//
//        TasksManagement tasksManagement = new TasksManagement(tasks);
//        tasksManagement.getTasks().put(e1, tasks1);
//        tasksManagement.getTasks().put(e2, tasks2);
//
//        tasksManagement.getTasks().put(e3, new ArrayList<>());
//        tasksManagement.getTasks().get(e3).add(t9);
//        tasksManagement.getTasks().put(e4, new ArrayList<>());
//        tasksManagement.getTasks().get(e4).add(t10);

        //Utility.over40Hours(tasksManagement);
//        List<Task> tasks = View.flattenTasks(Model.loadTasks());
//        for (Task task : tasks) {
//            System.out.println("task id: " + task.getIdTask());
//        }
    }
}