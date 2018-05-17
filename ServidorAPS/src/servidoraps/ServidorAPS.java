
package servidoraps;

import java.net.*;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;


public class ServidorAPS {

    public static void main(String[] args) {
        
        ArrayList<PrintStream> clientes = new ArrayList();
        
        try{
            ServerSocket server = new ServerSocket(5000);
            Socket socket;
           
            while(true){
                socket = server.accept();
                //Guarda o endereço do cliente
                clientes.add(new PrintStream(socket.getOutputStream()));
                Mensagens mensagem = new Mensagens(socket, clientes);

            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
