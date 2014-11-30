/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.sun.opengl.util.Animator;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author krzysztof
 */
public class GUI extends javax.swing.JFrame {
    /**
     * Creates new form GUI
     */
    double xl,yl,zl;
    float xang=0,yang=0;
    float x,y,z;
    double py=0;
    int mx,my;
    public static List<Shape> shapesList=new ArrayList<Shape>();
    
    
    public GUI() {
        initComponents();
        DefaultListModel<String> model=new DefaultListModel<String>();
        model.addElement("ball");
        model.addElement("cube");
        model.addElement("cuboid");
        this.elementsList.setModel(model);
        this.elementsList.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent lse) {
                //tu będzie blokowanie odpowiednich pól
            }
        } );       
    }
    
    public void prepareJOGL(){
        this.gLJPanel1.addGLEventListener(new GLEventListener() {

            public void init(GLAutoDrawable drawable) {
                GL gl=drawable.getGL();
                gl.setSwapInterval(1);
                gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
                gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.                
                drawable.addMouseListener(new MouseListener() {

                    public void mouseClicked(MouseEvent me) {                        
                    }

                    public void mousePressed(MouseEvent me) {
                        System.out.println("Pressed");
                        mx=me.getX();
                        my=me.getY();
                    }

                    public void mouseReleased(MouseEvent me) {
                    }

                    public void mouseEntered(MouseEvent me) {
                    }

                    public void mouseExited(MouseEvent me) {
                    }
                });
                drawable.addMouseMotionListener(new MouseMotionListener() {

                    public void mouseDragged(MouseEvent me) {
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

                    public void mouseMoved(MouseEvent me) {}
                });                
            }

            public void display(GLAutoDrawable drawable) {
                GL gl = drawable.getGL();
        GLU glu=new GLU();
        
        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();
        gl.glPushMatrix();
        
            
            
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
            
            
        
        for(Shape s : shapesList){
            s.draw(gl);            
        }    
            
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();

        // Perspective.
        float widthHeightRatio = (float) 640 / (float) 480;
        glu.gluPerspective(45, widthHeightRatio, 1, 1000);        
        
        xl=Math.sin(xang);
        xl*=Math.cos(yang);
        zl=Math.cos(xang);
        zl*=Math.cos(yang);
        yl=y+Math.sin(yang);
        
        xl=x+xl;
        zl=z-zl;
        
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

            public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
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

            public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
                //tu będzie  jakiś komentarz, póki co DUPA!!!!!!!!!!!!!!!!!(by kłuło w oczy, i bym szybko zmienił)
            }
        });
        final Animator animator=new Animator(this.gLJPanel1);
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
                                    .addComponent(yAngleTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
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
                                    .addComponent(xAngleLabel))
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(xAngleTextField)
                                    .addComponent(cTextField)
                                    .addComponent(bTextField)
                                    .addComponent(aTextField)
                                    .addComponent(zTextField)
                                    .addComponent(rTextField)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(positionLabel)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(678, 678, 678))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
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
                .addContainerGap(110, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addShapeButton)
                .addGap(85, 85, 85)
                .addComponent(positionLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addShapeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addShapeButtonActionPerformed
        String selected= this.elementsList.getSelectedValue().toString();
        if(selected.equals("ball")){
            float x=Float.parseFloat(xTextField.getText());
            float y=Float.parseFloat(yTextField.getText());
            float z=Float.parseFloat(zTextField.getText());
            float r=Float.parseFloat(rTextField.getText());
            float xa=Float.parseFloat(xAngleTextField.getText());
            float ya=Float.parseFloat(yAngleTextField.getText());
            float za=Float.parseFloat(zAngleTextField.getText());
            shapesList.add(new Ball(x,y,z,r,xa,ya,za));
        }
        else if(selected.equals("cube")){
            float x=Float.parseFloat(xTextField.getText());
            float y=Float.parseFloat(yTextField.getText());
            float z=Float.parseFloat(zTextField.getText());
            float xa=Float.parseFloat(xAngleTextField.getText());
            float ya=Float.parseFloat(yAngleTextField.getText());
            float za=Float.parseFloat(zAngleTextField.getText());
            float a=Float.parseFloat(aTextField.getText());
            shapesList.add(new Cube(x,y,z,xa,ya,za,a));
        }
        else if(selected.equals("cuboid")){
            float x=Float.parseFloat(xTextField.getText());
            float y=Float.parseFloat(yTextField.getText());
            float z=Float.parseFloat(zTextField.getText());
            float xa=Float.parseFloat(xAngleTextField.getText());
            float ya=Float.parseFloat(yAngleTextField.getText());
            float za=Float.parseFloat(zAngleTextField.getText());
            float a=Float.parseFloat(aTextField.getText());
            float b=Float.parseFloat(bTextField.getText());
            float c=Float.parseFloat(cTextField.getText());
            shapesList.add(new Cuboid(x,y,z,xa,ya,za,a,b,c));
        }
        else{
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
                GUI gui=new GUI();
                gui.setVisible(true);  
                gui.prepareJOGL();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aLabel;
    private javax.swing.JTextField aTextField;
    private javax.swing.JButton addShapeButton;
    private javax.swing.JLabel bLabel;
    private javax.swing.JTextField bTextField;
    private javax.swing.JLabel cLabel;
    private javax.swing.JTextField cTextField;
    private javax.swing.JList elementsList;
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
