package ru.shestakov.lab.methods;

import ru.shestakov.lab.Function;

public class RectangleMethod {
    private Function function;

    public RectangleMethod(Function function) {
        this.function = function;
    }

    public double calculate(double sectionFrom, double sectionTo, double limit) {
        double result = 0;
        double step = (sectionTo - sectionFrom) / limit;
        for (int i=0; i<limit; i++) {
            result += function.calculate(sectionFrom + step * (i + 0.5));
        }
        return result *= step;
    }

}
