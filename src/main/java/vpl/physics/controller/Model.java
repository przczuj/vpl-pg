package vpl.physics.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import vpl.gui.viewmodel.selection.SelectionItem;
import vpl.math.Triple;
import vpl.physics.Collision;
import vpl.physics.Force;
import vpl.physics.RigidBody;
import vpl.physics.api.RigidBodyDrawingInfo;
import vpl.physics.controller.ControllerStub;
import vpl.physics.controller.Experiment;
import vpl.physics.AxisAngle;
import vpl.physics.shapes.Shape;

public class Model {
    private static Model instance;
    
    public final static String SELECTED_ITEM_CHANGED = "selectedItemName";
    public final static String RIGID_BODY_LIST_CHANGED = "rigidBodyList";
    public final static String AN_RIGID_BODY_CHANGED = "rigidBody";
    public final static String CAMERA_POSITION_CHANGED = "camera";
    
    private List<SimpleListener> listeners;

    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }
    
    @Getter @Setter private Experiment experiment;
    
    @Getter @Setter private SelectionItem selectedItem;
    
    @Getter @Setter private double time;
    
    @Getter private Triple cameraPosition;
    @Getter private Triple lookingPoint;
    @Getter @Setter private double cameraAngleV, cameraAngleH; 
    
    @Getter @Setter private ControllerStub physics;
    
    public Model() {
        //experiments = new HashMap<>();
        experiment = new Experiment();
        
        cameraPosition = new Triple(0,0,0);
        lookingPoint=new Triple(0,0,-1);
        cameraAngleV=0;
        cameraAngleH=0;
        
        physics = new ControllerStub(this);
        
        listeners = new ArrayList<>();
    }
    
    public void createRigidBody(Shape shape, Triple position, double timeToLive, double mass, AxisAngle initialRotation, Triple initialSpeed) {
        
    }
    
//    public List<Shape> getStaticShapeList() {
//        List<Shape> shapeList = new ArrayList<>();
//        
//        for (Map.Entry<String, VplObject> objectEntry : experiment.getObjects().entrySet()) {
//            String name = objectEntry.getKey();
//            VplObject object = objectEntry.getValue();
//            Shape shape = object.getStartingState().getShape();
//            shapeList.add(shape);
//        }
//        
//        return shapeList;
//    }
//    
//    public List<Shape> getDynamicShapeList() {
//        List<Shape> shapeList = new ArrayList<>();
//
//        for (Map.Entry<String, VplObject> objectEntry : execution.getExperiment().getObjects().entrySet()) {
//            String name = objectEntry.getKey();
//            VplObject object = objectEntry.getValue();
//            Shape shape = execution.getCurrentSnapshot().getObjectStates().get(name).getShape();
//            shapeList.add(shape);
//        }
//
//        return shapeList;
//    }
//    
//    public void addShape(String name, Shape shape) {
//        execution.getExperiment().getObjects().put(name, new VplObject(0, shape, 10));
//    }

    public void register(SimpleListener listener) {
        listeners.add(listener);
    }
    
    public void refreshView(String message) {
        for(SimpleListener listener : listeners) {
            listener.valuesChanged(message);
        }
    }
}
