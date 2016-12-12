/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.proyecto;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author santi
 */
public class FileSharingFrame1 extends javax.swing.JFrame implements IFilesManager {

    private JFileChooser jChooser;
    private DefaultListModel<String> modelList = new DefaultListModel<>();
    private static final Integer PORT = 8888;
    private List<String> lstDireccionesRed;
    private ClientPeer client;
    
    
    /**
     * Creates new form FileSharingFrame
     */
    public FileSharingFrame1() throws UnknownHostException {
            
        initComponents();
        this.setTitle("BookCase");
       //logo();
        jlsArchivos.setModel(modelList);
        lstDireccionesRed = new ArrayList<>();
        lstDireccionesRed.add("192.168.43.253");
        lstDireccionesRed.add("192.168.43.133");
        lstDireccionesRed.add("192.168.43.188");
//        llenarDireccionesRed();
        ServerPeer server = new ServerPeer(PORT, this);
        server.start();
        for (String direccion : lstDireccionesRed) {
            client = new ClientPeer(PORT, direccion, "192.168.43.133", "actualizar");
            client.start();
        }
        
        for(File file:new File("C:\\Computacion Distribuida").listFiles()){
            updateFileAdded(file.getName());
        }
    }

    //public Image getIconImage(){
    //    Image retValue= Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("book.png"));
     //   return retValue;
    //}
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    
        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlsArchivos = new javax.swing.JList<String>();
        jLabel1 = new javax.swing.JLabel();
        subirArchivo = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());

        jPanel1.setBackground(new java.awt.Color(96, 125, 139));

        jlsArchivos.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jScrollPane1.setViewportView(jlsArchivos);

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Archivos");

        subirArchivo.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        subirArchivo.setText("Subir Archivo");
        subirArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subirArchivoActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jButton1.setText("Eliminar Archivo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(subirArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subirArchivo)
                    .addComponent(jButton1))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void subirArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subirArchivoActionPerformed
        jChooser = new JFileChooser();
        jChooser.setDialogTitle("Seleccionar Carpeta");
        File selectdFile;
        if (jChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            selectdFile = jChooser.getSelectedFile();
            for (String direccion : lstDireccionesRed) {
                client = new ClientPeer(PORT, direccion, selectdFile, "subir");
                client.start();
            }
        }
    }//GEN-LAST:event_subirArchivoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        for (String direccion : lstDireccionesRed) {
                    for (String fileName : jlsArchivos.getSelectedValuesList()) {
                        client = new ClientPeer(fileName,PORT, direccion, "eliminar");
                        client.start();
                        
                    }
        }               
     
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FileSharingFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FileSharingFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FileSharingFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FileSharingFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FileSharingFrame1().setVisible(true);
                } catch (UnknownHostException ex) {
                    Logger.getLogger(FileSharingFrame1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> jlsArchivos;
    private javax.swing.JButton subirArchivo;
    // End of variables declaration//GEN-END:variables

    @Override
    public void updateFileAdded(String fileName) {
        if (!modelList.contains(fileName)) {
            modelList.addElement(fileName);
        }
    }

    @Override
    public void updateFileDeleted(String fileName) {
        if (modelList.contains(fileName)) {
            modelList.removeElement(fileName);
        }
    }
    
    

    public void llenarDireccionesRed() {
        for (int i = 1; i < 255; i++) {
            lstDireccionesRed.add("192.168.43." + i);
        }
    }
}
