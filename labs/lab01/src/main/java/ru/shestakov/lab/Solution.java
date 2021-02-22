package ru.shestakov.lab;

import java.awt.*;
import java.util.List;

import static ru.shestakov.lab.Util.initInterpolationNodes;
import static ru.shestakov.lab.Util.initXData;

public class Solution {

    public static void main(String[] args) {
        List<Node> interpolationNodes = initInterpolationNodes();
        List<Data> xData = initXData(0, 5, 0.5);

        EventQueue.invokeLater(() -> {
            LineChart ex = new LineChart(interpolationNodes, xData);
            ex.setVisible(true);
        });
    }

    private static void print(List<Data> xData) {
        xData.forEach(elem -> {
            System.out.println(elem.getX() + " " + elem.getY());
        });
    }

}
