package ru.shestakov.lab.method;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 */
class TridiagonalMatrixAlgorithmTest {
    private double[][] matrix;
    private double[] expected;

    @BeforeEach
    void init() {
        initMatrix();
        initResult();
    }

    @Test
    void testCalculation() {
        TridiagonalMatrixAlgorithm tma = new TridiagonalMatrixAlgorithm(matrix);
        double[] result = tma.calculate();
        for (int i=0; i<expected.length; i++) {
            assertEquals(Math.round(expected[i]), Math.round(result[i]));
        }
    }

    private void initResult() {
        expected = new double[]{
                -10d,
                5d,
                -2d,
                -10d,
                -3d
        };
    }

    private void initMatrix() {
        matrix = new double[][]{
                {2d, -1d, 0, 0, 0, -25d},
                {-3d, 8d, -1d, 0, 0, 72d},
                {0, -5d, 12d, 2d, 0, -69d},
                {0, 0, -6d, 18d, -4d, -156d},
                {0, 0, 0, -5d, 10d, 20d}
        };
    }

}