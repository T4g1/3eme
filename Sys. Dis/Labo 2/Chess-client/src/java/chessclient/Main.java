/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chessclient;

import Session.NewSessionBeanRemote;
import javax.ejb.EJB;

/**
 *
 * @author T4g1
 */
public class Main {
    @EJB
    private static NewSessionBeanRemote newSessionBean;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        newSessionBean.businessMethod("param");
    }
}
