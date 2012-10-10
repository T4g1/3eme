package EJBCalcul;
import java.util.Vector;
import javax.ejb.Stateful;

@Stateful
public class EJBCalculBean implements EJBCalculRemote
{
    private Vector l_nombre;
    private int minimum;
    private int maximum;
    
    public EJBCalculBean()
    {
        l_nombre = new Vector();
        minimum = -1;
        maximum = -1;
    }
    
    public void addNombre(int nombre)
    {
        l_nombre.add(nombre);
        
        if(nombre < minimum || minimum == -1)
        {
            minimum = nombre;
        }
        
        if(nombre > maximum || maximum == -1)
        {
            maximum = nombre;
        }
    }
    
    public int getSomme()
    {
        int somme = 0;
        
        for(Object nombre: l_nombre)
        {
            somme += (int)nombre;
        }
        
        return somme;
    }
    
    public float getMoyenne()
    {
        return getSomme() / l_nombre.size();
    }
    
    public int getMinimum()
    {
        return minimum;
    }
    
    public int getMaximum()
    {
        return maximum;
    }
}