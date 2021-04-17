package ru.shestakov.lab.method;

import ru.shestakov.lab.model.MatrixCoefficients;
import ru.shestakov.lab.model.Node;

import java.util.List;

public class SplineInterpolation {
    private List<Node> interpolationNodes;
    private int intervals;
    private double[] hValues;
    private double[] mValues;

    public SplineInterpolation(List<Node> interpolationNodes) {
        this.interpolationNodes = interpolationNodes;
        this.intervals = interpolationNodes.size()-1;
        this.hValues = new double[interpolationNodes.size()];
        initHValues();
        calculateCoefficients();
    }

    public double calculate(double xValue) {
        int interval = calculateInterval(xValue);
        return mValues[interval-1] * Math.pow(interpolationNodes.get(interval).getX() - xValue, 3) / 6 * hValues[interval]
                + mValues[interval] * Math.pow(xValue - interpolationNodes.get(interval-1).getX(), 3) / 6 * hValues[interval]
                + (interpolationNodes.get(interval-1).getY() - mValues[interval-1] * hValues[interval] / 6) * (interpolationNodes.get(interval).getX() - xValue) / hValues[interval] +
                + (interpolationNodes.get(interval).getY() - mValues[interval] * hValues[interval] / 6) * (xValue - interpolationNodes.get(interval-1).getX()) / hValues[interval];
    }

    private int calculateInterval(double x) {
        int result = interpolationNodes.size()-1;
        for (int i=1; i<interpolationNodes.size(); i++) {
            if (x >= interpolationNodes.get(i-1).getX() && x < interpolationNodes.get(i).getX()) {
                result = i;
                break;
            }
        }
        return result;
    }

    private void calculateCoefficients() {
        mValues = new TridiagonalMatrixAlgorithm(prepareMatrixCoefficients(), interpolationNodes.size()).calculate();
    }

    private MatrixCoefficients prepareMatrixCoefficients() {
        double[] matrixA = new double[intervals];
        matrixA[0] = 0;
        double[] matrixB = new double[intervals];
        matrixB[0] = -1;
        matrixB[intervals -1] = -1;
        double[] matrixC = new double[intervals];
        matrixC[intervals -1] = 0;
        double[] matrixD = new double[intervals];
        for (int i=1; i<interpolationNodes.size()-1; i++) {
            double hCurr = hValues[i];
            double hNext = hValues[i+1];
            double a = hCurr / 6;
            double c = hNext / 6;
            double b = (hCurr + hNext) / 3 * -1;
            double d = (interpolationNodes.get(i+1).getY() - interpolationNodes.get(i).getY()) / hNext
                  - (interpolationNodes.get(i).getY() - interpolationNodes.get(i-1).getY()) / hCurr;

            matrixA[i] = a;
            matrixB[i] = b;
            matrixC[i-1] = c;
            matrixD[i] = d;
        }
        return new MatrixCoefficients(
                matrixA,
                matrixB,
                matrixC,
                matrixD
        );
    }

    private void initHValues() {
        hValues[0] = 0;
        for (int i=1; i<interpolationNodes.size(); i++) {
            hValues[i] = interpolationNodes.get(i).getX() - interpolationNodes.get(i-1).getX();
        }
    }

}
