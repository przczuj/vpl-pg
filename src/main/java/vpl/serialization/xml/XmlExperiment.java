/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vpl.serialization.xml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vpl.physics.Force;
import vpl.physics.RigidBody;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "experiment")
@XmlRootElement(name = "experiment")
public class XmlExperiment implements Serializable {
    
    public XmlExperiment(Map<String, RigidBody> rigidBodyMap, Map<String, Force> uniformForceMap) {
        for (Entry<String, RigidBody> rigidBodyEntry : rigidBodyMap.entrySet()) {
            String name = rigidBodyEntry.getKey();
            RigidBody rigidBody = rigidBodyEntry.getValue();
            rigidBodies.add(new XmlRigidBody(name, rigidBody));
        }
        for (Entry<String, Force> uniformForceEntry : uniformForceMap.entrySet()) {
            String name = uniformForceEntry.getKey();
            Force uniformForce = uniformForceEntry.getValue();
            unifiedForces.add(new XmlUniformForce(name, uniformForce));
        }
    }

    @XmlElement(name = "rigidBody")
    private List<XmlRigidBody> rigidBodies = new ArrayList<>();
    
    @XmlElement(name = "unifiedForce")
    private List<XmlUniformForce> unifiedForces = new ArrayList<>();
    
    public Map<String, RigidBody> exportRigidBodyMap() {
        Map<String, RigidBody> rigidBodyMap = new HashMap<>();
        for (XmlRigidBody xmlRigidBody : rigidBodies) {
            RigidBody rigidBody = xmlRigidBody.generateRigidBody();
            rigidBodyMap.put(xmlRigidBody.getName(), rigidBody);
            for (XmlUniformForce xmlUniformForce : unifiedForces) {
                rigidBody.registerUniformForce(xmlUniformForce.generateUniformForce());
            }
        }
        return rigidBodyMap;
    }
    
    public Map<String, Force> exportUniformForceMap() {
        Map<String, Force> uniformForceMap = new HashMap<>();
        for (XmlUniformForce xmlUniformForce : unifiedForces) {
            uniformForceMap.put(xmlUniformForce.getName(), xmlUniformForce.generateUniformForce());
        }
        return uniformForceMap;
    }
}
