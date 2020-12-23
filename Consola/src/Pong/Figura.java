package Pong;

public class Figura{

    int posX;
    int posY;
    int largo;
    int movimientoX = 1;
    int movimientoY = 0;
    int[][] posiciones;
    int[][] tablero;

    public Figura(int[][] tablero, int posX, int posY, int largo) {
        this.tablero = tablero;
        this.posX = posX;
        this.posY = posY;
        this.largo = largo;

    }

    public void crearSprite(){
        this.posiciones = new int[this.largo*2][2];

        int cont = 0;
        int pos = 0;

        while (cont < largo){
            posiciones[pos] = new int[]{posX, posY + cont};
            posiciones[pos+1] = new int[]{posX+1, posY+ cont};
            pos+=2;
            cont++;
        }

    }

    public void colocar(){
        crearSprite();
        for (int j = posX; j < posX+2; j++){
            for (int i = posY; i < posY+largo; i++){
                tablero[i][j] = 1;
            }
        }

        for (int[] posicion : posiciones) {
            tablero[posicion[1]][posicion[0]] = 10;
        }
    }

    public void mover(int posXNueva, int posYNueva){
        this.posX = posXNueva;
        this.posY = posYNueva;
    }

}
