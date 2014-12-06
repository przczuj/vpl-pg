/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vpl.gui.viewmodel.properties.binders.shapes;

import vpl.gui.viewmodel.properties.binders.ObjectPropertyBinder;
import vpl.gui.viewmodel.properties.binders.PropertyBinder;
import vpl.gui.viewmodel.properties.binders.SimplerPropertyBinder;
import vpl.physics.shapes.BallShape;
import vpl.physics.shapes.Shape;

public class BallShapePropertyBinder implements ObjectPropertyBinder {

    private final BallShape shape;
    
    private final PropertyBinder[] properties = new PropertyBinder[]{
        new SimplerPropertyBinder("Radius", true) {
            @Override public String getProperty() {
                return String.valueOf(shape.getR());
            }

            @Override public void setProperty(String text) {
                shape.setR(Double.parseDouble(text));
            }
        }
    };

    public BallShapePropertyBinder(BallShape shape) {
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
