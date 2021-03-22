package ru.shestakov.lab.utils;

import com.sun.javafx.UnmodifiableArrayList;
import org.jfree.chart.util.StringUtils;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import ru.shestakov.lab.model.Entity;

import java.util.List;

public class LineChartUtil {

    public XYSeries createDataset(String label, List<Entity> list) {
        XYSeries series = new XYSeries(label);
        list.forEach(elem -> {
            series.add(elem.getX(), elem.getY());
        });
        return series;
    }

    public void printDataset(XYSeriesCollection dataset) {
        List<XYSeries> list = dataset.getSeries();
        for (int i=0; i<list.size(); i++) {
            XYSeries series = dataset.getSeries(i);
            System.out.println(series.getKey());
            for (Object obj : series.getItems()) {
                XYDataItem item = (XYDataItem) obj;
                double x = item.getXValue();
                double y = item.getYValue();
                System.out.println(x + " " + y);
            }
            System.out.println();
        }
    }

}
