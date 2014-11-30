/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author krzysztof
 */
public class Ball{
    private float r;
    private float x,y,z;
    private float a, b, c;
    private float xAngle,yAngle,zAngle;
        
    Ball(float r, float x, float y,float z){
        this.r=r;
        this.x=x;
        this.y=y;
        this.z=z;
    }
    Ball(float r, float x, float y,float z,float a,float b,float c,
            float xAngle, float yAngle, float zAngle){
        this.r=r;
        this.x=x;
        this.y=y;
        this.a=a;
        this.b=b;
        this.c=c;   
        this.xAngle=xAngle;
        this.yAngle=yAngle;
        this.zAngle=zAngle;
        }

    /**
     * @return the r
     */
    public float getR() {
        return r;
    }

    /**
     * @param r the r to set
     */
    public void setR(float r) {
        this.r = r;
    }

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
     * @return the a
     */
    public float getA() {
        return a;
    }

    /**
     * @param a the a to set
     */
    public void setA(float a) {
        this.a = a;
    }

    /**
     * @return the b
     */
    public float getB() {
        return b;
    }

    /**
     * @param b the b to set
     */
    public void setB(float b) {
        this.b = b;
    }

    /**
     * @return the c
     */
    public float getC() {
        return c;
    }

    /**
     * @param c the c to set
     */
    public void setC(float c) {
        this.c = c;
    }

    /**
     * @return the xAngle
     */
    public float getxAngle() {
        return xAngle;
    }

    /**
     * @param xAngle the xAngle to set
     */
    public void setxAngle(float xAngle) {
        this.xAngle = xAngle;
    }

    /**
     * @return the yAngle
     */
    public float getyAngle() {
        return yAngle;
    }

    /**
     * @param yAngle the yAngle to set
     */
    public void setyAngle(float yAngle) {
        this.yAngle = yAngle;
    }

    /**
     * @return the zAngle
     */
    public float getzAngle() {
        return zAngle;
    }

    /**
     * @param zAngle the zAngle to set
     */
    public void setzAngle(float zAngle) {
        this.zAngle = zAngle;
    }
    }