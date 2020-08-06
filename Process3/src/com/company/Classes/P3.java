package com.company.Classes;

import com.company.Controllers.NetworkController;
import com.company.Data.Spot;

import java.util.Observable;

/**
 * This class takes in the spot and pushes it back to the controller.
 */
public class P3 extends Observable {

    Spot spot;

    public P3(Spot spot) {
        this.spot = spot;

        NetworkController.push(spot);

    }


}
