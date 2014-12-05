/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vpl.physics.controller;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import vpl.physics.Force;
import vpl.physics.RigidBody;

public class Experiment {

    @Getter @Setter private Map<String, RigidBody> rigidBodies;
    @Getter @Setter private Map<String, Force> uniformForces;

    public void saveAs(String filepath) {
        
    }
    
}
