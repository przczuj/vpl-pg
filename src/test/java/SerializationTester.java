
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import vpl.serialization.XmlSerializationManager;
import vpl.serialization.xml.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public class SerializationTester {
    
    public static void main(String[] args) {
        XmlExperiment experiment = new XmlExperiment();
        
        XmlRigidBody rigidBody1 = new XmlRigidBody();
        experiment.getRigidBodies().add(rigidBody1);
        
        XmlRigidBody rigidBody2 = new XmlRigidBody();
        experiment.getRigidBodies().add(rigidBody2);
        
        XmlRigidBody rigidBody3 = new XmlRigidBody();
        experiment.getRigidBodies().add(rigidBody3);
        
        XmlUniformForce uniformForce = new XmlUniformForce();
        experiment.getUnifiedForces().add(uniformForce);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(System.getProperty("user.home") + "/Desktop/experiment1.xml");
            XmlSerializationManager.marshal(fileOutputStream, experiment);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SerializationTester.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
