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
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.Setter;
import vpl.math.BasicMath;
import vpl.math.Matrix;
import vpl.physics.AxisAngle;
import vpl.physics.shapes.CuboidShape;

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

    @Getter
    @Setter
    private Map<String, RigidBody> rigidBodies;
    @Getter
    @Setter
    private Map<String, Force> uniformForces;
    @Getter
    @Setter
    private List<Collision> collisions;
    @Getter
    @Setter
    private List<RigidBodyDrawingInfo> toBeDrawn;
    private BasicMath mathLogic;
    private RigidBodyApi api;

    public void checkCollisions() {
        List<RigidBody> rigidBodies2 = new ArrayList<>(getRigidBodies().values());
        RigidBody floor = getRigidBodies().get("_floor");
        if (floor != null) {
            rigidBodies2.remove(floor);
        }
        
        BasicMath logic = new BasicMath();
        //for (int i = 0; i < rigidBodies2.size(); i++) {
        //List<RigidBody> checkInNextStep = new ArrayList<RigidBody>();
        //for (int j = i + 1; j < rigidBodies2.size(); j++) {
        
        int i=0;
        for (RigidBody rb1 : rigidBodies2) {
                //RigidBody rb1 = rigidBodies2.get(i);
            //RigidBody rb2 = rigidBodies2.get(j);
            int j=0;
            for (RigidBody rb2 : rigidBodies2) {
                if (j>i) {
                    double distance = rb1.getPosition().getDistance(rb2.getPosition());
                    double r1 = rb1.getShape().getSphereRadius();
                    double r2 = rb2.getShape().getSphereRadius();
                    if (distance <= r1 + r2) {
                        //checkInNextStep.add(rb2);
                        //checkInNextStep.add(rb2);

 //spheral collision
                        //basis vector
                        double xx, xy, xz;
                        Triple pos1, pos2;
                        pos1 = rb1.getPosition();
                        pos2 = rb2.getPosition();
                        xx = pos1.getX() - pos2.getX();
                        xy = pos1.getY() - pos2.getY();
                        xz = pos1.getZ() - pos2.getZ();
                        Triple xvec = new Triple(xx, xy, xz);

                        //normalization
                        double d = xvec.getLength();
                        xx /= d;
                        xy /= d;
                        xz /= d;
                        xvec = new Triple(xx, xy, xz);

                        //x-direction velocity vector and the perpendicular y-vector
                        Triple collisionPoint = new Triple(pos2.getX() + r2 * xvec.getX(),
                                pos2.getY() + r2 * xvec.getY(), pos2.getZ() + r2 * xvec.getZ());
                        //third line
                        Triple v1 = rb1.getPointVelociy(collisionPoint);
                        //fourth line
                        double x1 = logic.dotProduct(xvec, v1);
                        //fifth line
                        Triple v1x = new Triple();
                        v1x.setXYZ(xvec.getX() * x1, xvec.getY() * x1, xvec.getZ() * x1);
                        //sixth line
                        Triple v1y = new Triple();
                        v1y.setXYZ(v1.getX() - v1x.getX(), v1.getY() - v1x.getY(), v1.getZ() - v1x.getZ());
                        //seventh line
                        double m1 = rb1.getMass();

                        //anologycal operations for second rb
                        xvec.setXYZ(xvec.getX() * (-1), xvec.getY() * (-1), xvec.getZ() * (-1));
                        //x-direction velocity vector and the perpendicular y-vector
                        Triple collisionPoint2 = new Triple(pos2.getX() + r2 * xvec.getX(),
                                pos2.getY() + r2 * xvec.getY(), pos2.getZ() + r2 * xvec.getZ());
                        //third line
                        Triple v2 = rb2.getPointVelociy(collisionPoint);
                        //fourth line
                        double x2 = logic.dotProduct(xvec, v2);
                        //fifth line
                        Triple v2x = new Triple();
                        v2x.setXYZ(xvec.getX() * x2, xvec.getY() * x2, xvec.getZ() * x2);
                        //sixth line
                        Triple v2y = new Triple();
                        v2y.setXYZ(v2.getX() - v2x.getX(), v2.getY() - v2x.getY(), v2.getZ() - v2x.getZ());
                        //seventh line
                        double m2 = rb2.getMass();

                        //velocity 1
                        Triple vel1;
                        vel1 = new Triple();
                        double mass1 = (m1 - m2) / (m2 + m1);
                        double mass2 = (2 * m2) / (m1 + m2);
                        vel1.setX(v1x.getX() * mass1 + v2x.getX() * mass2 + v1y.getX());
                        vel1.setY(v1x.getY() * mass1 + v2x.getY() * mass2 + v1y.getY());
                        vel1.setZ(v1x.getZ() * mass1 + v2x.getZ() * mass2 + v1y.getZ());

                        //velocity2
                        Triple vel2;
                        vel2 = new Triple();
                        double mass3 = (2 * m1) / (m1 + m2);
                        double mass4 = (m2 - m1) / (m1 + m2);
                        vel2.setX(v1x.getX() * mass3 + v2x.getX() * mass4 + v2y.getX());
                        vel2.setY(v1x.getY() * mass3 + v2x.getY() * mass4 + v2y.getY());
                        vel2.setZ(v1x.getZ() * mass3 + v2x.getZ() * mass4 + v2y.getZ());
                        rb1.setLinearVelocity(vel1);
                        rb2.setLinearVelocity(vel2);
                        Force f=new Force();
                        f.setForceValue(new Triple(0,0,0));
                        
                        rb1.setTotalForce(f);
                        rb2.setTotalForce(f);
                        rb1.setLinearMomentum(new Triple(vel1.getX() * m1, vel1.getY() * m1, vel1.getZ() * m1));
                        rb2.setLinearMomentum(new Triple(vel2.getX() * m2, vel2.getY() * m2, vel2.getZ() * m2));
                        while((distance = rb1.getPosition().getDistance(rb2.getPosition()))<=(r1+r2)){
                            /*Triple rb1pos=rb1.getPosition();
                            rb1pos.setX(rb1pos.getX()+(vel1.getX()/100.0));
                            rb1pos.setY(rb1pos.getY()+(vel1.getY()/100.0));
                            rb1pos.setZ(rb1pos.getZ()+(vel1.getZ()/100.0));
                            
                            Triple rb2pos=rb2.getPosition();
                            rb2pos.setX(rb2pos.getX()+(vel2.getX()/100.0));
                            rb2pos.setY(rb2pos.getY()+(vel2.getY()/100.0));
                            rb2pos.setZ(rb2pos.getZ()+(vel2.getZ()/100.0));*/
                            Triple rb1pos=rb1.getPosition();
                            rb1pos.setX(rb1pos.getX()-(v1.getX()/100.0));
                            rb1pos.setY(rb1pos.getY()-(v1.getY()/100.0));
                            rb1pos.setZ(rb1pos.getZ()-(v1.getZ()/100.0));
                            rb1.setPosition(rb1pos);
                            /*Triple rb2pos=rb2.getPosition();
                            rb2pos.setX(rb2pos.getX()+(vel2.getX()/100.0));
                            rb2pos.setY(rb2pos.getY()+(vel2.getY()/100.0));
                            rb2pos.setZ(rb2pos.getZ()+(vel2.getZ()/100.0));*/
                        }
                    }
                }
                j++;
            }
            i++;
        }
    }
    
    public void checkFloorCollisions() {
        List<RigidBody> rigidBodies2 = new ArrayList<>(getRigidBodies().values());
        RigidBody floor = getRigidBodies().get("_floor");
        if (floor == null) {
            return;
        }
        rigidBodies2.remove(floor);

        BasicMath logic = new BasicMath();
        //for (int i = 0; i < rigidBodies2.size(); i++) {
        //List<RigidBody> checkInNextStep = new ArrayList<RigidBody>();
        //for (int j = i + 1; j < rigidBodies2.size(); j++) {

        int i = 0;
        for (RigidBody rb1 : rigidBodies2) {
            //RigidBody rb1 = rigidBodies2.get(i);
            //RigidBody rb2 = rigidBodies2.get(j);
            
            RigidBody rb2 = floor;
            
            double distance = Math.abs(rb2.getPosition().getY() - rb1.getPosition().getY()); // rb1.getPosition().getDistance(rb2.getPosition());
            double r1 = rb1.getShape().getSphereRadius();
            double r2 = ((CuboidShape)rb2.getShape()).getY()/2;
            if (distance <= r1 + r2) {
                System.out.println("floor collision!");
                if (rb1.getLinearMomentum().getY() < 0) {
                    rb1.getLinearMomentum().setY(-rb1.getLinearMomentum().getY());
                }
//                //checkInNextStep.add(rb2);
//                //checkInNextStep.add(rb2);
//
//                //spheral collision
//                //basis vector
//                double xx, xy, xz;
//                Triple pos1, pos2;
//                pos1 = rb1.getPosition();
//                pos2 = rb2.getPosition();
//                xx = pos1.getX() - pos2.getX();
//                xy = pos1.getY() - pos2.getY();
//                xz = pos1.getZ() - pos2.getZ();
//                Triple xvec = new Triple(xx, xy, xz);
//
//                //normalization
//                double d = xvec.getLength();
//                xx /= d;
//                xy /= d;
//                xz /= d;
//                xvec = new Triple(xx, xy, xz);
//
//                //x-direction velocity vector and the perpendicular y-vector
//                Triple collisionPoint = new Triple(pos2.getX() + r2 * xvec.getX(),
//                        pos2.getY() + r2 * xvec.getY(), pos2.getZ() + r2 * xvec.getZ());
//                //third line
//                Triple v1 = rb1.getPointVelociy(collisionPoint);
//                //fourth line
//                double x1 = logic.dotProduct(xvec, v1);
//                //fifth line
//                Triple v1x = new Triple();
//                v1x.setXYZ(xvec.getX() * x1, xvec.getY() * x1, xvec.getZ() * x1);
//                //sixth line
//                Triple v1y = new Triple();
//                v1y.setXYZ(v1.getX() - v1x.getX(), v1.getY() - v1x.getY(), v1.getZ() - v1x.getZ());
//                //seventh line
//                double m1 = rb1.getMass();
//
//                //anologycal operations for second rb
//                xvec.setXYZ(xvec.getX() * (-1), xvec.getY() * (-1), xvec.getZ() * (-1));
//                //x-direction velocity vector and the perpendicular y-vector
//                Triple collisionPoint2 = new Triple(pos2.getX() + r2 * xvec.getX(),
//                        pos2.getY() + r2 * xvec.getY(), pos2.getZ() + r2 * xvec.getZ());
//                //third line
//                Triple v2 = rb2.getPointVelociy(collisionPoint);
//                //fourth line
//                double x2 = logic.dotProduct(xvec, v2);
//                //fifth line
//                Triple v2x = new Triple();
//                v2x.setXYZ(xvec.getX() * x2, xvec.getY() * x2, xvec.getZ() * x2);
//                //sixth line
//                Triple v2y = new Triple();
//                v2y.setXYZ(v2.getX() - v2x.getX(), v2.getY() - v2x.getY(), v2.getZ() - v2x.getZ());
//                //seventh line
//                double m2 = rb2.getMass();
//
//                //velocity 1
//                Triple vel1;
//                vel1 = new Triple();
//                double mass1 = (m1 - m2) / (m2 + m1);
//                double mass2 = (2 * m2) / (m1 + m2);
//                vel1.setX(v1x.getX() * mass1 + v2x.getX() * mass2 + v1y.getX());
//                vel1.setY(v1x.getY() * mass1 + v2x.getY() * mass2 + v1y.getY());
//                vel1.setZ(v1x.getZ() * mass1 + v2x.getZ() * mass2 + v1y.getZ());
//
//                //velocity2
//                Triple vel2;
//                vel2 = new Triple();
//                double mass3 = (2 * m1) / (m1 + m2);
//                double mass4 = (m2 - m1) / (m1 + m2);
//                vel2.setX(v1x.getX() * mass3 + v2x.getX() * mass4 + v2y.getX());
//                vel2.setY(v1x.getY() * mass3 + v2x.getY() * mass4 + v2y.getY());
//                vel2.setZ(v1x.getZ() * mass3 + v2x.getZ() * mass4 + v2y.getZ());
//                rb1.setLinearVelocity(vel1);
//                rb2.setLinearVelocity(vel2);
//                Force f = new Force();
//                f.setForceValue(new Triple(0, 0, 0));
//
//                rb1.setTotalForce(f);
//                rb2.setTotalForce(f);
//                rb1.setLinearMomentum(new Triple(vel1.getX() * m1, vel1.getY() * m1, vel1.getZ() * m1));
//                rb2.setLinearMomentum(new Triple(vel2.getX() * m2, vel2.getY() * m2, vel2.getZ() * m2));
//                while ((distance = rb1.getPosition().getDistance(rb2.getPosition())) <= (r1 + r2)) {
//                    /*Triple rb1pos=rb1.getPosition();
//                     rb1pos.setX(rb1pos.getX()+(vel1.getX()/100.0));
//                     rb1pos.setY(rb1pos.getY()+(vel1.getY()/100.0));
//                     rb1pos.setZ(rb1pos.getZ()+(vel1.getZ()/100.0));
//
//                     Triple rb2pos=rb2.getPosition();
//                     rb2pos.setX(rb2pos.getX()+(vel2.getX()/100.0));
//                     rb2pos.setY(rb2pos.getY()+(vel2.getY()/100.0));
//                     rb2pos.setZ(rb2pos.getZ()+(vel2.getZ()/100.0));*/
//                    Triple rb1pos = rb1.getPosition();
//                    rb1pos.setX(rb1pos.getX() - (v1.getX() / 100.0));
//                    rb1pos.setY(rb1pos.getY() - (v1.getY() / 100.0));
//                    rb1pos.setZ(rb1pos.getZ() - (v1.getZ() / 100.0));
//                    rb1.setPosition(rb1pos);
//                    /*Triple rb2pos=rb2.getPosition();
//                     rb2pos.setX(rb2pos.getX()+(vel2.getX()/100.0));
//                     rb2pos.setY(rb2pos.getY()+(vel2.getY()/100.0));
//                     rb2pos.setZ(rb2pos.getZ()+(vel2.getZ()/100.0));*/
//                }
            }
            i++;
        }
    }

    public void solveCollisions() {
        checkCollisions();
        checkFloorCollisions();
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
        checkCollisions();
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
        mathLogic = new BasicMath();
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
        //rb.setTimeTick(timeTick);
        rb.setTimeTick(PhysicsExecutionTask.TICK_RATE_MILLISEC);
        rb.setPosition(position);
        rb.setMass(mass);
        rb.getShape().recalculate();
        rigidBodies.put("body " + rigidBodies.size(), rb);
    }

    public void createRigidBody(Shape shape, Triple position, double timeTick, double mass, AxisAngle initialRotation) throws Exception {
        RigidBody rb = new RigidBody();
        rb.setShape(shape);
        //rb.setTimeTick(timeTick);
        Matrix rotation = mathLogic.anglesToRotationMatrix(initialRotation);

        rb.setTimeTick(PhysicsExecutionTask.TICK_RATE_MILLISEC);
        rb.setPosition(position);
        rb.setMass(mass);
        rb.getShape().recalculate();
        rb.setRotationMatrix(rotation);
        for (Force uniformForce : uniformForces.values()) {
            rb.registerUniformForce(uniformForce);
        }
        rigidBodies.put("body " + rigidBodies.size(), rb);
    }

    public void createUniformForce(Triple value) {
        Force uniformForce = new Force();
        uniformForce.setForceValue(value);
        uniformForces.put("uniform force " + uniformForces.size(), uniformForce);
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
