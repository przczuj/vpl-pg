/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.physics.shapes;

import java.util.logging.Level;
import java.util.logging.Logger;
import vpl.math.Matrix;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author kppx
 */
public abstract class Shape {

    @Getter
    @Setter
    protected String type;
    @Getter
    @Setter
    protected Matrix IBody;
    @Getter
    @Setter
    protected Matrix TIBody;
    @Getter
    @Setter
    protected Matrix NormalizedIBody;
    @Getter
    @Setter
    protected Matrix InvertedIBody;
    @Getter
    protected double sphereRadius;//used for simple elimination in collisions
    @Getter
    protected double mass;

    public void setMass(double mass) throws Exception {
        this.mass = mass;
        //since mass has changed we have to recalculate inertia
        init();
        recalculate();
    }

    // private float r;
    //private float x,y,z;
    //  private float a, b, c;
    //private float xAngle,yAngle,zAngle;
    //since by the time the class was written different shapes hadn't been implemented yet 
    //the default shape was set to be a ball
    public Shape() throws Exception {
        init();
    }

    protected void init() throws Exception {
        IBody = new Matrix(3, 3);
        NormalizedIBody = new Matrix(3, 3);
        if (type == null) {
            type = new String("DEFAULT");
        }
        //     setDefault();
        //  BasicMath mathLogic = new BasicMath();

        //   InvertedIBody =  IBody.inverse(IBody);
    }

    public abstract void calculateRadius();

    public abstract void recalculate();/*
     {
     //for a axbxc block
     IBody.setValueAt(0, 0, (y*y+z*z)*mass/12);
     IBody.setValueAt(1, 1, (x*x+z*z)*mass/12);
     IBody.setValueAt(2, 2, (x*x+y*y)*mass/12);
     try {
     InvertedIBody =  IBody.inverse(IBody);
     } catch (Exception ex) {
     Logger.getLogger(Shape.class.getName()).log(Level.SEVERE, null, ex);
     }
     }*/
    /*
     public float getX() {
     return x;
     }

     public void setX(float x) throws Exception {
     this.x = x;
     init();
     recalculate();
     }

     public float getY() {
     return y;
     }

     public void setY(float y) {
     this.y = y;
     }

     public float getZ() {
     return z;
     }

     public void setZ(float z) {
     this.z = z;
     }*/

}
