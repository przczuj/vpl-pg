/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vpl.gui.viewmodel.selection;

import lombok.Getter;
import vpl.physics.Force;

public class UniformForceSelectionItem implements SelectionItem {
    @Getter private String name;
    @Getter private final Force force;

    public UniformForceSelectionItem(String name, Force force) {
        this.name = name;
        this.force = force;
    }

    public String toString() {
        return name;
    }
}
