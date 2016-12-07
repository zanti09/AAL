package epn.edu.compu.clases;
import java.io.*;
import java.net.*;
import java.util.*;


/**
 * This example shows how to use sockets to send and receive objects.
 * This file contains the class Server, which does the receiving of objects
 * from class WriteSocket in file WriteSocket.java
 * The Server has to run first and wait for the WriteSocket
 * to send the information.
 *
 * Compiled and Tested with JDK1.1 & JDK1.2
 */
public class Server {

    /**
     * Create the serversocket and use its stream to receive serialized objects
     */
    public static void main(String args[]) {

      ServerSocket ser = null;
      Socket soc = null;

      try {
        ser = new ServerSocket(8020);
        /*
         * This will wait for a connection to be made to this socket.
         */
        soc = ser.accept();
        InputStream in = null;
        OutputStream out = null;
        
        in = soc.getInputStream();
        out = new FileOutputStream("C:\\Users\\santi\\Downloads\\PopcornTime\\VideoCopia.mkv");

        byte[] bytes = new byte[64*1024];

        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }

        out.close();
        in.close();
        soc.close();
        ser.close();
      } catch (Exception e) {
          System.out.println(e.getMessage());
          System.out.println("Error during serialization");
          System.exit(1);
      }
    }
}
