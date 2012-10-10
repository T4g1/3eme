package EJBCalcul;
import javax.ejb.Remote;

@Remote
public interface EJBCalculRemote
{
    void addNombre(int nombre);
    int getSomme();
    float getMoyenne();
    int getMinimum();
    int getMaximum();
}