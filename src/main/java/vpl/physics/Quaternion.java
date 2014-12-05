/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.physics;

import vpl.math.Matrix;
import vpl.math.Triple;

/**
 *
 * @author kppx
 */
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
public class Quaternion {

    private double w, x, y, z;
    private double s;
    private Triple v;

    public Quaternion() {
        v = new Triple();
        s = 0;

    }

    //obtaining rotation matrix from quaternion:
// [1-2vy^2-2vz^2,2vxvy -   2svz, 2vxvz+2svy]
// [2vxvy +2svz, 1-2v^2 - 2vz^2, 2vyvz-2svx]
// [2vxvz - 2svy, 2vvz+2svx, 1-2vx^2-2vy^2]
    public Matrix toRotationMatrix() {
        Matrix mat = new Matrix(3, 3);
        mat.setValueAt(0, 0, (1 - 2 * Math.pow(v.getY(), 2) - 2 * Math.pow(v.getZ(), 2)));
        mat.setValueAt(1, 0, (1 - 2 * Math.pow(v.getY(), 2) - 2 * Math.pow(v.getZ(), 2)));
        /// mat.
        return mat;
    }
}
