package com.company.Classes;

import com.company.Controllers.NetworkController;

/**
 * This application is passed a string from the main server application prompting it to generate a spot and pass this spot on to process 2.
 * @author Benjamin Frost 2020
 */
public class Main {

    /**
     * This method prompts the network controller.
     * @param args reads in arguments when run from the command line.
     */
    public static void main(String[] args) {

        NetworkController.establishConnection();

    }
}
