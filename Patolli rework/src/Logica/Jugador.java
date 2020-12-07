package Logica;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;

public class Jugador implements Serializable {

	public String ID;

	public String color;
	public String Nombre;
	public boolean Eliminado = false;
	public int cantidad = 0;
	public int numfichasrestantes = 0;
	public Color colorficha;
	public Ficha[] fichas;

	public Casilla CasillaInicial;
	public Casilla CasillaFinal;

	public boolean PagarApuesta(int cantidadPagar) {
		if (cantidad > cantidadPagar) {
			cantidad -= cantidadPagar;
			return true;
		} else {
			return false;
		}
	}

	public boolean checkFichasAcabadas() {
		int eliminadas = 0;
		for (Ficha ficha : fichas) {
			if (ficha.eliminada) {
				eliminadas++;
			}
		}
		return eliminadas == fichas.length;
	}

	public boolean checkFichasCruzaronMeta() {
		int cruzadasMeta = 0;
		for (Ficha ficha : fichas) {
			if (ficha.llegoMeta) {
				cruzadasMeta++;
			}
		}
		return cruzadasMeta == (fichas.length - getFichasEliminadas().length);
	}

	public Ficha[] getFichasEnTablero() {
		ArrayList<Ficha> entablero = new ArrayList<Ficha>();
		for (Ficha ficha : fichas) {
			if (ficha.enTablero) {
				entablero.add(ficha);
			}
		}
		return entablero.toArray(new Ficha[0]);
	}

	public Ficha[] getFichasEliminadas() {
		ArrayList<Ficha> eliminadas = new ArrayList<Ficha>();
		for (Ficha ficha : fichas) {
			if (ficha.eliminada) {
				eliminadas.add(ficha);
			}
		}
		return eliminadas.toArray(new Ficha[0]);
	}

	public Ficha[] getFichasDisponibles() {
		ArrayList<Ficha> disponibles = new ArrayList<Ficha>();
		for (Ficha ficha : fichas) {
			if (!ficha.enTablero && !ficha.eliminada && !ficha.llegoMeta) {
				disponibles.add(ficha);
			}
		}
		return disponibles.toArray(new Ficha[0]);
	}

	public Ficha getFichaDisponible() {
		for (Ficha ficha : fichas) {
			if (!ficha.enTablero && !ficha.eliminada && !ficha.llegoMeta) {
				return ficha;
			}
		}

		return null;
	}

	public void EliminarJugador() {
		for (Ficha ficha : fichas) {

		}
	}

	public void initFichas(int nfichas) {
		fichas = new Ficha[nfichas];
		for (int i = 0; i < fichas.length; i++) {
			fichas[i] = new Ficha();
			fichas[i].colorf = colorficha;
			fichas[i].ID = ID + i;
			fichas[i].player = this;
		}
	}

}
