/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vpl.gui.viewmodel.properties.binders;

public abstract class SimplerPropertyBinder implements PropertyBinder {
    
    protected final String name;
    protected final boolean editable;

    public SimplerPropertyBinder(String name, boolean editable) {
        this.name = name;
        this.editable = editable;
    }
    
    @Override
    public boolean isPropertyEditable() {
        return editable;
    }

    @Override
    public String getPropertyName() {
        return name;
    }

}
