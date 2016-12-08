/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.proyecto;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import org.apache.commons.io.IOUtils;

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
        DataOutputStream dataOut=new DataOutputStream(clientSocket.getOutputStream());
        dataOut.writeChars(file.getName());
        OutputStream out=clientSocket.getOutputStream();
        try {
            byte[] bytes = new byte[64 * 1024];
            InputStream in = new FileInputStream(file);            

            int count;
            while ((count = in.read(bytes)) > 0) {
                out.write(bytes, 0, count);
            }
            in.close();
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

}
