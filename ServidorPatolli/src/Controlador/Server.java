package Controlador;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;



public class Server extends CustomObservable implements Runnable{
    
    // Componentes
    ServerSocket server;
    ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    
    int PORT;
    int nClientes;
    int currentID = 0;
    
    public Server(int PORT){                
        this.PORT = PORT;
    }
    
    public void Init() throws IOException{
        server = new ServerSocket(PORT);   
        Thread hilo = new Thread(this);
        hilo.start();
    }
    
    public int getNexID(){
        currentID++;
        return currentID;
    }

    @Override
    public void run() {
        try {
            while (true){            
                Socket cliente;            
                cliente = server.accept();     
                AddClient(cliente);
            }            
        } catch (Exception e) {
            System.out.println(e);
        }       
    }
    
    public void AddClient(Socket socket){
        Cliente cliente = new Cliente(socket, "Cliente"+getNexID());
        clientes.add(cliente);

        notifyObservers(cliente);
    }
    
    public Cliente[] getClientes(){
        ArrayList<Cliente> conectados = new ArrayList<Cliente>();
        for (int i = 0; i < clientes.size(); i++) {
            if(clientes.get(i).Socket().isConnected()){
                conectados.add(clientes.get(i));
            }
        }
        
        return (Cliente[])(conectados.toArray(new Cliente[0]));
    }
    
    public void sendToClients(Object obj){
        for (Cliente cliente : getClientes()) {
            cliente.sendObject(obj);
        }
    }
	
	public void sendToClients(Object obj, String ID){
        for (Cliente cliente : getClientes()) {
			if(cliente.ID.equals(ID)){
				cliente.sendObject(obj);
			}
        }
    }

    public void sendToClients(Object obj, String[] IDs){
        for (Cliente cliente : getClientes()) {
            
            boolean hasID = false;
            for (String ID : IDs) {
                if(cliente.ID.equals(ID)){
                    hasID = true;
                }
            }
            
            if(hasID)
            cliente.sendObject(obj);
        }
    }
    
    
    
}
