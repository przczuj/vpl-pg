/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.gui;

import vpl.gui.viewmodel.selection.RigidBodySelectionItem;
import vpl.gui.viewmodel.selection.SelectionItem;
import vpl.math.Triple;
import vpl.physics.Force;
import vpl.physics.RigidBody;
import vpl.physics.controller.Model;
import vpl.physics.controller.SimpleListener;

public class AddForceJPanel extends javax.swing.JPanel implements SimpleListener {

    Model model;
    /**
     * Creates new form AddForceJPanel
     */
    public AddForceJPanel() {
        model = Model.getInstance();
        initComponents();
        model.register(this);
    }

    @Override
    public void valuesChanged(String message) {
        if (message.equals(Model.SELECTED_ITEM_CHANGED)) {
            if (model.getSelectedItem() != null) {
                objectAttachedTextField.setText(model.getSelectedItem().toString());
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        propertiesLabel = new javax.swing.JLabel();
        xLabel = new javax.swing.JLabel();
        yLabel = new javax.swing.JLabel();
        zLabel = new javax.swing.JLabel();
        cLabel = new javax.swing.JLabel();
        zTextField = new javax.swing.JTextField();
        yTextField = new javax.swing.JTextField();
        xTextField = new javax.swing.JTextField();
        ttlTextField = new javax.swing.JTextField();
        ttlLabel = new javax.swing.JLabel();
        aLabel = new javax.swing.JLabel();
        aTextField = new javax.swing.JTextField();
        bLabel = new javax.swing.JLabel();
        bTextField = new javax.swing.JTextField();
        cTextField = new javax.swing.JTextField();
        objectAttachedTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        foreverCheckBox = new javax.swing.JCheckBox();
        addForceButton = new javax.swing.JButton();
        uniformCheckBox = new javax.swing.JCheckBox();

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

        ttlTextField.setText("0.0");
        ttlTextField.setPreferredSize(new java.awt.Dimension(80, 28));

        ttlLabel.setText("ttl:");

        aLabel.setText("a:");

        aTextField.setText("0.0");
        aTextField.setPreferredSize(new java.awt.Dimension(80, 28));

        bLabel.setText("b:");

        bTextField.setText("0.0");
        bTextField.setPreferredSize(new java.awt.Dimension(80, 28));

        cTextField.setText("0.0");
        cTextField.setPreferredSize(new java.awt.Dimension(80, 28));

        objectAttachedTextField.setEditable(false);

        jLabel1.setText("force attached to:");

        foreverCheckBox.setText("forever");

        addForceButton.setText("Add");
        addForceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addForceButtonActionPerformed(evt);
            }
        });

        uniformCheckBox.setText("uniform");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bLabel)
                            .addComponent(cLabel)
                            .addComponent(yLabel)
                            .addComponent(zLabel)
                            .addComponent(ttlLabel)
                            .addComponent(aLabel)
                            .addComponent(xLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(aTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                            .addComponent(yTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(xTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(zTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ttlTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(foreverCheckBox))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(objectAttachedTextField))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(propertiesLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addForceButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(uniformCheckBox)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(objectAttachedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(2, 2, 2)
                .addComponent(propertiesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(xTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(ttlLabel)
                    .addComponent(ttlTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(foreverCheckBox))
                .addGap(26, 26, 26)
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
                    .addComponent(addForceButton)
                    .addComponent(uniformCheckBox))
                .addContainerGap(21, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addForceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addForceButtonActionPerformed
        double x = Double.parseDouble(xTextField.getText());
        double y = Double.parseDouble(yTextField.getText());
        double z = Double.parseDouble(zTextField.getText());
        double a = Double.parseDouble(aTextField.getText());
        double b = Double.parseDouble(bTextField.getText());
        double c = Double.parseDouble(cTextField.getText());
        boolean forever = foreverCheckBox.isSelected();
        double ttl = Double.parseDouble(ttlTextField.getText());
        
        if (uniformCheckBox.isSelected()) {
            model.getPhysics().createUniformForce(new Triple(a, b, c));
        } else if (model.getSelectedItem() instanceof RigidBodySelectionItem) {
            RigidBodySelectionItem selectedRigidBody = (RigidBodySelectionItem) model.getSelectedItem();
            RigidBody body = selectedRigidBody.getBody(); // model.getPhysics().getRigidBodies().get(selectedRigidBody.getName());
            model.getPhysics().createForce(new Triple(a, b, c), new Triple(x, y, z), body, ttl, forever);
        }
        model.refreshView(Model.RIGID_BODY_LIST_CHANGED);
    }//GEN-LAST:event_addForceButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aLabel;
    private javax.swing.JTextField aTextField;
    private javax.swing.JButton addForceButton;
    private javax.swing.JLabel bLabel;
    private javax.swing.JTextField bTextField;
    private javax.swing.JLabel cLabel;
    private javax.swing.JTextField cTextField;
    private javax.swing.JCheckBox foreverCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField objectAttachedTextField;
    private javax.swing.JLabel propertiesLabel;
    private javax.swing.JLabel ttlLabel;
    private javax.swing.JTextField ttlTextField;
    private javax.swing.JCheckBox uniformCheckBox;
    private javax.swing.JLabel xLabel;
    private javax.swing.JTextField xTextField;
    private javax.swing.JLabel yLabel;
    private javax.swing.JTextField yTextField;
    private javax.swing.JLabel zLabel;
    private javax.swing.JTextField zTextField;
    // End of variables declaration//GEN-END:variables

}
