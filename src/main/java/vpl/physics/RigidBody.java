/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.physics;

import vpl.physics.shapes.Shape;
import vpl.math.Triple;
import vpl.math.BasicMath;
import vpl.physics.shapes.BallShape;
import java.util.ArrayList;
import java.util.List;
import vpl.math.Matrix;

/**
 *
 * @author kppx
 */
public class RigidBody {
    private double mass;
    private Triple position;// location of the center of mass
    private Quaternion q;//for calculations of rotation
    private Triple linearMomentum;
    private Triple angularMomentum;
    private double timeTick;
    private BasicMath mathLogic;
    private Triple linearVelocity;
    private Triple angularVelocity;
    private Shape shape; //defines the shape of rigid body (eg. block, ball, disc etc)
    private Force totalForce;
    private Force previousTotalForce;
    private Torque previousTotalTorque;
    private Torque totalTorque;
    private ArrayList<Force> actingForces;
    private ArrayList<Force> actingUniformForces;
    private ArrayList<Torque> actingTorques;
    private boolean forcesChanged;
    private AxisAngle rotationAngles;
    private Matrix rotationMatrix;
    private Matrix rotationMatrixRateOfChange;
    public RigidBody() throws Exception
    {
     init();
    }
    private void init() throws Exception
    {
           totalForce = new Force();
        previousTotalForce = new Force();
        totalTorque = new Torque();
        previousTotalTorque = new Torque();
        mathLogic = new BasicMath();
        linearVelocity = new Triple();
        angularVelocity = new Triple();
        position = new Triple();
        actingForces = new ArrayList();
        actingUniformForces = new ArrayList();
        actingTorques = new ArrayList();
        linearMomentum = new Triple();
        angularMomentum = new Triple();
        rotationAngles = new AxisAngle();
        rotationMatrixRateOfChange = new Matrix(3,3);
        rotationMatrix = new Matrix(3,3);
        setInitialRotation();
        timeTick = 20;
        //by default, shape is set to be a ball
        shape = new BallShape();        
        shape.setMass(mass);

    }
    private void setInitialRotation()
    {
        // 1 0 0
        // 0 1 0
        // 0 0 1
        rotationMatrix.setValueAt(0,0, 1);
        rotationMatrix.setValueAt(1,1,1);
        rotationMatrix.setValueAt(2, 2, 1);
        
    }
    public boolean isForcesChanged() {
        return forcesChanged;
    }

    public void setForcesChanged(boolean forcesChanged) {
        this.forcesChanged = forcesChanged;
    }
    public double getTimeTick() {
        return timeTick;
    }

    public void setTimeTick(double timeTick) {
        //time is given in ms, we want the result to be in seconds though
        this.timeTick = timeTick/1000;
    }
    public double getMass() {
        return mass;
    }

    public void setMass(double mass) throws Exception {
        this.mass = mass;
        shape.setMass(mass);
    }

    public Triple getPosition() {
        calculatePosition();
        return position;
    }

    public void setPosition(Triple position) {
        this.position = position;
    }

    public Quaternion getQ() {
        return q;
    }

    public void setQ(Quaternion q) {
        this.q = q;
    }

    public Triple getLinearMomentum() {
        return linearMomentum;
    }

    public void setLinearMomentum(Triple linearMomentum) {
        this.linearMomentum = linearMomentum;
    }

    public Triple getAngularMomentum() {
        return angularMomentum;
    }

    public void setAngularMomentum(Triple angularMomentum) {
        this.angularMomentum = angularMomentum;
    }

    public Triple getLinearVelocity() {
        return linearVelocity;
    }

    public void setLinearVelocity(Triple linearVelocity) {
        this.linearVelocity = linearVelocity;
    }

    public Triple getAngularVelocity() {
        return angularVelocity;
    }

