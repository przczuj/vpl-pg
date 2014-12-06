/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vpl.gui.viewmodel.selection;

import lombok.Getter;
import vpl.physics.RigidBody;

public class RigidBodySelectionItem implements SelectionItem {
    
    @Getter private final String name;
    @Getter private final RigidBody body;
    
    public RigidBodySelectionItem(String name, RigidBody body) {
        this.name = name;
        this.body = body;
    }
    
    public String toString() {
        return name;
    }
}
