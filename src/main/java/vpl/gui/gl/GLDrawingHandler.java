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
import vpl.gui.shapes.Ball;
import vpl.gui.shapes.Cone;
import vpl.gui.shapes.Cube;
import vpl.gui.shapes.Cuboid;
import vpl.gui.shapes.Cyllinder;
import vpl.gui.shapes.Shape;
import vpl.math.BasicMath;

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
        //Timer.measureTime();
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
        //System.out.println(String.format("%.5f", Timer.getDelta()));
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
            //Triple angles = rb.getRotationAngles().getAngles();
            Triple angles=rb.getSimpleAngles();
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
        BasicMath logic=new BasicMath();
        for (int i = 0; i < rigidBodies.size(); i++) {
            List<RigidBody> checkInNextStep = new ArrayList<RigidBody>();
            for (int j = i + 1; j < rigidBodies.size(); j++) {
                RigidBody rb1 = rigidBodies.get(i);
                RigidBody rb2 = rigidBodies.get(j);
                double distance = rb1.getPosition().getDistance(rb2.getPosition());
                double r1 = rb1.getShape().getSphereRadius();
                double r2 = rb2.getShape().getSphereRadius();
                if (distance <= r1 + r2) {
                    //checkInNextStep.add(rb2);
                    
                    //spheral collision
                    
                    //basis vector
                    double xx,xy,xz;
                    Triple pos1,pos2;
                    pos1=rb1.getPosition();
                    pos2=rb2.getPosition();
                    xx=pos2.getX()-pos1.getX();
                    xy=pos2.getY()-pos2.getY();
                    xz=pos2.getZ()-pos2.getZ();                    
                    Triple xvec=new Triple(xx,xy,xz);
                    
                    //normalization
                    double d=xvec.getLength();
                    xx/=d;
                    xy/=d;
                    xz/=d;
                    xvec=new Triple(xx,xy,xz);
                    
                    //x-direction velocity vector and the perpendicular y-vector
                    Triple collisionPoint=new Triple(pos1.getX()+r1*xvec.getX(),
                        pos1.getY()+r1*xvec.getY(),pos1.getZ()+r1*xvec.getZ());
                    //third line
                    Triple v1= rb1.getPointVelociy(collisionPoint);
                    //fourth line
                    double x1=logic.dotProduct(xvec, v1);
                    //fifth line
                    Triple v1x=new Triple();
                    v1x.setXYZ(xvec.getX()*x1, xvec.getY()*x1, xvec.getZ()*x1);
                    //sixth line
                    Triple v1y=new Triple();
                    v1y.setXYZ(v1.getX()-v1x.getX(), v1.getY()-v1x.getY(), v1.getZ()-v1x.getZ());
                    //seventh line
                    double m1=rb1.getMass();
                    
                    //anologycal operations for second rb
                    xvec.setXYZ(xvec.getX()*(-1), xvec.getY()*(-1), xvec.getZ()*(-1));
                    //x-direction velocity vector and the perpendicular y-vector
                    Triple collisionPoint2=new Triple(pos2.getX()+r2*xvec.getX(),
                        pos2.getY()+r2*xvec.getY(),pos2.getZ()+r2*xvec.getZ());
                    //third line
                    Triple v2= rb2.getPointVelociy(collisionPoint);
                    //fourth line
                    double x2=logic.dotProduct(xvec, v2);
                    //fifth line
                    Triple v2x=new Triple();
                    v2x.setXYZ(xvec.getX()*x2, xvec.getY()*x2, xvec.getZ()*x2);
                    //sixth line
                    Triple v2y=new Triple();
                    v2y.setXYZ(v2.getX()-v2x.getX(), v2.getY()-v2x.getY(), v2.getZ()-v2x.getZ());
                    //seventh line
                    double m2=rb2.getMass();
                    
                    //velocity 1
                    Triple vel1;
                    vel1=new Triple();
                    double mass1=(m1-m2)/(m2+m1);
                    double mass2=(2*m2)/(m1+m2);
                    vel1.setX(v1x.getX()*mass1+v2x.getX()*mass2+v1y.getX());
                    vel1.setY(v1x.getY()*mass1+v2x.getY()*mass2+v1y.getY());
                    vel1.setX(v1x.getZ()*mass1+v2x.getZ()*mass2+v1y.getZ());
                    
                    //velocity2
                    Triple vel2;
                    vel2=new Triple();
                    double mass3=(2*m1)/(m1+m2);
                    double mass4=(m2-m1)/(m1+m2);
                    vel2.setX(v1x.getX()*mass3+v2x.getX()*mass4+v2y.getX());
                    vel2.setY(v1x.getY()*mass3+v2x.getY()*mass4+v2y.getY());
                    vel2.setX(v1x.getZ()*mass3+v2x.getZ()*mass4+v2y.getZ());
                }
            }
        }
    }
}
