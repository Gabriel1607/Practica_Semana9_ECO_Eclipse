package view;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import events.OnMessage;
import model.Instruct;

public class UDPConnection extends Thread {

    private DatagramSocket socket;
    private Instruct instruct;
    private OnMessage observer;



    //Patr�n Observer
    public void setObserver (OnMessage observer) {
        this.observer = observer;
    }

    @Override
    public void run() {
        try {
            socket = new DatagramSocket(6968);

            while (true) {

                // Recibir mensaje
                byte[] buffer = new byte[100];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                // Esperando datagram
               System.out.println("Esperando datagram");
                socket.receive(packet);

                // Contructor de String para pasar de de bytes a string
                String msg = new String(packet.getData()).trim();
              System.out.println("Datagram recibido" + msg);

                //Deserializaci�n
                observer.onMessageReceived(msg);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void sendMessage(String msg) {
        new Thread(
                ()->{

                    try {
                        InetAddress ip = InetAddress.getByName("192.168.0.32");

                        //Para enviar tiene 4 parametros
                        //1.buffer, 2. tama�o de arreglo, 3. ip, 4.Puerto del otro peer
                        DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.getBytes().length, ip, 6969);
                        socket.send(packet);

                    } catch (UnknownHostException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
        ).start();

    }

}