package com.company.Controllers;

import com.company.Classes.P3;
import com.company.Data.Spot;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class handles the incoming and outgoing connections to the application.
 */
public class NetworkController {

    /**
     * This method establishes the connection to process 2 and accepts the spot.
     */
    public static void establishConnection(){

        try (
                ServerSocket inboundServerSocket = new ServerSocket(5002);
        ){

            System.out.println("Running. Waiting for connection... ");

            while(true) {

                Socket inboundSocket = inboundServerSocket.accept();

                ObjectInputStream ois = new ObjectInputStream(inboundSocket.getInputStream());

                Spot spot = (Spot)ois.readObject();

                System.out.println("tock");

                new P3(spot);
            }


        } catch (IOException | ClassNotFoundException e){

            System.err.println(e.getMessage());

        }

    }

    /**
     * This method pushes the given spot to the server.
     * @param spot the spot object to be serialised and transferred.
     */
    public static void push(Spot spot){

        try (
                Socket outboundSocket = new Socket("localhost", 4999);
                ObjectOutputStream ois = new ObjectOutputStream(outboundSocket.getOutputStream());
        ){

            ois.writeObject(spot);

        } catch (IOException e){

            System.err.println("P2 is not running");

            System.exit(0);

        }

    }

}
