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
public class RigidBodyDrawingInfo {

    @Getter @Setter private RigidBodyShape shape;
    @Getter @Setter private RigidBodyState state;
}
