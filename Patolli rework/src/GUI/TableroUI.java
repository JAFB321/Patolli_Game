package GUI;

import Logica.Casilla;
import Logica.GameState;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import javax.swing.JPanel;


public class TableroUI extends JPanel {

    int[][] arreglo;
    GameState state;

    public TableroUI(int[][] arreglodearreglos) {
        arreglo = arreglodearreglos;
    }
    
    // Actualizar el game state (tablero)
    public void Refresh(GameState state){
        this.state = state;
        repaint();
    }

    private void pintartablero(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(5));

        int ox = 282;
        int oy = 156;

        // Tablero Coloreado
        //Aspa de abajo a la derecha
        g.setColor(new Color(204, 160, 95));
        Polygon poligon = new Polygon();
        poligon.addPoint(300 - ox, 539 - oy);
        poligon.addPoint(668 - ox, 174 - oy);
        poligon.addPoint(697 - ox, 155 - oy);
        poligon.addPoint(731 - ox, 186 - oy);
        poligon.addPoint(713 - ox, 218 - oy);
        poligon.addPoint(344 - ox, 585 - oy);
        poligon.addPoint(315 - ox, 600 - oy);
        poligon.addPoint(284 - ox, 572 - oy);
        g.fillPolygon(poligon);

        //Aspa de abajo a la Izquierda
        Polygon poligon2 = new Polygon();

        poligon2.addPoint(669 - ox, 587 - oy);
        poligon2.addPoint(699 - ox, 601 - oy);
        poligon2.addPoint(730 - ox, 571 - oy);
        poligon2.addPoint(714 - ox, 541 - oy);
        poligon2.addPoint(344 - ox, 173 - oy);
        poligon2.addPoint(316 - ox, 156 - oy);
        poligon2.addPoint(282 - ox, 187 - oy);
        poligon2.addPoint(299 - ox, 217 - oy);
        g.fillPolygon(poligon2);

        // Casillas Amarillas dibujadas
        g.setColor(new Color(255, 192, 0));
        Polygon cuadrito = new Polygon();
        cuadrito.addPoint(507 - ox, 423 - oy);
        cuadrito.addPoint(533 - ox, 451 - oy);
        cuadrito.addPoint(555 - ox, 427 - oy);
        cuadrito.addPoint(529 - ox, 401 - oy);
        g.fillPolygon(cuadrito);

        Polygon cuadrito2 = new Polygon();
        cuadrito2.addPoint(551 - ox, 378 - oy);
        cuadrito2.addPoint(579 - ox, 353 - oy);
        cuadrito2.addPoint(554 - ox, 329 - oy);
        cuadrito2.addPoint(530 - ox, 356 - oy);
        g.fillPolygon(cuadrito2);

        Polygon cuadrito3 = new Polygon();
        cuadrito3.addPoint(483 - ox, 354 - oy);
        cuadrito3.addPoint(506 - ox, 332 - oy);
        cuadrito3.addPoint(482 - ox, 309 - oy);
        cuadrito3.addPoint(457 - ox, 330 - oy);
        g.fillPolygon(cuadrito3);

        Polygon cuadrito4 = new Polygon();
        cuadrito4.addPoint(434 - ox, 406 - oy);
        cuadrito4.addPoint(458 - ox, 429 - oy);
        cuadrito4.addPoint(483 - ox, 401 - oy);
        cuadrito4.addPoint(459 - ox, 381 - oy);
        g.fillPolygon(cuadrito4);

        //Casillas aspas cruzeta izquierda arriba
        int x1 = 300 - ox;
        int y1 = 216 - oy;
        int x2 = 346 - ox;
        int y2 = 174 - oy;
        g.setColor(Color.WHITE);
        for (int i = 0; i < 6; i++) {
            g.drawLine(x1, y1, x2, y2);
            x1 += 27;
            y1 += 27;
            x2 += 27;
            y2 += 27;
        }
//**********************************************************//
        //Casillas aspas cruzeta derecha arriba
        int x3 = 670 - ox;
        int y3 = 175 - oy;
        int x4 = 712 - ox;
        int y4 = 215 - oy;

        for (int i = 0; i < 6; i++) {
            g.drawLine(x3, y3, x4, y4);
            x3 -= 27;
            y3 += 27;
            x4 -= 27;
            y4 += 27;

        }
//**********************************************************//
        //Casillas aspas cruzeta derecha abajo
        int x5 = 669 - ox;
        int y5 = 584 - oy;
        int x6 = 712 - ox;
        int y6 = 541 - oy;

