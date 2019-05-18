package com.Entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

public class Parameters {

    private int id;
    private String firstSide;
    private String secondSide;
    private String thirdSide;
    private Triangle triangleId;


    public Parameters(String firstSide, String secondSide, String thirdSide) {
        this.firstSide = firstSide;
        this.secondSide = secondSide;
        this.thirdSide = thirdSide;
    }

    public Double dGetFirstSide() {
        return Double.parseDouble(firstSide);
    }
    public String getFirstSide() {
        return firstSide;
    }
    public void setFirstSide(String firstSide) {
        this.firstSide = firstSide;
    }

    public Double dGetSecondSide() {
        return Double.parseDouble(secondSide);
    }
    public String getSecondSide() {
        return secondSide;
    }
    public void setSecondSide(String secondSide) {
        this.secondSide = secondSide;
    }

    public Double dGetThirdSide() {
        return Double.parseDouble(thirdSide);
    }
    public String getThirdSide() {
        return thirdSide;
    }
    public void setThirdSide(String thirdSide) {
        this.thirdSide = thirdSide;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Triangle getTriangleId() { return triangleId; }
    public void setTriangleId(Triangle triangleId) { this.triangleId = triangleId; }

    @Override
    public String toString() {
        return "Parameters{" +
                "firstSide='" + firstSide + '\'' +
                ", secondSide='" + secondSide + '\'' +
                ", thirdSide='" + thirdSide + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((firstSide == null) ? 0 : firstSide.hashCode());
        result = prime * result + ((secondSide == null) ? 0 : secondSide.hashCode());
        result = prime * result + ((thirdSide == null) ? 0 : thirdSide.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || obj.getClass()!=this.getClass()) return false;

        Parameters guest = (Parameters) obj;

        return  (firstSide == guest.firstSide
                || (firstSide != null && firstSide.equals(guest.getFirstSide())))
                && (secondSide == guest.secondSide
                || (secondSide != null && secondSide .equals(guest.getSecondSide()))
                && (thirdSide == guest.thirdSide
                || (thirdSide != null && thirdSide .equals(guest.getThirdSide()))));
    }
}
