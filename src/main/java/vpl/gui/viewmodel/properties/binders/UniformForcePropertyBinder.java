/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vpl.gui.viewmodel.properties.binders;

import vpl.gui.ParsingHelper;
import vpl.gui.viewmodel.properties.binders.ObjectPropertyBinder;
import vpl.physics.Force;

public class UniformForcePropertyBinder implements ObjectPropertyBinder {
    private final Force force;
    
    private final PropertyBinder[] properties = new PropertyBinder[]{
        //==========================================================//
        new SimplerPropertyBinder("Force value X", true) {
            @Override public String getProperty() {
                return String.valueOf(force.getForceValue().getX());
            }

            @Override public void setProperty(String text) {
                force.getForceValue().setX(ParsingHelper.parseDouble(text, this.name));
            }
        },
        new SimplerPropertyBinder("Force value Y", true) {
            @Override public String getProperty() {
                return String.valueOf(force.getForceValue().getY());
            }

            @Override public void setProperty(String text) {
                force.getForceValue().setY(ParsingHelper.parseDouble(text, this.name));
            }
        },
        new SimplerPropertyBinder("Force value Z", true) {
            @Override public String getProperty() {
                return String.valueOf(force.getForceValue().getZ());
            }

            @Override public void setProperty(String text) {
                force.getForceValue().setZ(ParsingHelper.parseDouble(text, this.name));
            }
        },
        //==========================================================//

        //==========================================================//
        new SimplerPropertyBinder("Time to live", true) {
            @Override public String getProperty() {
                return String.valueOf(force.getTimeToLive());
            }

            @Override public void setProperty(String text) {
                force.setTimeToLive(ParsingHelper.parseDouble(text, this.name));
            }
        },
        new SimplerPropertyBinder("Is forever", true) {
            @Override public String getProperty() {
                return String.valueOf(force.isForever());
            }

            @Override public void setProperty(String text) {
                force.setForever(ParsingHelper.parseBoolean(text, this.name));
            }
        }
    };

    public UniformForcePropertyBinder(Force force) {
        this.force = force;
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
