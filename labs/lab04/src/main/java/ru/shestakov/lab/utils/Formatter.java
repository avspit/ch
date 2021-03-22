package ru.shestakov.lab.utils;

import java.math.BigDecimal;

public class Formatter {

    public double round(double value, int scale) {
        BigDecimal a = new BigDecimal(value);
        return a.setScale(scale, BigDecimal.ROUND_HALF_EVEN).doubleValue();
    }

}
