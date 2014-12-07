/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vpl.serialization.xml;

import java.io.Serializable;
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
import vpl.physics.Force;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "force")
public class XmlForce implements Serializable {
    
    public XmlForce(Force force) {
        value = new XmlTriple(force.getForceValue());
        location = new XmlTriple(force.getForceLocation());
        if (!force.isForever()) {
            timeToLive = force.getTimeToLive();
        }
    }
    
    @XmlElement(name = "value")
    private XmlTriple value = new XmlTriple();
    
    @XmlElement(name = "location")
    private XmlTriple location = new XmlTriple();
    
    @XmlAttribute(required = false)
    private Double timeToLive;
    
    public Force generateForce() {
        Force force = new Force();
        force.setForceLocation(location.generateTriple());
        force.setForceValue(value.generateTriple());
        if (timeToLive == null) {
            force.setForever(true);
        } else {
            force.setForever(false);
            force.setTimeToLive(timeToLive);
        }
        return force;
    }
}
