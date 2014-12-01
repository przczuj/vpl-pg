/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl;

import java.util.Map;

public class VplExperiment {
    // state + rules of state change in time(/reaction)
    
    private Map<String, VplObject> objects;
    private Map<String, VplForce> forces;
    private VplGravity gravity;
    // private VplExperimentRules rules;
}
