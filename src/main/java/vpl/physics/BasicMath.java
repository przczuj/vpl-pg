/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpl.physics;
/**
 *
 * @author kppx
 */
public class BasicMath {
 double a;
 BasicMath()
         {
         }
 public double differentiate(double oldFX, double newFX, double deltaX)
 {
  double result;
  result = (newFX - oldFX)/deltaX;
  
  return result;
 }

    double integrate( double oldValue, double newValue, double timeTick) {
        double result;
  result = (newValue + oldValue)*timeTick/2;
  
  return result; }
    
        public Triple crossProduct(Triple A, Triple B)
    {
        //crossproduct of A and B gives C, where:       
//Cx = AyBz - AzBy 
//Cy = AzBx - AxBz 
//Cz = AxBy - AyBx 
 //thus:
     Triple C = new Triple();
     C.setX(A.getY()*B.getZ() - A.getZ()*B.getY());
     C.setY(A.getZ()*B.getX() - A.getX()*B.getZ());
     C.setZ(A.getX()*B.getY() - A.getZ()*B.getY());
     return C;
    }
        public Triple vectorSum(Triple A, Triple B)
        {
         Triple C = new Triple();
         
         C.setX(A.getX()+B.getX());
         C.setY(A.getY()+B.getY());
         C.setZ(A.getZ()+B.getZ());
         return C;
        }
            public Matrix multiplyByScalar(double scalar, Matrix toBeMultiplicated)
    {
     Matrix multiplicated = new Matrix(toBeMultiplicated.getMatrix());   
     for (int i =0;i <multiplicated.getRows();i++)
     {
           for (int j =0;j <multiplicated.getColumns();j++)
     {
         multiplicated.setValueAt(i, j, multiplicated.getValueAt(i, j)*scalar);
     }
     }
     
     return multiplicated;
    }
    public Matrix multiplyByMatrix(Matrix a, Matrix b)
    {
        //number of columns in 1st matrix has to be equal to number of rows in 2nd matrix
        //for multiplication to be possible
        assert( a.getColumns()  == b.getRows());
     final Matrix result = new Matrix(a.getRows(),b.getColumns());
     //time to calculate
     //since dimensions of matrices are small
     //(most likely 3x3)
     //there is no need for algorithm to be faster than O(n^3)
     double tmp = 0;
     for (int i = 0; i < a.getRows(); i++) 
     {
         for (int j = 0; j < b.getColumns(); j++)
         {
             for (int k = 0; k < b.getRows(); k++) 
             {
               tmp = tmp + a.getValueAt(i, k) * b.getValueAt(k, j);
             }
             result.setValueAt(i,j,tmp);
             tmp = 0;
         }
    }
     return result;
    }
    public Matrix matrixSum(Matrix a, Matrix b)
    {
        //works only if both matrixes are of the same size
     assert(a.getColumns()==b.getColumns() && a.getRows() == b.getRows());
            
     final Matrix result = new Matrix(a.getRows(),a.getColumns());   
     for (int i =0;i <result.getRows();i++)
     {
           for (int j =0;j <result.getColumns();j++)
     {
         result.setValueAt(i, j, a.getValueAt(i, j)+b.getValueAt(i, j));
     }
     }
     
     return result;
    }
      public Matrix matrixDifference(Matrix a, Matrix b)
    {
        //works only if both matrixes are of the same size
     assert(a.getColumns()==b.getColumns() && a.getRows() == b.getRows());
            
     final Matrix result = new Matrix(a.getRows(),a.getColumns());   
     for (int i =0;i <result.getRows();i++)
     {
           for (int j =0;j <result.getColumns();j++)
     {
         result.setValueAt(i, j, a.getValueAt(i, j)-b.getValueAt(i, j));
     }
     }
     
     return result;
    }

