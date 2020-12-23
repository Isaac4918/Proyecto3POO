package pantalla;

import javax.swing.*;

public class Ventana {
    Pantalla pixeles=new Pantalla(crearMatriz());

    public Ventana() {
        JFrame frame=new JFrame("pantalla");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(pixeles);
        frame.setSize(616,639);
        frame.setVisible(true);
    }
    public void refrescar(int[][] matriz){
        pixeles.actualizarMatriz(matriz);

    }
    public int[][] crearMatriz() {
        int[][] matriz=new int[50][50];
        for (int i = 0; i <= 49; i++) {
            for (int j = 0; j <= 49; j++) {
                matriz[i][j]=4;

            }
        }return matriz;
    }
}




