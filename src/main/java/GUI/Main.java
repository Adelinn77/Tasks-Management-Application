package GUI;

import DataAcces.Model;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Model.resetIdTasks();
        View frame = new View("Task Management GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}