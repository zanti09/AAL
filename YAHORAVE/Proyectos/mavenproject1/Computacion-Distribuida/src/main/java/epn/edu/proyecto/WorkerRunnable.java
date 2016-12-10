package epn.edu.proyecto;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
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
            String accion = dataIn.readUTF();
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

    private void actualizarArchivos() throws FileNotFoundException, IOException {
        File directorioPrincipal = new File("C:\\Computacion Distribuida");
//        DataOutputStream dataOut = new DataOutputStream(clientSocket.getOutputStream());
//        String size=String.valueOf(directorioPrincipal.listFiles().length);
//        dataOut.writeUTF(size);
//        System.out.println("size: "+size);
//        OutputStream out = null;
//        byte[] bytes = new byte[64 * 1024];
        List<String> lstDireccionesRed = new ArrayList<>();
        lstDireccionesRed.add("192.168.43.83");
        lstDireccionesRed.add("192.168.43.170");
        lstDireccionesRed.add("192.168.43.188");
        ClientPeer client;
        for (File file : directorioPrincipal.listFiles()) {
            for (String direccion : lstDireccionesRed) {
                client = new ClientPeer(8888, direccion, file, "subir");
                client.start();
            }
        }
//            dataOut = new DataOutputStream(clientSocket.getOutputStream());
//            dataOut.writeUTF(file.getName());
//            System.out.println("file.getName(): "+file.getName());
//            out = clientSocket.getOutputStream();
//
//            InputStream in = new FileInputStream(file);
//
//            int count;
//            while ((count = in.read(bytes)) > 0) {
//                out.write(bytes, 0, count);
//            }
//            out.flush();
//            in.close();

//        IOUtils.closeQuietly(out);
    }

    private void subirArchivo() throws IOException {
        DataInputStream dataIn = new DataInputStream(clientSocket.getInputStream());
        String fileName = dataIn.readUTF();
        InputStream in = clientSocket.getInputStream();
        try {
            FileOutputStream out = new FileOutputStream("C:\\Computacion Distribuida\\" + fileName);
            byte[] bytes = new byte[64 * 1024];

            int count;
            while ((count = in.read(bytes)) > 0) {
                out.write(bytes, 0, count);
                System.err.println(count);
            }
            out.close();
            ((IFilesManager) frame).updateFileAdded(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(in);
        }
    }
}
