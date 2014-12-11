/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vpl.gui.viewmodel.properties.binders;

import vpl.gui.ParsingHelper;
import vpl.physics.Force;
import vpl.physics.RigidBody;

public class ForcePropertyBinder implements ObjectPropertyBinder {
    private final Force force;
    private final RigidBody rb;
    
    private final PropertyBinder[] properties = new PropertyBinder[] {
        //==========================================================//
        new SimplerPropertyBinder("Force value X", true) {
            
            @Override public String getProperty() {
                return String.valueOf(force.getRealForceValue().getX());
            }
            
            @Override public void setProperty(String text) {
                force.getRealForceValue().setX(ParsingHelper.parseDouble(text, this.name));
            }
        },
        new SimplerPropertyBinder("Force value Y", true) {
            @Override public String getProperty() {
                return String.valueOf(force.getRealForceValue().getY());
            }

            @Override public void setProperty(String text) {
                force.getRealForceValue().setY(ParsingHelper.parseDouble(text, this.name));
            }
        },
        new SimplerPropertyBinder("Force value Z", true) {
            @Override public String getProperty() {
                return String.valueOf(force.getRealForceValue().getZ());
            }

            @Override public void setProperty(String text) {
                force.getRealForceValue().setZ(ParsingHelper.parseDouble(text, this.name));
            }
        },
        //==========================================================//
        
        //==========================================================//
        new SimplerPropertyBinder("Force location X", true) {
            @Override public String getProperty() {
                return String.valueOf(force.getForceLocation().getX());
            }

            @Override public void setProperty(String text) {
                force.getForceLocation().setX(ParsingHelper.parseDouble(text, this.name));
            }
        },
        new SimplerPropertyBinder("Force location Y", true) {
            @Override public String getProperty() {
                return String.valueOf(force.getForceLocation().getY());
            }

            @Override public void setProperty(String text) {
                force.getForceLocation().setY(ParsingHelper.parseDouble(text, this.name));
            }
        },
        new SimplerPropertyBinder("Force location Z", true) {
            @Override public String getProperty() {
                return String.valueOf(force.getForceLocation().getZ());
            }

            @Override public void setProperty(String text) {
                force.getForceLocation().setZ(ParsingHelper.parseDouble(text, this.name));
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

    public ForcePropertyBinder(Force force, RigidBody rb) {
        this.force = force;
        this.rb = rb;
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
        rb.setForcesChanged(true);
    }

}
