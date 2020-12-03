package Controlador.Acciones;

import java.io.Serializable;


public class AccionControlador implements Serializable{
    public enmAcciones Accion;

    public AccionControlador() {
    }
    
    public AccionControlador(enmAcciones Accion) {
        this.Accion = Accion;
    }
    
    
}
