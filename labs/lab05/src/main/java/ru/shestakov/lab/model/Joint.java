package ru.shestakov.lab.model;

public class Joint {
    private double leftA;
    private double leftB;
    private double leftC;
    private double leftD;
    private double leftX;
    private double leftXn;
    private double rightA;
    private double rightB;
    private double rightC;
    private double rightD;
    private double rightX;
    private double rightXn;

    public Joint() {
    }

    public Joint(double leftA, double leftB, double leftC, double leftD, double leftX, double leftXn, double rightA, double rightB, double rightC, double rightD, double rightX, double rightXn) {
        this.leftA = leftA;
        this.leftB = leftB;
        this.leftC = leftC;
        this.leftD = leftD;
        this.leftX = leftX;
        this.leftXn = leftXn;
        this.rightA = rightA;
        this.rightB = rightB;
        this.rightC = rightC;
        this.rightD = rightD;
        this.rightX = rightX;
        this.rightXn = rightXn;
    }

    public double getLeftA() {
        return leftA;
    }

    public void setLeftA(double leftA) {
        this.leftA = leftA;
    }

    public double getLeftB() {
        return leftB;
    }

    public void setLeftB(double leftB) {
        this.leftB = leftB;
    }

    public double getLeftC() {
        return leftC;
    }

    public void setLeftC(double leftC) {
        this.leftC = leftC;
    }

    public double getLeftD() {
        return leftD;
    }

    public void setLeftD(double leftD) {
        this.leftD = leftD;
    }

    public double getLeftX() {
        return leftX;
    }

    public void setLeftX(double leftX) {
        this.leftX = leftX;
    }

    public double getLeftXn() {
        return leftXn;
    }

    public void setLeftXn(double leftXn) {
        this.leftXn = leftXn;
    }

    public double getRightA() {
        return rightA;
    }

    public void setRightA(double rightA) {
        this.rightA = rightA;
    }

    public double getRightB() {
        return rightB;
    }

    public void setRightB(double rightB) {
        this.rightB = rightB;
    }

    public double getRightC() {
        return rightC;
    }

    public void setRightC(double rightC) {
        this.rightC = rightC;
    }

    public double getRightD() {
        return rightD;
    }

    public void setRightD(double rightD) {
        this.rightD = rightD;
    }

    public double getRightX() {
        return rightX;
    }

    public void setRightX(double rightX) {
        this.rightX = rightX;
    }

    public double getRightXn() {
        return rightXn;
    }

    public void setRightXn(double rightXn) {
        this.rightXn = rightXn;
    }
}
