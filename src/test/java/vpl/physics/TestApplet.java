/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.physics;

/**
 *
 * @author kppx
 */
import Acme.MainFrame;
import vpl.physics.Force;
import vpl.physics.Matrix;
import javax.swing.*;
import java.applet.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import vpl.physics.RigidBody;
import vpl.physics.Triple;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestApplet extends Applet {

    RigidBody rb;
    private final static int TICK_RATE_MILLISEC = 50;

    public static void main(String[] args) {
        final Applet applet = new TestApplet();
        final MainFrame frame = new MainFrame(applet, applet.getWidth(), applet.getHeight());
        applet.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                frame.setPreferredSize(applet.getSize());
            }
        });
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void main() {
        Matrix mattest = new Matrix(3, 3);
        mattest.setValueAt(0, 0, 0.4);
        mattest.setValueAt(1, 1, 0.4);
        mattest.setValueAt(2, 2, 0.4);
        try {
            double d = mattest.determinant(mattest);
            d = d;
        } catch (Exception ex) {
            Logger.getLogger(TestApplet.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            System.out.print("hej");
            //MovementTest  mv = new MovementTest();
            //       init();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void init() {

        try {
            rb = new RigidBody();
        } catch (Exception ex) {
            Logger.getLogger(TestApplet.class.getName()).log(Level.SEVERE, null, ex);
        }
        rb.setTimeTick(TICK_RATE_MILLISEC);
        Force f = new Force();
        Triple fval = new Triple();
        Triple floc = new Triple();
        fval.setX(2);
        f.setForceLocation(floc);
        f.setForceValue(fval);
        try {
            rb.setMass(1);
        } catch (Exception ex) {
            Logger.getLogger(TestApplet.class.getName()).log(Level.SEVERE, null, ex);
        }
        rb.registerForce(f);
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                /*  System.out.print("test1");
                 Triple t = rb.getPosition();
                 System.out.print(t.getX());
                 System.out.print(t.getY());
                 System.out.print(t.getZ());
                 System.out.print("test5");*/
            }
        }, /*first execution delay*/ 0, TICK_RATE_MILLISEC, TimeUnit.MILLISECONDS);

        try {
            JOptionPane.showMessageDialog(this, "Hello World!", "vpl-pg ;>", JOptionPane.INFORMATION_MESSAGE);

            System.out.print("test2");
            Triple t = rb.getPosition();
            //  MovementTest est = new MovementTest();
            //   est.main();
            TorqueTest est2 = new TorqueTest();
            est2.main();
            System.out.print(t.getX());
            System.out.print("test3");
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }

    }
}