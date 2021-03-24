package ru.shestakov.lab.table;

import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import ru.shestakov.lab.utils.Formatter;
import ru.shestakov.lab.utils.LineChartUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static ru.shestakov.lab.Solution.*;

public class GroupableJFrame extends JFrame {

    private void fillDataVector(Object[][] dataVector, XYSeries series, int columnNumber, boolean addDeltaValues, Formatter formatter) {
        List items = series.getItems();
        for (int k=0; k<items.size(); k++) {
            Object obj = items.get(k);
            XYDataItem item = (XYDataItem) obj;
            double y = item.getYValue();
            dataVector[k][columnNumber] = y;
            // delta
            if (addDeltaValues) {
                BigDecimal delta = new BigDecimal((Double) dataVector[k][1]).subtract(new BigDecimal(y));
                delta = delta.setScale(5, RoundingMode.HALF_UP);
                dataVector[k][columnNumber + 1] = delta.toString();
            }
        }
    }

    private Object[][] prepareDataVector(XYSeriesCollection dataset) {
        Formatter formatter = new Formatter();
        List<XYSeries> list = dataset.getSeries();
        int rows = list.get(0).getItems().size();
        Object[][] dataVector = new Object[rows][8];
        // Заполняем номера строк
        for (int i=0; i<rows; i++) {
            dataVector[i][0] = i+1;
        }
        // Заполняем другие строки
        for (int j=0; j<list.size(); j++) {
            XYSeries series = dataset.getSeries(j);
            switch (String.valueOf(series.getKey())) {
                case (LABEL_EXACT_SOLUTION):
                    fillDataVector(dataVector, series, 1, false, formatter);
                    break;
                case (LABEL_EULER):
                    fillDataVector(dataVector, series, 2, true, formatter);
                    break;
                case (LABEL_PREDICTOR_CORRECTOR):
                    fillDataVector(dataVector, series, 4, true, formatter);
                    break;
                case (LABEL_RUNGE_KUTTA):
                    fillDataVector(dataVector, series, 6, true, formatter);
                    break;
            }
        }
        return dataVector;
    }

    private Object[] prepareColumns() {
        return new Object[]{
                "No.",
                "Точные значения y",
                "y","delta",
                "y","delta",
                "y","delta"
        };
    }

    public GroupableJFrame(String name, XYSeriesCollection dataset) {
        super(name);

        DefaultTableModel dm = new DefaultTableModel();
        dm.setDataVector(
                prepareDataVector(dataset),
                prepareColumns()
        );

        JTable table = new JTable( dm ) {
            protected JTableHeader createDefaultTableHeader() {
                return new GroupableTableHeader(columnModel);
            }
        };

        DefaultTableCellRenderer rendererEuler = new DefaultTableCellRenderer();
        rendererEuler.setBackground(new Color(220, 225, 227));
        DefaultTableCellRenderer rendererPredictorCorrector = new DefaultTableCellRenderer();
        rendererPredictorCorrector.setBackground(new Color(187, 238, 255));
        DefaultTableCellRenderer rendererRungeKutta = new DefaultTableCellRenderer();
        rendererRungeKutta.setBackground(new Color(223, 255, 233));
        table.getColumnModel().getColumn(3).setCellRenderer(rendererEuler);
        table.getColumnModel().getColumn(5).setCellRenderer(rendererPredictorCorrector);
        table.getColumnModel().getColumn(7).setCellRenderer(rendererRungeKutta);

        TableColumnModel cm = table.getColumnModel();
        ColumnGroup groupEuler = new ColumnGroup("Метод Эйлера");
        groupEuler.add(cm.getColumn(2));
        groupEuler.add(cm.getColumn(3));
        ColumnGroup groupPredictorCorrector = new ColumnGroup("Метод 2 порядка");
        groupPredictorCorrector.add(cm.getColumn(4));
        groupPredictorCorrector.add(cm.getColumn(5));
        ColumnGroup groupRungeKutta = new ColumnGroup("Метод 4 порядка");
        groupRungeKutta.add(cm.getColumn(6));
        groupRungeKutta.add(cm.getColumn(7));

        GroupableTableHeader header = (GroupableTableHeader)table.getTableHeader();
        header.addColumnGroup(groupEuler);
        header.addColumnGroup(groupPredictorCorrector);
        header.addColumnGroup(groupRungeKutta);
        JScrollPane scroll = new JScrollPane(table);
        getContentPane().add(scroll);
        setSize( 700, 300 );
    }
}
