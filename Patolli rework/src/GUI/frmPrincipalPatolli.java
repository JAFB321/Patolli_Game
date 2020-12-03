package GUI;

import static GUI.frmPrincipalPatolli.PanelPrincipal;



public class frmPrincipalPatolli extends javax.swing.JFrame {
    
    public AccionesGUI Acciones;
    

    public frmPrincipalPatolli() {
        initComponents();
        this.setLocationRelativeTo(this);
        PanelPrincipal.setVisible(true);
        btnAnterior.addActionListener(null);       
    }
    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelFondo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblSerpienteDerecha = new javax.swing.JLabel();
        PanelPrincipal = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        lblImagencruz = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblSerpienteIzq = new javax.swing.JLabel();
        PanelFondoTotal = new javax.swing.JPanel();
        btnAnterior = new javax.swing.JButton();

        jLabelFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/fondomorado.jpg"))); // NOI18N
        jLabelFondo.setText("jLabel1");

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 0, 102));
        setMinimumSize(new java.awt.Dimension(892, 517));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSerpienteDerecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/Serpiente.png"))); // NOI18N
        lblSerpienteDerecha.setText("jLabel3");
        getContentPane().add(lblSerpienteDerecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 57, 170, 450));

        PanelPrincipal.setBackground(new java.awt.Color(0, 0, 0));
        PanelPrincipal.setMaximumSize(new java.awt.Dimension(666, 416));
        PanelPrincipal.setMinimumSize(new java.awt.Dimension(666, 416));
        PanelPrincipal.setPreferredSize(new java.awt.Dimension(666, 416));
        PanelPrincipal.setLayout(null);

        jButton2.setBackground(new java.awt.Color(255, 191, 0));
        jButton2.setFont(new java.awt.Font("Viner Hand ITC", 0, 36)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 102, 102));
        jButton2.setText("JUGAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        PanelPrincipal.add(jButton2);
        jButton2.setBounds(220, 250, 250, 80);

        lblImagencruz.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/Cruz.png"))); // NOI18N
        PanelPrincipal.add(lblImagencruz);
        lblImagencruz.setBounds(90, 200, 542, 471);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/TituloPatolli.gif"))); // NOI18N
        jLabel2.setText("jLabel2");
        PanelPrincipal.add(jLabel2);
        jLabel2.setBounds(60, -50, 540, 310);

        getContentPane().add(PanelPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 660, 520));

        lblSerpienteIzq.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/serpienteizq.png"))); // NOI18N
        lblSerpienteIzq.setText("jLabel3");
        getContentPane().add(lblSerpienteIzq, new org.netbeans.lib.awtextra.AbsoluteConstraints(-110, 80, -1, -1));

        PanelFondoTotal.setBackground(new java.awt.Color(0, 0, 0));
        PanelFondoTotal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAnterior.setBackground(new java.awt.Color(255, 51, 51));
        btnAnterior.setText("ATRÁS");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });
        PanelFondoTotal.add(btnAnterior, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        getContentPane().add(PanelFondoTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 520));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        Acciones.openConectarYUnirse();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed

    }//GEN-LAST:event_btnAnteriorActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelFondoTotal;
    public static javax.swing.JPanel PanelPrincipal;
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelFondo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblImagencruz;
    private javax.swing.JLabel lblSerpienteDerecha;
    private javax.swing.JLabel lblSerpienteIzq;
    // End of variables declaration//GEN-END:variables
}
