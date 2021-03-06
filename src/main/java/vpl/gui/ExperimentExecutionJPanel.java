/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.gui;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.AccessControlException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import vpl.physics.controller.Model;
import vpl.physics.controller.PhysicsExecutionTask;
import vpl.physics.controller.SimpleListener;
import vpl.serialization.XmlSerializationManager;
import vpl.serialization.xml.XmlExperiment;

public class ExperimentExecutionJPanel extends javax.swing.JPanel implements SimpleListener {

    private Model model;
    
    private SpinnerNumberModel restitutionSpinnerModel;
    
    /**
     * Creates new form ExperimentExecutionJPanel
     */
    public ExperimentExecutionJPanel() {
        model = Model.getInstance();
        initComponents();
        model.register(this);
        refreshPosition();
        restitutionSpinnerModel = new SpinnerNumberModel(0.0, 0.0, 1.0, 0.01);
        restitutionSpinnerModel.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                double restitution = (double) restitutionSpinnerModel.getValue();
                restitutionSlider.setValue((int)(restitutionSlider.getMaximum() * restitution));
                model.getPhysics().setCoefficientOfRestitution(restitution);
            }
        });
        restitutionSpinner.setModel(restitutionSpinnerModel);
    }
    
    @Override
    public void valuesChanged(String message) {
        if(message.equals(Model.CAMERA_POSITION_CHANGED)) {
            refreshPosition();
        }
    }
    
    private void refreshPosition() {
        cameraXTextField.setText(String.format("%4.2f", model.getCameraPosition().getX()));
        cameraYTextField.setText(String.format("%4.2f", model.getCameraPosition().getY()));
        cameraZTextField.setText(String.format("%4.2f", model.getCameraPosition().getZ()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        saveButton = new javax.swing.JButton();
        loadButton = new javax.swing.JButton();
        cameraJPanel = new javax.swing.JPanel();
        xLabel = new javax.swing.JLabel();
        yLabel = new javax.swing.JLabel();
        zLabel = new javax.swing.JLabel();
        cameraXTextField = new javax.swing.JTextField();
        cameraYTextField = new javax.swing.JTextField();
        cameraZTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        restitutionSpinner = new javax.swing.JSpinner();
        restitutionSlider = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(32767, 46));

        jToggleButton1.setText("Play");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        loadButton.setText("Load");
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });

        xLabel.setText("x:");

        yLabel.setText("y:");

        zLabel.setText("z:");

        cameraXTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cameraXTextFieldActionPerformed(evt);
            }
        });

        cameraYTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cameraYTextFieldActionPerformed(evt);
            }
        });

        cameraZTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cameraZTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cameraJPanelLayout = new javax.swing.GroupLayout(cameraJPanel);
        cameraJPanel.setLayout(cameraJPanelLayout);
        cameraJPanelLayout.setHorizontalGroup(
            cameraJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cameraJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(xLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cameraXTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(yLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cameraYTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(zLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cameraZTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cameraJPanelLayout.setVerticalGroup(
            cameraJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cameraJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cameraJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(xLabel)
                    .addComponent(cameraXTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yLabel)
                    .addComponent(cameraYTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(zLabel)
                    .addComponent(cameraZTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("Restitution:");

        restitutionSlider.setMaximum(1000);
        restitutionSlider.setValue(0);
        restitutionSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                restitutionSliderStateChanged(evt);
            }
        });

        jLabel2.setText("Camera position:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cameraJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(restitutionSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(restitutionSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(saveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(loadButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(restitutionSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(restitutionSpinner)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cameraJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel2))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(loadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jToggleButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        if (jToggleButton1.isSelected()) {
            playAction();
        } else {
            stopAction();
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void playAction() {
        jToggleButton1.setText("Pause");
        PhysicsExecutionTask.getInstance().start();
        System.out.println("Playing");
    }
    
    private void stopAction() {
        jToggleButton1.setText("Play");
        PhysicsExecutionTask.getInstance().stop();
        System.out.println("Paused");
    }
    
    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
        try {
            final FileDialog loadDialog = new FileDialog(new Frame(), "load", FileDialog.LOAD);
            loadDialog.setVisible(true);
            if (loadDialog.getFile() != null) {
                stopAction();
                File file = new File(loadDialog.getDirectory() + loadDialog.getFile());
                InputStream inputStream;
                try {
                    inputStream = new FileInputStream(file);
                    XmlExperiment xmlExperiment = XmlSerializationManager.unmarshal(inputStream);
                    model.getPhysics().setRigidBodies(xmlExperiment.exportRigidBodyMap());
                    model.getPhysics().setUniformForces(xmlExperiment.exportUniformForceMap());
                    inputStream.close();
                    model.refreshView(Model.RIGID_BODY_LIST_CHANGED);
                    model.refreshView(Model.AN_RIGID_BODY_CHANGED);
                    System.out.println("LOADED");
                } catch (AccessControlException e) {
                    JOptionPane.showMessageDialog(null, "Sorry, we don't have permissions to you file system.\n"
                            + "We can't load experiment xml file.", "Permission denied", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Can't parse experiment xml file. Check if it is valid.", "Parsing error", JOptionPane.INFORMATION_MESSAGE);
            Logger.getLogger(ExperimentExecutionJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_loadButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        try {
            final FileDialog saveDialog = new FileDialog(new Frame(), "save", FileDialog.SAVE);
            saveDialog.setVisible(true);
            if (saveDialog.getFile() != null) {
                System.out.println(saveDialog.getDirectory() + saveDialog.getFile());
                File file = new File(saveDialog.getDirectory() + saveDialog.getFile());
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    XmlSerializationManager.marshal(fileOutputStream,
                            new XmlExperiment(model.getPhysics().getRigidBodies(), model.getPhysics().getUniformForces()));
                    fileOutputStream.close();
                    System.out.println("SAVED");
                } catch (AccessControlException e) {
                    JOptionPane.showMessageDialog(null, "Sorry, we don't have permissions to you file system.\n"
                            + "We can't save experiment xml file.", "Permission denied", JOptionPane.ERROR_MESSAGE);
                    
//                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//                    XmlSerializationManager.marshal(outputStream,
//                            new XmlExperiment(model.getPhysics().getRigidBodies(), model.getPhysics().getUniformForces()));
//                    JOptionPane.showInputDialog(null,
//                            "Sorry, we don't have permissions to you file system.\n"
//                            + "We can't save experiment xml file."
//                            + "You can copy content of experiment file from text field below",
//                            "Permission denied",
//                            JOptionPane.PLAIN_MESSAGE, null, null, new String(outputStream.toByteArray(), StandardCharsets.UTF_8));
//                    outputStream.close();
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Couldn't serialize experiment to xml file.", "Parsing error", JOptionPane.INFORMATION_MESSAGE);
            Logger.getLogger(ExperimentExecutionJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void cameraXTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cameraXTextFieldActionPerformed
        try {
            model.getCameraPosition().setX(ParsingHelper.parseDouble(cameraXTextField.getText().replaceAll(",", "."), "Camera position X"));
        } catch (Exception e) {}
        model.refreshView(Model.CAMERA_POSITION_CHANGED);
    }//GEN-LAST:event_cameraXTextFieldActionPerformed

    private void cameraYTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cameraYTextFieldActionPerformed
        try {
            model.getCameraPosition().setY(ParsingHelper.parseDouble(cameraYTextField.getText().replaceAll(",", "."), "Camera position Y"));
        } catch(Exception e) {}
        model.refreshView(Model.CAMERA_POSITION_CHANGED);
    }//GEN-LAST:event_cameraYTextFieldActionPerformed

    private void cameraZTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cameraZTextFieldActionPerformed
        try {
            model.getCameraPosition().setZ(ParsingHelper.parseDouble(cameraZTextField.getText().replaceAll(",", "."), "Camera position Z"));
        } catch (Exception e) {}
        model.refreshView(Model.CAMERA_POSITION_CHANGED);
    }//GEN-LAST:event_cameraZTextFieldActionPerformed

    private void restitutionSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_restitutionSliderStateChanged
        double restitution = (double)restitutionSlider.getValue() / restitutionSlider.getMaximum();
        restitutionSpinnerModel.setValue(restitution);
        model.getPhysics().setCoefficientOfRestitution(restitution);
    }//GEN-LAST:event_restitutionSliderStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cameraJPanel;
    private javax.swing.JTextField cameraXTextField;
    private javax.swing.JTextField cameraYTextField;
    private javax.swing.JTextField cameraZTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JButton loadButton;
    private javax.swing.JSlider restitutionSlider;
    private javax.swing.JSpinner restitutionSpinner;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel xLabel;
    private javax.swing.JLabel yLabel;
    private javax.swing.JLabel zLabel;
    // End of variables declaration//GEN-END:variables
}
