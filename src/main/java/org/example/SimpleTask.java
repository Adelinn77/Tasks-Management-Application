package org.example;

import java.io.Serializable;

public class SimpleTask extends Task implements Serializable {
    private int startHour;
    private int endHour;

    public SimpleTask(){}
    public SimpleTask(int startHour, int endHour){
        super();
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    @Override
    public int estimateDuration(){
        return endHour - startHour;
    }
}
