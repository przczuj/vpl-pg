/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vpl.gui.viewmodel.properties.binders.shapes;

import vpl.gui.ParsingHelper;
import vpl.gui.viewmodel.properties.binders.ObjectPropertyBinder;
import vpl.gui.viewmodel.properties.binders.PropertyBinder;
import vpl.gui.viewmodel.properties.binders.SimplerPropertyBinder;
import vpl.physics.shapes.CyllinderShape;

public class CyllinderShapePropertyBinder implements ObjectPropertyBinder {

    private final CyllinderShape shape;
    
    private final PropertyBinder[] properties = new PropertyBinder[]{
        new SimplerPropertyBinder("Base radius", true) {
            @Override public String getProperty() {
                return String.valueOf(shape.getR());
            }

            @Override public void setProperty(String text) {
                shape.setR(ParsingHelper.parseDouble(text, this.name));
            }
        },
        
        new SimplerPropertyBinder("Height", true) {
            @Override public String getProperty() {
                return String.valueOf(shape.getH());
            }

            @Override public void setProperty(String text) {
                shape.setH(ParsingHelper.parseDouble(text, this.name));
            }
        }
    };

    public CyllinderShapePropertyBinder(CyllinderShape shape) {
        this.shape = shape;
    }

    @Override
    public int getPropertyCount() {
        return properties.length;
    }

    @Override
    public String getPropertyName(int index) {
        return properties[index].getPropertyName();
    }

    @Override
    public String getProperty(int index) {
        return properties[index].getProperty();
    }

    @Override
    public boolean isPropertyEditable(int index) {
        return properties[index].isPropertyEditable();
    }

    @Override
    public void setProperty(int index, String text) {
        properties[index].setProperty(text);
    }
}
