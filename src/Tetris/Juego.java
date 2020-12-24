package Tetris;


import Pong.Figura;
import control.VentanaControl;
import pantalla.Ventana;
import sockets.Client;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class Juego extends Thread {
    CableApantalla cable=new CableApantalla();
    Pieza pieza=new Pieza(1);
    VentanaControl control=new VentanaControl();
    public LinkedList listaCambios=new LinkedList();
    public int[][] matetriz=new int[10][20];
    public int[][] matrizAcumulados=new int[10][20];
    int fondo1=0;
    int fondo2=10;
    String jsonStr;
    Client client = new Client(9999);

    public void empezar() throws InterruptedException {
        this.crearMatriz();
        cable.insertarMatriz(cable.ReescalarPixeles(matetriz), 2);
        cable.mostrarMatriz();
        insertarPieza(pieza);

        int boton=0;
        while(true){
            //System.out.println("boton: "+boton);
            if (boton!=control.tecla){
                boton=control.tecla;
                //System.out.println("boton: "+boton);
                if (boton==37){
                    System.out.println("BOTON IZQUIERDO");
                    botonIzquierdo(pieza);
                    generarCambios();
                }
                if (boton==39){
                    System.out.println("BOTON DERECHO");
                    botonDerecho(pieza);
                    generarCambios();
                }
                if (boton==38){
                    rotarSprite(pieza);
                    generarCambios();
                }
                if (boton==32){
                    rotarSprite(pieza);
                    generarCambios();
                }
                if (boton==40){
                    tirarPieza(pieza);
                    generarCambios();
                }
            }


        }
    }

    public void gravity() throws InterruptedException {
        while(true){
            moverSprite("A", pieza);
            revisarFondo(pieza);
            generarCambios();
            TimeUnit.SECONDS.sleep(1);

        }

        }


    @Override
    public void run() {
        new Thread(() -> {
            try {
                this.gravity();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                this.empezar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

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
            matetriz[pieza.centro[0]][pieza.centro[1]]=fondo2;
            matetriz[pieza.bloque1[0]][pieza.bloque1[1]]=fondo2;
            matetriz[pieza.bloque2[0]][pieza.bloque2[1]]=fondo2;
            matetriz[pieza.bloque3[0]][pieza.bloque3[1]]=fondo2;

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
            //mostrarMatriz();

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

            if (tempx+1==10){
                return;
            }
            if(matrizAcumulados[tempx+1][tempy]==1){
                return;
            }

        }moverSprite("D", pieza);
        //mostrarMatriz();
    }

    public void rotarSprite(Pieza pieza){
        matetriz[pieza.centro[0]][pieza.centro[1]]=fondo2;
        matetriz[pieza.bloque1[0]][pieza.bloque1[1]]=fondo2;
        matetriz[pieza.bloque2[0]][pieza.bloque2[1]]=fondo2;
        matetriz[pieza.bloque3[0]][pieza.bloque3[1]]=fondo2;

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
                matetriz[j][i]=fondo2;
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
                    //System.out.println("linea completa: "+i);
                    linea=0;
                    eliminarLinea(i);
                    }
        }
    }

    public void eliminarLinea(int linea){

        for (int j=linea; j>= 1; j--) {
            for (int i = 0; i <= 9; i++) {
                matetriz[i][j]=matetriz[i][j-1];
                matrizAcumulados[i][j]=matrizAcumulados[i][j-1];

                if (j == 0) {
                    matetriz[i][j + 1] = 0;
                    matrizAcumulados[i][j + 1] = 0;
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

    public void generarCambios(){
        cable.insertarMatriz(cable.ReescalarPixeles(matetriz), 2);
        System.out.println("generando cambios");
        for (int i=0;i<=49;i++){
            for (int j=0;j<=49;j++){
                int color=cable.MatrizPantalla[i][j];
                if (color!=14){
                        int[] pixel = new int[3];
                        pixel[0] = i;
                        pixel[1] = j;

                        if (color==1){
                            pixel[2]=11;
                        }
                        if (color==2){
                            pixel[2]=9;
                        }
                        if (color==3){
                            pixel[2]=6;
                        }
                        if (color==4){
                            pixel[2]=2;
                        }else{
                            pixel[2]=color;
                        }
                        //System.out.println("color : " + pixel[2] + " en x :" + pixel[0] + " y " + pixel[1]);
                        listaCambios.add(pixel);
                }
            }
        }


        infoAJson(listaCambios);
        client.mensaje = this.jsonStr;

        listaCambios=new LinkedList();
    }

    public void infoAJson(LinkedList lista){
        this.jsonStr = "{\"posiciones\":[";
        for(int a = 0; a < lista.size(); a++){
            int[] tmp = (int[]) lista.get(a);
            jsonStr += "{";
            int posx = tmp[0];
            int posy = tmp[1];
            int color = tmp[2];
            jsonStr += "\"posx\":" + String.valueOf(posx) + ",";

            if(a < lista.size()-1){
                jsonStr += "\"posy\":" + String.valueOf(posy) + ",\"color\":" + String.valueOf(color) + "},";
            }else if(a == lista.size()-1){
                jsonStr += "\"posy\":" + String.valueOf(posy) + ",\"color\":" + String.valueOf(color) + "}";
            }
        }
        jsonStr += "]}";
    }



    public static void main(String[] args) throws InterruptedException {
        Juego game=new Juego();
            game.run();

    }


}
