package ru.shestakov.lab.function;

public class Function {

    public double calculate(double x) {
        // Привести цифры к double если вычисляет неверно - это известная ошибка вычисления double
        //return -Math.pow(x, 3) - 2d;
        return 3d/4d * Math.exp(-2*x) + 1d/2d*Math.pow(x, 2) - 1d/2d*x + 1d/4d;
    }

}
