package control;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

    public class VentanaControl extends JFrame {
        private JPanel contenido=new JPanel();
        public int tecla=0;

        public VentanaControl() throws HeadlessException {
            JTextField campoTexto=new JTextField();
            campoTexto.setBounds(40,300,200,30);

            KeyListener escuharTeclado=new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {


                }

                @Override
                public void keyPressed(KeyEvent e) {
                    tecla=e.getKeyCode();
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    tecla=0;


                }
            };
            this.addKeyListener(escuharTeclado);
            //add(campoTexto);
            add(contenido);

            setSize(400,400);
            setLayout(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);


        }
    }


