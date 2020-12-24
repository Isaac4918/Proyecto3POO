package pantalla;

import org.json.JSONArray;
import org.json.JSONObject;
import sockets.Server;

import java.io.IOException;
import java.util.LinkedList;

public class MainPantalla {

    public static void main(String[] args) throws IOException {
        Ventana ventana1 = new Ventana();
        Server server = new Server(9999);



        /*while (true){
            System.out.println(server.mensaje);
        }*/




        while (true){
            LinkedList cambios = new LinkedList();
            try {
                JSONObject test = new JSONObject(server.mensaje);
                JSONArray posiciones = test.getJSONArray("posiciones");
                System.out.println("entré");
                System.out.println(server.mensaje);

                for (int i = 0; i < posiciones.length(); i++){
                    JSONObject posicion = posiciones.getJSONObject(i);
                    int[] tmp = new int [3];

                    tmp[0] = posicion.getInt("posx");
                    tmp[1] = posicion.getInt("posy");
                    tmp[2] = posicion.getInt("color");
                    cambios.add(tmp);
                }

                ventana1.actualizarCambios(cambios);
            }catch (NullPointerException e){
                //System.out.println("");
            }
        }

    }


}
