/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.gui;

import javax.swing.table.TableModel;
import vpl.gui.viewmodel.properties.PropertiesTableModel;
import vpl.gui.viewmodel.selection.ForceSelectionItem;
import vpl.gui.viewmodel.selection.RigidBodySelectionItem;
import vpl.gui.viewmodel.selection.UniformForceSelectionItem;
import vpl.physics.Force;
import vpl.physics.RigidBody;
import vpl.physics.controller.Model;
import vpl.physics.controller.SimpleListener;

public class PropertiesJPanel extends javax.swing.JPanel implements SimpleListener {
    
    private PropertiesTableModel tableModel;
    private Model model;

    /**
     * Creates new form PropertiesJPanel
     */
    public PropertiesJPanel() {
        model = Model.getInstance();
        tableModel = new PropertiesTableModel();
        initComponents();
        
        jTable1.setModel(tableModel);
        
        model.register(this);
        valuesChanged(Model.SELECTED_ITEM_CHANGED);
    }
    
    @Override
    public void valuesChanged(String message) {
        if (message.equals(Model.SELECTED_ITEM_CHANGED)) {
            if (model.getSelectedItem() == null) {
                PropertyObjectNameLabel.setText("Select item from panel on the right");
                
            } else if (model.getSelectedItem() instanceof RigidBodySelectionItem) {
                RigidBodySelectionItem selectionItem = (RigidBodySelectionItem) model.getSelectedItem();
                RigidBody rigidBody = selectionItem.getBody(); // model.getPhysics().getRigidBodies().get(selectionItem.getName());
                tableModel.setModelObject(rigidBody);
                PropertyObjectNameLabel.setText(selectionItem.getName());
                
            } else if (model.getSelectedItem() instanceof ForceSelectionItem) {
                ForceSelectionItem selectionItem = (ForceSelectionItem) model.getSelectedItem();
                Force force = selectionItem.getForce(); // model.getPhysics().getUniformForces().get(selectionItem.getName());
                RigidBody rigidBody = selectionItem.getBody();
                tableModel.setModelObject(force, rigidBody);
                PropertyObjectNameLabel.setText(selectionItem.toString() + " of " + selectionItem.getBodyName());
                
            } else if (model.getSelectedItem() instanceof UniformForceSelectionItem) {
                UniformForceSelectionItem selectionItem = (UniformForceSelectionItem) model.getSelectedItem();
                Force force = selectionItem.getForce(); // model.getPhysics().getUniformForces().get(selectionItem.getName());
                tableModel.setModelObject(force);
                PropertyObjectNameLabel.setText(selectionItem.getName());
                
            }
            tableModel.fireTableDataChanged();
        } else if (message.equals(Model.AN_RIGID_BODY_CHANGED)) {
            tableModel.fireTableDataChanged();
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

        jPanel1 = new javax.swing.JPanel();
        PropertyObjectNameLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 200, Short.MAX_VALUE)
                    .addComponent(PropertyObjectNameLabel)
                    .addGap(0, 200, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 14, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 7, Short.MAX_VALUE)
                    .addComponent(PropertyObjectNameLabel)
                    .addGap(0, 7, Short.MAX_VALUE)))
        );

        add(jPanel1, java.awt.BorderLayout.NORTH);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel PropertyObjectNameLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