    public void setAngularVelocity(Triple angularVelocity) {
        this.angularVelocity = angularVelocity;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public Force getTotalForce() {
        return totalForce;
    }
 /*   public Force calculateTotalForce()
    {
        this.totalForce = totalForce;
    }
*/
    public Torque getTotalTorque() {
        return totalTorque;
    }

    public void setTotalTorque(Torque totalTorque) {
        this.totalTorque = totalTorque;
    }

    public List<Force> getActingForces() {
        return actingForces;
    }

    public void setActingForces(ArrayList<Force> actingForces) {
        this.actingForces = actingForces;
    }

    public List<Torque> getActingTorques() {
        return actingTorques;
    }

    public void setActingTorques(ArrayList<Torque> actingTorques) {
        this.actingTorques = actingTorques;
    }
  
    public void registerForce (Force f) //'normal' force
    {
        actingForces.add(f);
        Torque t = new Torque(f);
        actingTorques.add(t);
        setForcesChanged(true);
    }
    public void registerUniformForce(Force f) //uniform force - no toque
    {
        
        actingUniformForces.add(f);
        setForcesChanged(true);
    }
    
    private void calculateTotalTorque()
    {
     Torque newTotal = new Torque();
     for ( Torque t : actingTorques)
     {
         newTotal.setTorque(mathLogic.vectorSum(newTotal.getTorque(),t.getTorque()));
     }
     previousTotalTorque = totalTorque;
     totalTorque = newTotal;
     
    }   
    //since we're going to eventually enable user to modify acitng force
    //or  add new ones we have rec
    private void calculateTotalForce()
    {
        //calculating total force acting
        //it's done here in the iterative way
        //first, combination of first two forces from the list is calculated
        //then a combination of previously calculated forces and nthe next force
        //from the list is to be calculated
        //the process is repeated until no forces acting on the body are left unattended
       
        //no need to do the math again if nothing changed
       // Force f = new Force();
        if(isForcesChanged())
        {
            
            Force newTotalForce = new Force();
            Triple newVal = new Triple();
            previousTotalForce = totalForce;
            for (Force f : actingForces)
            {   
                newVal.setX(newVal.getX()+f.getForceValue().getX());
                newVal.setY(newVal.getY()+f.getForceValue().getY());
                newVal.setZ(newVal.getZ()+f.getForceValue().getZ());
            }
            for (Force f : actingUniformForces)
            {
                newVal.setX(newVal.getX()+f.getForceValue().getX());
                newVal.setY(newVal.getY()+f.getForceValue().getY());
                newVal.setZ(newVal.getZ()+f.getForceValue().getZ());
         
            }
            newTotalForce.setForceValue(newVal);
            totalForce = newTotalForce;
        }
        setForcesChanged(false);
    }
    private void calculateLinearMomentum()
    {//linMom describes a change of linearMomentum value since the last timeTick
        Triple linMom;
        linMom = new Triple();
        //time to recalculate total force acting upon the body
        calculateTotalForce();
        
        //dF = dp/dt
        //thus deltap = integral F(t)dt over <lastTickTime, cuurentTickTime>
        //due to neccessary simplifications, Force is calculated at discrete (although very close)
        //moments in time
        //value of F(t)dt integral over that could be approximated as the area of following trapesius 
        //deltaP = (Fcurrent - Fold)*timeElapsed
        //obviously current (estimated) value of linear momentum equals to linearMomentum + linMom
        
        
        linMom.setX(mathLogic.integrate(previousTotalForce.getForceValue().getX(), totalForce.getForceValue().getX(), timeTick));
        linMom.setY(mathLogic.integrate(previousTotalForce.getForceValue().getY(), totalForce.getForceValue().getY(), timeTick));
        linMom.setZ(mathLogic.integrate(previousTotalForce.getForceValue().getZ(), totalForce.getForceValue().getZ(), timeTick));
                if (linMom.getLength() !=0)
                {
                    linearMomentum.setX(linearMomentum.getX()+linMom.getX());
                    linearMomentum.setY(linearMomentum.getY()+linMom.getY());
                    linearMomentum.setZ(linearMomentum.getZ()+linMom.getZ());
                }
    }
       private void calculateAngularMomentum()
    {//linMom describes a change of linearMomentum value since the last timeTick
        Triple angMom;
        angMom = new Triple();
        //time to recalculate total force acting upon the body
        calculateTotalTorque();
        
        //dF = dp/dt
        //thus deltap = integral F(t)dt over <lastTickTime, cuurentTickTime>
        //due to neccessary simplifications, Force is calculated at discrete (although very close)
        //moments in time
        //value of F(t)dt integral over that could be approximated as the area of following trapesius 
        //deltaP = (Fcurrent - Fold)*timeElapsed
        //obviously current (estimated) value of linear momentum equals to linearMomentum + linMom
        
      //  totalTorque.
        angMom.setX(mathLogic.integrate(previousTotalTorque.getTorque().getX(), totalTorque.getTorque().getX(), timeTick));
        angMom.setY(mathLogic.integrate(previousTotalTorque.getTorque().getY(), totalTorque.getTorque().getY(), timeTick));
        angMom.setZ(mathLogic.integrate(previousTotalTorque.getTorque().getZ(), totalTorque.getTorque().getZ(), timeTick));
                if (angMom.getLength() !=0)
                {
                    angularMomentum.setX(angularMomentum.getX()+angMom.getX());
                    angularMomentum.setY(angularMomentum.getY()+angMom.getY());
                    angularMomentum.setZ(angularMomentum.getZ()+angMom.getZ());
                }
    }
    
    private void calculateLinearVelocity()
    {
        Triple vel;
        calculateLinearMomentum();
        vel = new Triple();
        vel.setX(linearMomentum.getX()/mass);
        vel.setY(linearMomentum.getY()/mass);
        vel.setZ(linearMomentum.getZ()/mass);
        linearVelocity = vel;
    }
    
    private void calculateAngularVelocity()
    {
        calculateAngularMomentum();
     //omega(t) = I^-1 * L(t)
       angularVelocity = mathLogic.multiplyByMatrix(shape.getInvertedIBody(), angularMomentum);
    }
    private void calculatePosition()
    {
        calculateLinearVelocity();
        Triple pos  = new Triple();
        pos.setX(linearVelocity.getX()*timeTick+position.getX());
        pos.setY(linearVelocity.getY()*timeTick+position.getY());
        pos.setZ(linearVelocity.getZ()*timeTick+position.getZ());
        position = pos;
    }
    private void calculateRotation()
    {
        
        calculateAngularVelocity();
        //R* (t) = omega(t) * R(t)  whereas R*(t) means rate of change of R, ie d/dt R(t)
        double[] angVel = new double[3];
        angVel[0] = angularVelocity.getX();
        angVel[1] = angularVelocity.getY();
        angVel[2] = angularVelocity.getZ();
        
        for (int i =0; i< 3; i++)
        {
            for(int j=0;j<3;j++)
            {
                //check if it's ok
             rotationMatrixRateOfChange.setValueAt(i, j, rotationMatrix.getValueAt(i, j)*angVel[i]);
            }
        }
        //now that we have rate of change
        for (int i =0; i< 3; i++)
        {
            for(int j=0;j<3;j++)
            {
                //check if it's ok
             rotationMatrix.setValueAt(i, j, rotationMatrix.getValueAt(i, j)+rotationMatrixRateOfChange.getValueAt(i, j)*timeTick);
                //rotation happens within 0..2PI value range
                //thus...
             rotationMatrix.setValueAt(i, j, rotationMatrix.getValueAt(i, j)%(2* Math.PI));
            }
        }
        
        
    }
    
    public void print()
    {
         /*position = rb.getPosition();
         angles = rb.getRotationAngles();
         Force total = rb.getTotalForce();
         Torque totalTorq = rb.getTotalTorque();*/
         StringBuilder sb = new StringBuilder();
         sb.append("Current position: " + position.toString()+ " \n Current angles: " + getRotationAngles().toString());
         sb.append(" \n Force location: " + totalForce.getForceLocation().toString() + " \n Force value: " + totalForce.getForceValue());
         sb.append(" \n Torque value: " + totalTorque.getTorque());
         System.out.println(sb.toString());
    }
    public void calculate()
    {
        calculatePosition();
        calculateRotation();
    }
    public AxisAngle getRotationAngles()
    {
        rotationAngles = mathLogic.rotationMatrixToAngles(rotationMatrix);
        return rotationAngles;
    }
}
//state variables
//v(t) = linMom(t)/massIbody^-1  * transpose(RotationMatrix)
//omega(t) = I^-1 * L(t)
//d/dt L(t) = torque(t)
//I^-1  = RotationMatrix * Ibody^-1  * transpose(RotationMatrix)
//omega(t) = I^-1 * L(t)
//d/dt L(t) = torque(t)
//d/dt P(t) = force(t)
//since we're given torque and force rather than their respective moments
//we have to the opposite operation:
// torque(t) = Integer of (L(t) dt)
// force(t) = Integer of (P(t) dt)


//R* (t) = omega(t) * R(t)  whereas R*(t) means rate of change of R, ie d/dt R(t)
//unfortunately severe numerical drift may happen (and most likely will at some point)
//the potential solution could be through the use of unit quaternions
//since their use reduces numerical drift
//they'll be used to store values
//and rotation matrices necessary for computation of I^t will be computed dynamically
//quaternion s + vx i + vy j + vz k
//will be written asthe pair [s,v]
//under this notation
//rotation of fi radians about unit axis u is represented by
//cos(fi/2),sin(fi/2)*u]
//multiplication of quaternions:
//[s1,v1]*[s2,v2] = [s1s2-v1v2,s1s2+v1v2+crossProduct(v1,v2)]
//rate of q(t) change:
//dq(t) / dt = 1/2 * omega(t) * q(t)
//obtaining rotation matrix from quaternion:
// [1-2vy^2-2vz^2,2vxvy -   2svz, 2vxvz+2svy]
// [2vxvy +2svz, 1-2v^2 - 2vz^2, 2vyvz-2svx]
// [2vxvz - 2svy, 2vvz+2svx, 1-2vx^2-2vy^2]
//the other way round
//if sumOfDiagonal(R) >=0
//s=sqrt (sumOfDiagonal +1)
//q[0] = s/2
//s2= 1/2s
//q[1]= (R[2][1] - R[1][2])*s2
//q[2]= (R[0][2] - R[2][0])*s2
//q[3]= (R[1][0] - R[0][1])*s2
//else....
//