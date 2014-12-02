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
import vpl.model.VplScene;

public class GuiControlHandler implements KeyListener, MouseListener, MouseMotionListener{
    
    private VplScene sceneModel;

    GuiControlHandler(VplScene sceneModel) {
        this.sceneModel = sceneModel;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
        int key = e.getKeyCode();
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
                //trzeba wyliczyć jak się poruszyć
                //mamy wektor

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
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    public void mouseClicked(MouseEvent me) {
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
        xang += (me.getX() - mx) / 100.0;
        mx = me.getX();
        yang -= (me.getY() - my) / 100.0;
        my = me.getY();
        if (Math.abs(xang) > 2 * Math.PI) {
            xang = 0;
        }
        /*if(Math.abs(yang)>2*Math.PI)
         yang=0;*/
        if (yang > Math.PI / 2) {
            yang = (double) (Math.PI / 2);
        } else if (yang < -Math.PI / 2) {
            yang = (double) (-Math.PI / 2);
        }
        System.out.println("Poruszona, yang=" + yang);
    }

    public void mouseMoved(MouseEvent me) {
    }
}
