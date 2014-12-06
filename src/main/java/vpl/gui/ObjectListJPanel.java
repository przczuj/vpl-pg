/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.gui;

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
import vpl.physics.Force;
import vpl.physics.RigidBody;
import vpl.physics.controller.Model;
import vpl.physics.controller.SimpleListener;

public class ObjectListJPanel extends javax.swing.JPanel implements SimpleListener {
    
    private Map<String, RigidBody> rigidBodies;
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
                String selectedItemName = getSelectedItemName();
                if (selectedItemName != null) {
                    model.setSelectedItemName(selectedItemName);
                    model.refreshView("selectedItemName");
                }
            }
        });
        
        model.register(this);
    }
    
    public String getSelectedItemName() {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree1.getLastSelectedPathComponent();
        if (node == null) {
            return null;
        }
        return (String) node.getUserObject();
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
        if (message.equals("rigidBodyList")) {
            refreshModel(model.getPhysics().getRigidBodies());
        }
    }
    
    public void refreshModel(Map<String, RigidBody> rigidBodies) {
        this.rigidBodies = rigidBodies;
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
            
            DefaultMutableTreeNode rigidBodyNode = new DefaultMutableTreeNode(rigidBodyName);
            int forceNum = 0;
            for (Force force : rigidBody.getActingForces()) {
                DefaultMutableTreeNode forceNode = new DefaultMutableTreeNode("Force " + forceNum);
                forceNum++;
                rigidBodyNode.add(forceNode);
            }
            
            root.add(rigidBodyNode);
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