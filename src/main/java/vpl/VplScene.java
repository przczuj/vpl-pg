package vpl;

import java.util.List;
import java.util.Map;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Przemysław Czuj
 */
public class VplScene {
    // VplScene (single)
    // |- VplExperiment (starting[] + current)
    //    |- VplExperimentState (object)
    //       |- VplObject (physical state)
    //       |  |- VplShape (final + reference)
    //       |- VplForce (can be attached to VplObject)
    //       |- VplGravity (global force)
    
    // core of LOGIC layer
    
    // collection of independant experiments - to switch between
    // current experiment copy with its state changed in time
    // (collection of experiment snapshots - for experiment rewind)
    // user preferences
    // new + load + save
    
    private Map<String, VplExperiment> experiments;
    private VplExperiment currentExperiment;
    private List<VplExperimentState> snapshots;
    private VplSettings settings;
}
