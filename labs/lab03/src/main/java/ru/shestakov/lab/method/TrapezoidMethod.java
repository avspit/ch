package ru.shestakov.lab.method;

import ru.shestakov.lab.Function;

public class TrapezoidMethod {
    private Function function;

    public TrapezoidMethod(Function function) {
        this.function = function;
    }

    public double calculate(double sectionFrom, double sectionTo, double limit) {
        double result = 0, xFrom = sectionFrom, xTo = 0;
        double step = (sectionTo - sectionFrom) / limit;
        for (int i=0; i<limit; i++) {
            xTo = xFrom + step;
            result += (function.calculate(xTo) + function.calculate(xFrom)) * step * 0.5;
            xFrom = xTo;
        }
        return result;
    }

}
