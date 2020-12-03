/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.Serializable;

/**
 *
 * @author jafb3
 */
public enum EstadoJuego implements  Serializable{
    NoIniciado,
    JugadorTurno,
    JugadorMoviendose,
    JuegoFinalizado
}
