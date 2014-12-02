/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.model;

import java.util.Map;
import lombok.Getter;
import vpl.math.Triple;

class VplExperimentSnapshot {
    @Getter private Map<String, VplObjectState> objectStates;
    @Getter private Map<String, VplForceState> forces;
    @Getter private VplGravity gravity;
}
