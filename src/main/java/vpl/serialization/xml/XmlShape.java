/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vpl.serialization.xml;

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
import vpl.physics.AxisAngle;
import vpl.physics.shapes.BallShape;
import vpl.physics.shapes.ConeShape;
import vpl.physics.shapes.CuboidShape;
import vpl.physics.shapes.CyllinderShape;
import vpl.physics.shapes.Shape;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "shape")
public class XmlShape {
    
    public final static String BALL = "BALL";
    public final static String CONE = "CONE";
    public final static String CUBOID = "CUBOID";
    public final static String CYLLINDER = "CYLLINDER";
    
    public XmlShape(Shape shape, AxisAngle axisAngle) {
        if (shape instanceof BallShape) {
            BallShape ballShape = (BallShape) shape;
            type = BALL;
            radius = ballShape.getR();
            
        } else if (shape instanceof ConeShape) {
            ConeShape coneShape = (ConeShape) shape;
            type = CONE;
            radius = coneShape.getR();
            height = coneShape.getH();
            
        } else if (shape instanceof CuboidShape) {
            CuboidShape cuboidShape = (CuboidShape) shape;
            type = CUBOID;
            xSize = cuboidShape.getX();
            ySize = cuboidShape.getY();
            zSize = cuboidShape.getZ();
            
        } else if (shape instanceof CyllinderShape) {
            CyllinderShape cyllinderShape = (CyllinderShape) shape;
            type = CYLLINDER;
            radius = cyllinderShape.getR();
            height = cyllinderShape.getH();
            
        }
        
        rotation = new XmlTriple(axisAngle);
    }

    @XmlAttribute
    private String type;
    
    @XmlAttribute(required = false)
    private Double xSize;
    
    @XmlAttribute(required = false)
    private Double ySize;
    
    @XmlAttribute(required = false)
    private Double zSize;
    
    @XmlAttribute(required = false)
    private Double radius;
    
    @XmlAttribute(required = false)
    private Double height;

    @XmlElement(name = "rotation")
    private XmlTriple rotation = new XmlTriple();

    public Shape generateShape() {
        Shape shape = null;
        try {
            if (type.equals(BALL)) {
                BallShape ballShape = new BallShape();
                ballShape.setR(radius);
                shape = ballShape;

            } else if (type.equals(CONE)) {
                ConeShape coneShape = new ConeShape();
                coneShape.setR(radius);
                coneShape.setH(height);
                shape = coneShape;

            } else if (type.equals(CUBOID)) {
                CuboidShape cuboidShape = new CuboidShape();
                cuboidShape.setX(xSize);
                cuboidShape.setY(ySize);
                cuboidShape.setZ(zSize);
                shape = cuboidShape;

            } else if (type.equals(CYLLINDER)) {
                CyllinderShape cyllinderShape = new CyllinderShape();
                cyllinderShape.setR(radius);
                cyllinderShape.setH(height);
                shape = cyllinderShape;

            }
        } catch (Exception ex) {
            Logger.getLogger(XmlShape.class.getName()).log(Level.SEVERE, null, ex);
        }
        return shape;
    }

}
