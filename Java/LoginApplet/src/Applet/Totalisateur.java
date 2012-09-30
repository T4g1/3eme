/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Applet;

import java.util.ArrayList;
import javax.swing.JLabel;

/**
 * Totalise les nombres généré
 * 
 * @author T4g1
 */
public class Totalisateur implements Runnable {
    private AppletLogin parent;
    private ArrayList l_generator; 
    private int total;
    private JLabel chanceLabel;
    private JLabel chanceNumber;
    
    /**
     * Initialise la classe
     */
    public Totalisateur(JLabel chanceLabel, JLabel chanceNumber) {
        this.chanceLabel = chanceLabel;
        this.chanceNumber = chanceNumber;
        
        l_generator = new ArrayList();
        total = 0;
    }
    
    @Override
    public void run() {
        while(true) {
            try {
                total = 0;
                
                // Calcule la somme
                for(Object generator: l_generator) {
                    total += ((RandomGenerator)generator).getRandomNumber();
                }
                
                updateReduction();
                
                // Sleep de 10 secondes
                Thread.sleep(10000);
            } catch(InterruptedException e) {
            }
        }
    }
    
    /**
     * Ajoute un objet qui peut générer des nombres aléatoire
     * 
     * @param generator         Objet implémentant RandomGenerator
     */
    public void addGenerator(RandomGenerator generator) {
        l_generator.add(generator);
    }
    
    /**
     * Récupére le total
     * 
     * @return      Total calculé
     */
    public int getTotal() {
        return total;
    }
    
    /**
     * Donne la réduction appliquée en pourcent
     * 
     * @return     -1 si pas de reduction, >-1 si réduction
     */
    public synchronized int getReduction() {
        if((total % 2 == 0)) {
            return 10;
        }
        else {
            return -1;
        }
    }
    
    /**
     * Affiche le numéro de chance
     */
    private void updateReduction() {
        // Réduction active
        if((total % 2 == 0)) {
            chanceNumber.setText(String.valueOf(total));
            chanceNumber.setVisible(true);
            
            chanceLabel.setVisible(true);
        } else {
            chanceNumber.setVisible(false);
            chanceLabel.setVisible(false);
        }
    }
}
