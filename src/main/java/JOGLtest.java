package org.yourorghere;

import com.sun.opengl.util.Animator;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;



/**
 * JOGLtest.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class JOGLtest implements GLEventListener, KeyListener,MouseListener, MouseMotionListener {
    Date timer;//timer used in app 
    long start;
    float x,y,z;
    double py=0;
    float xang=0,yang=0;
    int mx,my;
    double xl,yl,zl;
    float r=0.3f;
    public static List<Ball> balls=new ArrayList(); 
    float rot=0;
    
    public static void main(String[] args) {
        Frame frame = new Frame("JOGL test");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new JOGLtest());
        frame.add(canvas);
        frame.setSize(640, 480);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        // Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }

    public void init(GLAutoDrawable drawable) {//initialization
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));
        timer=new Date();
        start=timer.getTime();
        System.out.println("Czas uruchomienia: ");
        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.setSwapInterval(1);

        // Setup the drawing area and shading mode
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
        drawable.addKeyListener(this);
        drawable.addMouseMotionListener(this);
        drawable.addMouseListener(this);
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        //if component changes size
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        if (height <= 0) { // avoid a divide by zero error!
        
            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        GLU glu=new GLU();
        
        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();
        gl.glPushMatrix();
        /*gl.glRotatef(rotx, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(roty, 0.0f, 1.0f, 0.0f);*/
        //System.out.println("Rotx= "+xang+", roty= "+yang);
        //System.out.println("xobr"+ (1*Math.sin(xang))+", zobr= "+(-1*Math.cos(xang)));
        // Move the "drawing cursor" around
        //gl.glTranslatef(-1.5f, 0.0f, -6.0f);

        // Drawing Using Triangles
        /*gl.glBegin(GL.GL_TRIANGLES);
            gl.glColor3f(1.0f, 0.0f, 0.0f);    // Set the current drawing color to red
            gl.glVertex3f(0.0f, 1.0f, 0.0f);   // Top
            gl.glColor3f(0.0f, 1.0f, 0.0f);    // Set the current drawing color to green
            gl.glVertex3f(-1.0f, -1.0f, 0.0f); // Bottom Left
            gl.glColor3f(0.0f, 0.0f, 1.0f);    // Set the current drawing color to blue
            gl.glVertex3f(1.0f, -1.0f, 0.0f);  // Bottom Right
        // Finished Drawing The Triangle
        gl.glEnd();*/

        // Move the "drawing cursor" to another position
        //gl.glTranslatef(0.0f, 1.0f, 0.0f);
        timer=new Date();
        double cur=timer.getTime();
        double delta=cur-start;        
        
        /*gl.glBegin(GL.GL_TRIANGLES);
            gl.glColor3f(1f, 1f, 0f);
            gl.glVertex3f((1f),0f,0f);
            gl.glVertex3f((1.5f),0f,0f);
            gl.glVertex3f((2f), 1f, 0f);
        gl.glEnd();  */ 
            /*gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(1f,0f,0f);
            gl.glVertex3f(-0.5f, -0.5f,0.5f);
            gl.glVertex3f(0.5f, -0.5f,0.5f);
            gl.glVertex3f(0.5f, 0.5f,0.5f);
            gl.glVertex3f(-0.5f, 0.5f,0.5f);
            gl.glEnd();
            
            gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(0f,1f,0f);
            gl.glVertex3f(0.5f, -0.5f,0.5f);
            gl.glVertex3f(0.5f, -0.5f,-0.5f);
            gl.glVertex3f(0.5f, 0.5f,-0.5f);
            gl.glVertex3f(0.5f, 0.5f,0.5f);
            gl.glEnd();*/
            
            
            //dla odniesienia
            gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(1f,0f,0f);
            gl.glVertex3f(-0.5f, -0.5f,-2f);
            gl.glVertex3f(0.5f, -0.5f,-2f);
            gl.glVertex3f(0.5f, 0.5f,-2f);
            gl.glVertex3f(-0.5f, 0.5f,-2f);
            gl.glEnd();
            
            gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(0f,1f,0f);
            gl.glVertex3f(-0.5f, -0.5f,2f);
            gl.glVertex3f(0.5f, -0.5f,2f);
            gl.glVertex3f(0.5f, 0.5f,2f);
            gl.glVertex3f(-0.5f, 0.5f,2f);
            gl.glEnd();
            
            gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(0f,0f,1f);
            gl.glVertex3f(-2f, -0.5f,-0.5f);
            gl.glVertex3f(-2f, -0.5f,0.5f);
            gl.glVertex3f(-2f, 0.5f,0.5f);
            gl.glVertex3f(-2f, 0.5f,-0.5f);
            gl.glEnd();
            
            gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(1f,1f,1f);
            gl.glVertex3f(2f, -0.5f,-0.5f);
            gl.glVertex3f(2f, -0.5f,0.5f);
            gl.glVertex3f(2f, 0.5f,0.5f);
            gl.glVertex3f(2f, 0.5f,-0.5f);
            gl.glEnd();
            
            gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(1f,1f,0f);
            gl.glVertex3f(0.5f, -2f,0.5f);
            gl.glVertex3f(-0.5f, -2f,0.5f);
            gl.glVertex3f(-0.5f, -2f,-0.5f);
            gl.glVertex3f(0.5f, -2f,-0.5f);
            gl.glEnd();
            
            gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(1f,0f,1f);
            gl.glVertex3f(0.5f, 2f,0.5f);
            gl.glVertex3f(-0.5f, 2f,0.5f);
            gl.glVertex3f(-0.5f, 2f,-0.5f);
            gl.glVertex3f(0.5f, 2f,-0.5f);
            gl.glEnd();               
            
            //rysowanie kul
            for(Ball b:balls){
                float br,bx,by,bz;
                float ax,ay,az;
                br=b.getR();
                bx=b.getX();
                by=b.getY();
                bz=b.getZ();
                ax=b.getxAngle();
                ay=b.getyAngle();
                az=b.getzAngle();
                gl.glPushMatrix();
                gl.glTranslatef(bx, by, bz);
                
                gl.glRotatef(az, 0, 0, 1);
                gl.glRotatef(ay, 0, 1, 0);
                gl.glRotatef(ax,1,0,0);                
                gl.glTranslatef(-bx, -by, -bz);
                
                
                gl.glMatrixMode(GL.GL_MODELVIEW);
                gl.glBegin(GL.GL_QUADS);//tył
                gl.glColor3f(1f,0f,0f);
                gl.glVertex3f(bx-br,by-br,bz-br);
                gl.glVertex3f(bx-br,by+br,bz-br);
                gl.glVertex3f(bx+br,by+br,bz-br);
                gl.glVertex3f(bx+br,by-br,bz-br);
                gl.glEnd();
                
                gl.glBegin(GL.GL_QUADS);//przód
                gl.glColor3f(0f,1f,0f);
                gl.glVertex3f(bx-br,by-br,bz+br);
                gl.glVertex3f(bx-br,by+br,bz+br);
                gl.glVertex3f(bx+br,by+br,bz+br);
                gl.glVertex3f(bx+br,by-br,bz+br);
                gl.glEnd();
                
                gl.glBegin(GL.GL_QUADS);//lewo
                gl.glColor3f(0f,0f,1f);
                gl.glVertex3f(bx-br,by-br,bz-br);
                gl.glVertex3f(bx-br,by+br,bz-br);
                gl.glVertex3f(bx-br,by+br,bz+br);
                gl.glVertex3f(bx-br,by-br,bz+br);
                gl.glEnd();
                
                gl.glBegin(GL.GL_QUADS);//prawo
                gl.glColor3f(1f,1f,1f);
                gl.glVertex3f(bx+br,by-br,bz-br);
                gl.glVertex3f(bx+br,by+br,bz-br);
                gl.glVertex3f(bx+br,by+br,bz+br);
                gl.glVertex3f(bx+br,by-br,bz+br);
                gl.glEnd();
                
                gl.glBegin(GL.GL_QUADS);//dół
                gl.glColor3f(1f,1f,0f);
                gl.glVertex3f(bx-br,by-br,bz-br);
                gl.glVertex3f(bx+br,by-br,bz-br);
                gl.glVertex3f(bx+br,by-br,bz+br);
                gl.glVertex3f(bx-br,by-br,bz+br);
                gl.glEnd();
                
                gl.glBegin(GL.GL_QUADS);//góra
                gl.glColor3f(0f,1f,1f);
                gl.glVertex3f(bx-br,by+br,bz-br);
                gl.glVertex3f(bx+br,by+br,bz-br);
                gl.glVertex3f(bx+br,by+br,bz+br);
                gl.glVertex3f(bx-br,by+br,bz+br);
                gl.glEnd(); 
                gl.glPopMatrix();
            }
            //gl.glVertex3f()
        
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();

        // Perspective.
        float widthHeightRatio = (float) 640 / (float) 480;
        glu.gluPerspective(45, widthHeightRatio, 1, 1000);
        /*xl=x+1*Math.sin(xang);
        zl=z-1*Math.cos(xang);
        yl=y+Math.sin(yang);
        
        
        xl*=Math.cos(yang);
        zl*=Math.cos(yang);*/
        
        
        xl=Math.sin(xang);
        xl*=Math.cos(yang);
        zl=Math.cos(xang);
        zl*=Math.cos(yang);
        yl=y+Math.sin(yang);
        
        xl=x+xl;
        zl=z-zl;
        
        
        /*System.out.println("xl="+xl+", yl="+yl+", zl="+zl+"\n "
                + "x="+x+", y="+y+", z="+z);*/
        if(py!=yl){
            System.out.println("xl="+xl+", yl="+yl+", zl="+zl+"\n "
                + "x="+x+", y="+y+", z="+z);
            py=yl;
        }
        
        glu.gluLookAt(x, y, z, xl, yl, zl, 0, 1, 0);
        //glu.gluLookAt(x, y, z, -1, 0, 0, 0, 1, 0);
        /*tu będzie funkcja na poruszanie*/
        
        
        // Change back to model view matrix.
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        //System.out.println("x="+x+" y="+y+" z="+z);
        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    public void keyTyped(KeyEvent ke) {
//        throw new UnsupportedOperationException("Not supported yet.");
        int key=ke.getKeyCode();
        switch(key){
            case 'W':
                y+=0.1;
        }
        System.out.println("Typed: "+ke.getKeyChar());
    }
    public void addElement(){
        AddElementWindow window=new AddElementWindow();
        window.show();
    }
    private void editElement() {
        EditElementWindow window=new EditElementWindow();
        window.show();
    }
    public void keyPressed(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet.");
        int key=ke.getKeyCode();
        double x1=xl-x;
        double y1=yl-y;
        double z1=zl-z;
        int sx=1,sz=1;
        if(x1>=0&&z1>=0){
            sx=1;
            sz=-1;
        }
        else if(x1<=0&&z1>0){
            sx=1;
            sz=-1;
        }
        else if(x1<0&&z1<=0){//ok
            sx=1;
            sz=-1;
        }
        else if(x1>=0&&z1<0){//ok
            sx=1;
            sz=-1;
        }
        switch(key){
            case 37:
                //trzeba wyliczyć jak się poruszyć
                //mamy wektor
                
                x+=sx*0.1*z1;
                y+=0;
                z+=sz*0.1*x1;
                break;
            case 38:
                x+=0.1*x1;
                y+=0.1*y1;
                z+=0.1*z1;
                break;
            case 39:
                x-=sx*0.1*z1;
                y-=0;
                z-=sz*0.1*x1;
                break;
            case 40:
                x-=0.1*x1;
                y-=0.1*y1;
                z-=0.1*z1;
                break;
            case 'W':
                y+=0.1;
                break;
            case 'S':
                y-=0.1;
                break;
            case 'B':
                this.addElement();
                break;
            case 'E':
                this.editElement();
                break;
            case ' '://stawiamy nowy sześcian
                balls.add(new Ball(r,x,y,z));  
                break;
            case 107:
                /*r+=0.1;
                System.out.println("Promień="+r);*/
                rot++;
                break;
            case 109:
                /*if(r>0.1){
                    r-=0.1;
                }*/
                rot--;
                System.out.println("Promień="+r);
        }
        System.out.println("Pressed: "+ke.getKeyChar());
        System.out.println("Pressed: "+ke.getKeyCode());
    }

    public void keyReleased(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet.");
        int key=ke.getKeyCode();
        System.out.println("Relased: "+ke.getKeyChar());
    }

    public void mouseClicked(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet.");
        
        System.out.println("Clicked");
    }

    public void mousePressed(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet.");
        System.out.println("Pressed");
        mx=me.getX();
        my=me.getY();
    }

    public void mouseReleased(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseEntered(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseExited(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseDragged(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet.");
        /*int x=me.getX();
        int y=me.getY();
        
        float thetaY = 360.0f * ( (float)(x-mx)/(float)640);
        float thetaX = 360.0f * ( (float)(my-y)/(float)480);
    
    mx = x;
    my = y;

    rotx += thetaX;
    roty += thetaY;*/
    xang+=(me.getX()-mx)/100.0;
    mx=me.getX();
    yang-=(me.getY()-my)/100.0;
    my=me.getY();
    if(Math.abs(xang)>2*Math.PI)
        xang=0;
    /*if(Math.abs(yang)>2*Math.PI)
        yang=0;*/
    if(yang>Math.PI/2)
        yang=(float) (Math.PI/2);
    else if(yang<-Math.PI/2)
        yang=(float)(-Math.PI/2);
    System.out.println("Poruszona, yang="+yang);    
    }

    public void mouseMoved(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet.");
        
    }

    
}

