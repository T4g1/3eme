/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chessclient;

/**
 *
 * @author T4g1
 */
public class Main {
    private static IntroUI introUI;
    private static LobbyUI lobbyUI;
    private static GameUI gameUI;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        introUI = new IntroUI();
        lobbyUI = new LobbyUI();
        gameUI = new GameUI();
        
        showIntro();
    }
    
    /**
     * Affiche l'intro du jeu
     */
    public static void showIntro()
    {
        introUI.setVisible(true);
        lobbyUI.setVisible(false);
        gameUI.setVisible(false);
    }
    
    /**
     * Affiche le lobby du jeu
     */
    public static void showLobby()
    {
        introUI.setVisible(false);
        lobbyUI.setVisible(true);
        gameUI.setVisible(false);
    }
    
    /**
     * Affiche le jeu
     */
    public static void showGame()
    {
        introUI.setVisible(false);
        lobbyUI.setVisible(false);
        gameUI.setVisible(true);
    }
}
