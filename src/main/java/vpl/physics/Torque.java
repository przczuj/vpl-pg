/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.physics;

/**
 *
 * @author kppx
 */
public class Torque {

  private Triple torque;
  private Triple center;
  private Triple offset;

    public Triple getOffset() {
        return offset;
    }

    public void setOffset(Triple offset) {
        this.offset = offset;
    }
  
  private Force force;
   public Torque()
  {
      init();
  }
  public Torque(Force f)
  {
      init();
      force = f;
      offset = f.getForceLocation();
      calculate();
      
  }
  private void init()
  {
      
      torque = new Triple();
      force = new Force();
      center = new Triple();
      offset = new Triple();
  }
  private void calculate()
  {
//Torque is a crossproduct of r and F      
//crossproduct of A and B gives C, where:       
//Cx = AyBz - AzBy 
//Cy = AzBx - AxBz 
//Cz = AxBy - AyBx 
 //thus:
      BasicMath mathLogic = new BasicMath();
      Triple torqVal = mathLogic.crossProduct(force.getForceLocation(), force.getForceValue());
           
      torque = torqVal;
      /*
               * new Triple();
      torque.setX(force.getForceValue().getX()*offset.getX());
      torque.setY(force.getForceValue().getY()*offset.getY());
      torque.setZ(force.getForceValue().getZ()*offset.getZ());
      * */
  }
  public Torque recalculate()
  {
   calculate();
   return this;
  }
  public Triple getTorque() {
        return torque;
    }

    public void setTorque(Triple torque) {
        this.torque = torque;
    }

    public Triple getCenter() {
        return center;
    }

    public void setCenter(Triple center) {
        this.center = center;
    }

    public Force getForce() {
        return force;
    }

    public void setForce(Force force) {
        this.force = force;
    }
}
