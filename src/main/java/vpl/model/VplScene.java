package vpl.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import vpl.math.Triple;
import vpl.physics.controller.ControllerStub;
import vpl.shapes.Shape;

public class VplScene {
    // VplScene (single)
    // |
    // |- VplExperiment
    // |  |- VplObject[name]
    // |  |  |- VplObjectState (starting)
    // |  |     |- Shape
    // |  |- VplForce[name]
    // |  |  |- VplForceState (starting)
    // |  |     |- VplObject (whara force is attached)
    // |  |- VplGravity (starting)
    // |
    // |- VplExperimentExecution (object)
    // |  |- VplObjectState[name] (dynamic)
    // |  |  |- Shape
    // |  |- VplForceState[name] (dynamic)
    // |  |  |- VplObject (whara force is attached)
    // |  |- VplGravity (dynamic)
    // |
    // |- VplSettings (object)
    
    //@Getter private Map<String, VplExperiment> experiments; // if we want to load more than 1
    @Getter private VplExperiment experiment;
    @Getter private VplExperimentExecution execution;
    @Getter private VplSettings settings;
    
    @Getter private Triple cameraPosition;
    @Getter private Triple lookingPoint;
    @Getter @Setter private double cameraAngleV, cameraAngleH;
    
    @Getter @Setter private ControllerStub physics;    
    
    public VplScene() {
        //experiments = new HashMap<>();
        experiment = new VplExperiment();
        execution = new VplExperimentExecution(experiment);
        settings = new VplSettings();
        
        cameraPosition = new Triple(0,0,0);
        lookingPoint=new Triple(0,0,-1);
        cameraAngleV=0;
        cameraAngleH=0;
        
        physics = new ControllerStub();
    }
    
    public List<Shape> getStaticShapeList() {
        List<Shape> shapeList = new ArrayList<>();
        
        for (Map.Entry<String, VplObject> objectEntry : execution.getExperiment().getObjects().entrySet()) {
            String name = objectEntry.getKey();
            VplObject object = objectEntry.getValue();
            Shape shape = object.getStartingState().getShape();
            shapeList.add(shape);
        }
        
        return shapeList;
    }
    
    public List<Shape> getDynamicShapeList() {
        List<Shape> shapeList = new ArrayList<>();

        for (Map.Entry<String, VplObject> objectEntry : execution.getExperiment().getObjects().entrySet()) {
            String name = objectEntry.getKey();
            VplObject object = objectEntry.getValue();
            Shape shape = execution.getCurrentSnapshot().getObjectStates().get(name).getShape();
            shapeList.add(shape);
        }

        return shapeList;
    }
    
    public void addShape(String name, Shape shape) {
        execution.getExperiment().getObjects().put(name, new VplObject(0, shape, 10));
    }
}
