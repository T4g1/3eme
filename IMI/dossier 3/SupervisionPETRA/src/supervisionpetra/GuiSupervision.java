package supervisionpetra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GuiSupervision extends javax.swing.JFrame {
    private final int L1 = 0;
    private final int L2 = 1;
    private final int T = 2;
    private final int S = 3;
    private final int CS = 4;
    private final int AP = 5;
    private final int PP = 6;
    private final int DE = 7;
    
    private Petra petra;
    private ServerSocket server;
    public GuiSupervision() {
        initComponents();
        changeActuateurs(0);
        petra = new Petra("10.59.40.67", 27016);
        
        System.out.println("Ajout du changeSlider");
        setCP.addChangeListener(new changeSlider());
                
        System.out.println("Ecoute du PETRA");
        Server server = new Server(this);
        server.start();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        labelEtat = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        labelCP = new javax.swing.JLabel();
        labelC1 = new javax.swing.JLabel();
        labelC2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        labelPV = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        labelPA = new javax.swing.JLabel();
        labelAA = new javax.swing.JLabel();
        labelGA = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        labelCS = new javax.swing.JLabel();
        labelAP = new javax.swing.JLabel();
        labelPP = new javax.swing.JLabel();
        labelDE = new javax.swing.JLabel();
        labelS = new javax.swing.JLabel();
        labelT = new javax.swing.JLabel();
        labelL2 = new javax.swing.JLabel();
        labelL1 = new javax.swing.JLabel();
        switchState = new javax.swing.JButton();
        switchC1 = new javax.swing.JCheckBox();
        switchC2 = new javax.swing.JCheckBox();
        switchPV = new javax.swing.JCheckBox();
        switchPA = new javax.swing.JCheckBox();
        switchAA = new javax.swing.JCheckBox();
        switchGA = new javax.swing.JCheckBox();
        setCP = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Etat général:");

        labelEtat.setText("labelEtat");

        jLabel3.setText("Position chariot:");

        jLabel4.setText("Convoyeur 1:");

        jLabel5.setText("Convoyeur 2:");

        labelCP.setText("labelCP");

        labelC1.setText("labelC1");

        labelC2.setText("labelC2");

        jLabel9.setText("Aspiration plongeur:");

        labelPV.setText("labelPV");

        jLabel11.setText("Plongeur activé:");

        jLabel12.setText("Arbre activé:");

        jLabel13.setText("Grappin activé:");

        labelPA.setText("labelPA");

        labelAA.setText("labelAA");

        labelGA.setText("labelGA");

        jLabel17.setText("L1:");

        jLabel18.setText("L2:");

        jLabel19.setText("Epaisseur:");

        jLabel20.setText("S:");

        jLabel21.setText("Chariot stable:");

        jLabel22.setText("Arbre position:");

        jLabel23.setText("Plongeur position:");

        jLabel24.setText("Dispenser vide:");

        labelCS.setText("labelCS");

        labelAP.setText("labelAP");

        labelPP.setText("labelPP");

        labelDE.setText("labelDE");

        labelS.setText("labelS");

        labelT.setText("labelT");

        labelL2.setText("labelL2");

        labelL1.setText("labelL1");

        switchState.setText("jButton1");
        switchState.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                switchStateActionPerformed(evt);
            }
        });

        switchC1.setText("Activer convoyeur 1");
        switchC1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                switchC1ActionPerformed(evt);
            }
        });

        switchC2.setText("Activer convoyeur 2");
        switchC2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                switchC2ActionPerformed(evt);
            }
        });

        switchPV.setText("Aspirer");
        switchPV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                switchPVActionPerformed(evt);
            }
        });

        switchPA.setText("Activer le plongeur");
        switchPA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                switchPAActionPerformed(evt);
            }
        });

        switchAA.setText("Activer l'arbre");
        switchAA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                switchAAActionPerformed(evt);
            }
        });

        switchGA.setText("Activer le grappin");
        switchGA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                switchGAActionPerformed(evt);
            }
        });

        setCP.setMaximum(3);
        setCP.setMinorTickSpacing(1);
        setCP.setPaintLabels(true);
        setCP.setPaintTicks(true);
        setCP.setSnapToTicks(true);
        setCP.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel2.setText("Position du chariot:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelEtat)
                            .addComponent(labelC1)
                            .addComponent(labelCP)
                            .addComponent(labelC2)
                            .addComponent(labelPV)
                            .addComponent(labelPA)
                            .addComponent(labelAA)
                            .addComponent(labelGA)))
                    .addComponent(jLabel5)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(switchC1)
                    .addComponent(switchC2)
                    .addComponent(switchPV)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(setCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(switchGA)
                    .addComponent(switchAA)
                    .addComponent(switchPA)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24)
                            .addComponent(jLabel20)
                            .addComponent(jLabel19)
                            .addComponent(jLabel18)
                            .addComponent(jLabel17))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelL1)
                            .addComponent(labelL2)
                            .addComponent(labelT)
                            .addComponent(labelS)
                            .addComponent(labelDE)
                            .addComponent(labelPP)
                            .addComponent(labelAP)
                            .addComponent(labelCS)))
                    .addComponent(switchState))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(labelEtat)
                    .addComponent(switchState))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(labelCP)
                    .addComponent(jLabel17)
                    .addComponent(labelL1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(labelC1)
                    .addComponent(jLabel18)
                    .addComponent(labelL2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(labelC2)
                    .addComponent(jLabel19)
                    .addComponent(labelT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(labelPV)
                    .addComponent(jLabel20)
                    .addComponent(labelS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(labelPA)
                    .addComponent(jLabel21)
                    .addComponent(labelCS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(labelAA)
                    .addComponent(jLabel22)
                    .addComponent(labelAP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(labelGA)
                    .addComponent(jLabel23)
                    .addComponent(labelPP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(labelDE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(switchC1)
                    .addComponent(switchPA))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(switchC2)
                    .addComponent(switchAA))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(switchPV)
                    .addComponent(switchGA))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(setCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(140, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Eteint/Allume le dispositif
     */
    private void switchStateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_switchStateActionPerformed
        petra.switchState();
    }//GEN-LAST:event_switchStateActionPerformed

    private void switchC1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_switchC1ActionPerformed
        if(((javax.swing.JCheckBox)(evt.getSource())).isSelected()) {
            petra.setActuateur(Petra.C1, true);
        }
        else {
            petra.setActuateur(Petra.C1, false);
        }
    }//GEN-LAST:event_switchC1ActionPerformed

    private void switchC2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_switchC2ActionPerformed
        if(((javax.swing.JCheckBox)(evt.getSource())).isSelected()) {
            petra.setActuateur(Petra.C2, true);
        }
        else {
            petra.setActuateur(Petra.C2, false);
        }
    }//GEN-LAST:event_switchC2ActionPerformed

    private void switchPVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_switchPVActionPerformed
        if(((javax.swing.JCheckBox)(evt.getSource())).isSelected()) {
            petra.setActuateur(Petra.PV, true);
        }
        else {
            petra.setActuateur(Petra.PV, false);
        }
    }//GEN-LAST:event_switchPVActionPerformed

    private void switchPAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_switchPAActionPerformed
        if(((javax.swing.JCheckBox)(evt.getSource())).isSelected()) {
            petra.setActuateur(Petra.PA, true);
        }
        else {
            petra.setActuateur(Petra.PA, false);
        }
    }//GEN-LAST:event_switchPAActionPerformed

    private void switchAAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_switchAAActionPerformed
        if(((javax.swing.JCheckBox)(evt.getSource())).isSelected()) {
            petra.setActuateur(Petra.AA, true);
        }
        else {
            petra.setActuateur(Petra.AA, false);
        }
    }//GEN-LAST:event_switchAAActionPerformed

    private void switchGAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_switchGAActionPerformed
        if(((javax.swing.JCheckBox)(evt.getSource())).isSelected()) {
            petra.setActuateur(Petra.GA, true);
        }
        else {
            petra.setActuateur(Petra.GA, false);
        }
    }//GEN-LAST:event_switchGAActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GuiSupervision.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GuiSupervision().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel labelAA;
    private javax.swing.JLabel labelAP;
    private javax.swing.JLabel labelC1;
    private javax.swing.JLabel labelC2;
    private javax.swing.JLabel labelCP;
    private javax.swing.JLabel labelCS;
    private javax.swing.JLabel labelDE;
    private javax.swing.JLabel labelEtat;
    private javax.swing.JLabel labelGA;
    private javax.swing.JLabel labelL1;
    private javax.swing.JLabel labelL2;
    private javax.swing.JLabel labelPA;
    private javax.swing.JLabel labelPP;
    private javax.swing.JLabel labelPV;
    private javax.swing.JLabel labelS;
    private javax.swing.JLabel labelT;
    private javax.swing.JSlider setCP;
    private javax.swing.JCheckBox switchAA;
    private javax.swing.JCheckBox switchC1;
    private javax.swing.JCheckBox switchC2;
    private javax.swing.JCheckBox switchGA;
    private javax.swing.JCheckBox switchPA;
    private javax.swing.JCheckBox switchPV;
    private javax.swing.JButton switchState;
    // End of variables declaration//GEN-END:variables

    private void changeActuateurs(int value) {
        String bin = Integer.toBinaryString(value);
        System.out.println("Recu du serveur: " + bin);
        
        char[] l_bit = bin.toCharArray();
        char bitVal;
        // Pour les 8 capteurs (bit)
        for(int bit=0; bit<8; bit++) {
            // Bit dans le tableau recu
            if(bit < l_bit.length) {
                bitVal = l_bit[(l_bit.length-1) - bit];
            } else {
                // Bit non recu, donc a 0
                bitVal = '0';
            }
            
            System.out.println("bit " + bit + ": " + bitVal);
            setCapteur(bit, bitVal == '1');
        }
    }

    /**
     * Modifie ce qui est affiché pour le capteur donné
     * @param capteur
     * @param value
     */
    private void setCapteur(int capteur, boolean value) {
        switch(capteur) {
            case L1:
                labelL1.setText((value ? "ON": "OFF"));
                break;
            case L2:
                labelL2.setText((value ? "ON": "OFF"));
                break;
            case T:
                labelT.setText((value ? "ON": "OFF"));
                break;
            case S:
                labelS.setText((value ? "ON": "OFF"));
                break;
            case CS:
                labelCS.setText((value ? "ON": "OFF"));
                break;
            case AP:
                labelAP.setText((value ? "ON": "OFF"));
                break;
            case PP:
                labelPP.setText((value ? "ON": "OFF"));
                break;
            case DE:
                labelDE.setText((value ? "ON": "OFF"));
                break;
        }
    }
    
    public class Server extends Thread
    {
        private final GuiSupervision parent;
        
        public Server(GuiSupervision parent)
        {
            this.parent = parent;
        }
        
        @Override
        public void run() {
            System.out.println("LISTEN");

            try
            {
                server = new ServerSocket(27015);
                server.setSoTimeout(100000);
            } catch (IOException ioe) {
                System.err.println("[Cannot initialize Server]\n" + ioe);
                return;
            }
            System.out.println("Le serveur est à l'écoute du port " + server.getLocalPort());
            Socket clientSocket = null;
            try {
                System.out.println("Attente client ...");
                clientSocket = server.accept();
            } 
            catch (IOException e) {
                System.out.println("Accept failed");
                return;
            }
            System.out.println("Client connecte");

            BufferedReader in;
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            } catch (IOException ex) {
                System.out.println("Input null");
                return;
            }
            System.out.println("Debut des communication");

            String inputLine;
            try {
                while(true) {
                    inputLine = in.readLine();
                    if(inputLine == null) {
                        System.out.println("?");
                        continue;
                    }
                    
                    try {
                        parent.changeActuateurs(Integer.parseInt(inputLine));
                    } catch(NumberFormatException ex) { }
                }
            } catch (IOException ex) {
                System.out.println("Client déconnecté");
            }
        }
    }
    
    public class changeSlider implements ChangeListener
    {
        int oldValue = -1;
        
        @Override
        public void stateChanged(ChangeEvent ce)
        {
            int value = ((javax.swing.JSlider)(ce.getSource())).getValue();
            if(oldValue == value) {
                return;
            }
            
            oldValue = value;
            
            // Doit envoyer CP puis CP + 1 pour
            // donner les deux bit d'un seul coup
            petra.setActuateur(Petra.CP,      (value & 1) == 1);
            petra.setActuateur(Petra.CP + 1,  (value >> 1) == 1);
        }
    }
}