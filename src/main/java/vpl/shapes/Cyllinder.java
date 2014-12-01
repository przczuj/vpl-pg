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
public class Cyllinder extends Shape {

    protected double r, h;

    public Cyllinder(double x, double y, double z, double anglex, double angley, double anglez, double r, double h) {
        super(x, y, z, anglex, angley, anglez);
        this.r = r;
        this.h = h;
    }

    @Override
    public void draw(GL2 gl) {

        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        gl.glColor3f(1, 0, 0);
        gl.glVertex3d(x, y - (h / 2), z);
        for (int i = 0; i <= 36; i++) {
            gl.glVertex3d(x + (double) (r*Math.cos(Math.PI * 2  * i / 36)), y - (h / 2), z + (double) (r*Math.sin(Math.PI * 2  * i / 36)));
        }
        gl.glEnd();

        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        gl.glColor3f(0, 1, 0);
        gl.glVertex3d(x, y + (h / 2), z);
        for (int i = 0; i <= 36; i++) {
            gl.glVertex3d(x + (double) (r*Math.cos(Math.PI * 2 *  i / 36)), y + (h / 2), z + (double) (r*Math.sin(Math.PI * 2 * i / 36)));
        }
        gl.glEnd();

        gl.glBegin(GL2.GL_QUAD_STRIP);
        gl.glColor3f(0, 0, 1);

        for (int i = 0; i <= 36; i++) {

            double s = (double) Math.sin(Math.PI * 2 * i / 36.0);
            double c = (double) Math.cos(Math.PI * 2 * i / 36.0);

            gl.glVertex3d(x + r * c, y - (h / 2), z + r * s);
            gl.glVertex3d(x + r * c, y + (h / 2), z + r * s);
        }
        gl.glEnd();

    }

}
