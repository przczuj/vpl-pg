/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.physics.shapes;

import vpl.physics.Shape;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author kppx
 */
public class CyllinderShape extends Shape {

    @Setter @Getter protected double r, h;

    public CyllinderShape() throws Exception {
        init();
        type = new String("CYLLINDER");
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
