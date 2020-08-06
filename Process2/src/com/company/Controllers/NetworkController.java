package com.company.Controllers;

import com.company.Classes.P2;
import com.company.Data.Spot;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This controller handles the incoming and outgoing messages for the process.
 */
public class NetworkController {

    /**
     * This method establishes the connection with process 1 to receive the spot.
     */
    public static void establishConnection(){

        try (
                ServerSocket inboundServerSocket = new ServerSocket(5001);
        ){

            System.out.println("Running. Waiting for connection... ");

            Socket inboundSocket;

            ObjectInputStream ois;

            while(true) {

                inboundSocket = inboundServerSocket.accept();

                ois = new ObjectInputStream(inboundSocket.getInputStream());

                Spot spot = (Spot)ois.readObject();

                System.out.println("tock");

                new P2(spot);
            }


        } catch (IOException e){

            System.err.println("IOException " + e.getMessage());

        } catch (ClassNotFoundException b){

            System.err.println("Class not found " + b.getMessage());

        }

    }

    /**
     * This method pushes the given spot to process 3.
     * @param spot Spot to transfer.
     */
    public static void push(Spot spot){

        try (
                Socket outboundSocket = new Socket("localhost", 5002);
                ObjectOutputStream ois = new ObjectOutputStream(outboundSocket.getOutputStream());
        ){

            ois.writeObject(spot);

        } catch (IOException e){

            System.err.println("P3 is not running");

            System.exit(0);

        }

    }

}
