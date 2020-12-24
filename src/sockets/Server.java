package sockets;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{

    int PORT;
    public String mensaje;

    public Server(int PORT) {
        this.PORT = PORT;
        Thread T1 = new Thread(this);
        T1.start();
    }

    @Override
    public void run() {
        boolean flag = true;
        while (flag){
            try {
                ServerSocket serverSocket = new ServerSocket(this.PORT);
                Socket socket = serverSocket.accept();

                flag = false;

                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());

                while (true) {
                    this.mensaje = inputStream.readUTF();
                    //System.out.println(this.mensaje);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
