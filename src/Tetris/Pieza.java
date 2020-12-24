package Tetris;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class Pieza {
    public int[] centro=new int[2];
    public int[] bloque1=new int[2];
    public int[] bloque2=new int[2];
    public int[] bloque3=new int[2];
    public int forma;
    public int caso;


    public Pieza(int forma) {
        this.centro = centro;
        this.forma = forma;
        this.generarPieza();

    }
    public LinkedList listaBloques(){
        LinkedList bloques=new LinkedList();
        bloques.add(centro[0]);
        bloques.add(centro[1]);
        bloques.add(bloque1[0]);
        bloques.add(bloque1[1]);
        bloques.add(bloque2[0]);
        bloques.add(bloque2[1]);
        bloques.add(bloque3[0]);
        bloques.add(bloque3[1]);

        return bloques;
    }
    public void actualizarPieza(){
        // LETRA O ====================
        if (forma==1){
            bloque1[0]=centro[0]-1;  //X-1
            bloque1[1]=centro[1];    //x

            bloque2[0]=centro[0]-1;  //X-1
            bloque2[1]=centro[1]-1;  //Y-1

            bloque3[0]=centro[0];    //X
            bloque3[1]=centro[1]-1;
        }
        // LETRA S  ====================
        if (forma==2){
            if (caso==1 || caso==3){//caso 2 s
                bloque1[0]=centro[0]-1;  //X-1
                bloque1[1]=centro[1];    //x

                bloque2[0]=centro[0]-1;  //X-1
                bloque2[1]=centro[1]-1;  //Y-1

                bloque3[0]=centro[0];    //X
                bloque3[1]=centro[1]+1;  //Y+1
            }else{
                bloque1[0]=centro[0]-1;
                bloque1[1]=centro[1];

                bloque2[0]=centro[0];
                bloque2[1]=centro[1]-1;

                bloque3[0]=centro[0]+1;
                bloque3[1]=centro[1]-1;
            }

        }
        // LETRA T=====================
        if (forma==3){
            if (caso==1) {
                bloque1[0] = centro[0]-1;
                bloque1[1] = centro[1];

                bloque2[0] = centro[0];
                bloque2[1] = centro[1]-1;

                bloque3[0] = centro[0]+1;
                bloque3[1] = centro[1];
            }
            if (caso==2) {
                bloque1[0] = centro[0];
                bloque1[1] = centro[1]-1;

                bloque2[0] = centro[0];
                bloque2[1] = centro[1]+1;

                bloque3[0] = centro[0]+1;
                bloque3[1] = centro[1];
            }
            if (caso==3) {
                bloque1[0] = centro[0]+1;
                bloque1[1] = centro[1];

                bloque2[0] = centro[0]-1;
                bloque2[1] = centro[1];

                bloque3[0] = centro[0];
                bloque3[1] = centro[1]+1;
            }
            if (caso==4) {
                bloque1[0] = centro[0];
                bloque1[1] = centro[1]-1;

                bloque2[0] = centro[0];
                bloque2[1] = centro[1]+1;

                bloque3[0] = centro[0]-1;
                bloque3[1] = centro[1];
            }

        }
        // LETRA I=======================
        if (forma==4){
            if (caso==1 || caso==3){//caso 2 s
                bloque1[0]=centro[0];  //X-1
                bloque1[1]=centro[1]-1;    //x

                bloque2[0]=centro[0];  //X
                bloque2[1]=centro[1]+1;  //Y+1

                bloque3[0]=centro[0];    //X
                bloque3[1]=centro[1]+2;  //Y+2
            }else{
                bloque1[0]=centro[0]+1;
                bloque1[1]=centro[1];

                bloque2[0]=centro[0]-1;
                bloque2[1]=centro[1];

                bloque3[0]=centro[0]-2;
                bloque3[1]=centro[1];
            }

        }
    }

    public void moverPieza(String direccion){
        if (direccion=="A"){

            centro[1]=centro[1]+1;
            this.actualizarPieza();
        }

        if (direccion=="I"){
            if (centro[0]-1!=-1){
                centro[0]=centro[0]-1;
                this.actualizarPieza();
            }
        }

        if (direccion=="D"){
            if (centro[0]+1!=19){
                centro[0]=centro[0]+1;
                this.actualizarPieza();
            }
        }

    }
    public void rotarPieza(int forma){
        System.out.println("rotando en x:  "+centro[0]);
        if (forma==4){
            if (caso==1 || caso==3){
                if (centro[0]==9){
                    centro[0]=8;
                    caso = 1;
                    this.actualizarPieza();
                }
                if (centro[0]<2){
                    centro[0]=2;
                    caso = 1;
                    this.actualizarPieza();
                }
            }
        }
        if (forma==2){
            if (caso==1 || caso==3){
                if (centro[0]==9){
                    centro[0]=8;
                    caso = 1;
                    this.actualizarPieza();
                }
            }
        }
        if (forma==3){
            if (caso==2){
                if (centro[0]==0){
                    centro[0]=1;
                    caso = 3;
                    this.actualizarPieza();
                }
            }
            if (caso==4){
                if (centro[0]==9){
                    centro[0]=8;
                    caso = 1;
                    this.actualizarPieza();
                }
            }
        }
        if (caso==4){
            caso=1;
            this.actualizarPieza();
        }else {
            caso += 1;
            this.actualizarPieza();
        }
    }

    public void generarPieza(){
            centro[0]=4;
            centro[1]=1;
            caso=1;
            int randomNum = ThreadLocalRandom.current().nextInt(1, 5);
            forma=randomNum;
            this.actualizarPieza();


    }

}
