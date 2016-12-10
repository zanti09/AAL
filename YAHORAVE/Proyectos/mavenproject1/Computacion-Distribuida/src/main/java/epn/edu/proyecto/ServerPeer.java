package epn.edu.proyecto;

import java.io.*;
import java.net.*;
import javax.swing.JFrame;

public class ServerPeer extends Thread {

    private ServerSocket ser;
    private int port;
    private JFrame frame;

    public ServerPeer(int port, JFrame frame) {
        this.port = port;
        this.frame = frame;
    }

    @Override
    public void run() {
        try {
            File carpetaCompartida = new File("C:\\Computacion Distribuida");
            carpetaCompartida.mkdir();
            for(File file :carpetaCompartida.listFiles()){
                file.delete();
            }
            ser = new ServerSocket(port);
            while (true) {
                Socket clientSocket = ser.accept();
                new Thread(new WorkerRunnable(clientSocket, frame)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}
