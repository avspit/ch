package ru.shestakov.lab.method;

import ru.shestakov.lab.comparator.EntitySortByX;
import ru.shestakov.lab.function.Function;
import ru.shestakov.lab.model.Entity;
import ru.shestakov.lab.model.ExactSolutionEntity;
import ru.shestakov.lab.utils.Formatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExactSolution {
    private Function function;
    private Formatter formatter = new Formatter();

    public ExactSolution(Function function) {
        this.function = function;
    }

    public List<Entity> calculate(double from, double to, double h) {
        List<Entity> table = new ArrayList<Entity>();
        for (double i=from; i<to; i+=h) {
            double x = formatter.round(i, 2);
            double y = formatter.round(function.calculate(x), 5);
            ExactSolutionEntity entity = new ExactSolutionEntity(x, y);
            table.add(entity);
        }
        Collections.sort(table, new EntitySortByX());
        return table;
    }

}
