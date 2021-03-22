package ru.shestakov.lab;

import ru.shestakov.lab.method.RectangleMethod;
import ru.shestakov.lab.method.SimpsonMethod;
import ru.shestakov.lab.method.TrapezoidMethod;

import static ru.shestakov.lab.Function.FUNCTION;

public class Solution {
    private final static double INTERVAL_FROM = 0; // Интервал "от"
    private final static double INTERVAL_TO = Math.PI; // Интервал "до"
    private final static double SEGMENTS = 80; // Количество отрезков
    private final static double EXACT_VALUE = 2.0d; // Точное значение

    public static void main(String[] args) {
        Function function = new Function();
        double rectangleResult = new RectangleMethod(function).calculate(INTERVAL_FROM, INTERVAL_TO, SEGMENTS);
        double trapezoidResult = new TrapezoidMethod(function).calculate(INTERVAL_FROM, INTERVAL_TO, SEGMENTS);
        double simpsonResult = new SimpsonMethod(function).calculate(INTERVAL_FROM, INTERVAL_TO, SEGMENTS);
        printResult(rectangleResult, trapezoidResult, simpsonResult);
    }

    private static void printResult(double rectangleResult, double trapezoidResult, double simpsonResult) {
        System.out.println(String.format("Функция %s на интервале [%s, %s]", FUNCTION, INTERVAL_FROM, INTERVAL_TO));
        System.out.println("Точное значение (метод Ньютона-Лейбница): " + EXACT_VALUE);
        System.out.println("1. Метод средних прямоугольников: " + rectangleResult);
        System.out.println("2. Метод трапеций: " + trapezoidResult);
        System.out.println("3. Метод Симпсона: " + simpsonResult);
        System.out.println(new Util().whoIsMoreAccurate(EXACT_VALUE, rectangleResult, trapezoidResult, simpsonResult));
    }

}
