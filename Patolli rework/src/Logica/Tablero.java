package Logica;

import java.io.Serializable;

public class Tablero implements Serializable {

	public Casilla[] casillas;

	public Tablero() {
		initCasillas();
	}

	public Casilla[] getCasillas() {
		return casillas;
	}

	private boolean esTriangular(int pos) {
		int[] triangulares = {2, 14, 19, 31, 36, 48, 53, 65};
		for (int i = 0; i < triangulares.length; i++) {
			if (triangulares[i] == pos) {
				return true;
			}
		}

		return false;
	}

	private boolean esRedonda(int pos) {
		int[] triangulares = {2, 14, 19, 31, 36, 48, 53, 65};
		for (int i = 0; i < triangulares.length; i++) {
			if (triangulares[i] == pos) {
				return true;
			}
		}

		return false;
	}

	private void initCasillas() {
		casillas = new Casilla[68];

		for (int i = 0; i < casillas.length; i++) {
			casillas[i] = new Casilla(i);

			if (!esTriangular(i)) {
				casillas[i].Tipo = "N";
			} else {
				casillas[i].Tipo = "T";
			}

			if (i > 0) {
				casillas[i].prev = casillas[i - 1];
				casillas[i - 1].next = casillas[i];
			}
		}
		// Asignar prev y next a primera y ultima
		casillas[0].prev = casillas[casillas.length - 1];
		casillas[casillas.length - 1].next = casillas[0];
	}

	public boolean AvanzarFicha(Ficha ficha, int mov) {
		if (ficha.enTablero) {
			Casilla casilla = ficha.casilla;
			Casilla casillaNext = casilla.getNext(mov);

			if (!casillaNext.ocupada) {
				casilla.ficha = null;
				casilla.ocupada = false;

				casillaNext.ficha = ficha;
				casillaNext.ocupada = true;
				ficha.casilla = casillaNext;

				return true;
			}
		}

		return false;
	}

	// Para fichas de los jugadores
	public Casilla[] getCasillasInciales() {
		Casilla[] arr = {casillas[0], casillas[17], casillas[34], casillas[51]};
		return arr;
	}

	public boolean MeterFichaIncio(Ficha ficha) {
		Casilla casillaInicial = ficha.player.CasillaInicial;
		if (!casillaInicial.ocupada) {
			casillaInicial.ficha = ficha;
			ficha.enTablero = true;
			ficha.casilla = casillaInicial;
			
			return true;
		}

		return false;
	}

	public void QuitarFichasJugador(Jugador player) {
		Ficha[] eliminar = player.fichas;
		for (Ficha ficha : eliminar) {
			QuitarFicha(ficha);
		}
	}

	public void QuitarFicha(Ficha ficha) {
		for (Casilla casilla : casillas) {
			if (casilla.ficha != null) {
				if (casilla.ficha.ID == ficha.ID) {
					casilla.ficha = null;
				}
			}
		}
	}

	public Ficha getFichaAdelante(Jugador player) {

		Casilla inicial = player.CasillaInicial;		
		while (true) {
			if (inicial.prev.ficha != null) {
				if (inicial.prev.ficha.player.ID.equals(player.ID)) {
					return inicial.prev.ficha;
				}
			}
			inicial = inicial.prev;
			if (inicial.prev.pos == player.CasillaInicial.pos-1) {
				break;
			}
		}
		return null;
	}

}
