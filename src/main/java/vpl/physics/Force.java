/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.physics;

import vpl.math.Triple;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author kppx
 */
public class Force {

    @Getter @Setter private Triple forceValue;
    @Getter @Setter private Triple forceLocation;

    public Force() {
        forceValue = new Triple();
        forceLocation = new Triple();
    }
}
