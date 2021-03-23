package ru.shestakov.lab.table;

import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.util.List;

import static ru.shestakov.lab.Solution.*;

public class GroupableJFrame extends JFrame {

    private void fillDataVector(Object[][] dataVector, XYSeries series, int columnNumber) {
        List items = series.getItems();
        for (int k=0; k<items.size(); k++) {
            Object obj = items.get(k);
            XYDataItem item = (XYDataItem) obj;
            double y = item.getYValue();
            dataVector[columnNumber][k] = y;
        }
    }

    private Object[][] prepareDataVector(XYSeriesCollection dataset) {
        List<XYSeries> list = dataset.getSeries();
        Object[][] dataVector = new Object[8][list.size()];
        for (int i=0; i<list.size(); i++) {
            dataVector[0][i] = i+1;
        }
        for (int j=0; j<list.size(); j++) {
            XYSeries series = dataset.getSeries(j);
            switch (String.valueOf(series.getKey())) {
                case (LABEL_EXACT_SOLUTION):
                    fillDataVector(dataVector, series, 1);
                    break;
                case (LABEL_EULER):
                    fillDataVector(dataVector, series, 3);
                    break;
                case (LABEL_PREDICTOR_CORRECTOR):
                    fillDataVector(dataVector, series, 5);
                    break;
                case (LABEL_RUNGE_KUTTA):
                    fillDataVector(dataVector, series, 7);
                    break;
            }
        }


        return new Object[][]{
                {"119","foo","bar","ja","ko","zh"},
                {"911","bar","foo","en","fr","pt"}};
    }

    private Object[] prepareColumns() {
        return new Object[]{"No.","Точные значения y","y","delta","y","delta","y","delta"};
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


        TableColumnModel cm = table.getColumnModel();
        ColumnGroup groupEuler = new ColumnGroup("Метод Эйлера");
        groupEuler.add(cm.getColumn(1));
        groupEuler.add(cm.getColumn(2));
        ColumnGroup groupPredictorCorrector = new ColumnGroup("Метод 2 порядка");
        groupPredictorCorrector.add(cm.getColumn(3));
        groupPredictorCorrector.add(cm.getColumn(4));
        ColumnGroup groupRungeKutta = new ColumnGroup("Метод 4 порядка");
        groupRungeKutta.add(cm.getColumn(5));
        groupRungeKutta.add(cm.getColumn(6));

        GroupableTableHeader header = (GroupableTableHeader)table.getTableHeader();
        header.addColumnGroup(groupEuler);
        header.addColumnGroup(groupPredictorCorrector);
        header.addColumnGroup(groupRungeKutta);
        JScrollPane scroll = new JScrollPane(table);
        getContentPane().add(scroll);
        setSize( 600, 300 );
    }
}
