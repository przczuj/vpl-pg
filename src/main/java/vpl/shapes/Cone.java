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
public class Cone extends Shape {

    private double r, h;

    public Cone(double x, double y, double z, double anglex, double angley, double anglez, double r, double h) {
        super(x, y, z, anglex, angley, anglez);
        this.r = r;
        this.h = h;
    }

    @Override
    public void draw(GL2 gl) {

        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        gl.glColor3f(1, 0, 0);
        gl.glVertex3d(x, y - (h / 4), z);
        for (int i = 0; i < 36; i++) {
            gl.glVertex3d(x + (double) (r * Math.cos(Math.PI * 2 * i / 35)), y - (h / 4), z + (double) (r * Math.sin(Math.PI * 2 * i / 35)));
        }
        gl.glEnd();

        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        gl.glColor3f(0, 1, 0);
        gl.glVertex3d(x, y + (3 / 4 * h), z);
        for (int i = 0; i < 36; i++) {
            gl.glVertex3d(x + (double) (r * Math.cos(Math.PI * 2 * i / 35)), y - (h / 4), z + (double) (r * Math.sin(Math.PI * 2 * i / 35)));
        }
        gl.glEnd();
    }
}
