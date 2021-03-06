/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.physics.shapes;

import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author kppx
 */
public class ConeShape extends Shape {

    @Getter
    protected double r, h;

    public ConeShape() throws Exception {
        init();
        type = new String("CONE");

    }

    public void setR(double r) {
        this.r = r;
        calculateRadius();
    }

    public void setH(double h) {
        this.h = h;
        calculateRadius();
    }

    @Override
    public void calculateRadius() {
        double radius1, radius2;
        radius1 = h * 3 / 4.0;
        radius2 = h / 16.0 * h + r * r;
        if (radius1 > radius2) {
            sphereRadius = radius1;
        } else {
            sphereRadius = radius2;
        }
    }

    @Override
    public void recalculate() {
        //for a x*y*z block
        IBody.setValueAt(0, 0, (12 * h * h + 3 * r * r) * mass / 20);
        IBody.setValueAt(1, 1, (12 * h * h + 3 * r * r) * mass / 20);
        IBody.setValueAt(2, 2, (3 * r * r) * mass / 10);
        try {
            InvertedIBody = IBody.inverse(IBody);
        } catch (Exception ex) {
            Logger.getLogger(Shape.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
