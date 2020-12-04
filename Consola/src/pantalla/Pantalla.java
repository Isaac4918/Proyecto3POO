package pantalla;

import javax.swing.*;
import java.awt.*;

public class Pantalla extends JPanel {
    private JPanel contenido;


    public void paint(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.CYAN);
        g.drawRect(0,0,12,12);
        g.fillRect(12,0,12,12);

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(24,0,12,12);

    }
}
