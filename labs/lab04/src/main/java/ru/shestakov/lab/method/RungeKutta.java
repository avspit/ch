package ru.shestakov.lab.method;

import ru.shestakov.lab.comparator.EntitySortByX;
import ru.shestakov.lab.function.Cauchy;
import ru.shestakov.lab.model.Entity;
import ru.shestakov.lab.model.RungeKuttaEntity;
import ru.shestakov.lab.utils.Formatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RungeKutta {
    private Cauchy cauchy;
    private Formatter formatter = new Formatter();

    public RungeKutta(Cauchy cauchy) {
        this.cauchy = cauchy;
    }

    public List<Entity> calculate(double x, double y, double from, double to, double h) {
        List<Entity> table = new ArrayList<Entity>();
        double lastY = Double.MAX_VALUE;
        for (int i=0; i<(to-from)/h+1; i++) {
            if (lastY != Double.MAX_VALUE) {
                x = formatter.round((from+h)*i, 2);
                y = formatter.round(lastY, 5);
            }
            double k1 = cauchy.calculate(x, y);
            double k2 = cauchy.calculate(x + h/2, y + h*k1/2);
            double k3 = cauchy.calculate(x + h/2, y + h*k2/2);
            double k4 = cauchy.calculate(x + h, y + h*k3);
            double deltaY = h/6 * (k1 + 2*k2 + 2*k3 + k4);
            lastY = y + deltaY;
            RungeKuttaEntity entity = new RungeKuttaEntity(x, y, k1, k2, k3, k4, deltaY);
            table.add(entity);
        }
        Collections.sort(table, new EntitySortByX());
        return table;
    }

}
