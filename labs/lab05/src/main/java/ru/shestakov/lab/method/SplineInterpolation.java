package ru.shestakov.lab.method;

import ru.shestakov.lab.model.Node;

import java.util.List;

public class SplineInterpolation {
    private List<Node> interpolationNodes;

    public SplineInterpolation(List<Node> interpolationNodes) {
        this.interpolationNodes = interpolationNodes;
    }

    public double calculate(double xValue) {
        return 0d;
    }

    public void initSplines() {
        int d = 1;
    }

}
