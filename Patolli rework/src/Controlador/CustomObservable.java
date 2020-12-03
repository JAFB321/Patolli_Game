
package Controlador;

import java.util.Observable;


public class CustomObservable extends Observable{

    @Override
    public void notifyObservers(Object arg) {
        super.setChanged();
        super.notifyObservers(arg); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
