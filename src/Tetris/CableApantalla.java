package Tetris;

public class CableApantalla<matrizGrande> {
    public int[][] MatrizPantalla=new int[50][50];

    public CableApantalla() {
        this.crearMatriz();
        this.mostrarMatriz();
    }


    public void mostrarMatriz(){
        System.out.println("==================================================");
        String linea="";
        for (int i=0; i <= 49; i++){
            if (i<=9){
                linea+=""+i+"   ";
            }
            if(i>9){
                linea+=""+i+"  ";
            }
            for (int j=0; j <= 49;j++){

                linea+=MatrizPantalla[j][i]+"   ";
                if (j==49){
                    System.out.println(linea);
                    linea="";
                }
            }linea="";
        }
    }

    public void crearMatriz(){
        for (int i=0; i <= 49; i++){
            for (int j=0; j <= 49;j++){
                MatrizPantalla[j][i]=14;
            }
        }
    }
    public void insertarMatriz(int [][] matriz,int xInicial) {

        for (int i = 0; i <= 19; i++) {
            for (int j = 0; j <= 39; j++) {
                MatrizPantalla[xInicial+i][j]=matriz[i][j];
            }

        }
    }
    public int[][] ReescalarPixeles(int [][] matriz){
        int x=matriz.length*2;
        int y=matriz[0].length*2;
        int [][]matrizGrande=new int[x][y];
        for (int i=0;i<=19; i++){
            for (int j=0;j<=9; j++){
                int pixel=matriz[j][i];

                matrizGrande[j*2][i*2]=pixel;
                matrizGrande[j*2+1][i*2]=pixel;
                matrizGrande[j*2][i*2+1]=pixel;
                matrizGrande[j*2+1][i*2+1]=pixel;

            }

        }return matrizGrande;
    }
}
