/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

/**
 *
 * @author freddy
 */
public class HighScore {
    private String pseudo;
    private Long score;

    public HighScore(String pseudo, Long score) {
        this.pseudo = pseudo;
        this.score = score;
    }

    public HighScore() {
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }
    
    
}
