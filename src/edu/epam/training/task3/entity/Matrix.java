package edu.epam.training.task3.entity;

public class Matrix {

    private static int[][] matrix;
    private static final Matrix INSTANCE = new Matrix();

    public static Matrix getInstance(){
        return MatrixHolder.INSTANCE;
    }
    private Matrix() {}

    public void setElement(int element, int i, int j){
        matrix[i][j] = element;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (int[] row : matrix) {
            for (int elem : row) {
                sb.append(elem);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private static class MatrixHolder{
        private static final Matrix INSTANCE = new Matrix();
    }
}
