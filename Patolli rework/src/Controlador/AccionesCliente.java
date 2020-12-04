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
import GUI.AccionesGUI;
import Logica.Ficha;
import Logica.GameState;
import Logica.Jugador;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;


public class AccionesCliente implements AccionesGUI, Observer{
    
    public ControladorGUI GUI;
    
    public String ID;
    
    Cliente cliente;
    GameState state;    
    
    public AccionesCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public void Init() throws IOException{
        if(cliente != null){
            cliente.Init();
            cliente.addObserver(this);
        }
    }
    
    // Manejar peticiones del juego
    private void TirarDados(){
        GUI.TirarDados();        
        
        AccionControlador Accion = new AccionControlador(enmAcciones.sendTirarDados);
        cliente.SendObject(Accion);
    }
	
	private void SeleccionarFicha(Ficha[] fichas, int nmovimientos){
		String IDFicha = GUI.SeleccionarFicha(fichas, nmovimientos);
		
		AccionSendEscogerFicha Accion = new AccionSendEscogerFicha();
		Accion.IDFicha = IDFicha;
		cliente.SendObject(Accion);
	}
    
    // Manejar Server Juego
    private void RefreshGameState(){
        // GUI Actualizar
        GUI.RefreshGameState(state, ID);
    }
    
    // Manejar Server Salas
    @Override
    public void CreateGame(Jugador player, int cantidadAPag, int cantidadInicial, int nFichasIniciales, int nJugadores){
        AccionCreateGame params = new AccionCreateGame();
        params.cantidadAPag = cantidadAPag;
        params.cantidadInicial = cantidadInicial;
        params.nFichasIniciales = nFichasIniciales;
        params.nJugadores = nJugadores;
        params.player = player;
                
        cliente.SendObject(params);
    }
    
    @Override
    public void JoinGame(Jugador player){
        AccionJoinGame params = new AccionJoinGame();
        params.player = player;
        
        cliente.SendObject(params);
    }
    
    @Override
    public void requestGameAvailable() {
        cliente.SendObject(new AccionrequestGameAvailable());
    }

    public void RefreshGameAvailability(AccionsendGameAvailable params){
        boolean alreadyConected = false;
        
        if(params.players != null){
            for (Jugador player : params.players) {
                if(player.ID.equals(ID)){
                    alreadyConected = true;
                }
            }            
        }
        
        GUI.RefreshGameAvailability(params, alreadyConected);
    }
    

    @Override
    public void update(Observable o, Object arg) {       
        
        if(o instanceof Cliente && arg instanceof AccionControlador){
            if(arg instanceof AccionsetClientID){
                this.ID = ((AccionsetClientID) arg).ID;
            }
            
            else if(arg instanceof AccionsendGameAvailable){
                RefreshGameAvailability((AccionsendGameAvailable)(arg));
            }
            
            else if(arg instanceof AccionMostrarMensaje){
                GUI.MostrarMensaje(((AccionMostrarMensaje) (arg)).Mensaje);
            }

            else if(arg instanceof AccionSendGameState){
                state = ((AccionSendGameState)arg).state;
                RefreshGameState();
            }
			else if(arg instanceof AccionRequestEscogerFicha){
				AccionRequestEscogerFicha accion = ((AccionRequestEscogerFicha)arg);
				Ficha[] fichas = accion.fichas;
				int nmovimientos = accion.nmovimientos;
				SeleccionarFicha(fichas, nmovimientos);
			}
			
			else if(arg instanceof AccionNotifyMessage){
                AccionNotifyMessage Accion = (AccionNotifyMessage)arg;
				
				if(Accion.ERROR) GUI.MostrarError(Accion.msg); 
				else GUI.MostrarMensaje(Accion.msg);
				
				if(Accion.pauseGame){
					AccionControlador ResumeGame = new AccionControlador(enmAcciones.sendResumeGame);
					cliente.SendObject(ResumeGame);
				}
            }
			
            else if(((AccionControlador)arg).Accion == enmAcciones.requestTirarDados){
                TirarDados();
            }  
			else{
				System.out.println("OBJETO INVALIDO");
			}
                
        }

    }

    // Cambiar Pantalla
    @Override
    public void openConectarYUnirse() {
        GUI.openConectarYUnirse();
    }

    @Override
    public void openFichasYapuesta(Jugador player, int nJugadores) {
        GUI.openFichasYapuesta(player, nJugadores);
    }

    @Override
    public void openIniciarJuego() {
        GUI.openIniciarJuego();
    }
    
    @Override
    public void openTablerito() {
        GUI.openTablerito();
    }


}
