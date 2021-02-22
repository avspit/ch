package ru.shestakov.lab;

import java.awt.*;
import java.util.List;

import static ru.shestakov.lab.Util.initInterpolationNodes;
import static ru.shestakov.lab.Util.initXData;

public class Solution {

    public static void main(String[] args) {
        List<Node> interpolationNodes = initInterpolationNodes();
        List<Data> xData = initXData(0, 4, 0.5);

        EventQueue.invokeLater(() -> {
            LineChart ex = new LineChart(interpolationNodes, xData);
            ex.setVisible(true);
        });
    }

}
