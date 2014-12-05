/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.physics.api;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author kppx
 */
class RigidBodyShape {

    @Getter @Setter String type;
    @Getter @Setter double r, x, y, z, h;
    @Getter @Setter double mass;
}
