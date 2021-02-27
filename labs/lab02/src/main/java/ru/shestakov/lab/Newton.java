package ru.shestakov.lab;

import java.util.List;

public class Newton {
    private List<Node> interpolationNodes;

    public Newton(List<Node> interpolationNodes) {
        this.interpolationNodes = interpolationNodes;
    }

    public double calculate(double xValue) {
        double result = 0;
        for (int i = 0; i < interpolationNodes.size(); i++) {
            double term = 0;
            for (int j = 0; j <= i; j++) {
                double denom = 1;
                for (int k = 0; k <= i; k++) {
                    if (j != k) {
                        denom = denom * (interpolationNodes.get(j).getX() - interpolationNodes.get(k).getX());
                    }
                }
                term = term + interpolationNodes.get(j).getY() / denom;
            }
            double add = 1;
            for (int m = 0; m < i; m++) {
                add = add * (xValue - interpolationNodes.get(m).getX());
            }
            result = result + term * add;
        }
        return result;
    }

}
