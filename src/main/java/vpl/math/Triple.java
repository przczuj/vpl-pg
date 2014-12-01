/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.math;

import vpl.math.Matrix;

/**
 *
 * @author kppx
 */
public class Triple {
    private double x,y,z;
    public Triple()
    {
     this.x=0;
     this.y=0;
     this.z=0;
    }
    public Triple(double a,double b, double c)
    {
     this.x=a;
     this.y=b;
     this.z=c;
    }
    public double getX() {
        
        return x;
    }
    public double getLength()
    {
     double l;
     l=Math.pow(x, 2)+Math.pow(y,2) + Math.pow(z,2);//, l)
     l= Math.sqrt(l);
     return l;
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
    public Triple getDistance(Triple a, Triple b)
    {
     Triple t = new Triple();
     
     return t;
    }

    public Matrix toMatrix() {
        Matrix mat = new Matrix(3,1);
        mat.setValueAt(0, 0, getX());  
        mat.setValueAt(1, 0, getY()); 
        mat.setValueAt(2, 0, getZ()); 
        return mat;
    }
    @Override
    public String toString()
    {
       StringBuilder sb = new StringBuilder();
       sb.append("X: " + this.x + " Y: " + this.y + " Z: " + this.z);
       return sb.toString();
    }
    public void print()
    {
        System.out.println(toString());
    }
}
