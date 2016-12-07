package epn.edu.compu.clases;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.sling.commons.json.JSONArray;

/**
 * This example shows how to use sockets to send and receive objects. This file
 * contains the class Server, which does the receiving of objects from class
 * WriteSocket in file WriteSocket.java The Server has to run first and wait for
 * the WriteSocket to send the information.
 *
 * Compiled and Tested with JDK1.1 & JDK1.2
 */
public class ClientPeer extends Thread {

    private int port;

    public ClientPeer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        Socket socClient = null;

        try {

            socClient = new Socket(InetAddress.getLocalHost(), port);
            DataOutputStream out;
            DataInputStream in;
            while (true) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String message = reader.readLine();

//                List<Person> lstPersonas = new ArrayList<>();
//                lstPersonas.add(new Person("Santiago", "Lema", "mail@com.ec"));
//                lstPersonas.add(new Person("David", "Davila", "mail@com.ec"));
//                lstPersonas.add(new Person("Daniela", "Ramos", "mail@com.ec"));
//                Gson gson = new Gson();
                out = new DataOutputStream(socClient.getOutputStream());
                

                out.writeUTF(message);

                if ("chao".equals(message)) {
                    out.close();
                    socClient.close();
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Error during serialization");
            System.exit(1);
        }
    }
}
