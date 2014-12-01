/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.physics.api;

import vpl.physics.RigidBody;
import vpl.physics.shapes.BallShape;
import vpl.physics.shapes.ConeShape;
import vpl.physics.shapes.CuboidShape;
import vpl.physics.shapes.CyllinderShape;

/**
 *
 * @author kppx
 */
public class RigidBodyApi {

    //used to extract data about current position((Triple - x location, y location, z location and rotation angles
    //( angle, x axis angle, y axis angle, z axis angle- adapted to be used with GUI)
    public RigidBodyState getState(RigidBody rb) {
        RigidBodyState state = new RigidBodyState();
        state.setAngles(rb.getRotationAngles());
        state.setLocation(rb.getPosition());
        return state;
    }

    public RigidBodyShape getShape(RigidBody rb) {
        RigidBodyShape shape = new RigidBodyShape();
        String type = rb.getShape().getType();
        shape.setType(type);
        shape.setMass(rb.getMass());
        switch (type) {
            case "BALL":
                BallShape bs = (BallShape) rb.getShape();
                shape.setR(bs.getR());
                break;
            case "CONE":
                ConeShape cs = (ConeShape) rb.getShape();
                shape.setH(cs.getH());
                shape.setR(cs.getR());
                break;

            case "CUBOID":
                CuboidShape cbs = (CuboidShape) rb.getShape();
                shape.setX(cbs.getX());
                shape.setY(cbs.getY());
                shape.setZ(cbs.getZ());
                break;

            case "CYLLINDER":
                CyllinderShape cls = (CyllinderShape) rb.getShape();
                shape.setH(cls.getH());
                shape.setR(cls.getR());
                break;
        }
        return shape;
    }
}
