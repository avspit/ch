package ru.shestakov.lab.model;

public class EulerEntity extends Entity {
    private Double fXY;
    private Double hfXY;

    public EulerEntity() {
    }

    public EulerEntity(Double x, Double y, Double fXY, Double hfXY) {
        super.x = x;
        super.y = y;
        this.fXY = fXY;
        this.hfXY = hfXY;
    }

    public Double getfXY() {
        return fXY;
    }

    public void setfXY(Double fXY) {
        this.fXY = fXY;
    }

    public Double getHfXY() {
        return hfXY;
    }

    public void setHfXY(Double hfXY) {
        this.hfXY = hfXY;
    }
}
