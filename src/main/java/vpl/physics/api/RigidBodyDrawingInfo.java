/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.physics.api;

/**
 *
 * @author kppx
 */
public class RigidBodyDrawingInfo {
    private RigidBodyShape shape;
    private RigidBodyState state;

    public RigidBodyShape getShape() {
        return shape;
    }

    public void setShape(RigidBodyShape shape) {
        this.shape = shape;
    }

    public RigidBodyState getState() {
        return state;
    }

    public void setState(RigidBodyState state) {
        this.state = state;
    }
}
