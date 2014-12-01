package vpl.physics.controller;


import vpl.physics.Collision;
import vpl.physics.Force;
import vpl.physics.RigidBody;
import vpl.physics.shapes.Shape;
import vpl.physics.shapes.BallShape;
import vpl.math.Triple;
import vpl.physics.api.RigidBodyApi;
import vpl.physics.api.RigidBodyDrawingInfo;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kppx
 */
public class ControllerStub {
    private List<RigidBody> rigidBodies;
    private List<Force> uniformForces;

    public List<RigidBody> getRigidBodies() {
        return rigidBodies;
    }

    public void setRigidBodies(List<RigidBody> rigidBodies) {
        this.rigidBodies = rigidBodies;
    }

    public List<Force> getUniformForces() {
        return uniformForces;
    }

    public void setUniformForces(List<Force> uniformForces) {
        this.uniformForces = uniformForces;
    }


    public List<RigidBodyDrawingInfo> getToBeDrawn() {
        return toBeDrawn;
    }

    public void setToBeDrawn(List<RigidBodyDrawingInfo> toBeDrawn) {
        this.toBeDrawn = toBeDrawn;
    }
  
    private List<Collision> collisions;
    private List<RigidBodyDrawingInfo> toBeDrawn;
    private RigidBodyApi api;
    private void checkCollisions()
    {
           List<Collision> possibleCollisions;
           possibleCollisions = new ArrayList<Collision>();
        //checking if possible (not yet finally implemented
        //basically used to reduce the number of complex collision detection calculation
        for(RigidBody rb1 : rigidBodies)
        {
            for (RigidBody rb2 : rigidBodies)
            {
                //if rb1 and rb2 are both the same object, there won't be any collision...
             if (rb1.equals(rb2))
                 continue;
             Collision col = new Collision();
             col.setRigidBody1(rb1);
             col.setRigidBody2(rb2);
             if (col.collisionPossible())
                 possibleCollisions.add(col);
            }
        }
        for (Collision c : possibleCollisions )
        {
         if (c.collisionHappened())
             collisions.add(c);
        }
        
        
    }
    
    private void solveCollisions()
    {
     checkCollisions();
     for (Collision c : collisions)
         c.solveCollisions();
    }
    
    public ControllerStub()
    {
     init();   
    }
    //to be done every $timetick
    public void update()
    {
        toBeDrawn = new ArrayList<RigidBodyDrawingInfo>();
     for (RigidBody rb: rigidBodies)   
     {
         rb.calculate();
         RigidBodyDrawingInfo drawingInfo = new RigidBodyDrawingInfo();
         drawingInfo.setShape(api.getShape(rb));
         drawingInfo.setState(api.getState(rb));
         toBeDrawn.add(drawingInfo);
         
     }
     solveCollisions();
    }
    private void init()
    {
        rigidBodies = new ArrayList<RigidBody>();
      
        collisions = new ArrayList<Collision>();
        uniformForces = new ArrayList<Force>();
        api = new RigidBodyApi();
    }
   
    public void createRigidBodyExample() throws Exception
    {
        
        RigidBody rb = new RigidBody();
        BallShape shape = new BallShape();
        shape.setR(10); //r of the ball     
        rb.setShape(shape);
        rb.setTimeTick(20);//20 ms
        Triple initialPosition = new Triple(0,0,0);//starting location is 0,0,0
        rb.setPosition(initialPosition);
        rb.setMass(1);//1 kg
        rb.getShape().recalculate();
    }
    public void createRigidBody(Shape shape, Triple position, double timeTick,double mass) throws Exception
    {
        RigidBody rb = new RigidBody();
        rb.setShape(shape);
        rb.setTimeTick(timeTick);
        rb.setPosition(position);
        rb.setMass(mass);
        rb.getShape().recalculate();
        
    }
    public void createUniformForce(Triple value)
    {
        Force uniformForce = new Force();
        uniformForce.setForceValue(value);
        uniformForces.add(uniformForce);
        for (RigidBody rb : rigidBodies)
        {
         rb.registerUniformForce(uniformForce);
        }
    } public void createForce(Triple value, Triple location, RigidBody rb)
    {
        Force force = new Force();
        force.setForceValue(value);
        force.setForceLocation(location);
        rb.registerForce(force);
        
    }
}
