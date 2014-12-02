/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.model;

import lombok.Getter;
import lombok.Setter;
import vpl.math.Triple;

public class VplForce {
    // vector + point
    @Getter @Setter private long insertTime;
    @Getter private VplForceState state;
}
