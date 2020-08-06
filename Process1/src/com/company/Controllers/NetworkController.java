package com.company.Controllers;

import com.company.Classes.P1;
import com.company.Data.Spot;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This controller handles the majority of the processing for this process.
 * All ingoing and outgoing communications are processed here.
 */
public class NetworkController {

    /**
     * This method is run when establishing the connection to the server for the first time.
     */
    public static void establishConnection(){

        try (
                ServerSocket inboundServerSocket = new ServerSocket(5000);
        ){

            System.out.println("Running. Waiting for connection... ");

            Socket inboundSocket = inboundServerSocket.accept();

            DataInputStream in;

            while(true) {

                try {

                    in = new DataInputStream(inboundSocket.getInputStream());

                    String word = in.readUTF();

                    new P1();

                    System.out.println("tock");

                } catch (IOException e){

                    inboundSocket = inboundServerSocket.accept();

                }
            }

        } catch (IOException e){

            System.err.println("IOException: " + e.getMessage());

        }

    }

    /**
     * This method pushes the generated spot to the next process in the network chain.
     * @param spot the spot to be serialised and transferred.
     */
    public static void push(Spot spot){

        try (
                Socket outboundSocket = new Socket("localhost", 5001);
                ObjectOutputStream ois = new ObjectOutputStream(outboundSocket.getOutputStream());
        ){

            ois.writeObject(spot);

        } catch (IOException e){

            System.err.println("IOException: " + e.getMessage());

            System.exit(0);

        }

    }

}
