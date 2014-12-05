/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vpl.gui.gl;

import javax.media.opengl.awt.GLCanvas;

public class VplGLCanvas extends GLCanvas {
    
    public VplGLCanvas() {
        GLControlHandler controlHandler = new GLControlHandler();
        this.addKeyListener(controlHandler);
        this.addMouseListener(controlHandler);
        this.addMouseMotionListener(controlHandler);
        this.addGLEventListener(new GLDrawingHandler());
    }
}
