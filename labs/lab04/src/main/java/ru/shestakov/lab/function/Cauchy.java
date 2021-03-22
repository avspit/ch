package ru.shestakov.lab.function;

public class Cauchy {

    public double calculate(double x, double y) {
        // Привести цифры к double если вычисляет неверно - это известная ошибка вычисления double
        //return 3d * Math.pow(x, 2) / (Math.pow(x, 3) + y + 1d);
        return Math.pow(x, 2) - 2d * y;
    }
}
