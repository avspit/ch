package ru.shestakov.lab.comparator;

import ru.shestakov.lab.model.Entity;

import java.util.Comparator;

public class EulerEntitySortByX implements Comparator<Entity> {
    @Override
    public int compare(Entity o1, Entity o2) {
        return o1.getX().compareTo(o2.getX());
    }
}
