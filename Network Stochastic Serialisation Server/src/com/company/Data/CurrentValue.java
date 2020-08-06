package com.company.Data;

/**
 * This class holds the current value of the program.
 */
public class CurrentValue{

    public double value;

    public CurrentValue(double startValue) {

        this.value = startValue;

    }

    /**
     * This method adds the passed value to the current double value.
     * @param addition This value is added on to the current value.
     */
    public void addToValue(double addition){

        value += addition;

    }

}
