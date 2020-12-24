package pantalla;

import javax.swing.*;
import java.awt.*;

public class Pantalla extends JPanel {
    private JPanel contenido;
    public int[][] matriz=new int[50][50];
    public int[][] matrizFondo=new int[50][50];

    public Pantalla(int[][] matriz) {
        this.matriz = matriz;
    }


    public void paint(Graphics g){

        super.paintComponent(g);

        for (int i=0;i<=49;i++){
            for (int j=0;j<=49;j++){
                g.setColor(codificarColor(matriz[i][j]));
                g.fillRect(i*12,j*12,12,12);
            }
        }


    }


    public void actualizarMatriz(int[][] matrizNueva){
        matriz=matrizNueva;
        repaint();

    }
    public Color codificarColor(int codigo) {
        switch (codigo) {
            case 0:
                return Color.BLACK;
            case 1:
                return Color.BLUE;
            case 2:
                return Color.CYAN;
            case 3:
                return Color.DARK_GRAY;
            case 4:
                return Color.LIGHT_GRAY;
            case 5:
                return Color.GREEN;
            case 6:
                return Color.MAGENTA;
            case 7:
                return Color.ORANGE;
            case 8:
                return Color.PINK;
            case 9:
                return Color.RED;
            case 10:
                return Color.WHITE;
            case 11:
                return Color.YELLOW;
            case 12:
                return Color.decode("#808000");
            case 13:
                return Color.decode("#752C00");
            case 14:
                return Color.decode("#000080");
            case 15:
                return Color.decode("#800080");
            default:
                throw new IllegalStateException("Unexpected value: " + codigo);
        }

    }
}