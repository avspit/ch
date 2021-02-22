package ru.shestakov.lab;

import java.util.List;

public class Lagrangian {
    private List<Node> interpolationNodes;

    public Lagrangian(List<Node> interpolationNodes) {
        this.interpolationNodes = interpolationNodes;
    }

    public double calculate(double xValue) {
        double result = 0;
        for (int i = 0; i < interpolationNodes.size(); i++) {
            double term = interpolationNodes.get(i).getY();
            for (int j = 0; j < interpolationNodes.size(); j++) {
                if (j != i)
                    term *= (xValue - interpolationNodes.get(j).getX()) / (interpolationNodes.get(i).getX() - interpolationNodes.get(j).getX());
            }
            result += term;
        }
        return result;
    }

}
