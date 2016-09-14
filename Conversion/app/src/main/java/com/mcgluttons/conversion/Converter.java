package com.mcgluttons.conversion;

/**
 * Created by Justin Wolf on 13/09/2016.
 */
public class Converter {

    private double valueToConvert;

    public Converter (double value) {
        this.valueToConvert = value;
    }

    public double convertToCelsius() {
        return ((valueToConvert - 32) * 5 / 9);
    }

    public double convertToFahrenheit() {
        return (valueToConvert * 9 / 5 + 32);
    }

}
