package ru.shestakov.lab.method;

import ru.shestakov.lab.model.Node;

import java.util.List;

public class SplineInterpolation {
    private List<Node> interpolationNodes;
    private int numberOfIntervals;
    private double[] aValues;
    private double[] bValues;
    private double[] cValues;
    private double[] dValues;
    private double[] hValues;

    public SplineInterpolation(List<Node> interpolationNodes) {
        this.interpolationNodes = interpolationNodes;
        init();
        initHValues();
        calculateCoefficients();
    }

    public double calculate(double xValue) {
        return 0d;
    }

    private void calculateCoefficients() {
        cValues = new TridiagonalMatrixAlgorithm(prepareMatrix()).calculate();

        for (int i=1; i<interpolationNodes.size(); i++) {
            dValues[i-1] = (cValues[i] - cValues[i-1]) / hValues[i-1];
            bValues[i-1] = 1/2 * cValues[i] * hValues[i-1] - 1/6 * dValues[i-1] * Math.pow(hValues[i-1], 2) + (interpolationNodes.get(i).getY() - interpolationNodes.get(i-1).getY()) / hValues[i-1];
            aValues[i-1] = interpolationNodes.get(i).getY();
        }
    }

    private double[][] prepareMatrix() {
        double[][] result = new double[interpolationNodes.size()][interpolationNodes.size()+1];
        for (int i=1; i<interpolationNodes.size(); i++) {
            double hCurr = hValues[i-1];
            double hNext = hValues[i];
            double a = hCurr;
            double b = hNext;
            double c = 2 * (hCurr + hNext);
            double yCurr = interpolationNodes.get(i).getY();
            double yNext = interpolationNodes.get(i+1).getY();
            double yPrev = interpolationNodes.get(i-1).getY();
            double d = 3 * ((yNext - yCurr) / hNext - (yCurr - yPrev) / hCurr);

            result[i][i] = a;
            result[i-1][i-1] = c;
            result[i-1][i] = b;
            result[i-1][result.length-1] = d;
        }
        return result;
    }

    private void initHValues() {
        for (int i=1; i<interpolationNodes.size(); i++) {
            hValues[i-1] = interpolationNodes.get(i).getX() - interpolationNodes.get(i-1).getX();
        }
    }

    private void init() {
        // Количество интервалов (сплайнов)
        numberOfIntervals = interpolationNodes.size()-1;
        // Коэффициенты a, b, c, d, h
        aValues = new double[numberOfIntervals];
        bValues = new double[numberOfIntervals];
        cValues = new double[numberOfIntervals];
        dValues = new double[numberOfIntervals];
        hValues = new double[numberOfIntervals];
    }
}
