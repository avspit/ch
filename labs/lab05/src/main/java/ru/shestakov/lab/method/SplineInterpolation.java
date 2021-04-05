package ru.shestakov.lab.method;

import ru.shestakov.lab.model.Node;

import java.util.List;

public class SplineInterpolation {
    private List<Node> interpolationNodes;
    private double[] aValues;
    private double[] bValues;
    private double[] cValues;
    private double[] dValues;

    public SplineInterpolation(List<Node> interpolationNodes) {
        this.interpolationNodes = interpolationNodes;
    }

    public double calculate(double xValue) {
        return 0d;
    }

    public void initSplines() {
        init();
    }

    private void init() {
        int size = interpolationNodes.size();
        aValues = new double[size];
        bValues = new double[size];
        cValues = new double[size];
        dValues = new double[size];
    }
}
