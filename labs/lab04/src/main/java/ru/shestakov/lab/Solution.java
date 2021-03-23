package ru.shestakov.lab;

import ru.shestakov.lab.chart.LineChart;
import ru.shestakov.lab.function.Cauchy;
import ru.shestakov.lab.function.Function;
import ru.shestakov.lab.method.ExactSolution;
import ru.shestakov.lab.method.PredictorCorrector;
import ru.shestakov.lab.method.RungeKutta;
import ru.shestakov.lab.utils.LineChartUtil;
import ru.shestakov.lab.method.Euler;

import java.awt.*;

public class Solution {
    private final static double INTERVAL_FROM = 0; // Интервал "от"
    private final static double INTERVAL_TO = 1; // Интервал "до"
    private final static double STEP = 0.1; // Шаг
    private final static double INITIAL_CONDITION_X = 0; // Начальное условие x
    private final static double INITIAL_CONDITION_Y = -2; // Начальное условие y

    public static void main(String[] args) {
        Cauchy cauchy = new Cauchy();
        EventQueue.invokeLater(() -> {
            LineChart ex = new LineChart();
            // Точное решение
            ex.addDataSet(
                    new LineChartUtil().createDataset(
                            "Точное решение",
                            new ExactSolution(new Function()).calculate(INTERVAL_FROM, INTERVAL_TO, STEP)
                    )
            );
            // Метод Эйлера
            ex.addDataSet(
                    new LineChartUtil().createDataset(
                            "Метод Эйлера",
                            new Euler(cauchy).calculate(INITIAL_CONDITION_X, INITIAL_CONDITION_Y, INTERVAL_FROM, INTERVAL_TO, STEP)
                            )
            );
            // Усовершенствованный/модифицированный метод Эйлера (предиктор-корректор) 2 порядка
            ex.addDataSet(
                    new LineChartUtil().createDataset(
                            "Метод 2 порядка",
                            new PredictorCorrector(cauchy).calculate(INITIAL_CONDITION_X, INITIAL_CONDITION_Y, INTERVAL_FROM, INTERVAL_TO, STEP)
                    )
            );
            // Метод Рунге-Кутта 4 порядка
            ex.addDataSet(
                    new LineChartUtil().createDataset(
                            "Метод 4 порядка",
                            new RungeKutta(cauchy).calculate(INITIAL_CONDITION_X, INITIAL_CONDITION_Y, INTERVAL_FROM, INTERVAL_TO, STEP)
                    )
            );
            ex.setVisible(true);
            new LineChartUtil().printDataset(ex.getDataset());
        });


    }

}
