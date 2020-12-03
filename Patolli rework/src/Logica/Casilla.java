

package Logica;

import java.io.Serializable;


public class Casilla implements Serializable{
    
    public String Tipo;
    
    public int pos;
    boolean ocupada = false;
    public Ficha ficha;
    
    public Casilla next;
    public Casilla prev;

    
    public Casilla(int pos) {
        this.pos = pos;
    }
    
    public Casilla getNext(int i){
        Casilla nexti = this;
        for (int j = 0; j < i; j++) {
            nexti = nexti.next;
        }        
        return nexti;
    }
    
}
