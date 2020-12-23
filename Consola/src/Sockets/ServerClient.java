package Sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.JSONArray;
import org.json.JSONObject;


public class ServerClient implements Runnable {


    int puertoEntrada;
    int puertoSalida;
    JSONObject mensajeRecibido;

    public ServerClient(int inputPort, int outputPort) {
        this.puertoEntrada = inputPort;
        this.puertoSalida = outputPort;
        //Se creó un hilo que permita la continua ejecución del socketServer
        Thread t1 = new Thread(this);
        t1.start();
    }

    /**
     * Esta clase inicia un serverSocket que permite utilizar su puerto como número de contacto
     */
    @Override
    public void run() {
        boolean flag = true;
        //Se crea un bucle que intente iniciar un serverSocket en puertos consecutivos hasta encontrar uno con espacio disponible
        while (flag) {
            try {
                ServerSocket servidor = new ServerSocket(puertoEntrada);

                //En caso de que el try logre crear el ServerSocket se niega la condición del bucle
                flag = false;

                //Se inicia un bucle que acepte las conexiones de otros Sockets y muestre sus mensajes
                while (true) {
                    Socket socket = servidor.accept();
                    ObjectInputStream datos = new ObjectInputStream(socket.getInputStream());
                    mensajeRecibido = (JSONObject) datos.readObject();

                    socket.close();
                }
            } catch (UnknownHostException unknownHostException) {
                unknownHostException.printStackTrace();
                System.out.println("UnknownHostException cliente");
            } catch (IOException ioException) {
                //ioException.printStackTrace();
                System.out.println("IOException cliente");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Este método permite crear un socket de salida para enviar el mensaje al puerto específicado
     */

    public void enviar(JSONObject mensaje){
        int puerto = puertoSalida;
        try {
            //Se crea un nuevo socket para enviar la información
            Socket nuevoSocket = new Socket("127.0.0.1", puerto);

            //Obtiene los datos que son recibidos de la GUI para enviarlo al contacto espcíficado
            /*MensajeUsuario datosRecibidos = new MensajeUsuario();
            datosRecibidos.setPuerto(String.valueOf(puertoEntrada));
            datosRecibidos.setMensaje(campoEscribir.getText());************************************************IMPORTANTE
*/

            //Se crea un flujo de datos de salida para enviar los datos recibidos
            ObjectOutputStream informacionDatos = new ObjectOutputStream(nuevoSocket.getOutputStream());
            informacionDatos.writeObject(mensaje);
            nuevoSocket.close();

        } catch (UnknownHostException unknownHostException) {
            unknownHostException.printStackTrace();
            System.out.println("UnknownHostException cliente");
        } catch (IOException ioException) {
            System.out.println("IOException cliente");

        }
    }

}