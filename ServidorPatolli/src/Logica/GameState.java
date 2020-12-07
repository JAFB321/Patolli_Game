package Logica;

import java.io.Serializable;

public class GameState implements Serializable {

    public Jugador[] players;
    public Tablero tablero;
	
	public int Banco;

    public int cantidadAPag;
    public int cantidadInicial;
    public int nFichasIniciales;

    public int vuelta = 0;
    public int nmovimientos = -1;

    public EstadoJuego Estado = EstadoJuego.NoIniciado;

    public int currentPlayer = 0;

}
