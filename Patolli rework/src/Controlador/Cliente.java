
package Controlador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Cliente extends CustomObservable implements Runnable{
    // Params
    String Host;
    int puerto;
    
    // Componentes
    private Socket cliente;
    
    private DataInputStream in;
    private DataOutputStream out;
    
    private ObjectInputStream objIn;
    private ObjectOutputStream objOut;
    
    public Cliente(String Host, int puerto){
        this.Host = "localhost";
        this.puerto = puerto;
    }
    
    public void Init() throws IOException {
        cliente = new Socket(Host, puerto);
        
        in = new DataInputStream(cliente.getInputStream());
        out = new DataOutputStream(cliente.getOutputStream());                
        objOut = new ObjectOutputStream(out);     
        objOut.flush();
        objIn = new ObjectInputStream(in);
        
        Thread hilo = new Thread(this);
        hilo.start();
    }
       
    @Override
    public void run() {
        while (true) {   
            try {
                Object obj = objIn.readObject();     
                ObjectRecived(obj);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public void ObjectRecived(Object obj){
        notifyObservers(obj);
    }
    
    public boolean SendObject(Object obj){
        try {
            objOut.writeObject(obj);
            return true;
        } catch (Exception e) {
			System.out.println(e.getMessage());
            return false;
        }
    }
    
     public void Close() throws IOException{
        try{
        if(cliente != null) cliente.close();            
        }catch(IOException e){}
    }
    
    
    
    
}
