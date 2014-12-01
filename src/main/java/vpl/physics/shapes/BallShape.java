/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.physics.shapes;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kppx
 */
public class BallShape extends Shape{
    protected double r;

    public double getR() {
        return r;
    }
    public BallShape() throws Exception
            {
                super();
             init();   
             type = "BALL";
            }
    public void setR(double r) {
        this.r = r;
        try {
            init();
        } catch (Exception ex) {
            Logger.getLogger(BallShape.class.getName()).log(Level.SEVERE, null, ex);
        }
        recalculate();
    }
   

    @Override
    public void recalculate() {
        IBody.setValueAt(0, 0, (2*r*r)*mass/5);
        IBody.setValueAt(1, 1, (2*r*r)*mass/5);
        IBody.setValueAt(2, 2, (2*r*r)/mass/5);
        try {
        InvertedIBody =  IBody.inverse(IBody);
     } catch (Exception ex) {
         Logger.getLogger(Shape.class.getName()).log(Level.SEVERE, null, ex);
     }
          }


}
