package pantalla;

import javax.swing.*;

public class Ventana {
    Pantalla pixeles=new Pantalla();

    public Ventana() {
        JFrame frame=new JFrame("pantalla");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(pixeles);
        frame.setSize(600,600);
        frame.setVisible(true);
    }
    public void refrescar(int[][] matriz){
        pixeles.actualizarMatriz(matriz);

    }
}




