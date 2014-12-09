/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.gui.gl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import vpl.math.Triple;
import vpl.physics.controller.Model;

public class GLControlHandler implements KeyListener, MouseListener, MouseMotionListener {
    
//    private Triple cameraPosition;
//    private Triple lookingPoint;
//    private double angleX;
//    private double angleY;
//    private double x,y,z;
//    private double xl,yl,zl;
    double mx,my;
    
    private final Model model;

    GLControlHandler() {
        this.model = Model.getInstance();
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        //empty method
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
        int key = e.getKeyCode();
        Triple lookingPoint = model.getLookingPoint();
        Triple cameraPosition = model.getCameraPosition();
        
        double x = cameraPosition.getX();
        double y = cameraPosition.getY();
        double z = cameraPosition.getZ();
        
        double x1 = lookingPoint.getX() - x;
        double y1 = lookingPoint.getY() - y;
        double z1 = lookingPoint.getZ() - z;
        int sx = 1, sz = 1;
        if (x1 >= 0 && z1 >= 0) {
            sx = 1;
            sz = -1;
        } else if (x1 <= 0 && z1 > 0) {
            sx = 1;
            sz = -1;
        } else if (x1 < 0 && z1 <= 0) {//ok
            sx = 1;
            sz = -1;
        } else if (x1 >= 0 && z1 < 0) {//ok
            sx = 1;
            sz = -1;
        }
        switch (key) {
            case KeyEvent.VK_A: //37:
                x += sx * 0.1 * z1;
                y += 0;
                z += sz * 0.1 * x1;
                break;
            case KeyEvent.VK_W: //38:
                x += 0.1 * x1;
                y += 0.1 * y1;
                z += 0.1 * z1;
                break;
            case KeyEvent.VK_D: //39:
                x -= sx * 0.1 * z1;
                y -= 0;
                z -= sz * 0.1 * x1;
                break;
            case KeyEvent.VK_S: //40:
                x -= 0.1 * x1;
                y -= 0.1 * y1;
                z -= 0.1 * z1;
                break;
            case KeyEvent.VK_SPACE: //'W':
                y += 0.1;
                break;
            case KeyEvent.VK_SHIFT: //'S':
                y -= 0.1;
                break;
        }
        cameraPosition.setX(x);
        cameraPosition.setY(y);
        cameraPosition.setZ(z);
        Model.getInstance().refreshView(Model.CAMERA_POSITION_CHANGED);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //empty method
    }
    
    public void mouseClicked(MouseEvent me) {
        //empty method
    }

    public void mousePressed(MouseEvent me) {
        System.out.println("Pressed");
        mx = me.getX();
        my = me.getY();
    }

    public void mouseReleased(MouseEvent me) {
    }

    public void mouseEntered(MouseEvent me) {
        System.out.println("entered");
    }

    public void mouseExited(MouseEvent me) {
        System.out.println("exited");
    }
    
    public void mouseDragged(MouseEvent me) {
        double angleX = model.getCameraAngleH();
        double angleY = model.getCameraAngleV();
        
        angleX += (me.getX() - mx) / 100.0;
        mx = me.getX();
        angleY -= (me.getY() - my) / 100.0;
        my = me.getY();
        if (Math.abs(angleX) > 2 * Math.PI) {
            angleX = 0;
        }
        if (angleY > Math.PI / 2) {
            angleY = (double) (Math.PI / 2);
        } else if (angleY < -Math.PI / 2) {
            angleY = (double) (-Math.PI / 2);
        }
        System.out.println("Poruszona, angleY=" + angleY);
        
        model.setCameraAngleH(angleX);
        model.setCameraAngleV(angleY);
    }

    public void mouseMoved(MouseEvent me) {
    }
}
