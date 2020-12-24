package pantalla;

import javax.swing.*;
import java.util.LinkedList;

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
    public void introducirFondo(int[][] matriz){
        pixeles.matrizFondo=matriz;
    }

    public void actualizarCambios(LinkedList cambios){

        pixeles.matriz=pixeles.matrizFondo;

        if (cambios.size()>1) {
            for (int i = 0; i <= cambios.size() - 1; i++) {
                int[] pixel = (int[]) cambios.get(i);
                int px = pixel[0];
                int py = pixel[1];
                int pcolor = pixel[2];
                pixeles.matriz[px][py] = pcolor;
                refrescar(pixeles.matriz);

            }
        }
    }
    public int[][] crearMatriz() {
        int[][] matriz=new int[50][50];
        for (int i = 0; i <= 49; i++) {
            for (int j = 0; j <= 49; j++) {
                matriz[i][j]=0;

            }
        }return matriz;
    }
}




