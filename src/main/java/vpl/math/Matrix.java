/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//partially inspired by http://www.codeproject.com/Articles/405128/Matrix-operations-in-Java
package vpl.math;

import vpl.math.Triple;
import javax.swing.JOptionPane;
import lombok.Getter;
import lombok.Setter;
import vpl.physics.AxisAngle;

/**
 *
 * @author kppx
 */
public class Matrix {

    @Getter
    @Setter
    private int columns;
    @Getter
    @Setter
    private int rows;
    @Getter
    @Setter
    private double[][] matrix;

    private boolean isSquare() {
        if (this.rows == this.columns) {
            return true;
        }
        return false;
    }

    public double determinant(Matrix matrix) throws NoSquareException {
        if (!matrix.isSquare()) {
            throw new NoSquareException("matrix need to be square.");
        }
        if (matrix.getColumns() == 1) {
            return matrix.getValueAt(0, 0);
        }
        if (matrix.getColumns() == 2) {
            return (matrix.getValueAt(0, 0) * matrix.getValueAt(1, 1)) - (matrix.getValueAt(0, 1) * matrix.getValueAt(1, 0));
        }
        if (matrix.getColumns() == 3) {
            return ((matrix.getValueAt(0, 0) * matrix.getValueAt(1, 1) * matrix.getValueAt(2, 2)) + (matrix.getValueAt(0, 1) * matrix.getValueAt(1, 2) * matrix.getValueAt(0, 2)) + (matrix.getValueAt(2, 0) * matrix.getValueAt(0, 1) * matrix.getValueAt(1, 2))
                    - (matrix.getValueAt(0, 0) * matrix.getValueAt(2, 1) * matrix.getValueAt(1, 2)) - (matrix.getValueAt(0, 1) * matrix.getValueAt(1, 0) * matrix.getValueAt(2, 2)) - (matrix.getValueAt(0, 2) * matrix.getValueAt(1, 1) * matrix.getValueAt(2, 0)));
        }
        double sum = 0.0;
        for (int i = 0; i < matrix.getColumns(); i++) {
            sum += (-1) * (i) * matrix.getValueAt(0, i) * determinant(createSubMatrix(matrix, 0, i));
        }

        return sum;
    }

    public Matrix anglesToRotationMatrix(AxisAngle angles) {
        Matrix matrix = new Matrix(3, 3);
        double c = Math.cos(Math.toRadians(angles.getAngle()));
        double s = Math.sin(Math.toRadians(angles.getAngle()));

        double t = 1.0 - c;
        //time to scale
        double magnitude = //2.0 * Math.PI / 360.0;
                Math.sqrt(angles.getAngles().getX() * angles.getAngles().getX()
                        + angles.getAngles().getY() * angles.getAngles().getY() + angles.getAngles().getZ() * angles.getAngles().getZ());

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

    public Matrix createSubMatrix(Matrix matrix, int excludingRow, int excludingColumn) {
        Matrix mat = new Matrix(matrix.getRows() - 1, matrix.getColumns() - 1);
        int r = -1;
        for (int i = 0; i < matrix.getRows(); i++) {
            if (i == excludingRow) {
                continue;
            }
            r++;
            int c = 0;
            for (int j = 0; j < matrix.getColumns(); j++) {
                if (j == excludingColumn) {
                    continue;
                }
                mat.setValueAt(r, c, matrix.getValueAt(i, j));
                c++;
            }
        }
        return mat;
    }

    private int changeSign(int i) {
        if (i % 2 == 0) {
            return -1;
        }
        return 1;
    }

    public Matrix cofactor(Matrix matrix) throws NoSquareException {
        Matrix mat = new Matrix(matrix.getRows(), matrix.getColumns());
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++) {
                mat.setValueAt(i, j, changeSign(i + j) * determinant(createSubMatrix(matrix, i, j)));
            }
        }

        return mat;
    }

    public Matrix inverse(Matrix matrix) throws NoSquareException {
        Matrix mat = transpose(cofactor(matrix));
        BasicMath mathLogic = new BasicMath();
        double test = (1.0 / determinant(matrix));
        return mathLogic.multiplyByScalar((1.0 / determinant(matrix)), mat);
    }

    public Matrix(double[][] matr) {
        this.matrix = matr;
        this.rows = matr.length;
        this.columns = matr[0].length;
    }

    public Matrix(int a, int b) {
        this.rows = a;
        this.columns = b;
        matrix = new double[rows][columns];
    }

    public double getValueAt(int i, int j) {
        return this.matrix[i][j];
    }

    public void setValueAt(int i, int j, double val) {
        this.matrix[i][j] = val;
    }

    public Matrix transpose(Matrix original) {
        Matrix transposedMatrix = new Matrix(original.getColumns(), original.getRows());
        for (int i = 0; i < original.getRows(); i++) {
            for (int j = 0; j < original.getColumns(); j++) {

                transposedMatrix.setValueAt(j, i, original.getValueAt(i, j));
            }
        }
        return transposedMatrix;
    }

    public Triple toTriple() {
        assert (getRows() == 3 && getColumns() == 1);
        Triple t = new Triple();
        t.setX(this.getValueAt(0, 0));
        t.setY(this.getValueAt(1, 0));
        t.setZ(this.getValueAt(2, 0));
        return t;
    }

    public double sumOfDiagonal() {
        int i = 0;
        double result = 0;
        while (i < rows && i < columns) {
            result += getValueAt(i, i);
        }
        return result;
    }

    private class NoSquareException extends Exception {

        private NoSquareException(String str) {

            JOptionPane.showMessageDialog(null,
                    str,
                    "Error",
                    JOptionPane.INFORMATION_MESSAGE,
                    null);
        }
    }
}