    Triple multiplyByMatrix(Matrix invertedIBody, Triple angularMomentum) {
        Matrix mat = angularMomentum.toMatrix();
        Matrix result = multiplyByMatrix(invertedIBody,mat);
        return result.toTriple();
        
    }
    
    
    //inspired by http://www.euclideanspace.com/maths/geometry/rotations/conversions/matrixToAngle/index.htm
    AxisAngle rotationMatrixToAngles(Matrix rot)
    {
     Triple t = new Triple();
     double angle;
     double epsilon = 0.01;       //allows for rounding errors
     double epsilon2 = 0.1;
     assert(rot.getColumns()==rot.getRows() && rot.getRows()==3);//rotation matrix is always 3x3
     double m[][] = rot.getMatrix();
     
     //testing for singularity - by euclideanspace
     if ((Math.abs(m[0][1]-m[1][0])< epsilon)
	  && (Math.abs(m[0][2]-m[2][0])< epsilon)
	  && (Math.abs(m[1][2]-m[2][1])< epsilon)) {
		// singularity found
		// first check for identity matrix which must have +1 for all terms
		//  in leading diagonaland zero in other terms
		if ((Math.abs(m[0][1]+m[1][0]) < epsilon2)
		  && (Math.abs(m[0][2]+m[2][0]) < epsilon2)
		  && (Math.abs(m[1][2]+m[2][1]) < epsilon2)
		  && (Math.abs(m[0][0]+m[1][1]+m[2][2]-3) < epsilon2)) {
			// this singularity is identity matrix so angle = 0
			return new AxisAngle(0,new Triple(1,0,0)); // zero angle, arbitrary axis
		}
		// otherwise this singularity is angle = 180
		angle = Math.PI;
     double xx = (m[0][0]+1)/2;
		double yy = (m[1][1]+1)/2;
		double zz = (m[2][2]+1)/2;
		double xy = (m[0][1]+m[1][0])/4;
		double xz = (m[0][2]+m[2][0])/4;
		double yz = (m[1][2]+m[2][1])/4;
		if ((xx > yy) && (xx > zz)) 
                { // m[0][0] is the largest diagonal term
			if (xx< epsilon) 
                        {
				t.setX(0);
				t.setY(0.7071);
				t.setZ(0.7071);
			} 
                        else
                        {
                                t.setX(Math.sqrt(xx));
				t.setY(xy/t.getX());
				t.setZ(xy/t.getZ());
			}
		} else if (yy > zz)
                { // m[1][1] is the largest diagonal term
			if (yy< epsilon)
                        {
                                t.setX(0.7071);
				t.setY(0);
				t.setZ(0.7071);
			}
                        else
                        {
                                t.setX(xy/t.getY());
				t.setY(Math.sqrt(yy));
				t.setZ(yz/t.getY());
			}	
		} 
                else 
                { // m[2][2] is the largest diagonal term so base result on this
			if (zz< epsilon) 
                        {
                                t.setX(0.7071);
				t.setY(0.7071);
				t.setZ(0);
			} 
                        else 
                        {
                                t.setX(xz/t.getZ());
				t.setY(yz/t.getZ());
				t.setZ(Math.sqrt(zz));
			}
		}
		return new AxisAngle(angle,new Triple(t.getX(), t.getY(), t.getZ())); // return 180 deg rotation
	}
     	// there are no singularities so we can handle normally
	double s = Math.sqrt((m[2][1] - m[1][2])*(m[2][1] - m[1][2])
		+(m[0][2] - m[2][0])*(m[0][2] - m[2][0])
		+(m[1][0] - m[0][1])*(m[1][0] - m[0][1])); // used to normalise
	if (Math.abs(s) < 0.001) s=1; 
		// prevents division by zero, should not happen if matrix is orthogonal and should be
		// caught by singularity test above, but I've left it in just in case
	angle = Math.acos(( m[0][0] + m[1][1] + m[2][2] - 1)/2);
	t.setX((m[2][1] - m[1][2])/s);
	t.setY((m[0][2] - m[2][0])/s);
	t.setZ((m[1][0] - m[0][1])/s);
  

     AxisAngle result = new AxisAngle(angle,t);
     return result;
    }
}
