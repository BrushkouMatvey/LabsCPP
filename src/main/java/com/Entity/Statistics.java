package com.Entity;

import com.Entity.Triangle;

public class Statistics {


    private Triangle minValue;
    private Triangle popularResult;
    private int inputsAmount;
    private int invalidInputs;
    private Triangle maxValue;

    public int getInputsAmount() {
        return inputsAmount;
    }

    public int getInvalidInputs() {
        return invalidInputs;
    }

    public Triangle getMaxValue() {
        return maxValue;
    }

    public Triangle getMinValue() {
        return minValue;
    }

    public Triangle getPopularResult() {
        return popularResult;
    }

    public void setInputsAmount(int inputsAmount) {
        this.inputsAmount = inputsAmount;
    }

    public void setInvalidInputs(int invalidInputs) {
        this.invalidInputs = invalidInputs;
    }

    public void setMaxValue(Triangle maxValue) {
        this.maxValue = maxValue;
    }

    public void setPopularResult(Triangle popularResult) {
        this.popularResult = popularResult;
    }

    public void setMinValue(Triangle minValue) {
        this.minValue = minValue;
    }

}
