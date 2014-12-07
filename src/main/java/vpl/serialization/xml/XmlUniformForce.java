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
@XmlType(name = "uniformForce")
public class XmlUniformForce implements Serializable {

    public XmlUniformForce(String name, Force force) {
        this.name = name;
        value = new XmlTriple(force.getForceValue());
        if (!force.isForever()) {
            timeToLive = force.getTimeToLive();
        } 
    }
    
    @XmlAttribute
    private String name;
    
    @XmlElement(name = "value")
    private XmlTriple value = new XmlTriple();
    
    @XmlAttribute(required = false)
    private Double timeToLive;

    public Force generateUniformForce() {
        Force force = new Force();
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
