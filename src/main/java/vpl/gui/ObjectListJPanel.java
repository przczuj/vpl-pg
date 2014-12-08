/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import lombok.Getter;
import vpl.gui.viewmodel.selection.ForceSelectionItem;
import vpl.gui.viewmodel.selection.RigidBodySelectionItem;
import vpl.gui.viewmodel.selection.SelectionItem;
import vpl.gui.viewmodel.selection.UniformForceSelectionItem;
import vpl.physics.Force;
import vpl.physics.RigidBody;
import vpl.physics.controller.Model;
import vpl.physics.controller.SimpleListener;

public class ObjectListJPanel extends javax.swing.JPanel implements SimpleListener {
    
    private Map<String, RigidBody> rigidBodies;
    private Map<String, Force> uniformForces;
    private DefaultTreeModel treeModel;
    private DefaultMutableTreeNode root;
    private Model model;

    /**
     * Creates new form ObjectListJPanel
     */
    public ObjectListJPanel() {
        model = Model.getInstance();
        root = new DefaultMutableTreeNode("");
        treeModel = new DefaultTreeModel(root);
        
        initComponents();
        jTree1.setRootVisible(false);
        jTree1.setModel(treeModel);
        jTree1.addTreeSelectionListener(new TreeSelectionListener() {

            @Override
            public void valueChanged(TreeSelectionEvent e) {
                model.setSelectedItem(getSelectedItem());
                model.refreshView(Model.SELECTED_ITEM_CHANGED);
            }
        });
        jTree1.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    System.out.println("DELETE");
                    SelectionItem selectedItem = model.getSelectedItem();
                    if (selectedItem instanceof RigidBodySelectionItem) {
                        RigidBodySelectionItem rigidBodySelectionItem = (RigidBodySelectionItem) selectedItem;
                        model.getPhysics().getRigidBodies().remove(rigidBodySelectionItem.getName());

                    } else if (selectedItem instanceof ForceSelectionItem) {
                        ForceSelectionItem forceSelectionItem = (ForceSelectionItem) selectedItem;
                        forceSelectionItem.getBody().getActingForces().remove(forceSelectionItem.getNum());

                    } else if (selectedItem instanceof UniformForceSelectionItem) {
                        UniformForceSelectionItem uniformForceSelectionItem = (UniformForceSelectionItem) selectedItem;
                        for (RigidBody rigidBody : model.getPhysics().getRigidBodies().values()) {
                            rigidBody.deregisterUniformForce(uniformForceSelectionItem.getForce());
                        }
                        model.getPhysics().getUniformForces().remove(uniformForceSelectionItem.getName());
                    }
                    model.refreshView(Model.RIGID_BODY_LIST_CHANGED);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        model.register(this);
    }
    
    public SelectionItem getSelectedItem() {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree1.getLastSelectedPathComponent();
        if (node != null) {
            return (SelectionItem) node.getUserObject();
        }
        return null;
    }
    
    public void setSelectedItemName(String name) {
//        Enumeration enumeration = root.children();
//        while(enumeration.hasMoreElements()) {
//            DefaultMutableTreeNode node = (DefaultMutableTreeNode) enumeration.nextElement();
//            if (((String)node.getUserObject()).equals(name)) {
//                jTree1.getSelectionModel().setSelectionPath();
//                int a = jTree1.
//                break;
//            }
//        }
//        
//        jTree1.setSelectionRow(WIDTH);
    }

    @Override
    public void valuesChanged(String message) {
        if (message.equals(Model.RIGID_BODY_LIST_CHANGED)) {
            refreshModel(model.getPhysics().getRigidBodies(), model.getPhysics().getUniformForces());
        }
    }
    
    public void refreshModel(Map<String, RigidBody> rigidBodies, Map<String, Force> uniformForces) {
        this.rigidBodies = rigidBodies;
        this.uniformForces = uniformForces;
        generateRigidBodyTree(root);
        treeModel.reload();
    }
    
    public void registerSelectionListener(TreeSelectionListener listener) {
        jTree1.addTreeSelectionListener(listener);
    }
    
    private void generateRigidBodyTree(DefaultMutableTreeNode root) {
        root.removeAllChildren();
        for (Entry<String, RigidBody> rigidBodyMapEntry : rigidBodies.entrySet()) {
            String rigidBodyName = rigidBodyMapEntry.getKey();
            RigidBody rigidBody = rigidBodyMapEntry.getValue();
            
            DefaultMutableTreeNode rigidBodyNode = new DefaultMutableTreeNode(new RigidBodySelectionItem(rigidBodyName, rigidBody));
            int forceNum = 0;
            for (Force force : rigidBody.getActingForces()) {
                DefaultMutableTreeNode forceNode = new DefaultMutableTreeNode(new ForceSelectionItem(forceNum, rigidBodyName, rigidBody));
                forceNum++;
                rigidBodyNode.add(forceNode);
            }
            
            root.add(rigidBodyNode);
        }
        
        for (Entry<String, Force> uniformForceMapEntry : uniformForces.entrySet()) {
            String uniformForceName = uniformForceMapEntry.getKey();
            Force uniformForce = uniformForceMapEntry.getValue();
            DefaultMutableTreeNode uniformForceNode = new DefaultMutableTreeNode(new UniformForceSelectionItem(uniformForceName, uniformForce));
            root.add(uniformForceNode);
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

        jScrollPane2 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();

        setMaximumSize(new java.awt.Dimension(150, 32767));
        setPreferredSize(new java.awt.Dimension(150, 405));

        jScrollPane2.setViewportView(jTree1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 149, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 321, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables
}
