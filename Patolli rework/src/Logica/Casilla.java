package Logica;

import java.io.Serializable;

public class Casilla implements Serializable {

	public String Tipo;

	public int pos;
	boolean ocupada = false;
	public Ficha ficha;

	public Casilla next;
	public Casilla prev;

	public Casilla(int pos) {
		this.pos = pos;
	}

	// obtiene la casilla siguiente a esta casilla
	// el parametro "i" hace que se obtenga la casilla a "i" lugares de la casilla actual
	public Casilla getNext(int i) {
		Casilla nexti = this;
		for (int j = 0; j < i; j++) {
			nexti = nexti.next;
		}
		return nexti;
	}

	// Retorna true en el caso donde si la ficha se mueve n movimientos y pasa por la casilla final (la meta)
	public boolean MovimientoCruzaLaMeta(Ficha ficha, int nmovimientos) {
		Casilla casilla = next;
		for (int i = 0; i < nmovimientos; i++) {
			if(ficha.player.CasillaFinal == casilla){
				return true;
			}
			casilla = casilla.next;
		}
		return false;
	}

}