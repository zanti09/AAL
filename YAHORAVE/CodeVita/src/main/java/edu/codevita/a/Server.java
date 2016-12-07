/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.codevita.a;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author santi
 */
public class Server {

    final int PUERTO = 5000;
    ServerSocket sc;
    Socket so;
    DataOutputStream salida;
    String mensajeRecibido ;
//SERVIDOR

    public void initServer() {
        BufferedReader entrada;
        try {
            sc = new ServerSocket(PUERTO);/* crea socket servidor que escuchara en puerto 5000*/
            so = new Socket();
            System.out.println("Esperando una conexión:");
            so = sc.accept();
//Inicia el socket, ahora esta esperando una conexión por parte del cliente
            System.out.println("Un cliente se ha conectado.");
//Canales de entrada y salida de datos
            entrada = new BufferedReader(new InputStreamReader(so.getInputStream()));
            salida = new DataOutputStream(so.getOutputStream());
            System.out.println("Confirmando conexion al cliente....");
            salida.writeUTF("Conexión exitosa...n envia un mensaje :D");
//Recepcion de mensaje
            mensajeRecibido = entrada.readLine();
            System.out.println(mensajeRecibido);
            salida.writeUTF("Se recibio tu mensaje.n Terminando conexion...");
            salida.writeUTF("Gracias por conectarte, adios!");
            System.out.println("Cerrando conexión...");
            sc.close();//Aqui se cierra la conexión con el cliente
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        Server server= new Server();
        server.initServer();
        
    }

}
