package ru.shestakov.lab;

import ru.shestakov.lab.chart.LineChart;
import ru.shestakov.lab.function.Cauchy;
import ru.shestakov.lab.function.Function;
import ru.shestakov.lab.method.ExactSolution;
import ru.shestakov.lab.utils.LineChartUtil;
import ru.shestakov.lab.method.Euler;

import java.awt.*;

public class Solution {
    private final static double INTERVAL_FROM = 0; // Интервал "от"
    private final static double INTERVAL_TO = 1; // Интервал "до"
    private final static double STEP = 0.1; // Шаг
    private final static double INITIAL_CONDITION_X = 0; // Начальное условие x
    private final static double INITIAL_CONDITION_Y = 1; // Начальное условие y

    public static void main(String[] args) {
        Cauchy cauchy = new Cauchy();
        EventQueue.invokeLater(() -> {
            LineChart ex = new LineChart();
            ex.addDataSet(
                    new LineChartUtil().createDataset(
                            "Exact",
                            new ExactSolution(new Function()).calculate(INTERVAL_FROM, INTERVAL_TO, STEP)
                    )
            );
            ex.addDataSet(
                    new LineChartUtil().createDataset(
                            "Euler",
                            new Euler(cauchy).calculate(INITIAL_CONDITION_X, INITIAL_CONDITION_Y, INTERVAL_FROM, INTERVAL_TO, STEP)
                            )
            );
            ex.setVisible(true);
            new LineChartUtil().printDataset(ex.getDataset());
        });


    }

}
