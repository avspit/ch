package ru.shestakov.lab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.shestakov.lab.Function.calculate;

public class Util {
    private final static double h = Math.PI / 7;

    public static List<Node> initInterpolationNodes() {
        return Arrays.asList(
                new Node(0.5 * h, calculate(0.5 * h)),
                new Node(1.5 * h, calculate(1.5 * h)),
                new Node(2.5 * h, calculate(2.5 * h)),
                new Node(3.5 * h, calculate(3.5 * h)),
                new Node(4.5 * h, calculate(4.5 * h)),
                new Node(6.5 * h, calculate(6.5 * h))
        );
        /*return Arrays.asList(
                new Node(0, 1),
                new Node(2, 3),
                new Node(4, 5),
                new Node(6, 7)
        );*/
    }

    public static List<Data> initXData(double from, double to, double step) {
        List<Data> xData = new ArrayList<>();
        for (double i = from; i <= to; i+=step) {
            xData.add(new Data(i, 0));
        }
        return xData;
    }
}
