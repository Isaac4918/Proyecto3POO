package control;

import sockets.Client;

public class MainControl {
    public static void main(String[] args) {
        VentanaControl vc = new VentanaControl();
        Client client = new Client(9999);

        while (true){
            vc.convertirInfo(vc.tecla);
        }
    }
}
