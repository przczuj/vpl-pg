package vpl.model;

import lombok.Getter;
import lombok.Setter;
import vpl.math.Triple;
import vpl.shapes.Shape;

public class VplObject {
    @Getter @Setter private long insertTime;
    @Getter private VplObjectState startingState;

    VplObject(long insertTime, Shape shape, double mass) {
        this.insertTime = insertTime;
        startingState = new VplObjectState(shape, mass);
    }
}
