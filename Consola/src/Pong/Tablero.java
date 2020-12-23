package Pong;


public class Tablero {

    public int[][] matriz = new int[50][50];
    Figura bolaPong = new Figura(matriz, 23,23, 2);
    Figura barra1 = new Figura(matriz, 0, 30, 12);
    Figura barra2 = new Figura(matriz, 48, 18, 12);

    public Tablero() {
        barra1.movimientoX = 0;
        barra2.movimientoX = 0;
        barra1.movimientoY = 1;
        barra2.movimientoY = 1;
        dibujar();
    }

    public void rellenar(){
        for(int i = 0; i < 50; i++){
            for(int j = 0; j< 50; j++){
                this.matriz[i][j] = 1;
            }
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
