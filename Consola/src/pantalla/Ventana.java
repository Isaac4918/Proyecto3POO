package pantalla;

import javax.swing.*;

public class Ventana {
    int[][] matriz;
    Pantalla pixeles;

    public Ventana(int[][] matriz) {
        this.matriz = matriz;
        this.pixeles = new Pantalla(matriz);
        JFrame frame = new JFrame("pantalla");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(pixeles);
        frame.setSize(616,639);
        frame.setVisible(true);
        frame.setResizable(false);
    }
    public void refrescar(int[][] matriz){
        pixeles.actualizarMatriz(matriz);
    }
}
