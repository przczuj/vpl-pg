    /*
     * To change this template, choose Tools | Templates
     * and open the template in the editor.
     */
    package vpl.math;
     
    import vpl.physics.AxisAngle;
    import vpl.math.Matrix;
    import java.lang.Exception;
import static java.lang.Math.PI;
    import static java.lang.Math.cos;
    import static java.lang.Math.sin;
     
    /**
     *
     * @author kppx
     */
    public class BasicMath {
     
        double a;
     
        public BasicMath() {
        }
     
        public double differentiate(double oldFX, double newFX, double deltaX) {
            double result;
            result = (newFX - oldFX) / deltaX;
     
            return result;
        }
     
        public double integrate(double oldValue, double newValue, double timeTick) {
            double result;
            result = (newValue + oldValue) * timeTick / 2;
     
            return result;
        }
     
        public Triple crossProduct(Triple A, Triple B) {
            //crossproduct of A and B gives C, where:      
    //Cx = AyBz - AzBy
    //Cy = AzBx - AxBz
    //Cz = AxBy - AyBx
            //thus:
            Triple C = new Triple();
            C.setX(A.getY() * B.getZ() - A.getZ() * B.getY());
            C.setY(A.getZ() * B.getX() - A.getX() * B.getZ());
            C.setZ(A.getX() * B.getY() - A.getY() * B.getX());
            return C;
        }
     
        public Triple vectorSum(Triple A, Triple B) {
            Triple C = new Triple();
     
            C.setX(A.getX() + B.getX());
            C.setY(A.getY() + B.getY());
            C.setZ(A.getZ() + B.getZ());
            return C;
        }

     public Triple createNormalVector(Triple A, Triple B, Triple C) {
            Triple AB = new Triple();
            AB.setX(B.getX() - A.getX());
            AB.setY(B.getY() - A.getY());
            AB.setZ(B.getZ() - A.getZ());
     
            Triple AC = new Triple();
            AC.setX(C.getX() - A.getX());
            AC.setY(C.getY() - A.getY());
            AC.setZ(C.getZ() - A.getZ());
     
            return crossProduct(AB, AC);
        }
     
        public Triple rotatePoint(Triple p, Triple c, double anglex, double angley, double anglez) {
            p.setXYZ(p.getX() - c.getX(), p.getY() - c.getY(), p.getZ() - c.getZ());
            double[][] pcoords = new double[1][3];
            pcoords[0][0] = p.getX();
            pcoords[0][1] = p.getY();
            pcoords[0][2] = p.getZ();
            Matrix P = new Matrix(pcoords);
            Matrix Rx = createRotationMatrix(1, 0, 0, anglex);
            Matrix Ry = createRotationMatrix(0, 1, 0, angley);
            Matrix Rz = createRotationMatrix(0, 0, 1, anglez);
     
            //multipling
            P = multiplyByMatrix(P, Rx);
            P = multiplyByMatrix(P, Ry);
            P = multiplyByMatrix(P, Rz);
     
            p.setX(P.getValueAt(0, 0) + c.getX());
            p.setY(P.getValueAt(0, 1) + c.getY());
            p.setZ(P.getValueAt(0, 2) + c.getZ());
     
            return p;
        }
     
        public Matrix createRotationMatrix(double x, double y, double z, double angle) {
            double[][] m = new double[3][3];
            double s = sin(angle/180.0*PI);
            double c = cos(angle/180.0*PI);
            m[0][0] = x * x * (1.0 - c) + c;
            m[0][1] = x * y * (1.0 - c) - z * s;
            m[0][2] = x * z * (1.0 - c) + y * s;
     
            m[1][0] = y * x * (1.0 - c) + z * s;
            m[1][1] = y * y * (1.0 - c) + c;
            m[1][2] = y * z * (1.0 - c) - x * s;
     
            m[2][0] = x * z * (1.0 - c) - y * s;
            m[2][1] = y * z * (1.0 - c) + x * s;
            m[2][2] = z * z * (1.0 - c) + c;
            return new Matrix(m);
        }
     
        public Matrix multiplyByScalar(double scalar, Matrix toBeMultiplied) {
            Matrix multiplied = new Matrix(toBeMultiplied.getMatrix());
            for (int i = 0; i < multiplied.getRows(); i++) {
                for (int j = 0; j < multiplied.getColumns(); j++) {
                    multiplied.setValueAt(i, j, multiplied.getValueAt(i, j) * scalar);
                }
            }
     
            return multiplied;
        }
        public Triple multiplyByScalar(double scalar, Triple toBeMultiplied) {
            Triple multiplied = new Triple(toBeMultiplied.getX()*scalar,toBeMultiplied.getY()*scalar,toBeMultiplied.getZ()*scalar);
          
     
            return multiplied;
        }
        public Matrix multiplyByMatrix(Matrix a, Matrix b) {
            //number of columns in 1st matrix has to be equal to number of rows in 2nd matrix
            //for multiplication to be possible
            assert (a.getColumns() == b.getRows());
            final Matrix result = new Matrix(a.getRows(), b.getColumns());
            //time to calculate
            //since dimensions of matrices are small
            //(most likely 3x3)
            //there is no need for algorithm to be faster than O(n^3)
            double tmp = 0;
            for (int i = 0; i < a.getRows(); i++) {
                for (int j = 0; j < b.getColumns(); j++) {
                    for (int k = 0; k < b.getRows(); k++) {
                        tmp = tmp + a.getValueAt(i, k) * b.getValueAt(k, j);
                    }
                    result.setValueAt(i, j, tmp);
                    tmp = 0;
                }
            }
            return result;
        }
     
        public Matrix matrixSum(Matrix a, Matrix b) {
            //works only if both matrixes are of the same size
            assert (a.getColumns() == b.getColumns() && a.getRows() == b.getRows());
     
            final Matrix result = new Matrix(a.getRows(), a.getColumns());
            for (int i = 0; i < result.getRows(); i++) {
                for (int j = 0; j < result.getColumns(); j++) {
                    result.setValueAt(i, j, a.getValueAt(i, j) + b.getValueAt(i, j));
                }
            }
     
            return result;
        }
     
        public Matrix matrixDifference(Matrix a, Matrix b) {
            //works only if both matrixes are of the same size
            assert (a.getColumns() == b.getColumns() && a.getRows() == b.getRows());
     
            final Matrix result = new Matrix(a.getRows(), a.getColumns());
            for (int i = 0; i < result.getRows(); i++) {
                for (int j = 0; j < result.getColumns(); j++) {
                    result.setValueAt(i, j, a.getValueAt(i, j) - b.getValueAt(i, j));
                }
            }
     
            return result;
        }
     
        public Triple multiplyByMatrix(Matrix invertedIBody, Triple angularMomentum) {
            Matrix mat = angularMomentum.toMatrix();
            Matrix result = multiplyByMatrix(invertedIBody, mat);
            return result.toTriple();
     
        }
     
        //inspired by http://www.euclideanspace.com/maths/geometry/rotations/conversions/angleToMatrix/index.htm
        public Matrix anglesToRotationMatrix(AxisAngle angles) {
                 Matrix matrix = new Matrix(3, 3);
            double c = Math.cos(Math.toRadians(angles.getAngle()));
            double s = Math.sin(Math.toRadians(angles.getAngle()));
     
            double t = 1.0 - c;
            //time to scale
            double magnitude =
                  Math.sqrt(angles.getAngles().getX()*angles.getAngles().getX()
             + angles.getAngles().getY()*angles.getAngles().getY() + angles.getAngles().getZ()*angles.getAngles().getZ());
     
            angles.getAngles().setX(angles.getAngles().getX() / magnitude);
            angles.getAngles().setY(angles.getAngles().getY() / magnitude);
            angles.getAngles().setZ(angles.getAngles().getZ() / magnitude);
            matrix.setValueAt(0, 0, (c + angles.getAngles().getX() * angles.getAngles().getX() * t));
            matrix.setValueAt(1, 1, (c + angles.getAngles().getY() * angles.getAngles().getY() * t));
            matrix.setValueAt(2, 2, (c + angles.getAngles().getZ() * angles.getAngles().getZ() * t));
            double tmp1 = angles.getAngles().getX() * angles.getAngles().getY() * t;
            double tmp2 = angles.getAngles().getZ() * s;
            matrix.setValueAt(1, 0, tmp1 + tmp2);
            matrix.setValueAt(0, 1, tmp1 - tmp2);
            tmp1 = angles.getAngles().getX() * angles.getAngles().getZ() * t;
            tmp2 = angles.getAngles().getY() * s;
            matrix.setValueAt(2, 0, tmp1 - tmp2);
            matrix.setValueAt(0, 2, tmp1 + tmp2);
            tmp1 = angles.getAngles().getY() * angles.getAngles().getZ() * t;
     
            tmp2 = angles.getAngles().getX() * s;
            matrix.setValueAt(2, 1, tmp1 + tmp2);
            matrix.setValueAt(1, 2, tmp1 - tmp2);
            return matrix;
        }
       
       public double dotProduct(Triple t1, Triple t2)
        {
            double result;
            /*result.setX(t1.getX()*t2.getX());
            result.setY(t1.getY()*t2.getY());
            result.setZ(t1.getZ()*t2.getZ());*/
            result=t1.getX()*t2.getX();
            result+=t1.getY()*t2.getX();
            result+=t1.getZ()*t2.getZ();
            return result;
        }
       
        //inspired by http://www.euclideanspace.com/maths/geometry/rotations/conversions/matrixToAngle/index.htm
        public AxisAngle rotationMatrixToAngles(Matrix rot) {
            Triple t = new Triple();
            double angle;
            double epsilon = 0.0001;       //allows for rounding errors
            double epsilon2 = 0.01;
            assert (rot.getColumns() == rot.getRows() && rot.getRows() == 3);//rotation matrix is always 3x3
           // Matrix normalized = rot.normalizeByColumn(rot);
           // double m[][] = normalized.getMatrix();
            //time to normalize
            double m[][] =rot.getMatrix();
            //testing for singularity - by euclideanspace
            if ((Math.abs(m[0][1] - m[1][0]) < epsilon)
                    && (Math.abs(m[0][2] - m[2][0]) < epsilon)
                    && (Math.abs(m[1][2] - m[2][1]) < epsilon)) {
                // singularity found
                // first check for identity matrix which must have +1 for all terms
                //  in leading diagonaland zero in other terms
                if ((Math.abs(m[0][1] + m[1][0]) < epsilon2)
                        && (Math.abs(m[0][2] + m[2][0]) < epsilon2)
                        && (Math.abs(m[1][2] + m[2][1]) < epsilon2)
                        && (Math.abs(m[0][0] + m[1][1] + m[2][2] - 3) < epsilon2)) {
                    // this singularity is identity matrix so angle = 0
                    return new AxisAngle(0, new Triple(1, 0, 0)); // zero angle, arbitrary axis
                }
                // otherwise this singularity is angle = 180
                angle = Math.PI;
                double xx = (m[0][0] + 1) / 2;
                double yy = (m[1][1] + 1) / 2;
                double zz = (m[2][2] + 1) / 2;
                double xy = (m[0][1] + m[1][0]) / 4;
                double xz = (m[0][2] + m[2][0]) / 4;
                double yz = (m[1][2] + m[2][1]) / 4;
                if ((xx > yy) && (xx > zz)) { // m[0][0] is the largest diagonal term
                    if (xx < epsilon) {
                        t.setX(0);
                        t.setY(0.7071);
                        t.setZ(0.7071);
                    } else {
                        t.setX(Math.sqrt(xx));
                        t.setY(xy / t.getX());
                        t.setZ(xz / t.getZ());
                    }
                } else if (yy > zz) { // m[1][1] is the largest diagonal term
                    if (yy < epsilon) {
                        t.setX(0.7071);
                        t.setY(0);
                        t.setZ(0.7071);
                    } else {
                        t.setX(xy / t.getY());
                        t.setY(Math.sqrt(yy));
                        t.setZ(yz / t.getY());
                    }
                } else { // m[2][2] is the largest diagonal term so base result on this
                    if (zz < epsilon) {
                        t.setX(0.7071);
                        t.setY(0.7071);
                        t.setZ(0);
                    } else {
                        t.setX(xz / t.getZ());
                        t.setY(yz / t.getZ());
                        t.setZ(Math.sqrt(zz));
                    }
                }
                return new AxisAngle(angle, new Triple(t.getX(), t.getY(), t.getZ())); // return 180 deg rotation
            }
            // there are no singularities so we can handle normally
            double s = Math.sqrt((m[2][1] - m[1][2]) * (m[2][1] - m[1][2])
                    + (m[0][2] - m[2][0]) * (m[0][2] - m[2][0])
                    + (m[1][0] - m[0][1]) * (m[1][0] - m[0][1])); // used to normalise
            if (Math.abs(s) < 0.001) {
                s = 1;
            }
            // prevents division by zero, should not happen if matrix is orthogonal and should be
            // caught by singularity test above, but I've left it in just in case
            double toAngle = (m[0][0] + m[1][1] + m[2][2] - 1) / 2;
            if (toAngle >=1  || toAngle <=-1)
            {
            /*    if(toAngle>0)
                toAngle = 1;
                else
                    toAngle=-1;*/
            }
            angle = Math.acos(toAngle);
            t.setX(Math.toDegrees((m[2][1] - m[1][2]) / s));
            t.setY(Math.toDegrees((m[0][2] - m[2][0]) / s));
            t.setZ(Math.toDegrees((m[1][0] - m[0][1]) / s));
            angle = Math.toDegrees(angle);
            
            AxisAngle result = new AxisAngle(angle, t);
            if (angle == Double.NaN) // for debugging purposes only
            {
             System.out.println("Nan encountered");   
            }
            return result;
        }
    }
