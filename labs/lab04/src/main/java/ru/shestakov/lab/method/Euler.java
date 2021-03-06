package ru.shestakov.lab.method;

import ru.shestakov.lab.function.Cauchy;
import ru.shestakov.lab.comparator.EntitySortByX;
import ru.shestakov.lab.model.Entity;
import ru.shestakov.lab.model.EulerEntity;
import ru.shestakov.lab.utils.Formatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Euler {
    private Cauchy cauchy;
    private Formatter formatter = new Formatter();

    public Euler(Cauchy cauchy) {
        this.cauchy = cauchy;
    }

    public List<Entity> calculate(double x, double y, double from, double to, double h) {
        List<Entity> table = new ArrayList<Entity>();
        EulerEntity last = null;
        for (int i=0; i<(to-from)/h+1; i++) {
            if (last != null) {
                x = formatter.round((from+h)*i, 2);
                y = formatter.round(last.getY() + last.getHfXY(), 5);
            }
            double fXY = cauchy.calculate(x,y);
            EulerEntity entity = new EulerEntity(x, y, fXY, h * fXY);
            table.add(entity);
            last = entity;
        }
        Collections.sort(table, new EntitySortByX());
        return table;
    }

}
