/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapes;

import javax.media.opengl.GL;

/**
 *
 * @author krzysztof
 */

public abstract class Shape {
    protected float x,y,z;
    protected float anglex,angley,anglez;
    
    Shape(float x,float y,float z, float anglex,float angley, float anglez){
        this.x=x;
        this.y=y;
        this.z=z;
        this.anglex=anglex;
        this.angley=angley;
        this.anglez=anglez;
    }
    
    public abstract void draw(GL gl);

    /**
     * @return the x
     */
    public float getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public float getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * @return the z
     */
    public float getZ() {
        return z;
    }

    /**
     * @param z the z to set
     */
    public void setZ(float z) {
        this.z = z;
    }

    /**
     * @return the anglex
     */
    public float getAnglex() {
        return anglex;
    }

    /**
     * @param anglex the anglex to set
     */
    public void setAnglex(float anglex) {
        this.anglex = anglex;
    }

    /**
     * @return the angley
     */
    public float getAngley() {
        return angley;
    }

    /**
     * @param angley the angley to set
     */
    public void setAngley(float angley) {
        this.angley = angley;
    }

    /**
     * @return the anglez
     */
    public float getAnglez() {
        return anglez;
    }

    /**
     * @param anglez the anglez to set
     */
    public void setAnglez(float anglez) {
        this.anglez = anglez;
    }
}
