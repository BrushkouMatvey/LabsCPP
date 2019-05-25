package com.Entity;

import javax.persistence.*;

@Entity
@Table(name = "cache")
public class Cache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first")
    private String firstSide;

    @Column(name = "second")
    private String secondSide;

    @Column(name = "third")
    private String thirdSide;

    @Column(name = "square")
    private Double square;

    @Column(name = "perimeter")
    private Double perimeter;

    @Column(name = "responceid")
    private int responceId;

    public int getResponceId() {
        return responceId;
    }

    public void setResponceId(int responceId) {
        this.responceId = responceId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstSide() {
        return firstSide;
    }

    public void setFirstSide(String firstSide) {
        this.firstSide = firstSide;
    }

    public String getSecondSide() {
        return secondSide;
    }

    public void setSecondSide(String firstSide) {
        this.secondSide = firstSide;
    }

    public String getThirdSide() {
        return thirdSide;
    }

    public void setThirdSide(String firstSide) {
        this.thirdSide = firstSide;
    }

    public Double getSquare() {
        return square;
    }

    public void setSquare(Double square) {
        this.square = square;
    }

    public Double getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(Double perimeter) {
        this.perimeter = perimeter;
    }

    @Override
    public String toString() {
        return "Cache{" +
                "id=" + id +
                ", firstSide='" + firstSide + '\'' +
                ", secondSide='" + secondSide + '\'' +
                ", thirdSide='" + thirdSide + '\'' +
                ", square=" + square +
                ", perimeter=" + perimeter +
                ", responceId=" + responceId +
                '}';
    }
}
