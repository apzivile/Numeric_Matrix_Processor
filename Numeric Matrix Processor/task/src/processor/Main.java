package processor;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        help();
    }

    public static void help() {
        Scanner scanner = new Scanner(System.in);
        int type = 1;
        while (type != 0) {
            System.out.println("1. Add matrices");
            System.out.println("2. Multiply matrix to a constant");
            System.out.println("3. Multiply matrices");
            System.out.println("4. Transpose matrix");
            System.out.println("0. Exit");
            System.out.print("Your choice: ");
            type = scanner.nextInt();
            switch (type) {
                case 1:
                    String[][] filledMatrix1 = inputMatrix("first");
                    String[][] filledMatrix2 = inputMatrix("second");
                    displayMatrix(matrixAddition(filledMatrix1, filledMatrix2));
                    System.out.println();
                    break;
                case 2:
                    filledMatrix1 = inputMatrix("");
                    displayMatrix(multiplyByNumber(filledMatrix1));
                    System.out.println();
                    break;
                case 3:
                    filledMatrix1 = inputMatrix("first");
                    filledMatrix2 = inputMatrix("second");
                    displayMatrix(multiplyMatrices(filledMatrix1, filledMatrix2));
                    System.out.println();
                    break;
                case 4:
                    String transposeType;
                    System.out.println();
                    System.out.println("1. Main diagonal");
                    System.out.println("2. Side diagonal");
                    System.out.println("3. Vertical line");
                    System.out.println("4. Horizontal line");
                    System.out.print("Your choice: ");
                    int tranType = scanner.nextInt();
                    filledMatrix1 = inputMatrix("");
                    switch (tranType) {
                        case 1:
                            transposeType = "main";
                            displayMatrix(transpose(filledMatrix1, transposeType));
                            System.out.println();
                            break;
                        case 2:
                            transposeType = "side";
                            displayMatrix(transpose(filledMatrix1, transposeType));
                            System.out.println();
                            break;
                        case 3:
                            transposeType = "vert";
                            displayMatrix(transpose(filledMatrix1, transposeType));
                            System.out.println();
                            break;
                        case 4:
                            transposeType = "horiz";
                            displayMatrix(transpose(filledMatrix1, transposeType));
                            System.out.println();
                            break;
                    }

            }
        }
    }

    public static String[][] inputMatrix(String number) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter size of " + number + " matrix: ");
        int rows = scanner.nextInt();
        int columns = scanner.nextInt();
        String[][] matrix = new String[rows][columns];
        System.out.println("Enter " + number + " matrix: ");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = scanner.next();
            }
        }
        return matrix;
    }

    public static double[][] transpose(String[][] matrix1, String type) {
        System.out.println("The result is: ");
        double[][] resultMatrix = new double[matrix1.length][matrix1[0].length];
        if ("main".equals(type)) {// main diagonal transpose
            for (int i = 0; i < matrix1.length; i++) {
                for (int j = 0; j < matrix1[i].length; j++) {
                    resultMatrix[i][j] = Double.parseDouble(matrix1[j][i]);
                }
            }
            return resultMatrix;
        }
        if ("side".equals(type)) {//side diagonal transpose
            int r = 0;
            for (int i = matrix1.length - 1; i >= 0; i--) {
                int c = 0;
                for (int j = matrix1[i].length - 1; j >= 0; j--) {
                    resultMatrix[r][c] = Double.parseDouble(matrix1[j][i]);
                    if (c < matrix1[i].length) {
                        c++;
                    }
                }
                if (r < matrix1.length) {
                    r++;
                }
            }
            return resultMatrix;
        }
        if ("vert".equals(type)) {// vertical transpose
            for (int i = 0; i < matrix1.length; i++) {
                int c = 0;
                for (int j = matrix1[i].length - 1; j >= 0; j--) {
                    resultMatrix[i][c] = Double.parseDouble(matrix1[i][j]);
                    if (c < matrix1[i].length) {
                        c++;
                    }
                }
            }
            return resultMatrix;
        }
        if ("horiz".equals(type)) {//horizontal transpose
            int r = 0;
            for (int i = matrix1.length - 1; i >= 0; i--) {
                for (int j = 0; j < matrix1[i].length; j++) {
                    resultMatrix[r][j] = Double.parseDouble(matrix1[i][j]);
                }
                if (r < matrix1.length) {
                    r++;
                }
            }
        }
        return resultMatrix;
    }

    public static double[][] matrixAddition(String[][] matrix1, String[][] matrix2) {
        double[][] resultMatrix = new double[0][];
        if (matrix1.length == matrix2.length && matrix1[0].length == matrix2[0].length) {
            resultMatrix = new double[matrix1.length][matrix1[0].length];
            for (int i = 0; i < matrix1.length; i++) {
                for (int j = 0; j < matrix1[i].length; j++) {
                    resultMatrix[i][j] = Double.parseDouble(matrix1[i][j]) + Double.parseDouble(matrix2[i][j]);
                }
            }
        }
        return resultMatrix;
    }

    public static double[][] multiplyByNumber(String[][] matrix1) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        double[][] resultMatrix = new double[matrix1.length][matrix1[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[i].length; j++) {
                resultMatrix[i][j] = Integer.parseInt(matrix1[i][j]) * number;
            }
        }
        return resultMatrix;
    }

    public static double[][] multiplyMatrices(String[][] matrix1, String[][] matrix2) {
        System.out.println("The multiplication result is: ");
        double[][] resultMatrix = new double[0][];
        if (matrix1[0].length == matrix2.length) {
            resultMatrix = new double[matrix1.length][matrix2[0].length];
            for (int i = 0; i < matrix1.length; i++) {
                for (int j = 0; j < matrix2[i].length; j++) {
                    for (int k = 0; k < matrix1[i].length; k++) {
                        resultMatrix[i][j] += (Double.parseDouble(matrix1[i][k]) * Double.parseDouble(matrix2[k][j]));
                    }
                }
            }
        }
        return resultMatrix;
    }

    public static void displayMatrix(double[][] matrix) {
        if (Arrays.deepEquals(matrix, new double[0][])) {
            System.out.println("ERROR");
        } else {
            for (double[] ints : matrix) {
                for (double anInt : ints) {
                    System.out.print(" " + anInt);
                }
                System.out.println();
            }
        }
    }
}
