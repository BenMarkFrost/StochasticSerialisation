package com.company.Classes;

import com.company.Controllers.CurrentValueController;
import com.company.Controllers.FileController;
import com.company.Controllers.NetworkController;
import com.company.Controllers.TimerController;
import com.company.Data.Spot;
import com.company.GUI.Home;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;

/**
 * This application simulates a stochastic process by generating a random value and adding it on to a current value every 1 second.
 * The running of the application relies on having three other java processes also running on the same PC: Processes 1, 2 and 3. These processes are included in the project folder.
 *
 * @author Benjamin Frost 2020
 */

public class Main {

    static Timer timer;
    static Home home;


    /**
     * This method starts the GUI.
     * @param args reads in arguments when starting from the command line.
     */
    public static void main(String[] args) {

        startGUI();

    }

    /**
     * Once the application is started from the GUI this method is run.
     * Values are initialised and the timer controlling the application is started. Each run of the timer is referred to as a tick.
     */
    public static void startTimer(){

        CurrentValueController.createCurrentValue();

        FileController.resetOutput();

        NetworkController.establishConnection();

        timer = new Timer();

        timer.schedule(new TimerController(), 0, 1000);

    }

    /**
     * Initialising the GUI
     */
    public static void startGUI(){

        JFrame frame = new JFrame("Simple Stochastic Simulation");
        frame.setPreferredSize(new Dimension(1280, 720));

        home = new Home();

        frame.add(home.getHomePanel());

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    /**
     * This method updates the GUI and object file with current values.
     * @param spot the current spot value is passed in.
     */
    public static void updateAfterTick(Spot spot){

        home.update();

        CurrentValueController.tick(spot);

        FileController.savePoint();

    }

    /**
     * This method updates the GUI and resets the application after the strike is reached.
     */
    public static void reachedStrike(){


        System.out.println("STRIKE");

        home.reachedStrike();

        reset();

    }


    /**
     * This method updates the GUI and resets the application after the max iterations are reached.
     */
    public static void reachedMaxSteps(){

        System.out.println("Max Iterations Reached");

        home.reachedMaxIters();

        reset();

    }

    /**
     * When program execution is finished, this method is run to reset values to their starting states.
     * This way the program can be run again once finished without restarting.
      */
    public static void reset(){

        timer.cancel();

        NetworkController.closeConnections();

    }

}
