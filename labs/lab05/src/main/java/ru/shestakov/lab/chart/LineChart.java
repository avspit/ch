package ru.shestakov.lab.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import ru.shestakov.lab.function.Function;
import ru.shestakov.lab.method.SplineInterpolation;
import ru.shestakov.lab.method.SplineInterpolationOld;
import ru.shestakov.lab.model.Data;
import ru.shestakov.lab.model.Node;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LineChart extends JFrame {
    private static final String TITLE = "Интерполяция сплайнами";
    private static final String X_LABEL = "x";
    private static final String Y_LABEL = "y";
    private static final String FUNCTION_LABEL = "График функции";
    private static final String SPLINE_LABEL = "Интерполяция сплайнами";
    private List<Node> interpolationNodes;
    private List<Data> xData;

    public LineChart(List<Node> interpolationNodes, List<Data> xData) {
        this.interpolationNodes = interpolationNodes;
        this.xData = xData;
        initUI();
    }

    private void initUI() {
        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);

        add(chartPanel);

        pack();
        setTitle(TITLE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private XYDataset createDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(createFunctionDataset());
        dataset.addSeries(createSplineDataset());
        return dataset;
    }

    private XYSeries createSplineDataset() {
        XYSeries series = new XYSeries(SPLINE_LABEL);
        SplineInterpolationOld spline = new SplineInterpolationOld(interpolationNodes);
        xData.forEach(elem -> {
            series.add(elem.getX(), spline.calculate(elem.getX()));
        });
        /*SplineInterpolation spline = new SplineInterpolation(interpolationNodes);
        spline.calculateEquations();
        spline.calculateJoints();
        spline.calculateCurvatures();*/
        return series;
    }

    private XYSeries createFunctionDataset() {
        XYSeries series = new XYSeries(FUNCTION_LABEL);
        xData.forEach(elem -> {
            Function function = new Function();
            series.add(elem.getX(), function.calculate(elem.getX()));
        });
        return series;
    }

    private JFreeChart createChart(final XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                TITLE,
                X_LABEL,
                Y_LABEL,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

        XYSplineRenderer renderer = new XYSplineRenderer();

        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesPaint(1, Color.RED);
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinesVisible(false);
        plot.setDomainGridlinesVisible(false);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle(TITLE,
                        new Font("Serif", Font.BOLD, 18)
                )
        );

        return chart;
    }

}
