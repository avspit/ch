package ru.shestakov.lab.model;

public class Equasion {
    private double s;
    private double a;
    private double b;
    private double c;
    private double d;
    private double x;
    private double xn;

    public Equasion() {
    }

    public Equasion(double s, double a, double b, double c, double d, double x, double xn) {
        this.s = s;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.x = x;
        this.xn = xn;
    }

    public double getS() {
        return s;
    }

    public void setS(double s) {
        this.s = s;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getXn() {
        return xn;
    }

    public void setXn(double xn) {
        this.xn = xn;
    }
}
