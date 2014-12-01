package vpl.physics;


import vpl.physics.AxisAngle;
import vpl.physics.Force;
import vpl.physics.RigidBody;
import vpl.physics.Shape;
import vpl.physics.shapes.BallShape;
import vpl.physics.Torque;
import vpl.math.Triple;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kppx
 */
public class MovementTest {
     
        //static
                RigidBody rb;
     //static 
                Triple position;
    //static 
                Triple linearVelocity;
    ///static
                Triple angularVelocity;
    
    AxisAngle angles;
    public MovementTest() throws Exception
    {
     //RigidBody  
        rb = new RigidBody();
        rb.setPosition(new Triple());
        BallShape s = new BallShape();
        s.setR(1);
        rb.setShape(s);
        rb.setMass(1);
        rb.getShape().recalculate();
        rb.setTimeTick(50);
        Force f = new Force();
        f.setForceLocation(new Triple(-3,0,-2));
        f.setForceValue(new Triple(0,0,2));
        
        Force f2 = new Force();
        f2.setForceLocation(new Triple(3,0,-2));
        f2.setForceValue(new Triple(0,0,2));
        rb.registerForce(f);
        rb.registerForce(f2);
        
        
        performTest();
        
    }
        
    public void main()
    {
                    try {
                        performTest();
                    } catch (IOException ex) {
                        Logger.getLogger(MovementTest.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }
    private void performTest() throws IOException
    {
     for  (int i=0;i<20;i++)
     {
         rb.calculate();
         position = rb.getPosition();
      //   angles = rb.getRotationAngles();
        // Force total = rb.getTotalForce();
         //Torque totalTorq = rb.getTotalTorque();
         rb.print();
     }
     byte b[] = new byte[20];
     System.in.read(b);
    }
}
