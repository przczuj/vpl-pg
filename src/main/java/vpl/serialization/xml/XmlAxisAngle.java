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
@XmlType(name = "axisAngle")
public class XmlAxisAngle {

    public XmlAxisAngle(double a, Triple triple) {
        x = triple.getX();
        y = triple.getY();
        z = triple.getZ();
    }

    public XmlAxisAngle(AxisAngle axisAngle) {
        this(axisAngle.getAngle(), axisAngle.getAngles());
    }

    @XmlAttribute
    private double x;
    
    @XmlAttribute
    private double y;
    
    @XmlAttribute
    private double z;
    
    @XmlAttribute
    private double a;
    
    public AxisAngle generateAxisAngle() {
        return new AxisAngle(a, new Triple(x, y, z));
    }
}
