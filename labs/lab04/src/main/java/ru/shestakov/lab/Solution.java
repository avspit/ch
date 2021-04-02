package ru.shestakov.lab;

import ru.shestakov.lab.chart.LineChart;
import ru.shestakov.lab.function.Cauchy;
import ru.shestakov.lab.function.Function;
import ru.shestakov.lab.method.ExactSolution;
import ru.shestakov.lab.method.PredictorCorrector;
import ru.shestakov.lab.method.RungeKutta;
import ru.shestakov.lab.table.GroupableJFrame;
import ru.shestakov.lab.utils.LineChartUtil;
import ru.shestakov.lab.method.Euler;

import java.awt.*;

public class Solution {
    private final static double INTERVAL_FROM = 0; // Интервал "от"
    private final static double INTERVAL_TO = 2; // Интервал "до"
    private final static double STEP = 0.2; // Шаг
    private final static double INITIAL_CONDITION_X = 0; // Начальное условие x
    private final static double INITIAL_CONDITION_Y = -2; // Начальное условие y
    public final static String LABEL_EXACT_SOLUTION = "Точное решение";
    public final static String LABEL_EULER = "Метод Эйлера";
    public final static String LABEL_PREDICTOR_CORRECTOR = "Метод 2 порядка";
    public final static String LABEL_RUNGE_KUTTA = "Метод 4 порядка";

    public static void main(String[] args) {
        Cauchy cauchy = new Cauchy();
        EventQueue.invokeLater(() -> {
            // Создаем график
            LineChart ex = new LineChart();
            createLineChart(ex, cauchy);
            ex.setVisible(true);

            // Создаем таблицу сравнения значений
            GroupableJFrame frame = new GroupableJFrame("Таблица сравнения значений", ex.getDataset());
            frame.setVisible(true);

            // Выведем данные в консоль для проверки
            new LineChartUtil(ex.getDataset()).printDataset();
        });
    }

    private static void createLineChart(LineChart ex, Cauchy cauchy) {
        // Точное решение
        addExactSolutionDataset(ex);
        // Метод Эйлера
        addEulerDataset(ex, cauchy);
        // Усовершенствованный/модифицированный метод Эйлера (предиктор-корректор) 2 порядка
        addPredictorCorrectorDataset(ex, cauchy);
        // Метод Рунге-Кутта 4 порядка
        addRungeKuttaDataset(ex, cauchy);
    }

    private static void addExactSolutionDataset(LineChart ex) {
        ex.addDataSet(
                new LineChartUtil().createDataset(
                        LABEL_EXACT_SOLUTION,
                        new ExactSolution(new Function()).calculate(INTERVAL_FROM, INTERVAL_TO, STEP)
                )
        );
    }

    private static void addEulerDataset(LineChart ex, Cauchy cauchy) {
        ex.addDataSet(
                new LineChartUtil().createDataset(
                        LABEL_EULER,
                        new Euler(cauchy).calculate(INITIAL_CONDITION_X, INITIAL_CONDITION_Y, INTERVAL_FROM, INTERVAL_TO, STEP)
                )
        );
    }

    private static void addPredictorCorrectorDataset(LineChart ex, Cauchy cauchy) {
        ex.addDataSet(
                new LineChartUtil().createDataset(
                        LABEL_PREDICTOR_CORRECTOR,
                        new PredictorCorrector(cauchy).calculate(INITIAL_CONDITION_X, INITIAL_CONDITION_Y, INTERVAL_FROM, INTERVAL_TO, STEP)
                )
        );
    }

    private static void addRungeKuttaDataset(LineChart ex, Cauchy cauchy) {
        ex.addDataSet(
                new LineChartUtil().createDataset(
                        LABEL_RUNGE_KUTTA,
                        new RungeKutta(cauchy).calculate(INITIAL_CONDITION_X, INITIAL_CONDITION_Y, INTERVAL_FROM, INTERVAL_TO, STEP)
                )
        );
    }

}
