package com.hmeng.calculateforme.Data;

import java.util.Date;

public class BalconyRadiusOperationTask {

    private double firstAttribute;
    private double secondAttribute;
    private double result;
    private String taskDate;

    public BalconyRadiusOperationTask() {
    }

    public BalconyRadiusOperationTask(double firstAttribute, double secondAttribute, double result, String taskDate) {
        this.firstAttribute = firstAttribute;
        this.secondAttribute = secondAttribute;
        this.result = result;
        this.taskDate = taskDate;
    }

    public BalconyRadiusOperationTask(double firstAttribute, double secondAttribute, double result) {
        this.firstAttribute = firstAttribute;
        this.secondAttribute = secondAttribute;
        this.result = result;
    }

    public double getFirstAttribute() {
        return firstAttribute;
    }

    public void setFirstAttribute(double firstAttribute) {
        this.firstAttribute = firstAttribute;
    }

    public double getSecondAttribute() {
        return secondAttribute;
    }

    public void setSecondAttribute(double secondAttribute) {
        this.secondAttribute = secondAttribute;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }
}
