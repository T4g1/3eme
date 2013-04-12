/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entity.Simulation;
import javax.ejb.Remote;

/**
 *
 * @author T4g1
 */
@Remote
public interface CalculeMensualiteRemote {
    Simulation getMensualite(int captial, int duree, int ta);
}
