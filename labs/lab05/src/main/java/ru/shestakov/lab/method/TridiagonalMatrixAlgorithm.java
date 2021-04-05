package ru.shestakov.lab.method;

import java.util.Arrays;

/**
 * Метод прогонки (алгоритм трехдиагональной матрицы)
 * Предполагается, что СЛАУ задана расширенной матрицей коэффициентов, например:
 * B1 C1 0  0  0  0  | D1
 * A1 B2 C2 0  0  0  | D2
 * 0  A2 B3 C3 0  0  | D3
 * 0  0  A3 B4 C4 0  | D4
 * 0  0  0  A4 B5 C5 | D5
 */
public class TridiagonalMatrixAlgorithm {
    private double[][] slauMatrix;
    private double[][] diagonalSlauMatrix;
    private double[] result;

    public TridiagonalMatrixAlgorithm(double[][] slauMatrix) {
        this.slauMatrix = slauMatrix;
        initDiagonalMatrix();
        initResultArray();
    }

    public double[] calculate() {

        // Прямой ход
        double a = 1;
        double b = slauMatrix[0][0];
        double c = slauMatrix[0][1];
        double d = slauMatrix[0][slauMatrix[0].length-1];
        double gamma = b;
        double alfa = -c / gamma;
        double betta = d / gamma;
        addAlfaBettaToDiagonalSlauMatrix(alfa, betta, 0);
        for (int row=1; row<slauMatrix.length; row++) {
            a = slauMatrix[row][row-1];
            b = slauMatrix[row][row];
            c = slauMatrix[row][row+1];
            d = slauMatrix[row][slauMatrix[0].length-1];
            gamma = b + a * alfa;
            alfa = -c / gamma;
            betta = (d - a * betta) / gamma;
            addAlfaBettaToDiagonalSlauMatrix(alfa, betta, row);
        }

        // Обратный ход
        betta = diagonalSlauMatrix[diagonalSlauMatrix.length-1][diagonalSlauMatrix[0].length-1];
        double x = betta;
        addXToResult(x, diagonalSlauMatrix.length-1);
        for (int row = diagonalSlauMatrix.length-2; row>=0; row--) {
            betta = diagonalSlauMatrix[row][diagonalSlauMatrix[row].length-1];
            a = diagonalSlauMatrix[row][row+1];
            x = a * x + betta;
            addXToResult(x, row);
        }

        return result;
    }

    private void addXToResult(double x, int row) {
        result[row] = x;
    }

    private void addAlfaBettaToDiagonalSlauMatrix(double alfa, double betta, int row) {
        diagonalSlauMatrix[row][row+1] = alfa; // ??? * (-1)
        diagonalSlauMatrix[row][diagonalSlauMatrix[row].length-1] = betta;
    }

    private void initDiagonalMatrix() {
        diagonalSlauMatrix = Arrays.stream(slauMatrix).map(double[]::clone).toArray(double[][]::new); //копия
        for (int row=0; row<diagonalSlauMatrix.length; row++) {
            for (int col=0; col<diagonalSlauMatrix[row].length; col++) {
                if (col == row) {
                    diagonalSlauMatrix[row][col] = 1;
                } else {
                    diagonalSlauMatrix[row][col] = 0;
                }
            }
        }
    }

    private void initResultArray() {
        result = new double[slauMatrix.length];
    }
}
