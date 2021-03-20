package ru.shestakov.lab;

public class Util {

    public String whoIsMoreAccurate(double exactvalue, double... values) {
        for (int i=0; i<values.length; i++) {
            if (values[i] > exactvalue) {
                values[i] = values[i] - exactvalue;
            } else {
                values[i] = exactvalue - values[i];
            }
        }
        if (allEquals(values)) {
            return "Все имеют одинаковую погрешность: " + values[0];
        }
        return checkAccuracy(values);
    }

    private String checkAccuracy(double[] values) {
        int result = 0;
        double min = Integer.MAX_VALUE;
        for (int i=0; i<values.length; i++) {
            double tmp = Math.min(values[i], min);
            if (tmp < min) {
                min = tmp;
                result = i+1;
            }
        }
        return String.format("Точнее считает № %s (наименьшая погрешность)", result);
    }

    private boolean allEquals(double[] values) {
        boolean result = true;
        for (int i=0; i<values.length-1; i++) {
            if (values[i] - values[i+1] != 0d) {
                result = false;
            }
        }
        return result;
    }
}
