/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.physics;

/**
 *
 * @author kppx
 */

//used to store results of rotationMatrix/angles conversion
public class AxisAngle {
    double angle;
    Triple angles;
    public AxisAngle()
    {
        angles=new Triple(1,0,0);
        angle = 0;
    }
    public AxisAngle(double angle, Triple angles)
    {
     this.angles = angles;
     this.angle = angle;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public Triple getAngles() {
        return angles;
    }

    public void setAngles(Triple angles) {
        this.angles = angles;
    }
    
    public void print()
    {
     System.out.println("Angle: " + angle + " X Y Z angles: " + angles.toString());   
    }
    
    @Override public String toString()
    {
        return new String("Angle: " + angle + " X Y Z angles: " + angles.toString());
    }
}
