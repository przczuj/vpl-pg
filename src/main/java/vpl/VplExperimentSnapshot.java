/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl;

import java.util.Map;
import vpl.math.Triple;

class VplExperimentSnapshot {
    private Map<String, VplObjectState> objectStates;
    private Map<String, Triple> forces;
    private VplGravity gravity;
}
