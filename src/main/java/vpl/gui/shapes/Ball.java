/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.gui.shapes;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

/**
 *
 * @author krzysztof
 */
public class Ball extends Shape {

    double r;

    public Ball(double x, double y, double z, double r, double anglex, double angley, double anglez) {
        super(x, y, z, anglex, angley, anglez);
        this.r = r;
    }

    @Override
    public void draw(GL2 gl) {
        gl.glPushMatrix();
        GLU glu = new GLU();
        gl.glColor3f(0.3f, 0.5f, 0.3f);
        gl.glTranslated(x, y, z);
        gl.glRotated(anglex, 1, 0, 0);
        gl.glRotated(angley, 0, 1, 0);
        gl.glRotated(anglez, 0, 0, 1);
        GLUquadric quadric = glu.gluNewQuadric();
        glu.gluQuadricDrawStyle(quadric, GLU.GLU_FILL);
        glu.gluQuadricNormals(quadric, GLU.GLU_FLAT);
        glu.gluQuadricOrientation(quadric, GLU.GLU_OUTSIDE);
        glu.gluSphere(quadric, r, 20, 20);
        glu.gluDeleteQuadric(quadric);
        gl.glPopMatrix();
    }
}
