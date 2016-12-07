/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.proyecto;

import epn.edu.compu.clases.Person;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import static java.lang.System.in;
import static java.lang.System.out;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author santi
 */
public class ClientPeer extends Thread {

    int port;
    int localPort;
    private Socket clientSocket;

    public ClientPeer(int port, int localPort) {
        this.port = port;
        this.localPort = localPort;
    }

    @Override
    public void run() {
        try {
            clientSocket = new Socket(InetAddress.getLocalHost(), port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void uploadFile(File file) throws FileNotFoundException, IOException {
        OutputStream o = clientSocket.getOutputStream();
        ObjectOutput s = new ObjectOutputStream(o);

        s.writeObject(file);
        out.close();
        in.close();
    }

}