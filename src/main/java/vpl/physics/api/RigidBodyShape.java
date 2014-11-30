/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.physics.api;

/**
 *
 * @author kppx
 */
class RigidBodyShape {

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
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

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }
    String type;
    double r,x,y,z,h;
    double mass;
}
