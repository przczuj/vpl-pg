/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.model;

import lombok.Getter;
import lombok.Setter;
import vpl.math.Triple;
import vpl.shapes.Shape;

public class VplObjectState {

    @Getter @Setter private Triple velocity;
    @Getter @Setter private Shape shape;
    @Getter @Setter private double mass;

    VplObjectState(Shape shape, double mass) {
        this.velocity = new Triple();
        this.shape = shape;
        this.mass = mass;
    }
}
