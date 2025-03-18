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
            view.clearTaskHistory();
            view.displayEmployeesTable();
        }
        else if(e.getSource() == view.getTasksButton()) {
            view.clearTaskHistory();
            view.displayTasksTable();
        }
        else if(e.getSource() == view.getAddEmployeeButton()) {
            view.clearTaskHistory();
            view.openAddEmployeeDialog();
        }
        else if(e.getSource() == view.getAddTaskButton()) {
            view.clearTaskHistory();
            view.openAddTaskWindow();
        }
        else if(e.getSource() == view.getSimpleTaskButton()) {
            view.openSimpleTaskDialog();
        }
        else if(e.getSource() == view.getComplexTaskButton()) {
            view.openComplexTaskWindow();
        }
        else if(e.getSource() == view.getSimpleLeafTaskButton()){
            view.openSimpleLeafTaskDialog(View.taskHistory.peek());
        }
        else if(e.getSource() == view.getComplexTreeTaskButton()){
            ComplexTask complexTask = new ComplexTask();
            view.addComplexTaskInTree(View.taskHistory.peek(), complexTask);
        }
        else if(e.getSource() == view.getAssignTaskButton()){
            view.clearTaskHistory();
            view.openAssignTaskDialog();
        }
        else if(e.getSource() == view.getModifyTaskButton()){
            view.openModifyTaskStatusDialog();
        }
        else if(e.getSource() == view.getFilterEmployeesButton()){
            view.filterEmployeesDialog();
        }
    }

}
