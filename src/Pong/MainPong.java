package Pong;

import sockets.Client;

import java.io.IOException;

public class MainPong {

    public static void main(String[] args) throws IOException {
        Tablero T1 = new Tablero();
        Client client = new Client(9999);


        while (true){
            T1.moverBola();
            T1.infoAJson();
            client.mensaje = T1.jsonStr;

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
