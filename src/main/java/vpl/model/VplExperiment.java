/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.model;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

public class VplExperiment {
    // state + rules of state change in time(/reaction)
    
    @Getter private Map<String, VplObject> objects;
    @Getter private Map<String, VplForce> forces;
    @Getter private VplGravity gravity;
    // private VplExperimentRules rules; // todo: dynamic object parameters defined with rules
    
    public VplExperiment() {
        objects = new HashMap<>();
        forces = new HashMap<>();
        gravity = new VplGravity();
    }    
    
}
