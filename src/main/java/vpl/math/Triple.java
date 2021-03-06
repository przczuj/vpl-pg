    /*
     * To change this template, choose Tools | Templates
     * and open the template in the editor.
     */
    package vpl.math;
     
    import lombok.Getter;
    import lombok.Setter;
     
    /**
     *
     * @author kppx
     */
    public class Triple {
     
        @Getter @Setter private double x, y, z;
     
        public Triple() {
            this.x = 0;
            this.y = 0;
            this.z = 0;
        }
     
        public Triple(double a, double b, double c) {
            this.x = a;
            this.y = b;
            this.z = c;
        }
     
        public double getLength() {
            double l;
            l = Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2);//, l)
            l = Math.sqrt(l);
            return l;
        }

        public void setXYZ(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

          public double getDistance(Triple other) {
            double dx=this.x-other.x;
            double dy=this.y-other.y;
            double dz=this.z-other.z;
            double distance=Math.sqrt(dx*dx+dy*dy+dz*dz);
            return distance;
         }

        public Triple getDistance(Triple a, Triple b) {
            Triple t = new Triple();
            t.setX(a.getX()-b.getX());
            t.setX(a.getY()-b.getY());
            t.setX(a.getZ()-b.getZ());
            return t;
        }
       
        //to unit length
        public Triple normalize()
        {
            double magnitude = Math.sqrt(x*x+y*y+z*z);
            Triple result = new Triple(x/magnitude,y/magnitude,z/magnitude);
            return result;
        }
       
           
        public Matrix toMatrix() {
            Matrix mat = new Matrix(3, 1);
            mat.setValueAt(0, 0, getX());
            mat.setValueAt(1, 0, getY());
            mat.setValueAt(2, 0, getZ());
            return mat;
        }
       
       
        public Matrix toMatrixForRotation()
        {
         Matrix matrix = new Matrix(3,3);
         matrix.setValueAt(0, 1, -1*z);
         matrix.setValueAt(0, 2, y);
         matrix.setValueAt(1, 0, z);
         matrix.setValueAt(1, 2, -1*x);
         matrix.setValueAt(2, 0, -1*y);
         matrix.setValueAt(2, 1, x);
         
         
         return matrix;
        }
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("X: " + this.x + " Y: " + this.y + " Z: " + this.z);
            return sb.toString();
        }
     
        public void print() {
            System.out.println(toString());
        }
    }
