
package servidorpatolli;

import Controlador.Server;
import Controlador.AccionesServer;


public class ServidorPatolli{

   
    public static void main(String[] args) throws ClassNotFoundException {
        
        Server PatolliServer = new Server(8080);
        try {
            PatolliServer.Init();            
            AccionesServer Patolli = new AccionesServer(PatolliServer);
            
            System.out.println("Server Conectado");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
