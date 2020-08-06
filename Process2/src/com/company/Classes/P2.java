package com.company.Classes;

import com.company.Controllers.NetworkController;
import com.company.Data.Spot;

import java.util.Observable;

/**
 * This class simply pushes the same spot to the controller.
 */
public class P2 extends Observable {

    Spot spot;

    public P2(Spot spot) {
        this.spot = spot;

        NetworkController.push(spot);

    }

}
