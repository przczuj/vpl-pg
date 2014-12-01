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

    private float r, h;

    public Cone(float x, float y, float z, float anglex, float angley, float anglez, float r, float h) {
        super(x, y, z, anglex, angley, anglez);
        this.r = r;
        this.h = h;
    }

    @Override
    public void draw(GL2 gl) {

        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        gl.glColor3f(1, 0, 0);
        gl.glVertex3f(x, y - (h / 4), z);
        for (int i = 0; i < 36; i++) {
            gl.glVertex3f(x + (float) (r* Math.cos(Math.PI * 2  * i / 35)), y - (h / 4), z + (float) (r*Math.sin(Math.PI * 2 * i / 35)));
        }
        gl.glEnd();

        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        gl.glColor3f(0, 1, 0);
        gl.glVertex3f(x, y + (3 / 4 * h), z);
        for (int i = 0; i < 36; i++) {
            gl.glVertex3f(x + (float) (r*Math.cos(Math.PI * 2 * i / 35)), y - (h / 4), z + (float) (r* Math.sin(Math.PI * 2 * i / 35)));
        }
        gl.glEnd();
    }

}
