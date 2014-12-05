/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.gui.gl;

import java.util.ArrayList;
import java.util.List;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import vpl.utils.Timer;
import vpl.math.Triple;
import vpl.physics.controller.Model;
import vpl.physics.RigidBody;
import vpl.physics.controller.ControllerStub;
import vpl.physics.shapes.BallShape;
import vpl.physics.shapes.ConeShape;
import vpl.physics.shapes.CuboidShape;
import vpl.physics.shapes.CyllinderShape;
import vpl.shapes.Ball;
import vpl.shapes.Cone;
import vpl.shapes.Cube;
import vpl.shapes.Cuboid;
import vpl.shapes.Cyllinder;
import vpl.shapes.Shape;

public class GLDrawingHandler implements GLEventListener {

    private double angleX;
    private double angleY;
    private double x, y, z;
    private double xl, yl, zl;
    double mx, my;
    double py = 0;
    public static List<Shape> shapesList;
    
    private final Model model;
    private final ControllerStub api;

    public GLDrawingHandler() {
        this.model = Model.getInstance();
        this.api = model.getPhysics();
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

    }

    @Override
    public void display(GLAutoDrawable drawable) {
        Timer.measureTime();
        GL2 gl = drawable.getGL().getGL2();
        GLU glu = new GLU();

        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();
        gl.glPushMatrix();

        //for reference
        drawReferenceSquares(gl);

        this.angleX = model.getCameraAngleH();
        this.angleY = model.getCameraAngleV();

        Triple cameraPosition = model.getCameraPosition();
        this.x = cameraPosition.getX();
        this.y = cameraPosition.getY();
        this.z = cameraPosition.getZ();

        Triple lookingPoint = model.getLookingPoint();
        this.xl = lookingPoint.getX();
        this.yl = lookingPoint.getY();
        this.zl = lookingPoint.getZ();

        //collisions (should be moved to better place)
        checkCollisions();

        convertRigidBodiesToShapes();
        drawShapes(gl);

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        // Perspective.
        double widthHeightRatio = (double) drawable.getSurfaceWidth() / (double) drawable.getSurfaceHeight();
        glu.gluPerspective(45, widthHeightRatio, 1, 1000);

        xl = Math.sin(angleX);
        xl *= Math.cos(angleY);
        zl = Math.cos(angleX);
        zl *= Math.cos(angleY);
        yl = y + Math.sin(angleY);

        xl = x + xl;
        zl = z - zl;

        model.getLookingPoint().setX(xl);
        model.getLookingPoint().setY(yl);
        model.getLookingPoint().setZ(zl);

        if (py != yl) {
            System.out.println("xl=" + xl + ", yl=" + yl + ", zl=" + zl + "\n "
                    + "x=" + x + ", y=" + y + ", z=" + z);
            py = yl;
        }

        glu.gluLookAt(x, y, z, xl, yl, zl, 0, 1, 0);

        // Change back to model view matrix.
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glFlush();
        Timer.measureTime();
        System.out.println(Timer.getDelta());
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
        System.out.println("RESIZE! " + width + " " + height);
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

    public void drawShapes(GL2 gl) {
        for (Shape s : shapesList) {
            s.draw(gl);
        }
    }

    public void convertRigidBodiesToShapes() {
        shapesList = new ArrayList<Shape>();
        List<RigidBody> rigidBodiesList = new ArrayList(api.getRigidBodies().values());
        for (RigidBody rb : rigidBodiesList) {
            String type = rb.getShape().getType();
            Triple position = rb.getPosition();
            Triple angles = rb.getRotationAngles().getAngles();
            switch (type.toUpperCase()) {
                case "BALL":
                    shapesList.add(new Ball(position.getX(), position.getY(),
                            position.getZ(), ((BallShape) rb.getShape()).getR(), angles.getX(), angles.getY(), angles.getZ()));
                    break;
                case "CUBE":
                    CuboidShape cube = (CuboidShape) rb.getShape();
                    shapesList.add(new Cube(position.getX(), position.getY(),
                            position.getZ(), angles.getX(), angles.getY(), angles.getZ(), cube.getX()));
                    break;
                case "CUBOID":
                    CuboidShape cs = (CuboidShape) rb.getShape();
                    shapesList.add(new Cuboid(position.getX(), position.getY(), position.getZ(),
                            angles.getX(), angles.getY(), angles.getZ(), cs.getX(), cs.getY(), cs.getZ()));
                    break;
                case "CYLLINDER":
                    CyllinderShape cyl = (CyllinderShape) rb.getShape();
                    shapesList.add(new Cyllinder(position.getX(), position.getY(), position.getZ(),
                            angles.getX(), angles.getY(), angles.getZ(), cyl.getR(), cyl.getH()));
                    break;
                case "CONE":
                    ConeShape cone = (ConeShape) rb.getShape();
                    shapesList.add(new Cone(position.getX(), position.getY(), position.getZ(),
                            angles.getX(), angles.getY(), angles.getZ(), cone.getR(), cone.getH()));
                    break;
            }
        }
    }

    private void checkCollisions() {
        List<RigidBody> rigidBodies = new ArrayList<>(api.getRigidBodies().values());

        for (int i = 0; i < rigidBodies.size(); i++) {
            List<RigidBody> checkInNextStep = new ArrayList<RigidBody>();
            for (int j = i + 1; j < rigidBodies.size(); j++) {
                RigidBody rb1 = rigidBodies.get(i);
                RigidBody rb2 = rigidBodies.get(j);
                double distance = rb1.getPosition().getDistance(rb2.getPosition());
                double r1 = rb1.getShape().getSphereRadius();
                double r2 = rb2.getShape().getSphereRadius();
                if (distance <= r1 + r2) {
                    checkInNextStep.add(rb2);
                }
            }
        }
    }
}
