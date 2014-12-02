/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vpl.model;

import lombok.Getter;
import lombok.Setter;
import vpl.math.Triple;

public class VplForceState {

    @Getter @Setter private VplObject binding;
    @Getter @Setter private Triple vector;
}
