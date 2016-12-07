/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.proyecto;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author santi
 */
public class PeerTwo {
    
    int serverPort;

    public PeerTwo(int serverPort) {
        this.serverPort=serverPort;
    }

    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket(InetAddress.getLocalHost(), 8888);
        DataOutputStream out;
        DataInputStream in;

        out = new DataOutputStream(clientSocket.getOutputStream());
        out.writeUTF("9999");

        while (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String message = reader.readLine();

            out = new DataOutputStream(clientSocket.getOutputStream());
            out.writeUTF(message);

            in = new DataInputStream(clientSocket.getInputStream());
            System.out.println(in.readUTF());
            if ("chao".equals(message)) {
                out.close();
                break;
            }
        }

        out.close();
        in.close();
        clientSocket.close();
    }

}
