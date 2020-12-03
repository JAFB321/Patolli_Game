
package GUI;

import Controlador.AccionesCliente;
import Controlador.Cliente;
import Controlador.ControladorGUI;


public class Main {
    public static void main(String[] args){        
        
        ControladorGUI controladorGUI = new ControladorGUI();
        frmPrincipalPatolli principal = null;
        AccionesCliente Acciones = null;
        
        try {            
            Cliente cliente = new Cliente("localhost", 8080);
            Acciones = new AccionesCliente(cliente);
            
            principal = new frmPrincipalPatolli();
            
            controladorGUI.SetFrame(principal, Acciones);
            Acciones.GUI = controladorGUI;
            
            Acciones.Init();                       
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
         
        principal.setVisible(true);
        
    }
            
    
}
