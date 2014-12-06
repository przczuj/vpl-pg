/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vpl.gui.viewmodel.selection;

import lombok.Getter;
import vpl.physics.Force;
import vpl.physics.RigidBody;

public class ForceSelectionItem implements SelectionItem {
    @Getter private final int num;
    @Getter private final String bodyName;
    @Getter private final RigidBody body;
    
    public ForceSelectionItem(int num, String bodyName, RigidBody body) {
        this.num = num;
        this.bodyName = bodyName;
        this.body = body;
    }

    public Force getForce() {
        return body.getActingForces().get(num);
    }

    public String toString() {
        return "force " + num;
    }
}
