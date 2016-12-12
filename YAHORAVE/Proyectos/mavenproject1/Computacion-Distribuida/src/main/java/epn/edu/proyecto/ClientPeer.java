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
import java.io.FileNotFoundException;
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
    private String localIP;
    private String fileName;

    public ClientPeer(int port, String serverIP, File file, String accion) {
        this.port = port;
        this.serverIP = serverIP;
        this.file = file;
        this.accion = accion;
    }

    public ClientPeer(int port, String serverIP, String localIP,String accion) {
        this.port = port;
        this.serverIP = serverIP;
        this.accion = accion;
        this.localIP=localIP;
    }
     public ClientPeer(String fileName, int port, String serverIP , String accion) {
        this.port = port;
        this.serverIP = serverIP;
        this.fileName = fileName;
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
                    dataOut = new DataOutputStream(clientSocket.getOutputStream());
                    System.out.println("Actualizando....");
                    dataOut.writeUTF(localIP);
                    break;
                case "subir":
                    subirArchivo();
                    break;
                case "eliminar":
                    eliminarArchivos();
                    break;
            }
            dataOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
     private void eliminarArchivos() throws IOException{
        DataOutputStream dataOut = new DataOutputStream(clientSocket.getOutputStream());
        dataOut.writeUTF(fileName);
        
        
    }
}
