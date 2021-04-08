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
        int interval = calculateInterval(xValue);
        return aValues[interval]
                + bValues[interval] * (xValue - interpolationNodes.get(interval).getX())
                + 1/2 * cValues[interval] * Math.pow(xValue - interpolationNodes.get(interval).getX(), 2)
                + 1/6 * dValues[interval] * Math.pow(xValue - interpolationNodes.get(interval).getX(), 3);
    }

    private int calculateInterval(double x) {
        int result = 0;
        for (int i=1; i<interpolationNodes.size(); i++) {
            if (x >= interpolationNodes.get(i-1).getX() && x < interpolationNodes.get(i).getX()) {
                result = i-1;
                break;
            }
        }
        return result;
    }

    private void calculateCoefficients() {
        // Вычисляем c
        cValues = new TridiagonalMatrixAlgorithm(prepareMatrix()).calculate();
        // Вычисляем a, b, d
        for (int i=1; i<interpolationNodes.size(); i++) {
            dValues[i-1] = (cValues[i] - cValues[i-1]) / hValues[i-1];
            bValues[i-1] = 1/2 * cValues[i] * hValues[i-1] - 1/6 * dValues[i-1] * Math.pow(hValues[i-1], 2) + (interpolationNodes.get(i).getY() - interpolationNodes.get(i-1).getY()) / hValues[i-1];
            aValues[i-1] = interpolationNodes.get(i).getY();
        }
    }

    /**
     * B1 C1 0  0  0  0  | D1
x     * A1 B2 C2 0  0  0  | D2
     * 0  A2 B3 C3 0  0  | D3
     * 0  0  A3 B4 C4 0  | D4
     * 0  0  0  A4 B5 C5 | D5
     */
    private double[][] prepareMatrix() {
        double[][] result = new double[interpolationNodes.size()][interpolationNodes.size()+1];
        for (int i=0; i<interpolationNodes.size()-1; i++) {
            double a = hValues[i+1];
            double b = hValues[i];
            double c = 2 * (hValues[i] + hValues[i+1]);
            double d = 3 * (
                    (interpolationNodes.get(i+1).getY() - interpolationNodes.get(i).getY()) / hValues[i+1]
                  - (interpolationNodes.get(i).getY() - (i==0 ? 0d : interpolationNodes.get(i-1).getY())) / hValues[i]
            );

            result[i+1][i] = a;
            result[i][i] = c;
            result[i][i+1] = b;
            result[i][result[i].length-1] = d;
        }
        return result;
    }

    private void initHValues() {
        for (int i=1; i<interpolationNodes.size(); i++) {
            hValues[i-1] = interpolationNodes.get(i).getX() - interpolationNodes.get(i-1).getX();
        }
    }

    private void init() {
        // Количество интервалов (сплайнов) = количество заданных узлов минус 1
        numberOfIntervals = interpolationNodes.size()-1;
        // Коэффициенты a, b, c, d, h
        aValues = new double[numberOfIntervals];
        bValues = new double[numberOfIntervals];
        cValues = new double[numberOfIntervals];
        dValues = new double[numberOfIntervals];
        hValues = new double[numberOfIntervals];
    }
}
