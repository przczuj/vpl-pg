
import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.FPSAnimator;
import java.awt.BorderLayout;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GLAnimatorControl;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import joglTest.JOGLtest;
import lombok.Getter;
import lombok.Setter;
import sun.security.util.SecurityConstants;
import vpl.gui.GLDrawingHandler;
import vpl.gui.GUI;
import vpl.gui.GuiControlHandler;
import vpl.math.Triple;
import vpl.model.VplScene;
import vpl.physics.controller.ControllerStub;
import vpl.physics.shapes.BallShape;
import vpl.physics.shapes.ConeShape;
import vpl.physics.shapes.CuboidShape;
import vpl.physics.shapes.CyllinderShape;
import vpl.shapes.Ball;
import vpl.shapes.Cone;
import vpl.shapes.Cube;
import vpl.shapes.Cuboid;
import vpl.shapes.Cyllinder;
import vpl.shapes.Shape;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public class VplMainApplet extends javax.swing.JApplet {

    private final static int TICK_RATE_MILLISEC = 50;
    private GLAnimatorControl glAnimatorControl;

    double xl, yl, zl;
    double xang = 0, yang = 0;
    double x, y, z;
    int mx, my;
    public List<Shape> shapesList = new ArrayList<Shape>();
    @Getter @Setter private VplScene sceneModel;
    @Getter @Setter private ControllerStub api;
    
    /**
     * Initializes the applet VplMainAppletOld
     */
    @Override
    public void init() {
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
            java.util.logging.Logger.getLogger(VplMainAppletOld.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VplMainAppletOld.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VplMainAppletOld.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VplMainAppletOld.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the applet */
        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    sceneModel = new VplScene();
                    glCanvas = new GLCanvas();
                    
                    initComponents();
                    
                    api = new ControllerStub();
                    mainJPanel.add(glCanvas);
                    glCanvas.setSize(mainJPanel.getSize());

                    DefaultListModel<String> model = new DefaultListModel<String>();
                    model.addElement("ball");
                    model.addElement("cube");
                    model.addElement("cuboid");
                    model.addElement("cyllinder");
                    model.addElement("cone");
                    elementsList.setModel(model);
                    elementsList.addListSelectionListener(new ListSelectionListener() {
                        public void valueChanged(ListSelectionEvent lse) {
                            //tu będzie blokowanie odpowiednich pól
                        }
                    });
                    
                    prepareJOGL();
                    
                    Container parent = getParent();
                    if (parent != null) {
                        setSize(parent.getSize());
                    }
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void prepareJOGL() {
        GuiControlHandler controlHandler = new GuiControlHandler(sceneModel);
        this.glCanvas.addKeyListener(controlHandler);
        this.glCanvas.addMouseListener(controlHandler);
        this.glCanvas.addMouseMotionListener(controlHandler);
        this.glCanvas.addGLEventListener(new GLDrawingHandler(sceneModel, api));
        final Animator animator = new Animator(this.glCanvas);
        animator.start();
    }
    
    public void initJogl(JPanel panel) {
        GLProfile.initSingleton();

        final GLCanvas glcanvas = new GLCanvas();

        JOGLtest context = new JOGLtest();
        glcanvas.addGLEventListener(context);
        glcanvas.addKeyListener(context);
        glcanvas.addMouseMotionListener(context);
        glcanvas.addMouseListener(context);

        glcanvas.setSize(panel.getSize());
        panel.add(glcanvas, BorderLayout.CENTER);
        glAnimatorControl = new FPSAnimator(glcanvas, 30);
    }
    
    @Override
    public void start() {
        if (glAnimatorControl != null) {
            glAnimatorControl.start();
        }
    }

    @Override
    public void stop() {
        if (glAnimatorControl != null) {
            glAnimatorControl.stop();
        }
    }
    
    private boolean hasAllPermission() {
        try {
            SecurityManager securityManager = System.getSecurityManager();
            if (securityManager != null) {
                securityManager.checkPermission(SecurityConstants.ALL_PERMISSION);
            }
            return true;
        } catch (SecurityException se) {
            return false;
        }
    }
    
    private void startTask() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // actions to be done every {TICK_RATE_MILLISEC} ms
            }
        }, /*first execution delay*/ 0, TICK_RATE_MILLISEC, TimeUnit.MILLISECONDS);
    }

    /**
     * This method is called from within the init() method to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolBarJPanel = new javax.swing.JPanel();
        ExperimentExecutionJToolBar = new javax.swing.JToolBar();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        SaveLoadJToolBar = new javax.swing.JToolBar();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
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
        hLabel = new javax.swing.JLabel();
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
        hTextField = new javax.swing.JTextField();
        xAngleTextField = new javax.swing.JTextField();
        yAngleTextField = new javax.swing.JTextField();
        zAngleTextField = new javax.swing.JTextField();
        addShapeButton = new javax.swing.JButton();
        positionLabel = new javax.swing.JLabel();
        mainJPanel = new javax.swing.JPanel();

        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));

        toolBarJPanel.setLayout(new java.awt.BorderLayout());

        ExperimentExecutionJToolBar.setRollover(true);

        jButton4.setText("Run");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ExperimentExecutionJToolBar.add(jButton4);

        jButton5.setText("Stop");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ExperimentExecutionJToolBar.add(jButton5);

        toolBarJPanel.add(ExperimentExecutionJToolBar, java.awt.BorderLayout.PAGE_START);

        SaveLoadJToolBar.setRollover(true);

        jButton6.setText("Save");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        SaveLoadJToolBar.add(jButton6);

        jButton7.setText("Load");
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        SaveLoadJToolBar.add(jButton7);

        toolBarJPanel.add(SaveLoadJToolBar, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(toolBarJPanel, java.awt.BorderLayout.PAGE_START);

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

        hLabel.setText("h:");

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(positionLabel)
                    .addComponent(addShapeButton, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(zAngleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(xAngleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(propertiesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(xLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(yLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(zLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(aLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(yAngleLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(xTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                        .addComponent(yTextField)
                        .addComponent(zTextField)
                        .addComponent(rTextField)
                        .addComponent(aTextField)
                        .addComponent(bTextField)
                        .addComponent(cTextField)
                        .addComponent(hTextField)
                        .addComponent(xAngleTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(yAngleTextField))
                    .addComponent(zAngleTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addShapeButton))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(propertiesLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(xLabel)
                            .addComponent(xTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(yLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(yTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(zLabel)
                            .addComponent(zTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rLabel)
                            .addComponent(rTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(aLabel)
                            .addComponent(aTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bLabel)
                            .addComponent(bTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cLabel)
                    .addComponent(cTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hLabel)
                    .addComponent(hTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(xAngleLabel)
                    .addComponent(xAngleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(positionLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yAngleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yAngleLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(zAngleLabel)
                    .addComponent(zAngleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(235, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.WEST);

        mainJPanel.setLayout(new java.awt.BorderLayout());
        getContentPane().add(mainJPanel, java.awt.BorderLayout.CENTER);
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
                BallShape ball = new BallShape();
                ball.setR(r);
                ball.setType("BALL");
                api.createRigidBody(ball, new Triple(x, y, z), 0, 10);
            } catch (Exception ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (selected.equals("cube")) {
            try {
                CuboidShape cube = new CuboidShape();
                cube.setX(a);
                cube.setY(a);
                cube.setZ(a);
                api.createRigidBody(cube, new Triple(x, y, z), 0, 10);
            } catch (Exception ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            shapesList.add(new Cube(x, y, z, xa, ya, za, a));
        } else if (selected.equals("cuboid")) {
            try {
                CuboidShape cuboid = new CuboidShape();
                cuboid.setX(a);
                cuboid.setY(b);
                cuboid.setZ(c);
                api.createRigidBody(cuboid, new Triple(x, y, z), 0, 10);

            } catch (Exception ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            shapesList.add(new Cuboid(x, y, z, xa, ya, za, a, b, c));
        } else if (selected.equals("cyllinder")) {
            try {
                CyllinderShape cyllinder = new CyllinderShape();
                cyllinder.setR(r);
                cyllinder.setH(h);
                api.createRigidBody(cyllinder, new Triple(x, y, z), 0, 10);
            } catch (Exception ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            shapesList.add(new Cyllinder(x, y, z, xa, ya, za, r, h));
        } else if (selected.equals("cone")) {
            try {
                ConeShape cone = new ConeShape();
                cone.setR(r);
                cone.setH(h);
                api.createRigidBody(cone, new Triple(x, y, z), 0, 10);
            } catch (Exception ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            shapesList.add(new Cone(x, y, z, xa, ya, za, r, h));
        } else {
            System.out.println("ERROR. WRONG SELECTION");
        }
    }//GEN-LAST:event_addShapeButtonActionPerformed

    private GLCanvas glCanvas;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar ExperimentExecutionJToolBar;
    private javax.swing.JToolBar SaveLoadJToolBar;
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
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainJPanel;
    private javax.swing.JLabel positionLabel;
    private javax.swing.JLabel propertiesLabel;
    private javax.swing.JLabel rLabel;
    private javax.swing.JTextField rTextField;
    private javax.swing.JPanel toolBarJPanel;
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
