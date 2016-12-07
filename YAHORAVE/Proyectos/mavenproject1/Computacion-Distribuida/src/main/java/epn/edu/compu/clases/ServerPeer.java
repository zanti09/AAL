package epn.edu.compu.clases;

import java.io.*;
import java.net.*;

/**
 * This example shows how to use sockets to send and receive objects. This file
 * contains the class Server, which does the receiving of objects from class
 * WriteSocket in file WriteSocket.java The Server has to run first and wait for
 * the WriteSocket to send the information.
 *
 * Compiled and Tested with JDK1.1 & JDK1.2
 */
public class ServerPeer extends Thread {

    private int port;

    public ServerPeer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        ServerSocket ser = null;
        Socket socServer = null;

        try {
            ser = new ServerSocket(port);

            socServer = ser.accept();
            DataOutputStream out;
            DataInputStream in;
            while (true) {
                in = new DataInputStream(socServer.getInputStream());
                System.out.println(in.readUTF());
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Error during serialization");
            System.exit(1);
        }
    }
    
    
}
