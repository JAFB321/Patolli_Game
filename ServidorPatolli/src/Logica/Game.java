package Logica;

public class Game implements Runnable {

	public AccionesGame acciones;
	public boolean waiting = false;

	public Jugador[] players;
	public Tablero tablero;
	public Dados dados = new Dados();

	public int Banco;

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
			if (ValidarFinJuego()) {
				continue;
			}
			SiguienteTurno();
		}
		
		Jugador winner = null;
		for (Jugador player : players) {
			if(!player.Eliminado) {
				winner = player;
				break;
			}
		}
		
		winner.cantidad += Banco;
		Banco = 0;
		
		acciones.sendGameState();
		
		acciones.notifyPlayers("El juego ha finalizado", false);
		acciones.notifyPlayers("El ganador es: "+winner.Nombre, false);
		acciones.notifyPlayers(winner.Nombre+" ha ganado "+winner.cantidad, false);
		acciones.notifyPlayers("Muchas gracias por participar!!!!", false);

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

	public boolean ValidarFinJuego() {
		int activePlayers = 0;
		for (Jugador player : players) {
			if (!player.Eliminado) {
				activePlayers++;
			}
		}
		if (activePlayers < 2) {
			Estado = EstadoJuego.JuegoFinalizado;
			return true;
		}
		return false;
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
		if (vuelta == 1) {
			nmovimientos = 1;
		}

		if (nmovimientos == 5) {
			nmovimientos = 10;
		}

		acciones.notifyPlayer(getCurrentPlayer(), "Tus cañas han caido en " + nmovimientos, true, false);

		if (nmovimientos == 1) {
			Ficha ficha = getCurrentPlayer().getFichaDisponible();
			boolean IngresoFicha = false;

			if (ficha != null) {
				acciones.notifyPlayer(getCurrentPlayer(), "Se ingresara una de tus fichas al tablero", true, false);
				IngresoFicha = tablero.MeterFichaIncio(ficha);
				if (!IngresoFicha) {
					acciones.notifyPlayer(getCurrentPlayer(), "Tu casilla inicial esta ocupada, elige otra ficha para mover", true, true);
				}
			}

			if (!IngresoFicha) {
				EscogerYAvanzarFicha();
			}
		}

		if (vuelta > 1) {
			if (nmovimientos > 1) {
				EscogerYAvanzarFicha();
			}
		}

		// Pagar apuesta
		if (nmovimientos == 0) {
			acciones.notifyPlayer(getCurrentPlayer(), "Pagaras una apuesta de " + cantidadAPag, true, false);
			JugadorPagarApuesta(cantidadAPag);
		}

	}

	// Acciones del juego
	public boolean EscogerYAvanzarFicha() {
		Ficha ficha;
		boolean Avanzo = false;
		do {
			ficha = waitEscogerFicha();

			if (ficha == null) {
				return false;
			}

			Avanzo = AvanzarFicha(ficha, nmovimientos);

		} while (!Avanzo);

		return true;
	}

	public boolean AvanzarFicha(Ficha ficha, int nmovimientos) {

		Tablero.strMovimientoFicha movimiento = tablero.AvanzarFicha(ficha, nmovimientos);

		if (movimiento.seMovio) {

			acciones.sendGameState();

			if (movimiento.llegoMeta) {
				acciones.notifyPlayer(getCurrentPlayer(), "Felicidades!! Tu ficha ha llegado a la meta!!", true, false);
				if(getCurrentPlayer().checkFichasCruzaronMeta()){
					WinPlayer(getCurrentPlayer());
				}
				return true;
			}

			switch (ficha.casilla.Tipo) {
				case "N":
					break;

				case "T":
					acciones.notifyPlayer(getCurrentPlayer(), "Pagaras una apuesta de " + (cantidadAPag * 2), true, false);
					JugadorPagarApuesta(cantidadAPag * 2);
					break;

				case "R":
					acciones.notifyPlayer(getCurrentPlayer(), "Has caido en una casilla redonda! Felicidades! Lanzas las cañas de nuevo!", true, false);
					EscogerYAvanzarFicha();
					break;

				case "C":
					if (movimiento.fichaEliminada != null) {
						acciones.notifyPlayer(movimiento.fichaEliminada.player, "El jugador " + getCurrentPlayer().Nombre + " te ha eliminado una ficha", false, false);
						acciones.notifyPlayer(getCurrentPlayer(), "Has eliminado una ficha de: " + movimiento.fichaEliminada.player.Nombre, true, false);
						
						if(movimiento.fichaEliminada.player.checkFichasAcabadas()){
							EliminarJugador(movimiento.fichaEliminada.player);
						}
						
					}
					break;

				default:
			}
		} else {
			if (movimiento.sePaso) {
				acciones.notifyPlayer(getCurrentPlayer(), "Tu ficha se paso de la meta, elige otra ficha para mover", true, true);
			} else {
				acciones.notifyPlayer(getCurrentPlayer(), "Caiste en la casilla con la ficha de un rival, te devuelves a donde empezaste!!", true, true);
				return true;
			}
		}

		return movimiento.seMovio;
	}
	
	public void EliminarJugadorActual() {
		EliminarJugador(getCurrentPlayer());
	}

	public void EliminarJugador(Jugador player) {
		if(!player.Eliminado){
			Banco += player.cantidad;
			player.cantidad = 0;
			player.Eliminado = true;
			tablero.QuitarFichasJugador(player);
			acciones.notifyPlayers("El jugador " + (player.Nombre) + " ha sido eliminado", false);
			acciones.sendGameState();			
		}
	}

	// Eventos del juego
	public void JugadorPagarApuesta(int cantidadAPag) {
		acciones.waitPagarApuesta();
		boolean pago = getCurrentPlayer().PagarApuesta(cantidadAPag);
		if (pago) {
			Banco += cantidadAPag;
		} else {
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
	
	public void WinPlayer(Jugador winner){
		for (Jugador player : players) {
			if(!player.ID.equals(winner.ID)){
				EliminarJugador(player);
			}
		}
	}

}
