package ru.shestakov.lab.model;

public class MatrixCoefficients {
    private double[] aValues;
    private double[] bValues;
    private double[] cValues;
    private double[] dValues;

    public MatrixCoefficients() {
    }

    public MatrixCoefficients(double[] aValues, double[] bValues, double[] cValues, double[] dValues) {
        this.aValues = aValues;
        this.bValues = bValues;
        this.cValues = cValues;
        this.dValues = dValues;
    }

    public double[] getaValues() {
        return aValues;
    }

    public void setaValues(double[] aValues) {
        this.aValues = aValues;
    }

    public double[] getbValues() {
        return bValues;
    }

    public void setbValues(double[] bValues) {
        this.bValues = bValues;
    }

    public double[] getcValues() {
        return cValues;
    }

    public void setcValues(double[] cValues) {
        this.cValues = cValues;
    }

    public double[] getdValues() {
        return dValues;
    }

    public void setdValues(double[] dValues) {
        this.dValues = dValues;
    }
}
