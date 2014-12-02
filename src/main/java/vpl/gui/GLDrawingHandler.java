/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.gui;

import java.util.ArrayList;
import java.util.List;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import vpl.math.Triple;
import vpl.model.VplScene;
import vpl.physics.RigidBody;
import vpl.physics.shapes.BallShape;
import vpl.shapes.Ball;
import vpl.shapes.Shape;


public class GLDrawingHandler implements GLEventListener {

    private VplScene sceneModel;

    GLDrawingHandler(VplScene sceneModel) {
        this.sceneModel = sceneModel;
    }
    
    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.setSwapInterval(1);
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL2.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glDepthFunc(GL.GL_LEQUAL);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        //todo: tu będzie  jakiś komentarz, póki co DUPA!!!!!!!!!!!!!!!!!(by kłuło w oczy, i bym szybko zmienił)
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        GLU glu = new GLU();

        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();
        gl.glPushMatrix();

        //dla odniesienia
        drawReferenceSquares(gl);

        shapesList = new ArrayList<Shape>();
        List<RigidBody> rigidBodiesList = api.getRigidBodies();
        for (RigidBody rb : rigidBodiesList) {
            String type = rb.getShape().getType();
            Triple position = rb.getPosition();
            Triple angles = rb.getRotationAngles().getAngles();
            switch (type.toUpperCase()) {
                case "BALL":
                    shapesList.add(new Ball(position.getX(), position.getY(),
                            position.getZ(), ((BallShape) rb.getShape()).getR(), angles.getX(), angles.getY(), angles.getZ()));
                    break;
            }
        }
        for (Shape s : shapesList) {
            s.draw(gl);
        }

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        // Perspective.
        double widthHeightRatio = (double) 640 / (double) 480;
        glu.gluPerspective(45, widthHeightRatio, 1, 1000);

        xl = Math.sin(xang);
        xl *= Math.cos(yang);
        zl = Math.cos(xang);
        zl *= Math.cos(yang);
        yl = y + Math.sin(yang);

        xl = x + xl;
        zl = z - zl;

        if (py != yl) {
            System.out.println("xl=" + xl + ", yl=" + yl + ", zl=" + zl + "\n "
                    + "x=" + x + ", y=" + y + ", z=" + z);
            py = yl;
        }

        glu.gluLookAt(x, y, z, xl, yl, zl, 0, 1, 0);
        //glu.gluLookAt(x, y, z, -1, 0, 0, 0, 1, 0);
        /*tu będzie funkcja na poruszanie*/

        // Change back to model view matrix.
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        //System.out.println("x="+x+" y="+y+" z="+z);
        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }
    
    private void drawReferenceSquares(GL2 gl) {
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(1f, 0f, 0f);
        gl.glVertex3f(-0.5f, -0.5f, -2f);
        gl.glVertex3f(0.5f, -0.5f, -2f);
        gl.glVertex3f(0.5f, 0.5f, -2f);
        gl.glVertex3f(-0.5f, 0.5f, -2f);
        gl.glEnd();

        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(0f, 1f, 0f);
        gl.glVertex3f(-0.5f, -0.5f, 2f);
        gl.glVertex3f(0.5f, -0.5f, 2f);
        gl.glVertex3f(0.5f, 0.5f, 2f);
        gl.glVertex3f(-0.5f, 0.5f, 2f);
        gl.glEnd();

        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(0f, 0f, 1f);
        gl.glVertex3f(-2f, -0.5f, -0.5f);
        gl.glVertex3f(-2f, -0.5f, 0.5f);
        gl.glVertex3f(-2f, 0.5f, 0.5f);
        gl.glVertex3f(-2f, 0.5f, -0.5f);
        gl.glEnd();

        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(1f, 1f, 1f);
        gl.glVertex3f(2f, -0.5f, -0.5f);
        gl.glVertex3f(2f, -0.5f, 0.5f);
        gl.glVertex3f(2f, 0.5f, 0.5f);
        gl.glVertex3f(2f, 0.5f, -0.5f);
        gl.glEnd();

        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(1f, 1f, 0f);
        gl.glVertex3f(0.5f, -2f, 0.5f);
        gl.glVertex3f(-0.5f, -2f, 0.5f);
        gl.glVertex3f(-0.5f, -2f, -0.5f);
        gl.glVertex3f(0.5f, -2f, -0.5f);
        gl.glEnd();

        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(1f, 0f, 1f);
        gl.glVertex3f(0.5f, 2f, 0.5f);
        gl.glVertex3f(-0.5f, 2f, 0.5f);
        gl.glVertex3f(-0.5f, 2f, -0.5f);
        gl.glVertex3f(0.5f, 2f, -0.5f);
        gl.glEnd();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();
        GLU glu = new GLU();

        if (height <= 0) { // avoid a divide by zero error!

            height = 1;
        }
        final double h = (double) width / (double) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
}
