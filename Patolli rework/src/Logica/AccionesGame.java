
package Logica;


public interface AccionesGame{               
    
    public boolean waitTirarDados();
    public Ficha waitSeleccionarFicha();    
    public boolean waitPagarApuesta(); 
    
    public void sendNextTurn();
    public void sendPlayerEliminated();
}
