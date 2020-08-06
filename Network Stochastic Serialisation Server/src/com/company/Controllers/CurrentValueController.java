package com.company.Controllers;

import com.company.Classes.Main;
import com.company.Data.CurrentValue;
import com.company.Data.Settings;
import com.company.Data.Spot;


/**
 * This controller maintains and updates the CurrentValue object throughout execution.
 */
public class CurrentValueController {

    public static CurrentValue cv;
    public static int iteration = 0;
    /**
     * The interest value can be changed, and will affect the rate of underlying increase of the current value.
     */
    private static double interest = 0.02;

    /**
     * This method initialises the current value object with the starting value defined in the GUI.
     */
    public static void createCurrentValue(){

        cv = new CurrentValue(Settings.startValue);

    }


    /**
     * Each time a new spot is received, it is received in this method to update the current value.
     * If either of the two conditions are met, the corresponding main method is called.
     * @param spot This is used to update the current value.
     */
    public static void tick(Spot spot){

        updateCurrentValue(spot.value);

        iteration ++;

        if (iteration >= Settings.maxSteps){

            Main.reachedMaxSteps();

        } else if (cv.value >= Settings.strikeValue){

            Main.reachedStrike();

        }

    }

    /**
     * This method handles updating hte current value with the spot value and the interest defined above.
     * @param value This is the value taken from the spot object.
     */
    private static void updateCurrentValue(double value){

        cv.addToValue((cv.value * interest) + value);

    }

}
