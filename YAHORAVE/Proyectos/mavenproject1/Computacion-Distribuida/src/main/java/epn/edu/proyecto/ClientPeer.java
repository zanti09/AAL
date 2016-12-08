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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
    String serverIP;
    private Socket clientSocket;

    public ClientPeer(int port, String serverIP) {
        this.port = port;
        this.serverIP = serverIP;
    }

    @Override
    public void run() {
        try {
            clientSocket = new Socket(InetAddress.getByName(serverIP), port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void uploadFile(File file) throws FileNotFoundException, IOException {
        byte[] bytes = new byte[64 * 1024];
        InputStream in = new FileInputStream(file);
        OutputStream out = clientSocket.getOutputStream();

        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }
        out.close();
        in.close();
    }

}
