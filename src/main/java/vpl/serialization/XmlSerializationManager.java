/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vpl.serialization;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import vpl.physics.controller.Model;
import vpl.serialization.xml.ObjectFactory;
import vpl.serialization.xml.XmlExperiment;
import vpl.serialization.xml.XmlRigidBody;
import vpl.serialization.xml.XmlUniformForce;

public class XmlSerializationManager {
    
    Model model;

    public XmlSerializationManager() {
        model = Model.getInstance();
    }
    
    public static XmlExperiment unmarshal(InputStream in) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class.getPackage().getName());
            Unmarshaller u = jaxbContext.createUnmarshaller();
            return (XmlExperiment) u.unmarshal(in);
        } catch (JAXBException ex) {
            Logger.getLogger(XmlSerializationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void marshal(OutputStream out, XmlExperiment experiment) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class.getPackage().getName());
            Marshaller m = jaxbContext.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(experiment, out);
        } catch (JAXBException ex) {
            Logger.getLogger(XmlSerializationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
