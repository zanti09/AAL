/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.proyecto;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

    private int port;
    private String serverIP;
    private Socket clientSocket;
    private File file;
    private String accion;

    public ClientPeer(int port, String serverIP, File file, String accion) {
        this.port = port;
        this.serverIP = serverIP;
        this.file = file;
        this.accion = accion;
    }

    public ClientPeer(int port, String serverIP, String accion) {
        this.port = port;
        this.serverIP = serverIP;
        this.accion = accion;
    }

    @Override
    public void run() {
        try {
            clientSocket = new Socket(InetAddress.getByName(serverIP), port);
            DataOutputStream dataOut = new DataOutputStream(clientSocket.getOutputStream());
            dataOut.writeUTF(accion);
            switch (accion) {
                case "actualizar":
                    actualizarArchivos();
                    break;
                case "subir":
                    subirArchivo();
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void actualizarArchivos() throws IOException {
        DataInputStream dataIn = new DataInputStream(clientSocket.getInputStream());
        int size = Integer.parseInt(dataIn.readUTF());
        InputStream in=null;
        for (int i = 0; i < size; i++) {
            dataIn = new DataInputStream(clientSocket.getInputStream());
            String fileName = dataIn.readUTF();
            in = clientSocket.getInputStream();
            try {
                FileOutputStream out = new FileOutputStream("C:\\Computacion Distribuida\\" + fileName);
                byte[] bytes = new byte[64 * 1024];

                int count;
                while ((count = in.read(bytes)) > 0) {
                    out.write(bytes, 0, count);
                    System.err.println(count);
                }
                out.close();                
            } catch (IOException e) {
                e.printStackTrace();
            } 
        }
        IOUtils.closeQuietly(in);
    }

    private void subirArchivo() throws IOException {
        DataOutputStream dataOut = new DataOutputStream(clientSocket.getOutputStream());
        dataOut.writeUTF(file.getName());
        OutputStream out = clientSocket.getOutputStream();
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
