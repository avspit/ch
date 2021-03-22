package ru.shestakov.lab.model;

public class RungeKuttaEntity extends Entity {
    private Double k1;
    private Double k2;
    private Double k3;
    private Double k4;
    private Double deltaY;

    public RungeKuttaEntity() {
    }

    public RungeKuttaEntity(Double x, Double y, Double k1, Double k2, Double k3, Double k4, Double deltaY) {
        super.x = x;
        super.y = y;
        this.k1 = k1;
        this.k2 = k2;
        this.k3 = k3;
        this.k4 = k4;
        this.deltaY = deltaY;
    }

    public Double getK1() {
        return k1;
    }

    public void setK1(Double k1) {
        this.k1 = k1;
    }

    public Double getK2() {
        return k2;
    }

    public void setK2(Double k2) {
        this.k2 = k2;
    }

    public Double getK3() {
        return k3;
    }

    public void setK3(Double k3) {
        this.k3 = k3;
    }

    public Double getK4() {
        return k4;
    }

    public void setK4(Double k4) {
        this.k4 = k4;
    }

    public Double getDeltaY() {
        return deltaY;
    }

    public void setDeltaY(Double deltaY) {
        this.deltaY = deltaY;
    }
}
