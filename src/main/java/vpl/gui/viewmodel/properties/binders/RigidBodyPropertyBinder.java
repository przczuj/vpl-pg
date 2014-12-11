/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vpl.gui.viewmodel.properties.binders;

import java.util.logging.Level;
import java.util.logging.Logger;
import vpl.gui.ParsingHelper;
import vpl.gui.viewmodel.properties.binders.PropertyBinder;
import vpl.gui.viewmodel.properties.binders.SimplerPropertyBinder;
import vpl.gui.viewmodel.properties.binders.ObjectPropertyBinder;
import vpl.gui.viewmodel.properties.binders.shapes.BallShapePropertyBinder;
import vpl.gui.viewmodel.properties.binders.shapes.ConeShapePropertyBinder;
import vpl.gui.viewmodel.properties.binders.shapes.CuboidShapePropertyBinder;
import vpl.gui.viewmodel.properties.binders.shapes.CyllinderShapePropertyBinder;
import vpl.math.BasicMath;
import vpl.math.Matrix;
import vpl.physics.AxisAngle;
import vpl.physics.RigidBody;
import vpl.physics.shapes.BallShape;
import vpl.physics.shapes.ConeShape;
import vpl.physics.shapes.CuboidShape;
import vpl.physics.shapes.CyllinderShape;

public class RigidBodyPropertyBinder implements ObjectPropertyBinder {
    private final RigidBody rigidBody;
    private final ObjectPropertyBinder shapePropertyBinder;
    
