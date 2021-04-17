package ru.shestakov.lab;

import ru.shestakov.lab.chart.LineChart;
import ru.shestakov.lab.model.Data;
import ru.shestakov.lab.model.Node;

import java.awt.*;
import java.util.List;

import static ru.shestakov.lab.utils.Util.initInterpolationNodes;
import static ru.shestakov.lab.utils.Util.initXData;

public class Solution {

    public static void main(String[] args) {
        List<Node> interpolationNodes = initInterpolationNodes();
        List<Data> xData = initXData(interpolationNodes.get(0).getX(), 2*Math.PI, 0.5);

        EventQueue.invokeLater(() -> {
            LineChart ex = new LineChart(interpolationNodes, xData);
            ex.setVisible(true);
        });
    }

}
