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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kppx
 */
public class ControllerStub {

    private Model model;
    
    @Getter @Setter private Map<String, RigidBody> rigidBodies;
    @Getter @Setter private Map<String, Force> uniformForces;
    @Getter @Setter private List<Collision> collisions;
    @Getter @Setter private List<RigidBodyDrawingInfo> toBeDrawn;
    
    private RigidBodyApi api;

    private void checkCollisions() {
        List<Collision> possibleCollisions;
        possibleCollisions = new ArrayList<Collision>();
        //checking if possible (not yet finally implemented
        //basically used to reduce the number of complex collision detection calculation
        for (RigidBody rb1 : rigidBodies.values()) {
            for (RigidBody rb2 : rigidBodies.values()) {
                //if rb1 and rb2 are both the same object, there won't be any collision...
                if (rb1.equals(rb2)) {
                    continue;
                }
                Collision col = new Collision();
                col.setRigidBody1(rb1);
                col.setRigidBody2(rb2);
                if (col.collisionPossible()) {
                    possibleCollisions.add(col);
                }
            }
        }
        for (Collision c : possibleCollisions) {
            if (c.collisionHappened()) {
                collisions.add(c);
            }
        }
    }

    public void solveCollisions() {
        checkCollisions();
        for (Collision c : collisions) {
            c.solveCollisions();
        }
    }

    public ControllerStub(Model model) {
        init();
        this.model = model;
    }
    //to be done every $timetick

    public void update() {
        toBeDrawn = new ArrayList<RigidBodyDrawingInfo>();
        for (RigidBody rb : rigidBodies.values()) {
            rb.calculate();
            RigidBodyDrawingInfo drawingInfo = new RigidBodyDrawingInfo();
            drawingInfo.setShape(api.getShape(rb));
            drawingInfo.setState(api.getState(rb));
            toBeDrawn.add(drawingInfo);

        }
        solveCollisions();
    }

    private void init() {
        rigidBodies = new HashMap<String, RigidBody>();

        collisions = new ArrayList<Collision>();
        uniformForces = new HashMap<String, Force>();
        api = new RigidBodyApi();
    }

    public void createRigidBodyExample() throws Exception {

        RigidBody rb = new RigidBody();
        BallShape shape = new BallShape();
        shape.setR(10); //r of the ball     
        rb.setShape(shape);
        rb.setTimeTick(20);//20 ms
        Triple initialPosition = new Triple(0, 0, 0);//starting location is 0,0,0
        rb.setPosition(initialPosition);
        rb.setMass(1);//1 kg
        rb.getShape().recalculate();
    }

    public void createRigidBody(Shape shape, Triple position, double timeTick, double mass) throws Exception {
        RigidBody rb = new RigidBody();
        rb.setShape(shape);
        rb.setTimeTick(timeTick);
        rb.setPosition(position);
        rb.setMass(mass);
        rb.getShape().recalculate();
        rigidBodies.put("body " + rigidBodies.size(), rb);
    }

    public void createUniformForce(Triple value) {
        Force uniformForce = new Force();
        uniformForce.setForceValue(value);
        uniformForces.put("force " + uniformForces.size(), uniformForce);
        for (RigidBody rb : rigidBodies.values()) {
            rb.registerUniformForce(uniformForce);
        }
    }

    public void createForce(Triple value, Triple location, RigidBody rb, double timeToLive, boolean forever) {
        Force force = new Force();
        force.setForceValue(value);
        force.setForceLocation(location);
        force.setTimeToLive(timeToLive);
        force.setForever(forever);
        rb.registerForce(force);

    }
}
