package ru.shestakov.lab.model;

import java.util.List;

public class Spline {
    private List<Equasion> equasions;

    public Spline() {
    }

    public Spline(List<Equasion> equasions) {
        this.equasions = equasions;
    }

    public List<Equasion> getEquasions() {
        return equasions;
    }

    public void setEquasions(List<Equasion> equasions) {
        this.equasions = equasions;
    }
}
