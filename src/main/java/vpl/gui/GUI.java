/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.gui;

import vpl.shapes.Ball;
import vpl.shapes.Cuboid;
import vpl.shapes.Cube;
import vpl.shapes.Shape;
import com.jogamp.opengl.util.Animator;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAnimatorControl;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import vpl.shapes.Cone;
import vpl.shapes.Cyllinder;
import vpl.physics.controller.ControllerStub;
import vpl.physics.shapes.BallShape;

/**
 *
 * @author krzysztof
 */
public class GUI extends javax.swing.JFrame {

    /**
     * Creates new form GUI
     */
    double xl, yl, zl;
    double xang = 0, yang = 0;
    double x, y, z;
    double py = 0;
    int mx, my;
    public static List<Shape> shapesList = new ArrayList<Shape>();
    ControllerStub api;

    public GUI() {
        glCanvas = new GLCanvas();

        initComponents();
        ControllerStub api = new ControllerStub();
        this.jPanel1.add(this.glCanvas);
        glCanvas.setSize(jPanel1.getSize());

        DefaultListModel<String> model = new DefaultListModel<String>();
        model.addElement("ball");
        model.addElement("cube");
        model.addElement("cuboid");
        model.addElement("cyllinder");
        model.addElement("cone");
        this.elementsList.setModel(model);
        this.elementsList.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent lse) {
                //tu będzie blokowanie odpowiednich pól
            }
        });
    }

    public void prepareJOGL() {
        this.glCanvas.addKeyListener(new KeyListener() {

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
        });
        this.glCanvas.addMouseListener(new MouseListener() {
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
        });
        this.glCanvas.addMouseMotionListener(new MouseMotionListener() {
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
        });
        this.glCanvas.addGLEventListener(new GLEventListener() {

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
                //tu będzie  jakiś komentarz, póki co DUPA!!!!!!!!!!!!!!!!!(by kłuło w oczy, i bym szybko zmienił)
            }

            @Override
            public void display(GLAutoDrawable drawable) {
                GL2 gl = drawable.getGL().getGL2();
                GLU glu = new GLU();

                // Clear the drawing area
                gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
                // Reset the current matrix to the "identity"
                gl.glLoadIdentity();
                gl.glPushMatrix();

                //dla odniesienia
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

                for (Shape s : shapesList) {
                    s.draw(gl);
                }

                gl.glMatrixMode(GL2.GL_PROJECTION);
                gl.glLoadIdentity();

                // Perspective.
                double widthHeightRatio = (double) 640 / (double) 480;
                glu.gluPerspective(45, widthHeightRatio, 1, 1000);

                xl = Math.sin(xang);
                xl *= Math.cos(yang);
                zl = Math.cos(xang);
                zl *= Math.cos(yang);
                yl = y + Math.sin(yang);

                xl = x + xl;
                zl = z - zl;

                if (py != yl) {
                    System.out.println("xl=" + xl + ", yl=" + yl + ", zl=" + zl + "\n "
                            + "x=" + x + ", y=" + y + ", z=" + z);
                    py = yl;
                }

                glu.gluLookAt(x, y, z, xl, yl, zl, 0, 1, 0);
                //glu.gluLookAt(x, y, z, -1, 0, 0, 0, 1, 0);
        /*tu będzie funkcja na poruszanie*/

                // Change back to model view matrix.
                gl.glMatrixMode(GL2.GL_MODELVIEW);
                gl.glLoadIdentity();
                //System.out.println("x="+x+" y="+y+" z="+z);
                // Flush all drawing operations to the graphics card
                gl.glFlush();
            }

            @Override
            public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
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
        });
        final Animator animator = new Animator(this.glCanvas);
        animator.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        elementsList = new javax.swing.JList();
        propertiesLabel = new javax.swing.JLabel();
        xLabel = new javax.swing.JLabel();
        yLabel = new javax.swing.JLabel();
        zLabel = new javax.swing.JLabel();
        rLabel = new javax.swing.JLabel();
        aLabel = new javax.swing.JLabel();
        bLabel = new javax.swing.JLabel();
        cLabel = new javax.swing.JLabel();
        xAngleLabel = new javax.swing.JLabel();
        yAngleLabel = new javax.swing.JLabel();
        zAngleLabel = new javax.swing.JLabel();
        xTextField = new javax.swing.JTextField();
        yTextField = new javax.swing.JTextField();
        zTextField = new javax.swing.JTextField();
        rTextField = new javax.swing.JTextField();
        aTextField = new javax.swing.JTextField();
        bTextField = new javax.swing.JTextField();
        cTextField = new javax.swing.JTextField();
        xAngleTextField = new javax.swing.JTextField();
        yAngleTextField = new javax.swing.JTextField();
        zAngleTextField = new javax.swing.JTextField();
        addShapeButton = new javax.swing.JButton();
        positionLabel = new javax.swing.JLabel();
        hLabel = new javax.swing.JLabel();
        hTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 639, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 585, Short.MAX_VALUE)
        );

        elementsList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(elementsList);

        propertiesLabel.setText("properties:");

        xLabel.setText("x:");

        yLabel.setText("y:");

        zLabel.setText("z:");

        rLabel.setText("r:");

        aLabel.setText("a:");

        bLabel.setText("b:");

        cLabel.setText("c:");

        xAngleLabel.setText("x angle:");

        yAngleLabel.setText("y angle:");

        zAngleLabel.setText("z angle");

        addShapeButton.setText("Add");
        addShapeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addShapeButtonActionPerformed(evt);
            }
        });

        positionLabel.setText("position:");

        hLabel.setText("h:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addShapeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(yAngleLabel)
                                    .addComponent(zAngleLabel))
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(yAngleTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                                    .addComponent(zAngleTextField)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(xLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(yLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(yTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                                    .addComponent(xTextField)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(propertiesLabel)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rLabel)
                                    .addComponent(zLabel)
                                    .addComponent(aLabel)
                                    .addComponent(bLabel)
                                    .addComponent(cLabel)
                                    .addComponent(xAngleLabel)
                                    .addComponent(hLabel))
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(hTextField)
                                    .addComponent(xAngleTextField)
                                    .addComponent(cTextField)
                                    .addComponent(bTextField)
                                    .addComponent(aTextField)
                                    .addComponent(zTextField)
                                    .addComponent(rTextField)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(positionLabel)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(propertiesLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(xLabel)
                            .addComponent(xTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(yLabel)
                            .addComponent(yTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(zLabel)
                            .addComponent(zTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rLabel)
                            .addComponent(rTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(aLabel)
                            .addComponent(aTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bLabel)
                            .addComponent(bTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hLabel)
                            .addComponent(hTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addShapeButton)
                        .addGap(85, 85, 85)
                        .addComponent(positionLabel)
                        .addContainerGap(182, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cLabel)
                            .addComponent(cTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(xAngleLabel)
                            .addComponent(xAngleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(yAngleLabel)
                            .addComponent(yAngleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(zAngleLabel)
                            .addComponent(zAngleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(183, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addShapeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addShapeButtonActionPerformed
        String selected = this.elementsList.getSelectedValue().toString();
        double x = Double.parseDouble(xTextField.getText());
        double y = Double.parseDouble(yTextField.getText());
        double z = Double.parseDouble(zTextField.getText());
        double r = Double.parseDouble(rTextField.getText());
        double xa = Double.parseDouble(xAngleTextField.getText());
        double ya = Double.parseDouble(yAngleTextField.getText());
        double za = Double.parseDouble(zAngleTextField.getText());
        double h = Double.parseDouble(hTextField.getText());
        double a = Double.parseDouble(aTextField.getText());
        double b = Double.parseDouble(bTextField.getText());
        double c = Double.parseDouble(cTextField.getText());

        if (selected.equals("ball")) {
            shapesList.add(new Ball(x, y, z, r, xa, ya, za));
            try {
                BallShape ball=new BallShape();
                //shape.setR(()r);
            } catch (Exception ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //api.createRigidBody(new Shape(), null, 0, 10);
            
        } else if (selected.equals("cube")) {
            shapesList.add(new Cube(x, y, z, xa, ya, za, a));
        } else if (selected.equals("cuboid")) {
            shapesList.add(new Cuboid(x, y, z, xa, ya, za, a, b, c));
        } else if (selected.equals("cyllinder")) {
            shapesList.add(new Cyllinder(x, y, z, xa, ya, za, r, h));
        } else if (selected.equals("cone")) {
            shapesList.add(new Cone(x, y, z, xa, ya, za, r, h));
        } else {
            System.out.println("ERROR. WRONG SELECTION");
        }
    }//GEN-LAST:event_addShapeButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GUI gui = new GUI();
                gui.setVisible(true);
                gui.prepareJOGL();
            }
        });
    }

    private GLCanvas glCanvas;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aLabel;
    private javax.swing.JTextField aTextField;
    private javax.swing.JButton addShapeButton;
    private javax.swing.JLabel bLabel;
    private javax.swing.JTextField bTextField;
    private javax.swing.JLabel cLabel;
    private javax.swing.JTextField cTextField;
    private javax.swing.JList elementsList;
    private javax.swing.JLabel hLabel;
    private javax.swing.JTextField hTextField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel positionLabel;
    private javax.swing.JLabel propertiesLabel;
    private javax.swing.JLabel rLabel;
    private javax.swing.JTextField rTextField;
    private javax.swing.JLabel xAngleLabel;
    private javax.swing.JTextField xAngleTextField;
    private javax.swing.JLabel xLabel;
    private javax.swing.JTextField xTextField;
    private javax.swing.JLabel yAngleLabel;
    private javax.swing.JTextField yAngleTextField;
    private javax.swing.JLabel yLabel;
    private javax.swing.JTextField yTextField;
    private javax.swing.JLabel zAngleLabel;
    private javax.swing.JTextField zAngleTextField;
    private javax.swing.JLabel zLabel;
    private javax.swing.JTextField zTextField;
    // End of variables declaration//GEN-END:variables
}
