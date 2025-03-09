package org.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
            view.openAddTaskWindow();
        }
        else if(e.getSource() == view.getSimpleTaskButton()) {
            view.openSimpleTaskDialog();
        }
        else if(e.getSource() == view.getComplexTaskButton()) {
            view.openComplexTaskWindow();
        }
        else if(e.getSource() == view.getSimpleComplexTaskButton()){
            view.openSimpleTaskDialog();
        }
    }

}
