package ru.shestakov.lab.method;

import ru.shestakov.lab.model.*;

import java.util.ArrayList;
import java.util.List;

public class SplineInterpolation {
    private List<Node> interpolationNodes;
    private int numberOfIntervals;
    private double[] aValues;
    private double[] bValues;
    private double[] cValues;
    private double[] dValues;
    private double[] hValues;

    private List<Spline> splines;
    private List<Joint> joints;
    private List<Curvature> curvatures;

    public SplineInterpolation(List<Node> interpolationNodes) {
        this.interpolationNodes = interpolationNodes;
    }

    public void calculateCurvatures() {
        for (int i=1; i<interpolationNodes.size(); i+=2) {

            Node node = interpolationNodes.get(i);
            Curvature curvature = new Curvature();
            curvature.setLeftX(node.getX());
            curvature.setLeftXn(interpolationNodes.get(i).getX());
            curvatures.add(curvature);

            node = interpolationNodes.get(i-1);
            curvature.setRightX(node.getX());
            curvature.setRightXn(interpolationNodes.get(i).getX());
            curvatures.add(curvature);

            curvatures.add(curvature);
        }
    }

    public void calculateJoints() {
        for (int i=1; i<interpolationNodes.size(); i+=2) {

            Node node = interpolationNodes.get(i);
            Joint joint = new Joint();
            joint.setLeftX(node.getX());
            joint.setLeftXn(interpolationNodes.get(i).getX());
            joints.add(joint);

            node = interpolationNodes.get(i-1);
            joint.setRightX(node.getX());
            joint.setRightXn(interpolationNodes.get(i).getX());
            joints.add(joint);

            joints.add(joint);
        }
    }

    public void calculateEquations() {
        int counter = 0;
        for (int i=1; i<interpolationNodes.size(); i+=2) {

            List<Equasion> equasions = new ArrayList<>();

            Node node = interpolationNodes.get(i);
            Equasion eq1 = new Equasion();
            eq1.setS(node.getY());
            eq1.setX(node.getX());
            eq1.setXn(interpolationNodes.get(counter).getX());
            equasions.add(eq1);

            node = interpolationNodes.get(i-1);
            Equasion eq2 = new Equasion();
            eq2.setS(node.getY());
            eq2.setX(node.getX());
            eq2.setXn(interpolationNodes.get(counter).getX());
            equasions.add(eq2);

            Spline spline = new Spline();
            spline.setEquasions(equasions);
            splines.add(spline);
        }
    }
}
