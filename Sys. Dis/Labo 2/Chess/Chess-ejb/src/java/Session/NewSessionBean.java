/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entity.NewEntity;
import javax.ejb.Stateless;

/**
 *
 * @author T4g1
 */
@Stateless
public class NewSessionBean implements NewSessionBeanRemote {

    @Override
    public String businessMethod(String parameter) {
        return null;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method"

    @Override
    public void businessMethod2(NewEntity parameter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
