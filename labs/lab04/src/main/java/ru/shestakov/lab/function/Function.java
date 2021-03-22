package ru.shestakov.lab.function;

public class Function {

    public double calculate(double x) {
        // Привести цифры к double если вычисляет неверно - это известная ошибка вычисления double
        //return -Math.pow(x, 3) - 2;
        return 3d/4*Math.exp(-Math.pow(x, 2)) + 1d/2*Math.pow(x, 2) - 1d/2*x + 1d/4;
    }

}
