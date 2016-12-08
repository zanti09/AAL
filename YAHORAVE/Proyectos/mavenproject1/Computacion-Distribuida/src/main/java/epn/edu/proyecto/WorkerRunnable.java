package epn.edu.proyecto;

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
            InputStream in = clientSocket.getInputStream();
            ObjectInput s = new ObjectInputStream(in);
            File file = (File) s.readObject();
            try {
                FileOutputStream out = new FileOutputStream("C:\\Computacion Distribuida\\" + file.getName());
                byte[] bytes = new byte[64 * 1024];

                int count;
                while ((count = in.read(bytes)) > 0) {
                    out.write(bytes, 0, count);
                }
                out.close();
                in.close();
                ((IFilesManager) frame).updateFileAdded(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(WorkerRunnable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
