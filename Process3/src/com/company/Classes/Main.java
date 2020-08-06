package com.company.Classes;

import com.company.Controllers.NetworkController;

/**
 * This application accepts a spot from process 2 and pushes the same spot back to the original server.
 * @author Benjamin Frost 2020
 */
public class Main {

    public static void main(String[] args) {

        NetworkController.establishConnection();

    }
}
