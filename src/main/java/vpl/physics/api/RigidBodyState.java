/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.physics.api;

import vpl.physics.AxisAngle;
import vpl.physics.Triple;

/**
 *
 * @author kppx
 */
public class RigidBodyState {
    private AxisAngle angles;

    public AxisAngle getAngles() {
        return angles;
    }

    public void setAngles(AxisAngle angles) {
        this.angles = angles;
    }

    public Triple getLocation() {
        return location;
    }

    public void setLocation(Triple location) {
        this.location = location;
    }
    private Triple location;
}
