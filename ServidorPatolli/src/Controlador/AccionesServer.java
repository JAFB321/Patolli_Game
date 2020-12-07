package Controlador;

import Controlador.Acciones.AccionControlador;
import Controlador.Acciones.AccionCreateGame;
import Controlador.Acciones.AccionJoinGame;
import Controlador.Acciones.AccionMostrarMensaje;
import Controlador.Acciones.AccionNotifyMessage;
import Controlador.Acciones.AccionRequestEscogerFicha;
import Controlador.Acciones.AccionSendEscogerFicha;
import Controlador.Acciones.AccionSendGameState;
import Controlador.Acciones.AccionrequestGameAvailable;
import Controlador.Acciones.AccionsendGameAvailable;
import Controlador.Acciones.AccionsetClientID;
import Controlador.Acciones.enmAcciones;
import Logica.Ficha;
import Logica.Game;
import Logica.GameState;
import Logica.AccionesGame;
import Logica.Jugador;
import java.awt.Color;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class AccionesServer implements AccionesGame, Observer {

	Game game;
	GameState state;

	Server server;
	ArrayList<Jugador> Jugadores = new ArrayList<Jugador>();

	// Preparacion antes de iniciar el juego
	AccionCreateGame gamePreparation;

	//  Accion Actual
	AccionControlador AccionActual;

	public AccionesServer(Server server) {
		this.server = server;
		server.addObserver(this);
	}

	private void setState() {
		state = new GameState();
		state.Estado = game.Estado;
		state.currentPlayer = game.currentPlayer;
		state.cantidadAPag = game.cantidadAPag;
		state.cantidadInicial = game.cantidadInicial;
		state.nFichasIniciales = game.nFichasIniciales;
		state.nmovimientos = game.nmovimientos;
		state.players = game.players;
		state.tablero = game.tablero;
		state.vuelta = game.vuelta;
		state.Banco = game.Banco;
	}

	private void InitGame() {
		int cantidadPagar = gamePreparation.cantidadAPag;
		int cantidadInicial = gamePreparation.cantidadInicial;
		int nFichasIniciales = gamePreparation.nFichasIniciales;

		Color[] colores = {new Color(75, 232, 60), new Color(60, 132, 232), new Color(183, 60, 232), new Color(255, 249, 64)};
		for (int i = 0; i < Jugadores.size(); i++) {
			Jugadores.get(i).colorficha = colores[i];
		}

		game = new Game(cantidadPagar, cantidadInicial, nFichasIniciales, Jugadores.toArray(new Jugador[0]));
		game.acciones = this;

		sendGameState();
		game.initGame();

		// algo
	}

	// Preparar el juego
	private void PrepareGame(AccionCreateGame gamePreparation) {
		if (this.gamePreparation == null) {
			this.gamePreparation = gamePreparation;
			Jugadores.clear();
			Jugadores.add(gamePreparation.player);
		}
		sendGameAvailable();
	}

	private void JoinGame(AccionJoinGame params, Cliente cliente) {
		if (gamePreparation != null) {
			if (Jugadores.size() < gamePreparation.nJugadores) {
				Jugadores.add(params.player);

				if (Jugadores.size() == gamePreparation.nJugadores) {
					InitGame();
					return;
				}

			} else {
				AccionMostrarMensaje msg = new AccionMostrarMensaje("La sala esta llena");
				cliente.sendObject(msg);
			}
		}
		sendGameAvailable();
	}

	// Manejar Clientes Juego
	@Override
	public void sendGameState() {
		// Enviar a los jugadores
		String[] IDs = new String[Jugadores.size()];
		for (int i = 0; i < IDs.length; i++) {
			IDs[i] = Jugadores.get(i).ID;
		}

		setState();
		AccionSendGameState Accion = new AccionSendGameState();
		Accion.state = (GameState) (Tools.deepCopy(state));
		server.sendToClients(Accion, IDs);
	}

	private void requestTirarDados() {
		Jugador player = game.getCurrentPlayer();
		if (player != null) {
			String playerID = player.ID;

			AccionControlador Accion = new AccionControlador(enmAcciones.requestTirarDados);
			server.sendToClients(Accion, playerID);
		}
	}

	private void requestEscogerFicha(Ficha[] fichas, Jugador player) {
		if (fichas != null && player != null) {
			AccionRequestEscogerFicha Accion = new AccionRequestEscogerFicha();
			Accion.fichas = (Ficha[]) (Tools.deepCopy(fichas));
			Accion.nmovimientos = game.nmovimientos;
			server.sendToClients(Accion, player.ID);
		}
	}

	// Manejar Clientes Sala
	private void sendGameAvailable() {
		AccionsendGameAvailable Accion = new AccionsendGameAvailable();

		if (gamePreparation != null) {
			Accion.players = (Jugador[]) (Jugadores.toArray(new Jugador[0]));
			Accion.creator = gamePreparation.player;
			Accion.GameCreated = true;
		} else {
			Accion.GameCreated = false;
		}

		server.sendToClients(Accion);
	}

	private void setClientID(Cliente cliente) {
		AccionsetClientID Accion = new AccionsetClientID();
		Accion.ID = cliente.ID;
		cliente.sendObject(Accion);
	}

	private void DisconectPlayer(String ID) {
		for (int i = 0; i < Jugadores.size(); i++) {
			Jugador player = Jugadores.get(i);
			if (player.ID.equals(ID)) {
				Jugadores.remove(i);
			}
		}

		if (Jugadores.size() == 0) {
			gamePreparation = null;
		}

		System.out.println(ID + " DESCONECTADO");
		sendGameAvailable();
	}

	// Acciones Jugador
	@Override
	public boolean waitTirarDados() {
		game.waiting = true;
		requestTirarDados();

		while (game.waiting) {
			try {
				game.hilo.wait(200);
			} catch (Exception e) {
			}
		}
		return true;
	}

	@Override
	public String waitSeleccionarFicha(Ficha[] fichas) {
		game.waiting = true;
		requestEscogerFicha(fichas, game.getCurrentPlayer());

		while (game.waiting) {
			try {
				game.hilo.wait(200);
			} catch (Exception e) {
			}
		}

		String IDFicha = ((AccionSendEscogerFicha) AccionActual).IDFicha;
		return IDFicha;
	}

	@Override
	public boolean waitPagarApuesta() {
		return false;

	}

	@Override
	public void sendNextTurn() {
		sendGameState();
	}

	@Override
	public void notifyPlayer(Jugador player, String msg, boolean pauseGame, boolean ERROR) {
		AccionNotifyMessage Accion = new AccionNotifyMessage();
		Accion.ERROR = ERROR;
		Accion.msg = msg;
		Accion.pauseGame = pauseGame;
		Accion.player = player;
		server.sendToClients(Accion, player.ID);

		if (pauseGame) {
			game.waiting = true;

			while (game.waiting) {
				try {
					game.hilo.wait(200);
				} catch (Exception e) {
				}
			}
			return;
		}

	}

	@Override
	public void notifyPlayers(String msg, boolean ERROR) {
		for (Jugador player : Jugadores) {
			notifyPlayer(player, msg, false, ERROR);
		}
	}

	// Observer
	@Override
	public void update(Observable o, Object arg) {

		// Cliente conectado
		if (o instanceof Server && arg instanceof Cliente) {
			Cliente cliente = ((Cliente) arg);
			cliente.addObserver(this);
			System.out.println("CONECTED: " + cliente.ID);
			setClientID(cliente);
		}

		//Cliente desconectado
		if (o instanceof Cliente && arg instanceof SocketException) {
			DisconectPlayer(((Cliente) o).ID);
		}

		//Acciones del cliente
		if (o instanceof Cliente && arg instanceof AccionControlador) {
			if (arg instanceof AccionCreateGame) {
				AccionCreateGame Accion = (AccionCreateGame) arg;
				Accion.player.ID = ((Cliente) o).ID;

				PrepareGame(Accion);
			} else if (arg instanceof AccionJoinGame) {
				AccionJoinGame Accion = (AccionJoinGame) arg;
				Accion.player.ID = ((Cliente) o).ID;

				JoinGame(Accion, (Cliente) (o));
			} else if (arg instanceof AccionrequestGameAvailable) {
				sendGameAvailable();
			} else if (arg instanceof AccionSendEscogerFicha) {
				AccionActual = (AccionSendEscogerFicha) arg;
				game.waiting = false;
			} else if (((AccionControlador) arg).Accion == enmAcciones.sendTirarDados) {
				game.waiting = false;
			} else if (((AccionControlador) arg).Accion == enmAcciones.sendResumeGame) {
				game.waiting = false;
			}
		}

	}

}
