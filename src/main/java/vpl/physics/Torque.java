/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.physics;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author kppx
 */
public class Torque {

    @Getter @Setter private Triple torque;
    @Getter @Setter private Triple center;
    @Getter @Setter private Triple offset;
    @Getter @Setter private Force force;

    public Torque() {
        init();
    }

    public Torque(Force f) {
        init();
        force = f;
        offset = f.getForceLocation();
        calculate();

    }

    private void init() {

        torque = new Triple();
        force = new Force();
        center = new Triple();
        offset = new Triple();
    }

    private void calculate() {
//Torque is a crossproduct of r and F      
//crossproduct of A and B gives C, where:       
//Cx = AyBz - AzBy 
//Cy = AzBx - AxBz 
//Cz = AxBy - AyBx 
        //thus:
        BasicMath mathLogic = new BasicMath();
        Triple torqVal = mathLogic.crossProduct(force.getForceLocation(), force.getForceValue());

        torque = torqVal;
        /*
         * new Triple();
         torque.setX(force.getForceValue().getX()*offset.getX());
         torque.setY(force.getForceValue().getY()*offset.getY());
         torque.setZ(force.getForceValue().getZ()*offset.getZ());
         * */
    }

    public Torque recalculate() {
        calculate();
        return this;
    }
}
