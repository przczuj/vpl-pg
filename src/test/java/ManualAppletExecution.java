/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Acme.MainFrame;
import java.awt.Window;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JApplet;
import javax.swing.JFrame;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.experimental.categories.Category;

public class ManualAppletExecution {
    
    public ManualAppletExecution() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    @Category(VplMainApplet.class)
    public void manualExecution() {
        MainFrame frame = initMainFrame();
        waitUntilWindowClose(frame);
    }
    
    private MainFrame initMainFrame() {
        final JApplet applet = new VplMainApplet();
        final MainFrame frame = new MainFrame(applet, applet.getWidth(), applet.getHeight());
        applet.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                frame.setPreferredSize(applet.getSize());
            }
        });
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        return frame;
    }
    
    private void waitUntilWindowClose(Window window) {
        synchronized (this) {
            window.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    synchronized (ManualAppletExecution.this) {
                        ManualAppletExecution.this.notify();
                    }
                }
            });
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(ManualAppletExecution.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void main(String[] args) {
        new ManualAppletExecution().initMainFrame();
    }
}
