/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dns.proyectodns.view;

import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;

/**
 *
 * @author cristobalcastrillonbalcazar
 */
public class UDPServer {
    public static void main(String[] a) throws Exception {
        
        int serverPortNumber = 9999; //TODO: Averiguar qué número de puerto debe usar el SERVIDOR
        
        DatagramSocket serverSocket = new DatagramSocket(serverPortNumber);
        
        int tamanoQuery = 1024; //Determinar de qué tamaño debe ser el paquete que contiene el query, teniendo en cuenta qué se envía (tal vez solo una URL).
        byte[] query = new byte[tamanoQuery];
        
        DatagramPacket serverReceivedPacket = new DatagramPacket(query, query.length);
        serverSocket.receive(serverReceivedPacket);
        
        String url = new String(serverReceivedPacket.getData());
        //Aquí van las instrucciones para buscar en BBDD la equivalencia de la URL en dirección IP (y demás campos del registro tipo A)...
        
        InetAddress client_ip_address = InetAddress.getLocalHost(); //Cómo determinar el IP Address del cliente que hizo el query? Se puede enviar en el mismo paquete que contiene el query?
        int clientPortNumber = 9999; //Cómo determinar el número de puerto sobre el cual el cliente va a estar a la escucha? Se puede enviar en el paquete de query?
        //La siguientes líneas simulan un resultado:
        String ipResuelto = new String("192.158.1.38");
        byte[] ipResByteArray = ipResuelto.getBytes();
        
        DatagramPacket queryResponse = new DatagramPacket(ipResByteArray, ipResByteArray.length, client_ip_address, clientPortNumber);
        serverSocket.send(queryResponse); //Se puede (o se debe) usar el mismo socket para recibir y enviar paquetes? Es escalable?
    }
}
