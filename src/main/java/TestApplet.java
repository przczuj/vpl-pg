import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.FPSAnimator;
import joglTest.JOGLtest;
import sun.security.util.SecurityConstants;

import javax.media.opengl.GLAnimatorControl;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestApplet extends Applet {

    private final static int TICK_RATE_MILLISEC = 50;

    private GLAnimatorControl glanimatorcontrol;

    @Override
    public void init() {
        if (!hasAllPermission()) {
            try {
                JOptionPane.showMessageDialog(this, "We have no permisssions ;<", "NO PERMISSIONS!", JOptionPane.INFORMATION_MESSAGE);
            }
            catch(Exception error) {
                System.out.println(error.getMessage());
            }
        } else {
            this.setSize(640, 480);
            initJoglTest2();
        }

        try {
            JOptionPane.showMessageDialog(this, "Hello World!", "VPL-PG", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception error) {
            System.out.println(error.getMessage());
        }
    }

    private boolean hasAllPermission()
    {
        try {
            System.getSecurityManager().checkPermission(SecurityConstants.ALL_PERMISSION);
            return true;
        } catch (SecurityException se) {
            return false;
        }
    }

    public void initJoglTest2() {
        GLProfile.initSingleton();
        setLayout( new BorderLayout() );

        final GLCanvas glcanvas = new GLCanvas();

        JOGLtest context = new JOGLtest();
        glcanvas.addGLEventListener(context);
        glcanvas.addKeyListener(context);
        glcanvas.addMouseMotionListener(context);
        glcanvas.addMouseListener(context);

        glcanvas.setSize( getSize() );
        add( glcanvas, BorderLayout.CENTER );
        glanimatorcontrol = new FPSAnimator( glcanvas, 30 );
    }

    @Override
    public void start() {
        if (glanimatorcontrol != null) {
            glanimatorcontrol.start();
        }
    }

    @Override
    public void stop() {
        if (glanimatorcontrol != null) {
            glanimatorcontrol.stop();
        }
    }

    private void initJoglTest() {
        Container frame = this;//new Frame("JOGL test");
        GLCanvas canvas = new GLCanvas();

        JOGLtest context = new JOGLtest();
        canvas.addGLEventListener(context);
        canvas.addKeyListener(context);
        canvas.addMouseMotionListener(context);
        canvas.addMouseListener(context);

        frame.add(canvas);
        frame.setSize(640, 480);
        final Animator animator = new Animator(canvas);
//        frame.addWindowListener(new WindowAdapter() {
//
//            @Override
//            public void windowClosing(WindowEvent e) {
//                // Run this on another thread than the AWT event queue to
//                // make sure the call to Animator.stop() completes before
//                // exiting
//                new Thread(new Runnable() {
//
//                    public void run() {
//                        animator.stop();
//                        System.exit(0);
//                    }
//                }).start();
//            }
//        });
//        // Center frame
//        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }

    private void startTask() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // placeholder
            }
        }, /*first execution delay*/0, TICK_RATE_MILLISEC, TimeUnit.MILLISECONDS);
    }

}