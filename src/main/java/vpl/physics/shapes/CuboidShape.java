/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.physics.shapes;

import vpl.physics.Shape;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kppx
 */
public class CuboidShape extends Shape {
    protected double x,y,z;
    public CuboidShape() throws Exception
    {
     init();   
         type = new String("CUBOID");
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
    @Override
    public void recalculate()
    {
        //for a x*y*z block
        IBody.setValueAt(0, 0, (y*y+z*z)*mass/12);
        IBody.setValueAt(1, 1, (x*x+z*z)*mass/12);
        IBody.setValueAt(2, 2, (x*x+y*y)*mass/12);
            try {
        InvertedIBody =  IBody.inverse(IBody);
     } catch (Exception ex) {
         Logger.getLogger(Shape.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
}
