package Logica;

import java.io.Serializable;

public class Game implements Runnable {

	public AccionesGame acciones;
	public boolean waiting = false;

	public Jugador[] players;
	public Tablero tablero;
	public Dados dados = new Dados();

	public int cantidadAPag;
	public int cantidadInicial;
	public int nFichasIniciales;

	public int vuelta = 0;
	public int nmovimientos = -1;

	public EstadoJuego Estado = EstadoJuego.NoIniciado;

	public int currentPlayer = -1;

	public Game(int cantidadApag, int cantidadInicial, int nFichas, Jugador[] players) {
		this.players = players;
		this.cantidadAPag = cantidadApag;
		this.cantidadInicial = cantidadInicial;
		this.nFichasIniciales = nFichas;

		initTablero();
		initJugadores();
	}

	public void initJugadores() {
		Casilla[][] iniciales = tablero.getCasillasInciales();

		for (int i = 0; i < players.length; i++) {
			players[i].cantidad = cantidadInicial;
			players[i].initFichas(nFichasIniciales);
			players[i].CasillaInicial = iniciales[i][0];
			players[i].CasillaFinal = iniciales[i][1];
		}
	}

	public void initTablero() {
		tablero = new Tablero();
	}

	public void initGame() {
		if (acciones == null) {
			return;
		}
		currentPlayer = 0;
		vuelta = 1;
		Estado = EstadoJuego.JugadorTurno;

		Thread hilo = new Thread(this);
		this.hilo = hilo;
		hilo.start();
	}
	public Thread hilo;

	@Override
	public void run() {
		while (Estado != EstadoJuego.JuegoFinalizado) {
			JugarTurno();
			SiguienteTurno();
		}

	}

	// Turnos
	public void SiguienteTurno() {

		int next = getNextPlayer(currentPlayer);
		while (players[next].Eliminado) {
			next = getNextPlayer(next);
		}
		currentPlayer = next;

		if (currentPlayer == 0) {
			vuelta++;
		}

		Estado = Estado.JugadorTurno;
		acciones.sendNextTurn();
	}

	public int getNextPlayer(int current) {
		if (current < players.length - 1) {
			return current + 1;
		} else {
			return 0;
		}
	}

	public Jugador getCurrentPlayer() {
		if (currentPlayer >= 0 && currentPlayer < players.length) {
			return players[currentPlayer];
		}
		return null;
	}

	
	public void JugarTurno() {
		Estado = EstadoJuego.JugadorMoviendose;

		acciones.waitTirarDados();

		boolean[] lanzada = dados.LanzarDados();

		nmovimientos = 0;
		for (boolean a : lanzada) {
			if (a) {
				nmovimientos++;
			}
		}

		// enviarle al cliente cuanto cayó
		if (vuelta % 2 == 1) {
			nmovimientos = 1;
		}

		if (nmovimientos == 5) {
			nmovimientos = 10;
		}

		acciones.notifyPlayer(getCurrentPlayer(), "Tus cañas han caido en " + nmovimientos, true, false);

		// Enviar info acerca de los siguientes eventos:
		// Mostrar mensaje cuando un jugador pague apuesta		
		
		if (nmovimientos == 1) {
			acciones.notifyPlayer(getCurrentPlayer(), "Se ingresara una de tus fichas al tablero", true, false);
			Ficha ficha = getCurrentPlayer().getFichaDisponible();

			if (ficha == null || !tablero.MeterFichaIncio(ficha)) {
				acciones.notifyPlayer(getCurrentPlayer(), "Tu casilla inicial esta ocupada, elige otra ficha para mover", true, true);

				EscogerYAvanzarFicha();
			}
			return;
		}

		if (vuelta > 1) {
			if (nmovimientos > 0) {
				EscogerYAvanzarFicha();
			}
		}

		// Pagar apuesta
		if (nmovimientos == 0) {
			JugadorPagarApuesta(cantidadAPag);
		}

	}

	// Acciones del juego
	public boolean EscogerYAvanzarFicha() {
		Ficha ficha;
		boolean Avanzo = false;
		do {
			ficha = waitEscogerFicha();
			Avanzo = AvanzarFicha(ficha, nmovimientos);
			if (!Avanzo) {
				acciones.notifyPlayer(getCurrentPlayer(), "La casilla donde quieres moverte esta ocupada, elige otra ficha para mover", true, true);
			}

		} while (!Avanzo);

		return true;
	}

	public boolean AvanzarFicha(Ficha ficha, int nmovimientos) {
		boolean seMovio = tablero.AvanzarFicha(ficha, nmovimientos);

		if (seMovio) {
			switch (ficha.casilla.Tipo) {
				case "N":
					break;

				case "T":
					JugadorPagarApuesta(cantidadAPag * 2);
					break;

				default:
			}
		}
		return seMovio;
	}

	public void EliminarJugadorActual() {
		getCurrentPlayer().Eliminado = true;
		tablero.QuitarFichasJugador(getCurrentPlayer());
		acciones.sendPlayerEliminated();
	}

	// Eventos del juego
	public void JugadorPagarApuesta(int cantidadAPag) {
		acciones.waitPagarApuesta();
		boolean pago = getCurrentPlayer().PagarApuesta(cantidadAPag);
		if (!pago) {
			EliminarJugadorActual();
		}
	}

	public Ficha waitEscogerFicha() {
		String IDFicha = acciones.waitSeleccionarFicha(getCurrentPlayer().getFichasEnTablero());
		for (Ficha ficha : getCurrentPlayer().getFichasEnTablero()) {
			if (ficha.ID.equals(IDFicha)) {
				return ficha;
			}
		}
		return null;
	}

}
