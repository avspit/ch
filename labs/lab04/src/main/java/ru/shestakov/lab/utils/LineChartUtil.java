package ru.shestakov.lab.utils;

import com.sun.javafx.UnmodifiableArrayList;
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
        XYSeries series0 = dataset.getSeries(0);
        System.out.println(series0.getKey());
        for (Object i : series0.getItems()) {
            XYDataItem item = (XYDataItem) i;
            double x = item.getXValue();
            double y = item.getYValue();
            System.out.println(x + " " + y);
        }
    }

}
