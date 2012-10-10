package EJBCalcul;
import javax.ejb.EJB;

public class Main
{
    @EJB
    private static EJBCalcul.EJBCalculRemote eJBCalculRemote;
    
    public Main()
    {
    }
    
    public static void main(String[] args)
    {
        try
        {
            eJBCalculRemote.addNombre(3);
            eJBCalculRemote.addNombre(16);
            eJBCalculRemote.addNombre(52);
            eJBCalculRemote.addNombre(10);
            
            System.out.println("Sum: " + eJBCalculRemote.getSomme());
            System.out.println("Moy: " + eJBCalculRemote.getMoyenne());
            System.out.println("Min: " + eJBCalculRemote.getMinimum());
            System.out.println("Max: " + eJBCalculRemote.getMaximum());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}