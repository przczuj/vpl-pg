/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.physics;

/**
 *
 * @author kppx
 */
public class Force {
   private Triple forceValue;
   private Triple forceLocation;
   
   public Force()
   {
    forceValue=new Triple();
    forceLocation = new Triple();
   }
    public Triple getForceValue() {
        return forceValue;
    }

    public void setForceValue(Triple forceValue) {
        this.forceValue = forceValue;
    }

    public Triple getForceLocation() {
        return forceLocation;
    }

    public void setForceLocation(Triple forceLocation) {
        this.forceLocation = forceLocation;
    }
   
}
