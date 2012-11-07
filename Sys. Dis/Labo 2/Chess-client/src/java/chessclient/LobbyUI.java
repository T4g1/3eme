/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chessclient;

import Entity.Echiquier;
import Session.LobbySessionRemote;
import TableModel.EchiquierTableModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Fenetre permettant de rejoindre ou crÃ©er une nouvelle partie
 * @author T4g1
 */
public class LobbyUI extends javax.swing.JFrame {
    private LobbySessionRemote lobbySession;
    
    /**
     * Creates new form GameUI
     */
    public LobbyUI() throws Exception {
        lobbySession = lookupLobbySessionRemote();
        
        initComponents();
    }
    
    /**
     * Met a jour la liste des parties
     */
    public void refresh() {
        Echiquier[] l_echiquier = lobbySession.getListing();
        
        EchiquierTableModel model = (EchiquierTableModel) jTableParties.getModel();
        model.setListing(l_echiquier);
        
        // Redessine le jTable
        jTableParties.invalidate();
        
        // Indique le nombre de parties trouvées
        if(l_echiquier.length <= 0) {
            labelLobbyState.setText("Aucune partie trouvée ...");
            rejoindrePartie.setEnabled(false);
        } else {
            labelLobbyState.setText(l_echiquier.length + " partie(s) trouvée(s) ...");
            rejoindrePartie.setEnabled(true);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableParties = new javax.swing.JTable();
        creerPartie = new javax.swing.JButton();
        nameEchiquier = new javax.swing.JTextField();
        rejoindrePartie = new javax.swing.JButton();
        refreshListing = new javax.swing.JButton();
        labelLobbyState = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTableParties.setModel(new EchiquierTableModel());
        jTableParties.setCellSelectionEnabled(false);
        jTableParties.setRowSelectionAllowed(true);
        jTableParties.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableParties.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableParties);
        jTableParties.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        creerPartie.setText("Créer une partie");
        creerPartie.setToolTipText("Crée une partie avec le nom saisit ci-contre");
        creerPartie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creerPartieActionPerformed(evt);
            }
        });

        nameEchiquier.setText("Partie sans nom");
        nameEchiquier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameEchiquierActionPerformed(evt);
            }
        });

        rejoindrePartie.setText("Rejoindre partie");
        rejoindrePartie.setToolTipText("Rejoins la partie séléctionnée dans la liste");
        rejoindrePartie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rejoindrePartieActionPerformed(evt);
            }
        });

        refreshListing.setText("Rafraîchir");
        refreshListing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshListingActionPerformed(evt);
            }
        });

        labelLobbyState.setText("Aucune partie trouvée ...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(nameEchiquier, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(creerPartie)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(refreshListing)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rejoindrePartie))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelLobbyState)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelLobbyState)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rejoindrePartie)
                    .addComponent(nameEchiquier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(creerPartie)
                    .addComponent(refreshListing))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void creerPartieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creerPartieActionPerformed
        // Création de la partie
        long id = lobbySession.createEchiquier(nameEchiquier.getText());
        if(id > -1) {
            Main.showGame();
        } else {
            JOptionPane.showMessageDialog(
                    new JFrame(), "Création de partie échouée ...",
                    "Erreur lors de la création de partie",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_creerPartieActionPerformed

    private void nameEchiquierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameEchiquierActionPerformed
        // Rien a faire ici ...
    }//GEN-LAST:event_nameEchiquierActionPerformed

    private void rejoindrePartieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rejoindrePartieActionPerformed
        // Récupére la partie que l'on veut rejoindre
        // TODO
        
        int id = 0;
        int resultCode = lobbySession.joinEchiquier(id);
        
        switch(resultCode) {
            case -1:
                JOptionPane.showMessageDialog(
                        new JFrame(), "Impossible de rejoindre la partie",
                        "Une erreur est survenue ...",
                        JOptionPane.ERROR_MESSAGE
                );
                break;
            case 0:
                JOptionPane.showMessageDialog(
                        new JFrame(), "Impossible de rejoindre la partie",
                        "La partie est pleine ...",
                        JOptionPane.ERROR_MESSAGE
                );
                break;
            case 1:
                Main.showGame();
                break;
        }
    }//GEN-LAST:event_rejoindrePartieActionPerformed

    private void refreshListingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshListingActionPerformed
        refresh();
    }//GEN-LAST:event_refreshListingActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton creerPartie;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableParties;
    private javax.swing.JLabel labelLobbyState;
    private javax.swing.JTextField nameEchiquier;
    private javax.swing.JButton refreshListing;
    private javax.swing.JButton rejoindrePartie;
    // End of variables declaration//GEN-END:variables

    private LobbySessionRemote lookupLobbySessionRemote() {
        try {
            Context c = new InitialContext();
            return (LobbySessionRemote) c.lookup("java:comp/env/LobbySession");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
