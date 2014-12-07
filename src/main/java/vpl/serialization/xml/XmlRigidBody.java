/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vpl.serialization.xml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vpl.math.BasicMath;
import vpl.math.Matrix;
import vpl.physics.Force;
import vpl.physics.RigidBody;
import vpl.physics.controller.PhysicsExecutionTask;
import vpl.physics.shapes.Shape;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rigidBody")
public class XmlRigidBody implements Serializable {

    public XmlRigidBody(String name, RigidBody rigidBody) {
        this.name = name;
        mass = rigidBody.getMass();
        for (Force force : rigidBody.getActingForces()) {
            forces.add(new XmlForce(force));
        }
        shape = new XmlShape(rigidBody.getShape(), rigidBody.getRotationAngles());
        position = new XmlTriple(rigidBody.getPosition());
        linearMomentum = new XmlTriple(rigidBody.getLinearMomentum());
        angularMomentumy = new XmlTriple(rigidBody.getAngularMomentum());
    }
    
    @XmlAttribute
    private String name;

    @XmlAttribute
    private double mass;

    @XmlElement(name = "force")
    private List<XmlForce> forces = new ArrayList<>();
    
    @XmlElement(name = "shape")
    private XmlShape shape = new XmlShape();
    
    @XmlElement(name = "position")
    private XmlTriple position = new XmlTriple();
    
    @XmlElement(name = "linearMomentum")
    private XmlTriple linearMomentum = new XmlTriple();
    
    @XmlElement(name = "angularMomentum")
    private XmlTriple angularMomentumy = new XmlTriple();

    public RigidBody generateRigidBody() {
        try {
            RigidBody rigidBody = new RigidBody();
            for (XmlForce xmlForce : forces) {
                rigidBody.registerForce(xmlForce.generateForce());
            }
            Matrix rotationMatrix = new BasicMath().anglesToRotationMatrix(shape.getRotation().generateAxisAngle());
            rigidBody.setRotationMatrix(rotationMatrix);
            Shape physicShape = shape.generateShape();
            physicShape.setMass(mass);
            rigidBody.setShape(physicShape);
            rigidBody.setMass(mass);
            rigidBody.setPosition(position.generateTriple());
            rigidBody.setLinearMomentum(linearMomentum.generateTriple());
            rigidBody.setAngularMomentum(angularMomentumy.generateTriple());
            rigidBody.setTimeTick(PhysicsExecutionTask.TICK_RATE_MILLISEC);
            rigidBody.getShape().recalculate();
            return rigidBody;
        } catch (Exception ex) {
            Logger.getLogger(XmlRigidBody.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
