package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client implements Runnable{

    int PORT;
    public String mensaje;

    public Client(int PORT) {
        this.PORT = PORT;
        Thread t2 = new Thread(this);
        t2.start();
    }

    @Override
    public void run() {
        boolean flag = true;
        while (flag){
            try {
                Socket socket = new Socket("localhost", this.PORT);

                flag = false;

                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());

                while (true) {
                    outputStream.writeUTF(this.mensaje);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
