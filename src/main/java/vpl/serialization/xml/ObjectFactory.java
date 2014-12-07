package vpl.serialization.xml;

import javax.xml.bind.annotation.XmlRegistry;

/**
 *
 * @author psysiu
 */
@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {
    }

    public XmlExperiment createExperiment() {
        return new XmlExperiment();
    }

    public XmlForce createForce() {
        return new XmlForce();
    }

    public XmlRigidBody createRigidBody() {
        return new XmlRigidBody();
    }

    public XmlShape createShape() {
        return new XmlShape();
    }

    public XmlTriple createTriple() {
        return new XmlTriple();
    }

    public XmlUniformForce createUniformForce() {
        return new XmlUniformForce();
    }
}
