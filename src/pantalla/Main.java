package pantalla;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
    JFrame frame=new JFrame("pantalla");
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    Pantalla pixel=new Pantalla();
    frame.add(pixel);
    frame.setSize(600,600);
    frame.setVisible(true);
    }
}
