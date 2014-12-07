/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vpl.serialization.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vpl.math.Triple;
import vpl.physics.AxisAngle;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "triple")
public class XmlTriple {

    public XmlTriple(Triple triple) {
        x = triple.getX();
        y = triple.getY();
        z = triple.getZ();
    }

    public XmlTriple(AxisAngle axisAngle) {
        this(axisAngle.getAngles());
    }

    @XmlAttribute
    private double x;
    
    @XmlAttribute
    private double y;
    
    @XmlAttribute
    private double z;
    
    public Triple generateTriple() {
        return new Triple(x, y, z);
    }
    
    public AxisAngle generateAxisAngle() {
        return new AxisAngle(0, generateTriple());
    }
}