        for (int i = 0; i < 6; i++) {
            g.drawLine(x5, y5, x6, y6);

            x5 -= 27;
            y5 -= 27;
            x6 -= 27;
            y6 -= 27;

        }
//**********************************************************//
        //Casillas aspas cruzeta izquierda abajo
        int x7 = 303 - ox;
        int y7 = 543 - oy;
        int x8 = 344 - ox;
        int y8 = 583 - oy;

        for (int i = 0; i < 6; i++) {
            g.drawLine(x7, y7, x8, y8);
            x7 += 27;
            y7 -= 27;
            x8 += 27;
            y8 -= 27;
        }
        g.setColor(Color.BLACK);

        //esquina 1
        g2d.drawLine(344 - ox, 585 - oy, 315 - ox, 600 - oy);
        g2d.drawLine(315 - ox, 600 - oy, 284 - ox, 572 - oy);
        g2d.drawLine(284 - ox, 572 - oy, 298 - ox, 540 - oy);

        //esquina 2
        g2d.drawLine(669 - ox, 587 - oy, 699 - ox, 601 - oy);
        g2d.drawLine(699 - ox, 601 - oy, 730 - ox, 571 - oy);
        g2d.drawLine(730 - ox, 571 - oy, 714 - ox, 541 - oy);

        //esquina 3
        g2d.drawLine(713 - ox, 218 - oy, 731 - ox, 186 - oy);
        g2d.drawLine(731 - ox, 186 - oy, 697 - ox, 155 - oy);
        g2d.drawLine(697 - ox, 155 - oy, 671 - ox, 171 - oy);

        //esquina 4 
        g2d.drawLine(344 - ox, 173 - oy, 316 - ox, 156 - oy);

        g2d.drawLine(316 - ox, 156 - oy, 282 - ox, 187 - oy);

        g2d.drawLine(282 - ox, 187 - oy, 299 - ox, 217 - oy);

        //lineas de aspas
        g.setColor(Color.BLACK);
        g2d.drawLine(300 - ox, 539 - oy, 668 - ox, 174 - oy);
        g2d.drawLine(345 - ox, 585 - oy, 713 - ox, 219 - oy);
        g2d.drawLine(301 - ox, 220 - oy, 668 - ox, 586 - oy);
        g2d.drawLine(345 - ox, 174 - oy, 713 - ox, 540 - oy);
        g.setColor(Color.white);
        //lineas de enmedio
        g2d.drawLine(300 - ox, 586 - oy, 712 - ox, 174 - oy);
        g2d.drawLine(301 - ox, 173 - oy, 713 - ox, 586 - oy);

        //Triangulos Rojos de Doble Apuesta
        g.setColor(new Color(255, 30, 23));
        Polygon Triangulo = new Polygon();
        Triangulo.addPoint(676 - ox, 255 - oy);
        Triangulo.addPoint(654 - ox, 186 - oy);
        Triangulo.addPoint(630 - ox, 210 - oy);
        Triangulo.addPoint(699 - ox, 231 - oy);
        g.fillPolygon(Triangulo);

        Polygon Triangulo2 = new Polygon();
        Triangulo2.addPoint(383 - ox, 213 - oy);
        Triangulo2.addPoint(313 - ox, 234 - oy);
        Triangulo2.addPoint(337 - ox, 258 - oy);
        Triangulo2.addPoint(358 - ox, 186 - oy);
        g.fillPolygon(Triangulo2);

        Polygon Triangulo3 = new Polygon();
        Triangulo3.addPoint(361 - ox, 570 - oy);
        Triangulo3.addPoint(339 - ox, 498 - oy);
        Triangulo3.addPoint(315 - ox, 522 - oy);
        Triangulo3.addPoint(385 - ox, 544 - oy);
        g.fillPolygon(Triangulo3);

        Polygon Triangulo4 = new Polygon();
        Triangulo4.addPoint(628 - ox, 545 - oy);
        Triangulo4.addPoint(694 - ox, 523 - oy);
        Triangulo4.addPoint(670 - ox, 498 - oy);
        Triangulo4.addPoint(652 - ox, 568 - oy);
        g.fillPolygon(Triangulo4);

//         g.setColor(new Color(102,51,0));
        //                  g.fillOval(arreglo[indice2][0], arreglo[indice2][1], 20, 20); 
        
        if(state != null){
            Casilla[] casillas = state.tablero.getCasillas();
            for (Casilla casilla : casillas) {
                if(casilla.ficha != null){
                    g.setColor(casilla.ficha.colorf);
                    g.fillOval(arreglo[casilla.pos][0], arreglo[casilla.pos][1], 20, 20);                     
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        pintartablero(g);
    }

}
