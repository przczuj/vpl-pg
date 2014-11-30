/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.physics;

/**
 *
 * @author kppx
 */
public class Collision {
    RigidBody rigidBody1;
    RigidBody rigidBody2;
    public boolean collisionPossible()
    {
     //not ready yet
        return boundingBoxesCheck();
    }
    public boolean collisionHappened()
    {
     //not ready yet
        return detailedCheck();
    }

    private boolean boundingBoxesCheck()
    {
        return true;
    }

    private boolean detailedCheck() 
    {
        return true;
    }

    public RigidBody getRigidBody1() {
        return rigidBody1;
    }

    public void setRigidBody1(RigidBody a) {
        this.rigidBody1 = a;
    }

    public RigidBody getRigidBody2() {
        return rigidBody2;
    }

    public void setRigidBody2(RigidBody b) {
        this.rigidBody2 = b;
    }
    public void solveCollisions()
    {
        
    }
}
