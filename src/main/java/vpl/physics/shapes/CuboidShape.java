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
public class CuboidShape extends Shape {

    @Getter
    @Setter
    protected double x, y, z;

    public CuboidShape() throws Exception {
        init();
        type = ("CUBOID");
    }

    @Override
    public void calculateRadius() {
        sphereRadius = Math.sqrt(x * x + y * y + z * z) / 2;
    }

    @Override
    public void recalculate() {
        //for a x*y*z block
        IBody.setValueAt(0, 0, (y * y + z * z) * mass / 12);
        IBody.setValueAt(1, 1, (x * x + z * z) * mass / 12);
        IBody.setValueAt(2, 2, (x * x + y * y) * mass / 12);
        try {
            InvertedIBody = IBody.inverse(IBody);
        } catch (Exception ex) {
            Logger.getLogger(Shape.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
