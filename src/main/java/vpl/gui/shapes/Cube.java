/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.gui.shapes;

import javax.media.opengl.GL2;

/**
 *
 * @author krzysztof
 */
public class Cube extends Shape {

    protected double a;

    public Cube(double x, double y, double z, double anglex, double angley, double anglez, double a) {
        super(x, y, z, anglex, angley, anglez);
        this.a = a;
    }

    @Override
    public void draw(GL2 gl) {
        gl.glPushMatrix();
        gl.glTranslated(x, y, z);

        gl.glRotated(anglez, 0, 0, 1);
        gl.glRotated(angley, 0, 1, 0);
        gl.glRotated(anglex, 1, 0, 0);
        gl.glTranslated(-x, -y, -z);


        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glBegin(GL2.GL_QUADS);//tył
        gl.glColor3f(1f, 0f, 0f);
        gl.glVertex3d(x - 0.5f * a, y - 0.5f * a, z - 0.5f * a);
        gl.glVertex3d(x - 0.5f * a, y + 0.5f * a, z - 0.5f * a);
        gl.glVertex3d(x + 0.5f * a, y + 0.5f * a, z - 0.5f * a);
        gl.glVertex3d(x + 0.5f * a, y - 0.5f * a, z - 0.5f * a);
        gl.glEnd();

        gl.glBegin(GL2.GL_QUADS);//przód
        gl.glColor3f(0f, 1f, 0f);
        gl.glVertex3d(x - 0.5f * a, y - 0.5f * a, z + 0.5f * a);
        gl.glVertex3d(x - 0.5f * a, y + 0.5f * a, z + 0.5f * a);
        gl.glVertex3d(x + 0.5f * a, y + 0.5f * a, z + 0.5f * a);
        gl.glVertex3d(x + 0.5f * a, y - 0.5f * a, z + 0.5f * a);
        gl.glEnd();

        gl.glBegin(GL2.GL_QUADS);//lewo
        gl.glColor3f(0f, 0f, 1f);
        gl.glVertex3d(x - 0.5f * a, y - 0.5f * a, z - 0.5f * a);
        gl.glVertex3d(x - 0.5f * a, y + 0.5f * a, z - 0.5f * a);
        gl.glVertex3d(x - 0.5f * a, y + 0.5f * a, z + 0.5f * a);
        gl.glVertex3d(x - 0.5f * a, y - 0.5f * a, z + 0.5f * a);
        gl.glEnd();

        gl.glBegin(GL2.GL_QUADS);//prawo
        gl.glColor3f(1f, 1f, 1f);
        gl.glVertex3d(x + 0.5f * a, y - 0.5f * a, z - 0.5f * a);
        gl.glVertex3d(x + 0.5f * a, y + 0.5f * a, z - 0.5f * a);
        gl.glVertex3d(x + 0.5f * a, y + 0.5f * a, z + 0.5f * a);
        gl.glVertex3d(x + 0.5f * a, y - 0.5f * a, z + 0.5f * a);
        gl.glEnd();

        gl.glBegin(GL2.GL_QUADS);//dół
        gl.glColor3f(1f, 1f, 0f);
        gl.glVertex3d(x - 0.5f * a, y - 0.5f * a, z - 0.5f * a);
        gl.glVertex3d(x + 0.5f * a, y - 0.5f * a, z - 0.5f * a);
        gl.glVertex3d(x + 0.5f * a, y - 0.5f * a, z + 0.5f * a);
        gl.glVertex3d(x - 0.5f * a, y - 0.5f * a, z + 0.5f * a);
        gl.glEnd();

        gl.glBegin(GL2.GL_QUADS);//góra
        gl.glColor3f(0f, 1f, 1f);
        gl.glVertex3d(x - 0.5f * a, y + 0.5f * a, z - 0.5f * a);
        gl.glVertex3d(x + 0.5f * a, y + 0.5f * a, z - 0.5f * a);
        gl.glVertex3d(x + 0.5f * a, y + 0.5f * a, z + 0.5f * a);
        gl.glVertex3d(x - 0.5f * a, y + 0.5f * a, z + 0.5f * a);
        gl.glEnd();
        gl.glPopMatrix();
    }
}
