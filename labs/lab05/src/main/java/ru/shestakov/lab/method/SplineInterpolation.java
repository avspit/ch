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
        initMValues();
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

    private MatrixCoefficients prepareMatrixCoefficients() {
        double[] aValues = new double[intervals];
        double[] bValues = new double[intervals];
        bValues[0] = -1;
        bValues[intervals-1] = -1;
        double[] cValues = new double[intervals];
        cValues[intervals-1] = 0;
        double[] dValues = new double[intervals];

        for (int i=1; i<interpolationNodes.size()-1; i++) {
            double hCurr = hValues[i];
            double hNext = hValues[i+1];
            double a = hCurr / 6;
            double c = hNext / 6;
            double b = (hCurr + hNext) / 3 * -1;
            double d = (interpolationNodes.get(i+1).getY() - interpolationNodes.get(i).getY()) / hNext
                  - (interpolationNodes.get(i).getY() - interpolationNodes.get(i-1).getY()) / hCurr;
            aValues[i] = a;
            bValues[i] = b;
            cValues[i-1] = c;
            dValues[i] = d;
        }
        return new MatrixCoefficients(aValues, bValues, cValues, dValues);
    }

    private void initHValues() {
        for (int i=1; i<interpolationNodes.size(); i++) {
            hValues[i] = interpolationNodes.get(i).getX() - interpolationNodes.get(i-1).getX();
        }
    }

    private void initMValues() {
        mValues = new TridiagonalMatrixAlgorithm(prepareMatrixCoefficients(), interpolationNodes.size()).calculate();
    }

}
