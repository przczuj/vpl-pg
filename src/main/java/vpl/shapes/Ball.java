/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.shapes;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

/**
 *
 * @author krzysztof
 */
public class Ball extends Shape{
    float r;
    
    public Ball(float x, float y, float z, float r,float anglex, float angley, float anglez) {
        super(x, y, z, anglex, angley, anglez);
        this.r=r;
    }

    @Override
    public void draw(GL2 gl) {
        gl.glPushMatrix();
        GLU glu=new GLU();
        gl.glColor3f(0.3f,0.5f,0.3f);
        gl.glTranslatef(x, y, z);
        gl.glRotatef(anglex, 1, 0, 0);
        gl.glRotatef(angley, 0, 1, 0);
        gl.glRotatef(anglez, 0, 0, 1);
        GLUquadric quadric=glu.gluNewQuadric();
        glu.gluQuadricDrawStyle(quadric, GLU.GLU_FILL);
        glu.gluQuadricNormals(quadric, GLU.GLU_FLAT);
        glu.gluQuadricOrientation(quadric, GLU.GLU_OUTSIDE);
        glu.gluSphere(quadric, r, 20, 20);
        glu.gluDeleteQuadric(quadric);
        gl.glPopMatrix();
    }
    
}
