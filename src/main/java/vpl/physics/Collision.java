/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.physics;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author kppx
 */
public class Collision {

    @Getter @Setter private RigidBody rigidBody1;
    @Getter @Setter private RigidBody rigidBody2;

    public boolean collisionPossible() {
        //not ready yet
        return boundingBoxesCheck();
    }

    public boolean collisionHappened() {
        //not ready yet
        return detailedCheck();
    }

    private boolean boundingBoxesCheck() {
        return true;
    }

    private boolean detailedCheck() {
        return true;
    }

    public void solveCollisions() {
    }
}
