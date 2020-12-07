package Logica;

import java.io.Serializable;

public class Tablero implements Serializable {

	public class strMovimientoFicha implements Serializable {

		public boolean seMovio;
		public Casilla origen;
		public Casilla destino;
		public Ficha fichaEliminada;

		public boolean llegoMeta;
		public boolean sePaso;

		public strMovimientoFicha(boolean seMovio, Casilla origen, Casilla destino, Ficha fichaEliminada) {
			this.seMovio = seMovio;
			this.origen = origen;
			this.destino = destino;
			this.fichaEliminada = fichaEliminada;
		}

		public strMovimientoFicha(boolean seMovio, Casilla origen, Casilla destino, Ficha fichaEliminada, boolean llegoMeta, boolean sePaso) {
			this.seMovio = seMovio;
			this.origen = origen;
			this.destino = destino;
			this.fichaEliminada = fichaEliminada;
			this.llegoMeta = llegoMeta;
			this.sePaso = sePaso;
		}

	}

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
		int[] redondas = {67, 0, 16, 17, 33, 34, 50, 51};
		for (int i = 0; i < redondas.length; i++) {
			if (redondas[i] == pos) {
				return true;
			}
		}

		return false;
	}

	private boolean esCentral(int pos) {
		int[] centrales = {8, 25, 42, 59};
		for (int i = 0; i < centrales.length; i++) {
			if (centrales[i] == pos) {
				return true;
			}
		}

		return false;
	}

	private void initCasillas() {
		casillas = new Casilla[68];

		for (int i = 0; i < casillas.length; i++) {
			casillas[i] = new Casilla(i);

			if (esTriangular(i)) {
				casillas[i].Tipo = "T";
			} else if (esRedonda(i)) {
				casillas[i].Tipo = "R";
			} else if (esCentral(i)) {
				casillas[i].Tipo = "C";
			} else {
				casillas[i].Tipo = "N";
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

	public strMovimientoFicha AvanzarFicha(Ficha ficha, int mov) {
		Casilla casilla = null;
		Casilla casillaNext = null;

		if (ficha.enTablero) {
			casilla = ficha.casilla;
			casillaNext = casilla.getNext(mov);

			if (!casillaNext.ocupada) {

				if (casillaNext == ficha.player.CasillaFinal) {
					// llega a la meta
					casilla.ficha = null;
					casilla.ocupada = false;

					ficha.casilla = null;
					ficha.enTablero = false;
					ficha.llegoMeta = true;

					return new strMovimientoFicha(true, casilla, casillaNext, null, true, false);
				} 
				else if (casilla.MovimientoCruzaLaMeta(ficha, mov)) {
					// se pasa de la meta (no avanza)
					return new strMovimientoFicha(false, casilla, null, null, false, true);
				} 
				else { 
					// Avanza normalmente
					casilla.ficha = null;
					casilla.ocupada = false;

					casillaNext.ficha = ficha;
					casillaNext.ocupada = true;
					ficha.casilla = casillaNext;

					return new strMovimientoFicha(true, casilla, casillaNext, null);
				}

			} else if (casillaNext.Tipo.equals("C") && casillaNext.ficha != null && !casillaNext.ficha.player.ID.equals(ficha.player.ID)) {
				// cae en casilla central y se come a una ficha enemiga
				Ficha fichaEliminada = casillaNext.ficha;
				fichaEliminada.casilla = null;
				fichaEliminada.enTablero = false;
				fichaEliminada.eliminada = true;

				casilla.ficha = null;
				casilla.ocupada = false;

				casillaNext.ficha = ficha;
				casillaNext.ocupada = true;
				ficha.casilla = casillaNext;

				return new strMovimientoFicha(true, casilla, casillaNext, fichaEliminada);
			}
		}

		return new strMovimientoFicha(false, casilla, null, null);
	}

	// Para fichas de los jugadores
	public Casilla[][] getCasillasInciales() {
		// Retorna un arreglo bidimensional con [FichaInicial][FichaFinal]
		Casilla[][] arr = {
			new Casilla[]{casillas[9], casillas[60]},
			new Casilla[]{casillas[26], casillas[9]},
			new Casilla[]{casillas[43], casillas[26]},
			new Casilla[]{casillas[60], casillas[43]}
		};

		return arr;
	}

	public boolean MeterFichaIncio(Ficha ficha) {
		Casilla casillaInicial = ficha.player.CasillaInicial;
		if (!casillaInicial.ocupada) {
			casillaInicial.ficha = ficha;
			casillaInicial.ocupada = true;
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
					casilla.ocupada = false;
					ficha.casilla = null;
					ficha.enTablero = false;
					ficha.eliminada = true;
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
			if (inicial.prev.pos == player.CasillaInicial.pos - 1) {
				break;
			}
		}
		return null;
	}

}
