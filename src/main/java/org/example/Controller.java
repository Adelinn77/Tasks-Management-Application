package org.example;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Controller implements ActionListener {
    private View view;
    Model model = new Model();

    public Controller(View view) {
        this.view = view;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getEmployeesButton()) {
            view.displayEmployeesTable(model);
        }
        else if(e.getSource() == view.getTasksButton()) {
            view.displayTasksTable(model);
        }
        else if(e.getSource() == view.getAddEmployeeButton()) {
            view.openAddEmployeeDialog();
        }
        else if(e.getSource() == view.getAddTaskButton()) {
            view.openAddTaskDialog();
        }
    }

}
