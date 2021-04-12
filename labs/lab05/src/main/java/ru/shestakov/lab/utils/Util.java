package ru.shestakov.lab.utils;

import ru.shestakov.lab.model.Data;
import ru.shestakov.lab.model.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.shestakov.lab.function.Function.calculate;

public class Util {
    private final static double h = Math.PI / 7;

    public static List<Node> initInterpolationNodes() {
        return Arrays.asList(
                new Node(0.5 * h, calculate(0.5 * h)),
                new Node(1.5 * h, calculate(1.5 * h)),
                new Node(2.5 * h, calculate(2.5 * h)),
                new Node(3.5 * h, calculate(3.5 * h)),
                new Node(4.5 * h, calculate(4.5 * h)),
                new Node(6.5 * h, calculate(6.5 * h)),
                new Node(7 * h, calculate(7 * h))
        );
    }

    public static List<Data> initXData(List<Node> interpolationNodes) {
        double from = interpolationNodes.get(0).getX();
        double to = interpolationNodes.get(interpolationNodes.size()-1).getX();
        double step = (to - from) / (interpolationNodes.size()-1);

        List<Data> xData = new ArrayList<>();
        for (double i = from; i <= to; i+=step) {
            xData.add(new Data(i, 0));
        }
        return xData;
    }
}
