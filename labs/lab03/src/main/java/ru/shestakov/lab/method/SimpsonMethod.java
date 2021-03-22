package ru.shestakov.lab.method;

import ru.shestakov.lab.Function;

public class SimpsonMethod {
    private Function function;

    public SimpsonMethod(Function function) {
        this.function = function;
    }

    public double calculate(double sectionFrom, double sectionTo, double limit) {
        double result = 0;
        double step = (sectionTo - sectionFrom) / limit;
        double firstResult = function.calculate(sectionFrom);
        double lastResult = function.calculate(sectionTo);
        for (int i=1; i<limit; i++) {
            int ratio = i % 2 == 0 ? 2 : 4;
            double x = sectionFrom + step * i;
            double y = function.calculate(x);
            x = simpsonCeiling(x);
            y = simpsonCeiling(y);
            result += ratio * y;
        }
        return (step / 3) * (result + firstResult + lastResult);
    }

    private double simpsonCeiling(double value) {
        return (double)Math.round(value * 1000d) / 1000d;
    }

}
