/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Applet;

import java.text.DateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author delskev
 */
public class DisplayDate extends javax.swing.JPanel implements Runnable {
    private Date dateHeure;
    private String myDate;
    private String pays;
    private int refreshTime;    // Rafraichir la date toutes les x secondes
    
    
    /**
     * Creates new form DisplayDate
     */
    public DisplayDate() {
        this("Europe/Brussels");
    }
    
    public DisplayDate(String zone) {
        setPays(zone);
        setRefreshTime(1);
        
        initComponents();
        showCurrentTime();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        display = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        display.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(display, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(display)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel display;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        showCurrentTime();
        
        try { 
            Thread.sleep(refreshTime * 1000);
        } catch(InterruptedException e) {
        }
    }
    
    /**
     * Affiche l'heure courante dans le label
     */
    public final void showCurrentTime() {
        dateHeure = new Date();
        
        TimeZone pstTime = TimeZone.getTimeZone(getPays());
        DateFormat df = DateFormat.getTimeInstance(DateFormat.DEFAULT); 
        df.setTimeZone(pstTime);
        
        setMyDate(df.format(dateHeure));

        display.setText(myDate);
    }

    //<editor-fold defaultstate="collapsed" desc="get and set">
    /**
     * @param myDate the myDate to set
     */
    private void setMyDate(String myDate) {
        this.myDate = myDate;
    }

    /**
     * @return the pays
     */
    public final String getPays() {
        return pays;
    }

    /**
     * @param pays the pays to set
     */
    public final void setPays(String pays) {
        this.pays = pays;
    }

    /**
     * Modifie la valeur de refreshTime
     * 
     * @param refreshTime     Nouvelle valeur
     */
    public final void setRefreshTime(int refreshTime) {
        this.refreshTime = refreshTime;
    }
    
    /**
     * Donne la valeur de refreshTime
     * 
     * @return      Temps d'attente en seconde entre chaque
     *              mise a jour de l'heure affichée
     */
    public int getRefresh() {
        return refreshTime;
    }
    //</editor-fold>
}
