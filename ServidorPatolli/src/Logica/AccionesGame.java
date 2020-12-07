
package Logica;


public interface AccionesGame {               
    
    public boolean waitTirarDados();
    public String waitSeleccionarFicha(Ficha[] fichas);    
    public boolean waitPagarApuesta();	
    
    public void sendNextTurn();
	public void notifyPlayer(Jugador player, String msg, boolean pauseGame, boolean ERROR);
	public void notifyPlayers(String msg, boolean ERROR);
	
	public void sendGameState();
}
