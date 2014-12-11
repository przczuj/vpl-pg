/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vpl.gui.viewmodel.properties.binders.shapes;

import vpl.gui.ParsingHelper;
import vpl.gui.viewmodel.properties.binders.ObjectPropertyBinder;
import vpl.gui.viewmodel.properties.binders.PropertyBinder;
import vpl.gui.viewmodel.properties.binders.SimplerPropertyBinder;
import vpl.physics.shapes.CuboidShape;

public class CuboidShapePropertyBinder implements ObjectPropertyBinder {

    private final CuboidShape shape;
    
    private final PropertyBinder[] properties = new PropertyBinder[]{
        new SimplerPropertyBinder("Size X", true) {
            @Override public String getProperty() {
                return String.valueOf(shape.getX());
            }

            @Override public void setProperty(String text) {
                shape.setX(ParsingHelper.parseDouble(text, this.name));
            }
        },
        
        new SimplerPropertyBinder("Size Y", true) {
            @Override public String getProperty() {
                return String.valueOf(shape.getY());
            }

            @Override public void setProperty(String text) {
                shape.setY(ParsingHelper.parseDouble(text, this.name));
            }
        },
        
        new SimplerPropertyBinder("Size Z", true) {
            @Override public String getProperty() {
                return String.valueOf(shape.getZ());
            }

            @Override public void setProperty(String text) {
                shape.setZ(ParsingHelper.parseDouble(text, this.name));
            }
        }
    };

    public CuboidShapePropertyBinder(CuboidShape shape) {
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
