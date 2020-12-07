
package Controlador;

import Controlador.Acciones.AccionsendGameAvailable;
import GUI.AccionesGUI;
import GUI.ConectarYUnirse;
import GUI.Fichasyapuesta;
import GUI.IniciarJuego;
import GUI.Tablerito;
import GUI.frmPrincipalPatolli;
import static GUI.frmPrincipalPatolli.PanelPrincipal;
import Logica.Ficha;
import Logica.GameState;
import Logica.Jugador;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ControladorGUI {

    frmPrincipalPatolli frame;
    
    // Paneles
    JPanel currentPanel;
    ConectarYUnirse PanelConectar;
    Fichasyapuesta PanelFichasApuesta;
    IniciarJuego PanelIniciarJuego;
    Tablerito FrameTablerito;
    
    public ControladorGUI() {
        
    }
    
    public void SetFrame(frmPrincipalPatolli frame, AccionesGUI Acciones){
        this.frame = frame;
        frame.Acciones = Acciones;
        PanelConectar = new ConectarYUnirse(Acciones);
        PanelFichasApuesta = new Fichasyapuesta(Acciones);
        PanelIniciarJuego = new IniciarJuego(Acciones);
        FrameTablerito = new Tablerito(Acciones);
    }
    
    
    
    public void MostrarMensaje(String msg){
			JOptionPane.showMessageDialog(frame.isVisible() ? currentPanel : FrameTablerito, msg);			
    }
	
	public void MostrarError(String msg){
        JOptionPane.showMessageDialog(frame.isVisible() ? currentPanel : FrameTablerito, msg, "Advertencia", 0);
    }
    
    
    // Juego
    public void RefreshGameState(GameState state, String ID){
        if(!FrameTablerito.isVisible()){
            openTablerito();
        }
        
        Jugador ClientPlayer = null;
        for (Jugador player : state.players) {
            if(player.ID.equals(ID)){
                ClientPlayer = player;
                break;
            }
        }
        FrameTablerito.RefreshGameState(state, ClientPlayer);
    }
    
    public void TirarDados(){
        JOptionPane.showMessageDialog(FrameTablerito, "Se lanzaran las cañas","Es su turno", 1);        
    }	
	
	public String SeleccionarFicha(Ficha[] fichas, int nmovimientos){
		String[] opcionesID = new String[fichas.length+1];
		
		for (int i = 0; i < fichas.length; i++) {
			int posInicial = fichas[i].player.CasillaInicial.pos;
			int posActual = fichas[i].casilla.pos;
			opcionesID[i] = "Ficha "+(i+1)+" - Posicion: "+(posActual-posInicial+1);
		}
		opcionesID[opcionesID.length-1] = "Saltar Turno";
		String opcionSel = (String)(JOptionPane.showInputDialog(FrameTablerito, "Elija la ficha a mover "+nmovimientos+" lugares", "Es su turno", 1, null, opcionesID, opcionesID[0]));
		
		if(opcionSel == null) return "";
		if(opcionSel.equals("Saltar Turno")) return "";
		
		for (int i = 0; i < opcionesID.length; i++) {
			if(opcionesID[i].equals(opcionSel)){
				return fichas[i].ID;
			}
		}
		return "";		
	}
    
    // Open Panels/Frames
    public void openConectarYUnirse() {        
        openPanel(PanelConectar);
        PanelConectar.getGameAvailability();
    }

    public void openFichasYapuesta(Jugador player, int nJugadores) {
        PanelFichasApuesta.setParams(player, nJugadores);
        openPanel(PanelFichasApuesta);
    }

    public void openTablerito() {
        FrameTablerito.setVisible(true);
        frame.setVisible(false);
    }

    public void openIniciarJuego() {
        openPanel(PanelIniciarJuego);
    }
    
    public void openPanel(JPanel p){
        currentPanel = p;
        
        p.setSize(666, 416);
        p.setLocation(0,60);
       
        PanelPrincipal.removeAll();
        PanelPrincipal.add(p, BorderLayout.CENTER);
        PanelPrincipal.revalidate();
        PanelPrincipal.repaint();
    }
    
    // Juego en Sala
    public void RefreshGameAvailability(AccionsendGameAvailable params, boolean alreadyConected){
        if(currentPanel == PanelConectar){
            PanelConectar.RefreshGameAvailability(params.creator, params.players, params.GameCreated, alreadyConected);            
        }        
    }
    
    
}
