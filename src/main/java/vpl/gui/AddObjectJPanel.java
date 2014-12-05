/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import vpl.math.Triple;
import vpl.physics.controller.ControllerStub;
import vpl.physics.controller.Model;
import vpl.physics.shapes.BallShape;
import vpl.physics.shapes.ConeShape;
import vpl.physics.shapes.CuboidShape;
import vpl.physics.shapes.CyllinderShape;
import vpl.gui.shapes.Ball;
import vpl.gui.shapes.Cone;
import vpl.gui.shapes.Cube;
import vpl.gui.shapes.Cuboid;
import vpl.gui.shapes.Cyllinder;
import vpl.gui.shapes.Shape;

public class AddObjectJPanel extends javax.swing.JPanel {

    public List<Shape> shapesList;
    private Model model;
    private ControllerStub api;
    
    /**
     * Creates new form AddObjectJPanel
     */
    public AddObjectJPanel() {
        model = Model.getInstance();
        api = model.getPhysics();
        
        shapesList = new ArrayList<>();
        initComponents();

        DefaultListModel<String> listModel = new DefaultListModel<String>();
        listModel.addElement("ball");
        listModel.addElement("cube");
        listModel.addElement("cuboid");
        listModel.addElement("cyllinder");
        listModel.addElement("cone");
        this.elementsList.setModel(listModel);
        this.elementsList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent lse) {
                //tu będzie blokowanie odpowiednich pól
            }
        });
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
        cLabel = new javax.swing.JLabel();
        zTextField = new javax.swing.JTextField();
        yTextField = new javax.swing.JTextField();
        xTextField = new javax.swing.JTextField();
        xAngleLabel = new javax.swing.JLabel();
        rTextField = new javax.swing.JTextField();
        rLabel = new javax.swing.JLabel();
        zAngleLabel = new javax.swing.JLabel();
        aLabel = new javax.swing.JLabel();
        aTextField = new javax.swing.JTextField();
        yAngleLabel = new javax.swing.JLabel();
        bLabel = new javax.swing.JLabel();
        bTextField = new javax.swing.JTextField();
        cTextField = new javax.swing.JTextField();
        xAngleTextField = new javax.swing.JTextField();
        yAngleTextField = new javax.swing.JTextField();
        zAngleTextField = new javax.swing.JTextField();
        addShapeButton = new javax.swing.JButton();
        positionLabel = new javax.swing.JLabel();
        hLabel = new javax.swing.JLabel();
        hTextField = new javax.swing.JTextField();

        setMaximumSize(new java.awt.Dimension(257, 32767));

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

        cLabel.setText("c:");

        zTextField.setText("0.0");
        zTextField.setMaximumSize(new java.awt.Dimension(80, 28));
        zTextField.setMinimumSize(new java.awt.Dimension(80, 28));
        zTextField.setPreferredSize(new java.awt.Dimension(80, 28));

        yTextField.setText("0.0");
        yTextField.setMaximumSize(new java.awt.Dimension(80, 28));
        yTextField.setPreferredSize(new java.awt.Dimension(80, 28));

        xTextField.setText("0.0");
        xTextField.setMaximumSize(new java.awt.Dimension(80, 28));
        xTextField.setPreferredSize(new java.awt.Dimension(80, 28));

        xAngleLabel.setText("x angle:");

        rTextField.setText("0.0");
        rTextField.setPreferredSize(new java.awt.Dimension(80, 28));

        rLabel.setText("r:");

        zAngleLabel.setText("z angle:");

        aLabel.setText("a:");

        aTextField.setText("0.0");
        aTextField.setPreferredSize(new java.awt.Dimension(80, 28));

        yAngleLabel.setText("y angle:");

        bLabel.setText("b:");

        bTextField.setText("0.0");
        bTextField.setPreferredSize(new java.awt.Dimension(80, 28));

        cTextField.setText("0.0");
        cTextField.setPreferredSize(new java.awt.Dimension(80, 28));

        xAngleTextField.setText("0.0");
        xAngleTextField.setPreferredSize(new java.awt.Dimension(80, 28));

        yAngleTextField.setText("0.0");
        yAngleTextField.setPreferredSize(new java.awt.Dimension(80, 28));

        zAngleTextField.setText("0.0");
        zAngleTextField.setPreferredSize(new java.awt.Dimension(80, 28));

        addShapeButton.setText("Add");
        addShapeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addShapeButtonActionPerformed(evt);
            }
        });

        positionLabel.setText("position:");

        hLabel.setText("h:");

        hTextField.setText("0.0");
        hTextField.setPreferredSize(new java.awt.Dimension(80, 28));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(positionLabel)
                    .addComponent(addShapeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(propertiesLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bLabel)
                            .addComponent(cLabel)
                            .addComponent(xAngleLabel)
                            .addComponent(yAngleLabel)
                            .addComponent(yLabel)
                            .addComponent(zLabel)
                            .addComponent(rLabel)
                            .addComponent(hLabel)
                            .addComponent(aLabel)
                            .addComponent(xLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(zAngleLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(zAngleTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(aTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(hTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(yTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(xTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(zTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(xAngleTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(yAngleTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(propertiesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(xTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(xLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(yLabel)
                            .addComponent(yTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(zTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(zLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rLabel)
                            .addComponent(rTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hLabel)
                            .addComponent(hTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(aLabel)
                            .addComponent(aTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bLabel)
                            .addComponent(bTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cLabel)
                            .addComponent(cTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(xAngleLabel)
                            .addComponent(xAngleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yAngleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yAngleLabel)
                    .addComponent(addShapeButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(zAngleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(zAngleLabel)
                    .addComponent(positionLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
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
            try {
                BallShape ball = new BallShape();
                ball.setR(r);
                ball.setType("BALL");
                ball.calculateRadius();
                api.createRigidBody(ball, new Triple(x, y, z), 0, 10);
            } catch (Exception ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            shapesList.add(new Ball(x, y, z, r, xa, ya, za));
        } else if (selected.equals("cube")) {
            try {
                CuboidShape cube = new CuboidShape();
                cube.setX(a);
                cube.setY(a);
                cube.setZ(a);
                cube.calculateRadius();
                api.createRigidBody(cube, new Triple(x, y, z), 0, 10);
            } catch (Exception ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            shapesList.add(new Cube(x, y, z, xa, ya, za, a));
        } else if (selected.equals("cuboid")) {
            try {
                CuboidShape cuboid = new CuboidShape();
                cuboid.setX(a);
                cuboid.setY(b);
                cuboid.setZ(c);
                if (a == b && b == c && a == c) {
                    cuboid.setType("CUBE");
                }
                cuboid.calculateRadius();
                api.createRigidBody(cuboid, new Triple(x, y, z), 0, 10);
            } catch (Exception ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            shapesList.add(new Cuboid(x, y, z, xa, ya, za, a, b, c));
        } else if (selected.equals("cyllinder")) {
            try {
                CyllinderShape cyllinder = new CyllinderShape();
                cyllinder.setR(r);
                cyllinder.setH(h);
                cyllinder.calculateRadius();
                api.createRigidBody(cyllinder, new Triple(x, y, z), 0, 10);
            } catch (Exception ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            shapesList.add(new Cyllinder(x, y, z, xa, ya, za, r, h));
        } else if (selected.equals("cone")) {
            try {
                ConeShape cone = new ConeShape();
                cone.setR(r);
                cone.setH(h);
                cone.calculateRadius();
                api.createRigidBody(cone, new Triple(x, y, z), 0, 10);
            } catch (Exception ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }

            shapesList.add(new Cone(x, y, z, xa, ya, za, r, h));
        } else {
            System.out.println("ERROR. WRONG SELECTION");
        }
        model.refreshView("rigidBodyList");
    }//GEN-LAST:event_addShapeButtonActionPerformed

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
