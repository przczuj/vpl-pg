/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.shapes;

import javax.media.opengl.GL2;

/**
 *
 * @author krzysztof
 */

public abstract class Shape {
    protected double x,y,z;
    protected double anglex,angley,anglez;
    
    Shape(double x,double y,double z, double anglex,double angley, double anglez){
        this.x=x;
        this.y=y;
        this.z=z;
        this.anglex=anglex;
        this.angley=angley;
        this.anglez=anglez;
    }
    
    public abstract void draw(GL2 gl);

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @return the z
     */
    public double getZ() {
        return z;
    }

    /**
     * @param z the z to set
     */
    public void setZ(double z) {
        this.z = z;
    }

    /**
     * @return the anglex
     */
    public double getAnglex() {
        return anglex;
    }

    /**
     * @param anglex the anglex to set
     */
    public void setAnglex(double anglex) {
        this.anglex = anglex;
    }

    /**
     * @return the angley
     */
    public double getAngley() {
        return angley;
    }

    /**
     * @param angley the angley to set
     */
    public void setAngley(double angley) {
        this.angley = angley;
    }

    /**
     * @return the anglez
     */
    public double getAnglez() {
        return anglez;
    }

    /**
     * @param anglez the anglez to set
     */
    public void setAnglez(double anglez) {
        this.anglez = anglez;
    }
}
