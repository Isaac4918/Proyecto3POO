package Pong;

import pantalla.Ventana;

public class Main {

    public static void main(String[] args) {
        Tablero T1 = new Tablero();
        Ventana ventana1 = new Ventana(T1.matriz);
        while (true){
            T1.moverBola();
            ventana1.refrescar(T1.matriz);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
