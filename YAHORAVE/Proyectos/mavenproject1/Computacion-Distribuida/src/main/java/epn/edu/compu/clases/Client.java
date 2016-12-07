package epn.edu.compu.clases;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * This example shows how to use sockets and serialization to send objects that
 * will be received (see class Server to see the receiving part)
 *
 * Compiled and Tested with JDK1.1 & JDK1.2
 */
public class Client {

    public static void main(String args[]) {

        try {
            // Create a socket
            Socket soc = new Socket(InetAddress.getLocalHost(), 8020);

            File file = new File("C:\\Users\\santi\\Downloads\\PopcornTime\\Video.mkv");
            // Get the size of the file
            long length = file.length();
            byte[] bytes = new byte[64 * 1024];
            InputStream in = new FileInputStream(file);
            OutputStream out = soc.getOutputStream();

            int count;
            while ((count = in.read(bytes)) > 0) {
                out.write(bytes, 0, count);
            }

            out.close();
            in.close();
            soc.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error during serialization");
            System.exit(1);
        }
    }
}
