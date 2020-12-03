
package Logica;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;


public class Jugador implements Serializable{
    
    public String ID;

    public String color;
    public String Nombre;
    public boolean Eliminado = false;
    public int cantidad = 0;
    public int numfichasrestantes = 0;
    public Color colorficha;
    public Ficha[] fichas;
    
    public Casilla CasillaInicial;


    public boolean PagarApuesta(int cantidadPagar){
        if(cantidad >= cantidadPagar){
            cantidad -= cantidadPagar;
            return true;
        }
        else return false;
    }
    
	public Ficha[] getFichasEnTablero(){
		ArrayList<Ficha> entablero = new ArrayList<Ficha>();
		for (Ficha ficha : fichas) {
			if(ficha.enTablero){
				entablero.add(ficha);
			}
		}
		return entablero.toArray(new Ficha[0]);
	}
    
    public Ficha getFichaDisponible(){
        for (Ficha ficha : fichas) {
            if(!ficha.enTablero) return ficha;
        }
        
        return null;
    }

    public void EliminarJugador(){
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
