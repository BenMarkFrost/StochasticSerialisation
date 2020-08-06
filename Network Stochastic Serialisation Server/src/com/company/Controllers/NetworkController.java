package com.company.Controllers;

import com.company.Classes.Main;
import com.company.Data.Spot;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This controller handles the connections between this application and the processes that it depends on.
 */
public class NetworkController {

    private static Socket socket;
    private static DataOutputStream os;

    private static ServerSocket serverSocket;
    private static Socket inboundSocket;
    private static ObjectInputStream ois;

    /**
     * This method establishes the initial connection with the first process.
     */
    public static void establishConnection(){

        System.out.println("Establishing connection");

        try {
            socket = new Socket("localhost", 5000);
            serverSocket = new ServerSocket(4999);
            System.out.println("Connected to process 1");
        } catch (IOException e){

            System.err.println("IOException: " + e.getMessage());
            System.exit(0);

        }

    }

    /**
     * This method is run once every second during execution and writes the message prompting process 1 to start generating a spot.
      */
    public static void tick(){

        try {

            writeOutboundMessage();

            Spot spot = readInboundSpot();

            Main.updateAfterTick(spot);

        } catch (IOException | ClassNotFoundException e){
            System.out.println("Something's gone wrong: " + e.getMessage());
        }
    }

    /**
     * This method writes the prompting string to process 1.
     * @throws IOException IOException is called here if a connection is dropped and the message cannot be sent.
     */
    private static void writeOutboundMessage() throws IOException{

        os = new DataOutputStream(socket.getOutputStream());

        os.writeUTF(" ");
        System.out.println("tick");

    }

    /**
     * This method reads in the object received from process 3.
     * @return returns the spot object
     * @throws ClassNotFoundException Thrown if the instantiated object cannot be cast to Spot
     * @throws IOException Thrown if the connection is dropped and the object cannot be read.
     */
    private static Spot readInboundSpot() throws ClassNotFoundException, IOException{

        inboundSocket = serverSocket.accept();
        ois = new ObjectInputStream(inboundSocket.getInputStream());

        Spot spot = (Spot)ois.readObject();

        System.out.println("Spot received: " + spot.value);

        return spot;

    }

    /**
     * At the end of execution the open sockets and streams are closed with this method.
     */
    public static void closeConnections(){

        try {
            socket.close();
            serverSocket.close();
            inboundSocket.close();
            os.close();
            ois.close();

        } catch (IOException e){

            System.out.println("Closing sockets failed " + e.getMessage());

        }
    }

}
