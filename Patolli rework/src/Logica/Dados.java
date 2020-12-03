
package Logica;

import java.io.Serializable;
import java.util.Random;


public class Dados implements  Serializable{
    
    public boolean[] LanzarDados(){
        boolean[] dados = new boolean[5];
        
        Random rd = new Random();
        for (int i = 0; i < dados.length; i++) {
            dados[i] = (rd.nextInt() %2 == 0);
        }
        
        return dados;
    }
    
}
