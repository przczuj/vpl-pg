/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.physics;

import java.util.logging.Level;
import java.util.logging.Logger;
import vpl.math.Matrix;

/**
 *
 * @author kppx
 */
public abstract class Shape {
 protected Matrix IBody;
 protected String type;
    public double getMass() {
        return mass;
    }

    public void setMass(double mass) throws Exception {
    this.mass = mass;  
    //since mass has changed we have to recalculate inertia
    init();
    recalculate();
    }
 protected Matrix TIBody;
 protected double mass;
    //
    protected Matrix NormalizedIBody;
    protected Matrix InvertedIBody;
    //dimensions

    public Matrix getIBody() {
        return IBody;
    }

    public void setIBody(Matrix IBody) {
        this.IBody = IBody;
    }

    public Matrix getTIBody() {
        return TIBody;
    }

    public void setTIBody(Matrix TIBody) {
        this.TIBody = TIBody;
    }

    public Matrix getInvertedIBody() {
        return InvertedIBody;
    }

    public void setInvertedIBody(Matrix InvertedIBody) {
        this.InvertedIBody = InvertedIBody;
    }
    
   // private float r;
    //private float x,y,z;
  //  private float a, b, c;
    //private float xAngle,yAngle,zAngle;
    
    
    //since by the time the class was written different shapes hadn't been implemented yet 
    //the default shape was set to be a ball
    public Shape() throws Exception
    {
       init();
        
    }
    protected void init() throws Exception
    {   
         IBody = new Matrix(3,3);
         NormalizedIBody = new Matrix(3,3);
         type = new String("DEFAULT");
    //     setDefault();
      //  BasicMath mathLogic = new BasicMath();
         
      //   InvertedIBody =  IBody.inverse(IBody);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    //assigning coefficients 
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
