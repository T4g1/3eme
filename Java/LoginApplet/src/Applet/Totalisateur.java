/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Applet;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
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
    private URL AdresseServeur;
    
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
    //<editor-fold defaultstate="collapsed" desc="get and set">
    
    
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
     * @return the AdresseServeur
     */
    public URL getAdresseServeur() {
        return AdresseServeur;
    }

    /**
     * @param AdresseServeur the AdresseServeur to set
     */
    public void setAdresseServeur(URL adresseServlet) {
        this.AdresseServeur = adresseServlet;
    }
    //</editor-fold>
    
    /**
     * Affiche le numéro de chance
     */
    private void updateReduction() {
        //récupération du numéro de chance depuis la servlet.
        //ouverture de la connexion
        URLConnection connexion;
        int MagicNumber = 1;
        try {
            connexion = AdresseServeur.openConnection();
            System.out.println("adresse > "+AdresseServeur.toString());
            //paramétrage de la connexion
            connexion.setUseCaches(false);
            connexion.setDefaultUseCaches(false);
            connexion.setDoOutput(true);//pour réception de message de la servlet
            System.out.println("Connexion permise");
            
            //ouverture du flux de sortie pour envoyer la requète
            ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
            PrintWriter pw = new PrintWriter(baos, true);
            System.out.println("print writer ok");
            
            //construction de la requète vers la servlet
            String query = "action="+URLEncoder.encode("GetNumber");
            pw.print(query);pw.flush();
            System.out.println("info printwriter ok");
            
            //construction des headers pour la requète
            connexion.setRequestProperty("Content-Length", String.valueOf(baos.size()));
            connexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            System.out.println("setrequestproperty ok");
            
            //envoie à la servlet
            baos.writeTo(connexion.getOutputStream());
            System.out.println("getoutputstream ok");
            //mise en place du mécanisme de réception
            BufferedReader input = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
            System.out.println("getinputstream ok");
            
            String reponse;
            while( (reponse = input.readLine()) != null){
                System.out.println("reponse: "+reponse);
                MagicNumber = Integer.parseInt(reponse);
            }
            
            
        } catch (IOException ex) {
            System.out.println("exception: "+ ex.getMessage());
        }
        
        // Réduction active
        if((total % MagicNumber == 0)) {
            chanceNumber.setText(String.valueOf(total));
            chanceNumber.setVisible(true);
            chanceLabel.setVisible(true);
        } else {
            chanceNumber.setText("no number");
            chanceNumber.setVisible(false);
            chanceLabel.setVisible(false);
        }
    }
}
