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
public class Cube extends Shape {
    protected float a;
    public Cube(float x, float y, float z, float anglex, float angley, float anglez, float a) {
        super(x, y, z, anglex, angley, anglez);
        this.a=a;
    }

    @Override
    public void draw(GL gl) {
        gl.glPushMatrix();
                gl.glTranslatef(x, y, z);
                
                gl.glRotatef(anglez, 0, 0, 1);
                gl.glRotatef(angley, 0, 1, 0);
                gl.glRotatef(anglex,1,0,0);                
                gl.glTranslatef(-x, -y, -z);
                
                
                gl.glMatrixMode(GL.GL_MODELVIEW);
                gl.glBegin(GL.GL_QUADS);//back
                gl.glColor3f(1f,0f,0f);
                gl.glVertex3f(x-0.5f*a,y-0.5f*a,z-0.5f*a);
                gl.glVertex3f(x-0.5f*a,y+0.5f*a,z-0.5f*a);
                gl.glVertex3f(x+0.5f*a,y+0.5f*a,z-0.5f*a);
                gl.glVertex3f(x+0.5f*a,y-0.5f*a,z-0.5f*a);
                gl.glEnd();
                
                gl.glBegin(GL.GL_QUADS);//front
                gl.glColor3f(0f,1f,0f);
                gl.glVertex3f(x-0.5f*a,y-0.5f*a,z+0.5f*a);
                gl.glVertex3f(x-0.5f*a,y+0.5f*a,z+0.5f*a);
                gl.glVertex3f(x+0.5f*a,y+0.5f*a,z+0.5f*a);
                gl.glVertex3f(x+0.5f*a,y-0.5f*a,z+0.5f*a);
                gl.glEnd();
                
                gl.glBegin(GL.GL_QUADS);//left
                gl.glColor3f(0f,0f,1f);
                gl.glVertex3f(x-0.5f*a,y-0.5f*a,z-0.5f*a);
                gl.glVertex3f(x-0.5f*a,y+0.5f*a,z-0.5f*a);
                gl.glVertex3f(x-0.5f*a,y+0.5f*a,z+0.5f*a);
                gl.glVertex3f(x-0.5f*a,y-0.5f*a,z+0.5f*a);
                gl.glEnd();
                
                gl.glBegin(GL.GL_QUADS);//right
                gl.glColor3f(1f,1f,1f);
                gl.glVertex3f(x+0.5f*a,y-0.5f*a,z-0.5f*a);
                gl.glVertex3f(x+0.5f*a,y+0.5f*a,z-0.5f*a);
                gl.glVertex3f(x+0.5f*a,y+0.5f*a,z+0.5f*a);
                gl.glVertex3f(x+0.5f*a,y-0.5f*a,z+0.5f*a);
                gl.glEnd();
                
                gl.glBegin(GL.GL_QUADS);//up
                gl.glColor3f(1f,1f,0f);
                gl.glVertex3f(x-0.5f*a,y-0.5f*a,z-0.5f*a);
                gl.glVertex3f(x+0.5f*a,y-0.5f*a,z-0.5f*a);
                gl.glVertex3f(x+0.5f*a,y-0.5f*a,z+0.5f*a);
                gl.glVertex3f(x-0.5f*a,y-0.5f*a,z+0.5f*a);
                gl.glEnd();
                
                gl.glBegin(GL.GL_QUADS);//down
                gl.glColor3f(0f,1f,1f);
                gl.glVertex3f(x-0.5f*a,y+0.5f*a,z-0.5f*a);
                gl.glVertex3f(x+0.5f*a,y+0.5f*a,z-0.5f*a);
                gl.glVertex3f(x+0.5f*a,y+0.5f*a,z+0.5f*a);
                gl.glVertex3f(x-0.5f*a,y+0.5f*a,z+0.5f*a);
                gl.glEnd(); 
                gl.glPopMatrix();        
    }
    
}
