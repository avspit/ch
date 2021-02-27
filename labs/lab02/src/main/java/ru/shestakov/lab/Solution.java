package ru.shestakov.lab;

import java.awt.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

import static ru.shestakov.lab.Util.initInterpolationNodes;
import static ru.shestakov.lab.Util.initXData;

public class Solution {

    public static void main(String[] args) {
        List<Node> interpolationNodes = initInterpolationNodes();
        List<Data> xData = initXData(0, 2*Math.PI, 0.5);

        EventQueue.invokeLater(() -> {
            LineChart ex = new LineChart(interpolationNodes, xData);
            ex.setVisible(true);
        });
    }
}
