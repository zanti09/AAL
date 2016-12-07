package epn.edu.proyecto;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.apache.commons.io.FileUtils;

public class WorkerRunnable implements Runnable {

    protected Socket clientSocket;
    private JFrame frame;

    public WorkerRunnable(Socket clientSocket, JFrame frame) {
        this.clientSocket = clientSocket;
        this.frame=frame;
    }

    @Override
    public void run() {
        try {
            InputStream o = clientSocket.getInputStream();
            ObjectInput s = new ObjectInputStream(o);
            File file = (File) s.readObject();
            File newFile = new File("C:\\Computacion Distribuida\\" + file.getName());
            try {
                FileUtils.copyFile(file, newFile);
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