    private final PropertyBinder[] properties = new PropertyBinder[] {
        
        //==========================================================//
        new SimplerPropertyBinder("Position X", true) {
            @Override public String getProperty() {
                return String.valueOf(rigidBody.getPosition().getX());
            }

            @Override public void setProperty(String text) {
                rigidBody.getPosition().setX(ParsingHelper.parseDouble(text, this.name));
            }
        },
        new SimplerPropertyBinder("Position Y", true) {
            @Override public String getProperty() {
                return String.valueOf(rigidBody.getPosition().getY());
            }

            @Override public void setProperty(String text) {
                rigidBody.getPosition().setY(ParsingHelper.parseDouble(text, this.name));
            }
        },
        new SimplerPropertyBinder("Position Z", true) {
            @Override public String getProperty() {
                return String.valueOf(rigidBody.getPosition().getZ());
            }

            @Override public void setProperty(String text) {
                rigidBody.getPosition().setZ(ParsingHelper.parseDouble(text, this.name));
            }
        },
        //==========================================================//
        
        //==========================================================//
        new SimplerPropertyBinder("Rotation X", true) {
            @Override public String getProperty() {
                return String.valueOf(rigidBody.getRotationAngles().getAngles().getX());
            }

            @Override public void setProperty(String text) {
                AxisAngle rotationAngles = rigidBody.getRotationAngles();
                rotationAngles.getAngles().setX(ParsingHelper.parseDouble(text, this.name));
                rigidBody.setRotationMatrix(new BasicMath().anglesToRotationMatrix(rotationAngles));
            }
        },
        new SimplerPropertyBinder("Rotation Y", true) {
            @Override public String getProperty() {
                return String.valueOf(rigidBody.getRotationAngles().getAngles().getY());
            }

            @Override public void setProperty(String text) {
                AxisAngle rotationAngles = rigidBody.getRotationAngles();
                rotationAngles.getAngles().setY(ParsingHelper.parseDouble(text, this.name));
                rigidBody.setRotationMatrix(new BasicMath().anglesToRotationMatrix(rotationAngles));
            }
        },
        new SimplerPropertyBinder("Rotation Z", true) {
            @Override public String getProperty() {
                return String.valueOf(rigidBody.getRotationAngles().getAngles().getZ());
            }

            @Override public void setProperty(String text) {
                AxisAngle rotationAngles = rigidBody.getRotationAngles();
                rotationAngles.getAngles().setZ(ParsingHelper.parseDouble(text, this.name));
                rigidBody.setRotationMatrix(new BasicMath().anglesToRotationMatrix(rotationAngles));
            }
        },
        new SimplerPropertyBinder("Rotation angle", true) {
            @Override public String getProperty() {
                return String.valueOf(rigidBody.getRotationAngles().getAngle());
            }

            @Override public void setProperty(String text) {
                AxisAngle rotationAngles = rigidBody.getRotationAngles();
                rotationAngles.setAngle(ParsingHelper.parseDouble(text, this.name));
                rigidBody.setRotationMatrix(new BasicMath().anglesToRotationMatrix(rotationAngles));
            }
        },
        //==========================================================//
            
        //==========================================================//
        new SimplerPropertyBinder("Linear Velocity X", false) {
            @Override public String getProperty() {
                return String.valueOf(rigidBody.getLinearVelocity().getX());
            }

            @Override public void setProperty(String text) {
                rigidBody.getLinearVelocity().setX(ParsingHelper.parseDouble(text, this.name));
            }
        },
        new SimplerPropertyBinder("Linear Velocity Y", false) {
            @Override public String getProperty() {
                return String.valueOf(rigidBody.getLinearVelocity().getY());
            }

            @Override public void setProperty(String text) {
                rigidBody.getLinearVelocity().setY(ParsingHelper.parseDouble(text, this.name));
            }
        },
        new SimplerPropertyBinder("Linear Velocity Z", false) {
            @Override public String getProperty() {
                return String.valueOf(rigidBody.getLinearVelocity().getZ());
            }

            @Override public void setProperty(String text) {
                rigidBody.getLinearVelocity().setZ(ParsingHelper.parseDouble(text, this.name));
            }
        },
        //==========================================================//

        //==========================================================//
        new SimplerPropertyBinder("Angular Velocity X", false) {
            @Override public String getProperty() {
                return String.valueOf(rigidBody.getAngularVelocity().getX());
            }

            @Override public void setProperty(String text) {
                rigidBody.getAngularVelocity().setX(ParsingHelper.parseDouble(text, this.name));
            }
        },
        new SimplerPropertyBinder("Angular Velocity Y", false) {
            @Override public String getProperty() {
                return String.valueOf(rigidBody.getAngularVelocity().getY());
            }

            @Override public void setProperty(String text) {
                rigidBody.getAngularVelocity().setY(ParsingHelper.parseDouble(text, this.name));
            }
        },
        new SimplerPropertyBinder("Angular Velocity Z", false) {
            @Override public String getProperty() {
                return String.valueOf(rigidBody.getAngularVelocity().getZ());
            }

            @Override public void setProperty(String text) {
                rigidBody.getAngularVelocity().setZ(ParsingHelper.parseDouble(text, this.name));
            }
        },
        //==========================================================//
        
        //==========================================================//
        new SimplerPropertyBinder("Linear Momentum X", true) {
            @Override public String getProperty() {
                return String.valueOf(rigidBody.getLinearMomentum().getX());
            }

            @Override public void setProperty(String text) {
                rigidBody.getLinearMomentum().setX(ParsingHelper.parseDouble(text, this.name));
            }
        },
        new SimplerPropertyBinder("Linear Momentum Y", true) {
            @Override public String getProperty() {
                return String.valueOf(rigidBody.getLinearMomentum().getY());
            }

            @Override public void setProperty(String text) {
                rigidBody.getLinearMomentum().setY(ParsingHelper.parseDouble(text, this.name));
            }
        },
        new SimplerPropertyBinder("Linear Momentum Z", true) {
            @Override public String getProperty() {
                return String.valueOf(rigidBody.getLinearMomentum().getZ());
            }

            @Override public void setProperty(String text) {
                rigidBody.getLinearMomentum().setZ(ParsingHelper.parseDouble(text, this.name));
            }
        },
        //==========================================================//

        //==========================================================//
        new SimplerPropertyBinder("Angular Momentum X", true) {
            @Override public String getProperty() {
                return String.valueOf(rigidBody.getAngularMomentum().getX());
            }

            @Override public void setProperty(String text) {
                rigidBody.getAngularMomentum().setX(ParsingHelper.parseDouble(text, this.name));
            }
        },
        new SimplerPropertyBinder("Angular Momentum Y", true) {
            @Override public String getProperty() {
                return String.valueOf(rigidBody.getAngularMomentum().getY());
            }

            @Override public void setProperty(String text) {
                rigidBody.getAngularMomentum().setY(ParsingHelper.parseDouble(text, this.name));
            }
        },
        new SimplerPropertyBinder("Angular Momentum Z", true) {
            @Override public String getProperty() {
                return String.valueOf(rigidBody.getAngularMomentum().getZ());
            }

            @Override public void setProperty(String text) {
                rigidBody.getAngularMomentum().setZ(ParsingHelper.parseDouble(text, this.name));
            }
        },
        //==========================================================//
        
        new SimplerPropertyBinder("Shape", false) {
            @Override public String getProperty() {
                return rigidBody.getShape().getType();
            }

            @Override public void setProperty(String text) {
            }
        },
        new SimplerPropertyBinder("Mass", true) {
            @Override public String getProperty() {
                return String.valueOf(rigidBody.getMass());
            }

            @Override public void setProperty(String text) {
                try {
                    double mass = ParsingHelper.parseDouble(text, this.name);
                    rigidBody.setMass(mass);
                    rigidBody.getShape().setMass(mass);
                } catch (Exception ex) {
                    Logger.getLogger(RigidBodyPropertyBinder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    };

    public RigidBodyPropertyBinder(RigidBody rigidBody) {
        this.rigidBody = rigidBody;
        if (rigidBody.getShape() instanceof BallShape) {
            shapePropertyBinder = new BallShapePropertyBinder((BallShape)rigidBody.getShape());
            
        } else if (rigidBody.getShape() instanceof ConeShape) {
            shapePropertyBinder = new ConeShapePropertyBinder((ConeShape) rigidBody.getShape());
            
        } else if (rigidBody.getShape() instanceof CuboidShape) {
            shapePropertyBinder = new CuboidShapePropertyBinder((CuboidShape) rigidBody.getShape());
            
        } else if (rigidBody.getShape() instanceof CyllinderShape) {
            shapePropertyBinder = new CyllinderShapePropertyBinder((CyllinderShape) rigidBody.getShape());
            
        } else {
            shapePropertyBinder = null;
        }
    }

    @Override
    public int getPropertyCount() {
        return properties.length + (shapePropertyBinder != null ? shapePropertyBinder.getPropertyCount() : 0);
    }

    @Override
    public String getPropertyName(int index) {
        if (index < properties.length) {
            return properties[index].getPropertyName();
        } else {
            return shapePropertyBinder.getPropertyName(index - properties.length);
        }
    }

    @Override
    public String getProperty(int index) {
        if (index < properties.length) {
            return properties[index].getProperty();
        } else {
            return shapePropertyBinder.getProperty(index - properties.length);
        }
    }

    @Override
    public boolean isPropertyEditable(int index) {
        if (index < properties.length) {
            return properties[index].isPropertyEditable();
        } else {
            return shapePropertyBinder.isPropertyEditable(index - properties.length);
        }
    }

    @Override
    public void setProperty(int index, String text) {
        if (index < properties.length) {
            properties[index].setProperty(text);
        } else {
            shapePropertyBinder.setProperty(index - properties.length, text);
        }
    }

}
