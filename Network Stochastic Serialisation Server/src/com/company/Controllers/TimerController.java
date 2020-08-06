package com.company.Controllers;

import java.util.TimerTask;

/**
 * This method handles the timing of the program.
 */
public class TimerController extends TimerTask{

    @Override
    public void run() {

        NetworkController.tick();

    }


}
