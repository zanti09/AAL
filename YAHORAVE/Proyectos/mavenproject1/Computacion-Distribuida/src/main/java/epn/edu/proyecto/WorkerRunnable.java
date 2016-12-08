package epn.edu.proyecto;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.apache.commons.io.IOUtils;

public class WorkerRunnable implements Runnable {

    protected Socket clientSocket;
    private JFrame frame;

    public WorkerRunnable(Socket clientSocket, JFrame frame) {
        this.clientSocket = clientSocket;
        this.frame = frame;
    }

    @Override
    public void run() {
        try {
            DataInputStream dataIn = new DataInputStream(clientSocket.getInputStream());
            String fileName=dataIn.readUTF();
            InputStream in = clientSocket.getInputStream();
            try {
                FileOutputStream out = new FileOutputStream("C:\\Computacion Distribuida\\" + fileName);
                byte[] bytes = new byte[64 * 1024];

                int count;
                while ((count = in.read(bytes)) > 0) {
                    out.write(bytes, 0, count);
                    System.err.println(count);
                }
                System.err.println("despues");
                out.close();
                System.err.println("mucho despues");
                ((IFilesManager) frame).updateFileAdded(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally{
                IOUtils.closeQuietly(in);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
}
