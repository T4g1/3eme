/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import javax.ejb.Remote;

/**
 *
 * @author T4g1
 */
@Remote
public interface UserInfoRemote {
    public boolean isLogged();
    public void setLogged(boolean value);
}
