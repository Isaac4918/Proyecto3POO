package Tetris;


import pantalla.Pantalla;
import pantalla.Ventana;

import java.util.LinkedList;

public class Juego {
    public MatrisTetris Matriz50pxls=new MatrisTetris();
    Pantalla pantalla=new Pantalla();


    public int[][] matetriz=new int[10][20];
    public int[][] matrizAcumulados=new int[10][20];

    public void empezar(){
        this.crearMatriz();
        this.mostrarMatriz();
        CableApantalla cable=new CableApantalla();


        Pieza pieza=new Pieza(1);
        System.out.println("len : "+matetriz[0].length);

        insertarPieza(pieza);
        mostrarMatriz();
        cable.insertarMatriz(cable.ReescalarPixeles(matetriz),9);
        //Ventana ventana=new Ventana();
        //ventana.refrescar(cable.MatrizPantalla);
        cable.mostrarMatriz();

        /*
        mostrarMatriz();
    //AL MOVER LA PIEZA HAY QUE REVISARLA CON
        //rotarSprite(pieza);

        //moverSprite("D", pieza);
        tirarPieza(pieza);
*/
    }

    public boolean revisarFondo(Pieza pieza){
        int yBloqueInferior= (int) pieza.listaBloques().get(1);
        int xBloqueInferior= (int) pieza.listaBloques().get(0);
        int xBloqueSuperior= (int) pieza.listaBloques().get(0);
        //Busca de los bloques que componen a una pieza el que esté con el Y más cercano a 20


        for (int i=0;i<=7;i=i+2){
            int tempx= (int) pieza.listaBloques().get(i);
            int tempy= (int) pieza.listaBloques().get(i+1);

            if (tempy+1==20){
                matrizAcumulados[(int) pieza.listaBloques().get(0)][(int) pieza.listaBloques().get(1)]=1;
                matrizAcumulados[(int) pieza.listaBloques().get(2)][(int) pieza.listaBloques().get(3)]=1;
                matrizAcumulados[(int) pieza.listaBloques().get(4)][(int) pieza.listaBloques().get(5)]=1;
                matrizAcumulados[(int) pieza.listaBloques().get(6)][(int) pieza.listaBloques().get(7)]=1;
                revisarLinea();
                pieza.generarPieza();
                return true;
            }


        }
        if (this.revisarColision(pieza)) {
            for (int i = 0; i <= 7; i += 2) {
                int tempx = (int) pieza.listaBloques().get(i);
                int tempy = (int) pieza.listaBloques().get(i + 1);
                matrizAcumulados[tempx][tempy] = 1;
            }
            revisarLinea();
            pieza.generarPieza();
            return true;

        }
        //System.out.println("y inferior : "+yBloqueInferior);
        //System.out.println("Bloque x menor: "+xBloqueInferior);
        //System.out.println("Bloque x mayor: "+xBloqueSuperior);
        //System.out.println("Bloque y menor: "+yBloqueInferior);


        return false;
    }

    public boolean revisarColision(Pieza pieza){
        for (int i=0;i<=7;i+=2){
            int tempx= (int) pieza.listaBloques().get(i);
            int tempy= (int) pieza.listaBloques().get(i+1);


            if (matrizAcumulados[tempx][tempy+1]==1){
                return true;
            }

        }return false;

    }

    public void moverSprite(String direccion,Pieza pieza){
            matetriz[pieza.centro[0]][pieza.centro[1]]=0;
            matetriz[pieza.bloque1[0]][pieza.bloque1[1]]=0;
            matetriz[pieza.bloque2[0]][pieza.bloque2[1]]=0;
            matetriz[pieza.bloque3[0]][pieza.bloque3[1]]=0;

            pieza.moverPieza(direccion);

            matetriz[pieza.centro[0]][pieza.centro[1]]=pieza.forma;
            matetriz[pieza.bloque1[0]][pieza.bloque1[1]]=pieza.forma;
            matetriz[pieza.bloque2[0]][pieza.bloque2[1]]=pieza.forma;
            matetriz[pieza.bloque3[0]][pieza.bloque3[1]]=pieza.forma;


    }

