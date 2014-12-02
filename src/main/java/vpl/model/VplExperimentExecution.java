/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.model;

import java.util.List;
import lombok.Getter;
import vpl.physics.controller.ControllerStub;

public class VplExperimentExecution {
    @Getter private VplExperiment experiment;
    //private List<VplExperimentSnapshot> snapshots; // todo: feature to rewind using calculated snapshots
    
    @Getter private VplExperimentSnapshot currentSnapshot;

    VplExperimentExecution(VplExperiment experiment) {
        this.experiment = experiment;
    }
}
