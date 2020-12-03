
package GUI;

import Logica.Jugador;

public class Fichasyapuesta extends javax.swing.JPanel {

    AccionesGUI Acciones;
    
    Jugador player;
    int nJugadores;

    public Fichasyapuesta(AccionesGUI Acciones) {
        initComponents();
        this.setVisible(true);
        this.Acciones = Acciones;
    }

    public void setParams(Jugador player, int nJugadores) {        
        this.nJugadores = nJugadores;
        this.player = player;
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnIniciarPartida = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        spCantidadInicial = new javax.swing.JSpinner();
        spApuesta = new javax.swing.JSpinner();
        spNFichas = new javax.swing.JSpinner();
        lblCantidadInicial = new javax.swing.JLabel();

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Perpetua Titling MT", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText(" Número de fichas");
        jLabel7.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 1, 1, 1));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 153, 0), 14, true));
        setMaximumSize(new java.awt.Dimension(666, 416));
        setMinimumSize(new java.awt.Dimension(666, 416));
        setPreferredSize(new java.awt.Dimension(666, 416));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Perpetua Titling MT", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText(" Número de fichas");
        jLabel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 1, 1, 1));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 370, 50));

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Perpetua Titling MT", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("apuestas");
        jLabel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 1, 1, 1));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 370, 50));

        jLabel3.setFont(new java.awt.Font("Californian FB", 3, 18)); // NOI18N
        jLabel3.setText("CANTIDAD INICIAL:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 240, -1));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Californian FB", 3, 18)); // NOI18N
        jLabel1.setText("CANTIDAD A PAGAR POR APUESTA:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 360, -1));

        btnIniciarPartida.setBackground(new java.awt.Color(204, 153, 0));
        btnIniciarPartida.setFont(new java.awt.Font("Viner Hand ITC", 3, 18)); // NOI18N
        btnIniciarPartida.setText("INICIAR PARTIDA");
        btnIniciarPartida.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 4, true));
        btnIniciarPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarPartidaActionPerformed(evt);
            }
        });
        add(btnIniciarPartida, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 260, 200, 110));

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Perpetua Titling MT", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("APUESTAS");
        jLabel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 1, 1, 1));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addComponent(jLabel6)
                .addContainerGap(136, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, -1, 50));

        spCantidadInicial.setFont(new java.awt.Font("Viner Hand ITC", 0, 24)); // NOI18N
        spCantidadInicial.setModel(new javax.swing.SpinnerNumberModel(2, 2, null, 1));
        spCantidadInicial.setToolTipText("");
        spCantidadInicial.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 0, 0), 4, true));
        spCantidadInicial.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        spCantidadInicial.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        spCantidadInicial.setValue(100);
        add(spCantidadInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 160, 50));

        spApuesta.setFont(new java.awt.Font("Viner Hand ITC", 0, 24)); // NOI18N
        spApuesta.setModel(new javax.swing.SpinnerNumberModel(2, 2, null, 1));
        spApuesta.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 0, 0), 4, true));
        spApuesta.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        spApuesta.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        add(spApuesta, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 350, 160, 50));

        spNFichas.setFont(new java.awt.Font("Viner Hand ITC", 0, 36)); // NOI18N
        spNFichas.setModel(new javax.swing.SpinnerNumberModel(2, 2, 6, 1));
        spNFichas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 0, 0), 4, true));
        spNFichas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        spNFichas.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        add(spNFichas, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, 100, 60));

        lblCantidadInicial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/FondoJugadores.jpg"))); // NOI18N
        lblCantidadInicial.setText("INICIAL");
        lblCantidadInicial.setMaximumSize(new java.awt.Dimension(666, 416));
        lblCantidadInicial.setMinimumSize(new java.awt.Dimension(666, 416));
        lblCantidadInicial.setName(""); // NOI18N
        lblCantidadInicial.setPreferredSize(new java.awt.Dimension(666, 416));
        add(lblCantidadInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 640, 400));
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarPartidaActionPerformed
             
        int nFichas = (int) spNFichas.getValue();
        int apuesta = (int) spApuesta.getValue();
        int cantidadInicial = (int) spCantidadInicial.getValue();
        
        Acciones.CreateGame(player, apuesta, cantidadInicial, nFichas, nJugadores);
        Acciones.openConectarYUnirse();
    }//GEN-LAST:event_btnIniciarPartidaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciarPartida;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lblCantidadInicial;
    private javax.swing.JSpinner spApuesta;
    private javax.swing.JSpinner spCantidadInicial;
    private javax.swing.JSpinner spNFichas;
    // End of variables declaration//GEN-END:variables
}
