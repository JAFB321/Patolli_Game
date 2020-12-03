package GUI;

import Logica.Jugador;
import static GUI.frmPrincipalPatolli.PanelPrincipal;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;


public class IniciarJuego extends javax.swing.JPanel {

    AccionesGUI Acciones;
    
    private Jugador[] players;
    private int num = 2;

    public IniciarJuego(AccionesGUI Acciones) {
        initComponents();
        this.setMinimumSize(new Dimension(485, 523));
        //Quitar fondo a botón
        btnsig.setOpaque(false);
        btnsig.setContentAreaFilled(false);
        btnsig.setBorderPainted(false);      
        
        this.Acciones = Acciones;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        txtjugador = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Opcion1 = new javax.swing.JRadioButton();
        Opcion2 = new javax.swing.JRadioButton();
        Opcion3 = new javax.swing.JRadioButton();
        btnsig = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 153, 0), 14, true));
        setMaximumSize(new java.awt.Dimension(666, 416));
        setMinimumSize(new java.awt.Dimension(666, 416));
        setPreferredSize(new java.awt.Dimension(666, 416));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtjugador.setBackground(new java.awt.Color(0, 0, 0));
        txtjugador.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        txtjugador.setForeground(new java.awt.Color(255, 255, 255));
        txtjugador.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtjugador.setText("Ingresa tu nombre");
        add(txtjugador, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, 350, 40));
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(372, 489, 113, 34));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Perpetua Titling MT", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ASIGNA JUGADORES");
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 1, 1, 1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 370, 50));

        Opcion1.setBackground(new java.awt.Color(255, 255, 0));
        buttonGroup1.add(Opcion1);
        Opcion1.setFont(new java.awt.Font("Segoe UI Black", 0, 48)); // NOI18N
        Opcion1.setForeground(new java.awt.Color(102, 0, 0));
        Opcion1.setSelected(true);
        Opcion1.setText("2 ");
        Opcion1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 51, 0), 4, true));
        Opcion1.setBorderPainted(true);
        Opcion1.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        Opcion1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(Opcion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 70, 130, 80));

        Opcion2.setBackground(new java.awt.Color(255, 255, 0));
        buttonGroup1.add(Opcion2);
        Opcion2.setFont(new java.awt.Font("Segoe UI Black", 0, 48)); // NOI18N
        Opcion2.setForeground(new java.awt.Color(102, 0, 0));
        Opcion2.setText("3");
        Opcion2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 51, 0), 4, true));
        Opcion2.setBorderPainted(true);
        Opcion2.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        Opcion2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(Opcion2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 160, 130, 80));

        Opcion3.setBackground(new java.awt.Color(255, 255, 0));
        buttonGroup1.add(Opcion3);
        Opcion3.setFont(new java.awt.Font("Segoe UI Black", 0, 48)); // NOI18N
        Opcion3.setForeground(new java.awt.Color(102, 0, 0));
        Opcion3.setText("4");
        Opcion3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 51, 0), 4, true));
        Opcion3.setBorderPainted(true);
        Opcion3.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        Opcion3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(Opcion3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 250, 130, 80));

        btnsig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/botonsiguiente (3).png"))); // NOI18N
        btnsig.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/botonsiguiente (3).png"))); // NOI18N
        btnsig.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/botonsiguiente (2).png"))); // NOI18N
        btnsig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsigActionPerformed(evt);
            }
        });
        add(btnsig, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 220, 120));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/FondoJugadores.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        jLabel1.setMaximumSize(new java.awt.Dimension(666, 416));
        jLabel1.setMinimumSize(new java.awt.Dimension(666, 416));
        jLabel1.setPreferredSize(new java.awt.Dimension(666, 416));
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 640, 400));
    }// </editor-fold>//GEN-END:initComponents

    private void btnsigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsigActionPerformed

        int nJugadores = 0;
        if(Opcion1.isSelected()) nJugadores = 2;
        else if(Opcion2.isSelected()) nJugadores = 3;
        else if(Opcion3.isSelected()) nJugadores = 4;
        
        Jugador player = new Jugador();
        player.Nombre = txtjugador.getText();        
        
        Acciones.openFichasYapuesta(player, nJugadores);
    }//GEN-LAST:event_btnsigActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton Opcion1;
    private javax.swing.JRadioButton Opcion2;
    private javax.swing.JRadioButton Opcion3;
    private javax.swing.JButton btnsig;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtjugador;
    // End of variables declaration//GEN-END:variables

}
