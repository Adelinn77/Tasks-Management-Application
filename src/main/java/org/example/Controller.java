package org.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private View view;
    public Controller(View view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getEmployeesButton()) {
            view.displayEmployeesTable();
        }
        else if(e.getSource() == view.getTasksButton()) {
            view.displayTasksTable();
        }
        else if(e.getSource() == view.getAddEmployeeButton()) {
            view.openAddEmployeeDialog();
        }
        else if(e.getSource() == view.getAddTaskButton()) {
            view.openAddTaskWindow();
        }
        else if(e.getSource() == view.getSimpleTaskButton()) {
            view.openSimpleTaskDialog();
        }
        else if(e.getSource() == view.getComplexTaskButton()) {
            view.openComplexTaskWindow();
        }
        else if(e.getSource() == view.getSimpleLeafTaskButton()){
            view.openSimpleTaskDialog();
        }
        else if(e.getSource() == view.getComplexTreeTaskButton()){
            view.openSimpleLeafTaskDialog(view.getCurrentComplexTask());
        }
    }

}
