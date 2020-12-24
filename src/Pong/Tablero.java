package Pong;


public class Tablero {

    public int[][] matriz = new int[50][50];
    String jsonStr;
    Figura bolaPong = new Figura(matriz, 23,23, 2);
    Figura barra1 = new Figura(matriz, 0, 30, 12);
    Figura barra2 = new Figura(matriz, 48, 18, 12);
    Figura[] figuras = {bolaPong, barra1, barra2};

    public Tablero() {
        barra1.movimientoX = 0;
        barra2.movimientoX = 0;
        barra1.movimientoY = 1;
        barra2.movimientoY = 1;
        dibujar();
    }


    //String cadenaJson = "[{\"nombre\":\"Maggie\",\"edad\":3},{\"nombre\":\"Snowball\",\"edad\":1}];

    public void infoAJson(){
        this.jsonStr = "{\"posiciones\":[";
        for(int a = 0; a < this.figuras.length; a++){
            Figura figura = this.figuras[a];
            for(int b = 0 ; b < figura.posiciones.length; b++){
                int[] tmp = figura.posiciones[b];
                jsonStr += "{";
                int posx = tmp[0];
                int posy = tmp[1];
                jsonStr += "\"posx\":" + String.valueOf(posx) + ",";
                if(a < this.figuras.length-1){
                    jsonStr += "\"posy\":" + String.valueOf(posy) + ",\"color\":10},";
                }else if(a == this.figuras.length-1){
                    if(b < figura.posiciones.length-1){
                        jsonStr += "\"posy\":" + String.valueOf(posy) + ",\"color\":10},";
                    }
                    else {
                        jsonStr += "\"posy\":" + String.valueOf(posy) + ",\"color\":10}";
                    }
                }
            }
        }

        jsonStr += "]}";
    }


    public void rellenar(){
        for(int i = 0; i < 50; i++){
            for(int j = 0; j< 50; j++){
                this.matriz[i][j] = 1;
            }
        }
    }

    public void mostrar(){
        for(int i = 0; i < 50; i++){
            String linea = "";
            for(int j = 0; j< 50; j++){
                linea+= this.matriz[i][j] + " ";
            }
            System.out.println(linea);
        }
    }

    public void dibujar(){
        rellenar();
        barra1.colocar();
        barra2.colocar();
        bolaPong.colocar();
    }


    public void moverBola(){
        colisionesBola();
        /*colisionesBarra(barra1);
        colisionesBarra(barra2);
        this.barra1.mover(this.barra1.posX, this.barra1.posY + this.barra1.movimientoY);
        this.barra2.mover(this.barra2.posX, this.barra2.posY + this.barra2.movimientoY);*/
        this.bolaPong.mover(this.bolaPong.posX + this.bolaPong.movimientoX, this.bolaPong.posY + this.bolaPong.movimientoY);
        dibujar();
    }

    public void colisionesBarra(Figura barra){
        if(barra.posY <= 0){
            barra.movimientoY *= -1;
        }
        else if(barra.posY >= 38){
            barra.movimientoY *= -1;
        }
    }

    public void colisionesBola(){
        if(this.bolaPong.posX <= 0){
            this.bolaPong.mover(23,23);
            this.bolaPong.movimientoX *= -1;
            this.bolaPong.movimientoY = 0;
            this.barra1.mover(this.barra1.posX, 18);
            this.barra2.mover(this.barra2.posX, 18);
        }
        else if(this.bolaPong.posX >= 48){
            this.bolaPong.mover(23,23);
            this.bolaPong.movimientoX *= -1;
            this.bolaPong.movimientoY = 0;
            this.barra1.mover(this.barra1.posX, 18);
            this.barra2.mover(this.barra2.posX, 18);
        }
        else if(this.bolaPong.posY <= 0){
            this.bolaPong.movimientoY *= -1;
        }
        else if(this.bolaPong.posY >= 48){
            this.bolaPong.movimientoY *= -1;
        }
        else if(this.bolaPong.posX == this.barra1.posX + 2){
            if(this.bolaPong.posY >= this.barra1.posY && this.bolaPong.posY < this.barra1.posY+4){
                this.bolaPong.movimientoX *= -1;
                this.bolaPong.movimientoY = -1;
            }
            else if(this.bolaPong.posY >= this.barra1.posY+4 && this.bolaPong.posY < this.barra1.posY+8){
                this.bolaPong.movimientoX *= -1;
                this.bolaPong.movimientoY = 0;
            }
            else if(this.bolaPong.posY >= this.barra1.posY+8 && this.bolaPong.posY < this.barra1.posY+12){
                this.bolaPong.movimientoX *= -1;
                this.bolaPong.movimientoY = 1;
            }
        }
        else if(this.bolaPong.posX == this.barra2.posX - 2){
            if(this.bolaPong.posY >= this.barra2.posY && this.bolaPong.posY < this.barra2.posY+4){
                this.bolaPong.movimientoX *= -1;
                this.bolaPong.movimientoY = -1;
            }
            else if(this.bolaPong.posY >= this.barra2.posY+4 && this.bolaPong.posY < this.barra2.posY+8){
                this.bolaPong.movimientoX *= -1;
                this.bolaPong.movimientoY = 0;
            }
            else if(this.bolaPong.posY >= this.barra2.posY+8 && this.bolaPong.posY < this.barra2.posY+12){
                this.bolaPong.movimientoX *= -1;
                this.bolaPong.movimientoY = 1;
            }
        }
    }



}
