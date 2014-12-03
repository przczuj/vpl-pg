/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import vpl.math.Triple;

public class GuiControlHandler implements KeyListener, MouseListener, MouseMotionListener{
    
    private Triple cameraPosition;
    private Triple lookingPoint;
    private double angleX;
    private double angleY;
    private double x,y,z;
    private double xl,yl,zl;
    double mx,my;

    GuiControlHandler() {
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        //empty method
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
        int key = e.getKeyCode();
        this.lookingPoint=GUI.getSceneModel().getLookingPoint();
        xl=this.lookingPoint.getX();
        yl=this.lookingPoint.getY();
        zl=this.lookingPoint.getZ();
        
        double x1 = xl - x;
        double y1 = yl - y;
        double z1 = zl - z;
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
        GUI.getSceneModel().getCameraPosition().setX(x);
        GUI.getSceneModel().getCameraPosition().setY(y);
        GUI.getSceneModel().getCameraPosition().setZ(z);  
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
    }

    public void mouseExited(MouseEvent me) {
    }
    
    public void mouseDragged(MouseEvent me) {
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
        
        GUI.getSceneModel().setCameraAngleH(angleX);
        GUI.getSceneModel().setCameraAngleV(angleY);
    }

    public void mouseMoved(MouseEvent me) {
    }
}
