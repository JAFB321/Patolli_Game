
package GUI;

import Logica.Jugador;


public interface AccionesGUI{
    public void CreateGame(Jugador player, int cantidadAPag, int cantidadInicial, int nFichasIniciales, int nJugadores);
    public void JoinGame(Jugador player);
    
    public void requestGameAvailable();
    
    public void openConectarYUnirse();
    public void openFichasYapuesta(Jugador player, int nJugadores);
    public void openTablerito();
    public void openIniciarJuego();   
}
