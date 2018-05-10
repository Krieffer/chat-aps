
package servidoraps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;
import java.util.ArrayList;

public class Mensagens {
        private Socket s;
        private ArrayList<PrintStream> clientes;

       
    //set
    public void setS(Socket s){
        this.s = s;
    }

    public Mensagens(Socket s,ArrayList<PrintStream> clientes){
        
        this.s = s;
        this.clientes = clientes;
        
        Thread();
        
    }
    private void Thread(){
        
        String mensagem = "";
        
        try{
            InputStreamReader isr = new InputStreamReader(s.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            while((mensagem = br.readLine()) != null){
                   enviarMensagem(mensagem);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
       
        
        
    }
    
    private void enviarMensagem(String mensagem){
        for(int a =0; a < clientes.size();a++) {
            clientes.get(a).println(mensagem);
            clientes.get(a).flush();
        }
    }
}