    public void tirarPieza(Pieza pieza){

        for (int i=0;i<=20;i=i++){

            if (revisarFondo(pieza)==true){
                //moverSprite("A", pieza);
                i=25;
                break;
            }
            moverSprite("A", pieza);
            mostrarMatriz();

        }


    }
    public void botonIzquierdo(Pieza pieza){
        for (int i=0;i<=7;i=i+2) {
            int tempx = (int) pieza.listaBloques().get(i);
            int tempy = (int) pieza.listaBloques().get(i + 1);
            if (tempx-1==-1){
                return;
            }
            if(matrizAcumulados[tempx-1][tempy]==1){
                return;
            }
        }moverSprite("I", pieza);

    }

    public void botonDerecho(Pieza pieza){
        for (int i=0;i<=7;i=i+2) {
            int tempx = (int) pieza.listaBloques().get(i);
            int tempy = (int) pieza.listaBloques().get(i + 1);

            System.out.println("x: "+tempx);
            if (tempx+1==10){
                System.out.println("Pegó con cerca");
                return;
            }
            if(matrizAcumulados[tempx+1][tempy]==1){
                System.out.println("Pegó con cerca");
                return;
            }

        }moverSprite("D", pieza);
        mostrarMatriz();
    }

    public void rotarSprite(Pieza pieza){
        matetriz[pieza.centro[0]][pieza.centro[1]]=0;
        matetriz[pieza.bloque1[0]][pieza.bloque1[1]]=0;
        matetriz[pieza.bloque2[0]][pieza.bloque2[1]]=0;
        matetriz[pieza.bloque3[0]][pieza.bloque3[1]]=0;

        pieza.rotarPieza(pieza.forma);

        matetriz[pieza.centro[0]][pieza.centro[1]]=pieza.forma;
        matetriz[pieza.bloque1[0]][pieza.bloque1[1]]=pieza.forma;
        matetriz[pieza.bloque2[0]][pieza.bloque2[1]]=pieza.forma;
        matetriz[pieza.bloque3[0]][pieza.bloque3[1]]=pieza.forma;

    }

    public void insertarPieza(Pieza pieza){
        //System.out.println("centro : "+pieza.bloque2[0]);


        matetriz[pieza.centro[0]][pieza.centro[1]]=pieza.forma;
        matetriz[pieza.bloque1[0]][pieza.bloque1[1]]=pieza.forma;
        matetriz[pieza.bloque2[0]][pieza.bloque2[1]]=pieza.forma;
        matetriz[pieza.bloque3[0]][pieza.bloque3[1]]=pieza.forma;


    }

    public void crearMatriz(){
        for (int i=0; i <= 19; i++){
            for (int j=0; j <= 9;j++){
                matetriz[j][i]=0;
            }
        }
        for (int i=0; i <= 19; i++){
            for (int j=0; j <= 9;j++){
                matrizAcumulados[j][i]=0;
            }
        }
    }

    public void revisarLinea(){
        for (int i=0;i<=19; i++){
            int linea=0;
            for (int j=0;j<=9; j++){

                if (matrizAcumulados[j][i]==1){
                    linea+=1;
                    }
                }
                if (linea==10){
                    System.out.println("linea completa: "+i);
                    eliminarLinea(i);
                    }
        }
    }

    public void eliminarLinea(int linea){

        for (int j=linea; j>= 1; j--) {
            for (int i = 0; i <= 9; i++) {
                matetriz[i][j]=matetriz[i][j-1];

                if (j == 0) {
                    matetriz[i][j + 1] = 0;
                    }
                }
            }
        }

    public void mostrarMatriz(){
        System.out.println("==================================================");
        String linea="";
        for (int i=0; i <= 19; i++){
            if (i<=9){
                linea+=""+i+"   ";
            }
            if(i>9){
                linea+=""+i+"  ";
            }
            for (int j=0; j <= 9;j++){

                linea+=matetriz[j][i]+"   ";
                if (j==9){
                    System.out.println(linea);
                    linea="";
                }
            }linea="";
        }

    }


    public static void main(String[] args) {
    Juego game=new Juego();
        game.empezar();
    }}
