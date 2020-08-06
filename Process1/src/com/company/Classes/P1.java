package com.company.Classes;

import com.company.Controllers.NetworkController;
import com.company.Data.Spot;

import java.util.Observable;

/**
 * This class generates a new spot object and pushes it to the network controller.
 */
public class P1{

    public P1() {

        Spot spot = new Spot();

        NetworkController.push(spot);

    }
}
