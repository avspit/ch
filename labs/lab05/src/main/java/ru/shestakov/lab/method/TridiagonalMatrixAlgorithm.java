package ru.shestakov.lab.method;

import ru.shestakov.lab.model.MatrixCoefficients;

public class TridiagonalMatrixAlgorithm {
    private MatrixCoefficients coefficients;
    private double[] kValues;
    private double[] eValues;
    private int nodes;

    public TridiagonalMatrixAlgorithm(MatrixCoefficients coefficients, int nodes) {
        this.coefficients = coefficients;
        this.nodes = nodes-1;
        init();
    }

    public double[] calculate() {
        double[] mValues = new double[nodes+1];
        mValues[nodes-1] = 0;
        mValues[nodes] = 1;

        // Прямой ход
        for (int i=0; i<nodes; i++) {
            kValues[i] = coefficients.getcValues()[i] / (coefficients.getbValues()[i] - kValues[i] * coefficients.getaValues()[i]);
        }
        for (int i=0; i<nodes; i++) {
            eValues[i] = (coefficients.getaValues()[i] * eValues[i] - coefficients.getdValues()[i]) / (coefficients.getbValues()[i] - kValues[i] * coefficients.getaValues()[i]);
        }
        // Обратный ход
        for (int i=nodes-1; i>=0; i--) {
            mValues[i] = kValues[i] * mValues[i+1] + eValues[i];
        }
        return mValues;
    }

    private void init() {
        kValues = new double[nodes];
        kValues[0] = 1;
        eValues = new double[nodes];
        eValues[0] = 1;
    }

}
