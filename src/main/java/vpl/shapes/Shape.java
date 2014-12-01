/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.shapes;

import javax.media.opengl.GL2;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author krzysztof
 */
public abstract class Shape {

    @Getter @Setter protected double x, y, z;
    @Getter @Setter protected double anglex, angley, anglez;

    public Shape(double x, double y, double z, double anglex, double angley, double anglez) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.anglex = anglex;
        this.angley = angley;
        this.anglez = anglez;
    }

    public abstract void draw(GL2 gl);
}
