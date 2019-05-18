package com.Entity;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

public class Triangle {
    @JsonIgnore
    private int id;
    private Double square;
    private Double perimeter;
    private Parameters parameters;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Triangle(Double square, Double perimeter) {
        this.square = square;
        this.perimeter = perimeter;
    }

    public void setPerimeter(Double perimeter) {
        this.perimeter = perimeter;
    }


    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || obj.getClass()!=this.getClass()) return false;

        Triangle guest = (Triangle) obj;

        return  (perimeter == guest.perimeter || (perimeter != null && perimeter.equals(guest.getPerimeter())))
                && (square == guest.square
                || (square != null && square.equals(guest.getSquare())));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((square == null) ? 0 : square.hashCode());
        result = prime * result + ((perimeter == null) ? 0 : perimeter.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "square=" + square +
                ", perimeter=" + perimeter +
                '}';
    }
}
