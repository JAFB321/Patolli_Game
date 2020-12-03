
package Controlador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Cliente extends CustomObservable implements Runnable{

    public String ID;  
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    
    private ObjectInputStream objIn;
    private ObjectOutputStream objOut;
    
    public Cliente(Socket socket, String ID){
        this.ID = ID;
        this.socket = socket;                
        initCliente();
        
        Thread hilo = new Thread(this);
        hilo.start();
    }
    
    public void initCliente(){
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            objOut = new ObjectOutputStream(out);   
            objOut.flush();
            objIn = new ObjectInputStream(in);
            
        } catch (Exception e) {}
    }
    
    public Socket Socket(){
        return socket;
    }
    
    public boolean sendObject(Object obj){
        try {
            objOut.writeObject(obj);            
            return true;
        } catch (Exception e) {return false;}
    }
    
    
    @Override
    public void run() {
        try {
            while (true) {            
                Object obj = objIn.readObject(); 
                ObjectRecived(obj);
            }            
        } catch (SocketException e) {
            Disconected(e);
        } catch (IOException ex) {
            //Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e){
			System.out.println("NULL POINTER");
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			System.out.println(e.getLocalizedMessage());
		}   
    }
    
    public void ObjectRecived(Object obj){
        notifyObservers(obj);
    }       
    
    public void Disconected(SocketException e){
        notifyObservers(e);
    }
    
}
