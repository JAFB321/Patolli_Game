package Logica;

import java.awt.Color;
import java.io.Serializable;

public class Ficha implements Serializable {

	public String ID;

	public Jugador player;
	public int Num;
	public Color colorf;
	public boolean eliminada = false;
	public boolean enTablero = false;
	public boolean llegoMeta = false;
	public Casilla casilla;
}
