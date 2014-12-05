/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.physics.api;

import lombok.Getter;
import lombok.Setter;
import vpl.physics.AxisAngle;
import vpl.math.Triple;

/**
 *
 * @author kppx
 */
public class RigidBodyState {

    @Getter @Setter private AxisAngle angles;
    @Getter @Setter private Triple location;
}
