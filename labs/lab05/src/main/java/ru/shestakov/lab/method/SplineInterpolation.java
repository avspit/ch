package ru.shestakov.lab.method;

import ru.shestakov.lab.model.MatrixCoefficients;
import ru.shestakov.lab.model.Node;

import java.util.List;

public class SplineInterpolation {
    private List<Node> nodes;
    private int intervals;
    private double[] h;
    private double[] m;

    public SplineInterpolation(List<Node> nodes) {
        this.nodes = nodes;
        this.intervals = nodes.size()-1;
        this.h = new double[nodes.size()];
        initHValues();
        initMValues();
    }

    public double calculate(double xValue) {
        int interval = calculateInterval(xValue);
        return m[interval-1] * Math.pow(nodes.get(interval).getX() - xValue, 3) / 6 * h[interval]
                + m[interval] * Math.pow(xValue - nodes.get(interval-1).getX(), 3) / 6 * h[interval]
                + (nodes.get(interval-1).getY() - m[interval-1] * Math.pow(h[interval], 2) / 6) * (nodes.get(interval).getX() - xValue) / h[interval] +
                + (nodes.get(interval).getY() - m[interval] * Math.pow(h[interval], 2) / 6) * (xValue - nodes.get(interval-1).getX()) / h[interval];
    }

    private int calculateInterval(double x) {
        int result = nodes.size()-1;
        for (int i = 1; i< nodes.size(); i++) {
            if (x >= nodes.get(i-1).getX() && x < nodes.get(i).getX()) {
                result = i;
                break;
            }
        }
        return result;
    }

    private MatrixCoefficients prepareMatrixCoefficients() {
        double[] aValues = new double[intervals];
        double[] bValues = new double[intervals];
        bValues[0] = -1;
        bValues[intervals-1] = -1;
        double[] cValues = new double[intervals];
        cValues[intervals-1] = 0;
        double[] dValues = new double[intervals];

        for (int i = 1; i< nodes.size()-1; i++) {
            double hCurr = h[i];
            double hNext = h[i+1];
            double a = hCurr / 6;
            double c = hNext / 6;
            double b = (hCurr + hNext) / 3 * -1;
            double d = (nodes.get(i+1).getY() - nodes.get(i).getY()) / hNext
                  - (nodes.get(i).getY() - nodes.get(i-1).getY()) / hCurr;
            aValues[i] = a;
            bValues[i] = b;
            cValues[i-1] = c;
            dValues[i] = d;
        }
        return new MatrixCoefficients(aValues, bValues, cValues, dValues);
    }

    private void initHValues() {
        for (int i = 1; i< nodes.size(); i++) {
            h[i] = nodes.get(i).getX() - nodes.get(i-1).getX();
        }
    }

    private void initMValues() {
        m = new TridiagonalMatrixAlgorithm(prepareMatrixCoefficients(), nodes.size()).calculate();
    }

}
