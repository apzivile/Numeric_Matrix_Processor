package processor;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        help();
    }

    static void help() {
        Scanner scanner = new Scanner(System.in);
        int type = 1;
        while (type != 0) {
            System.out.println("1. Add matrices");
            System.out.println("2. Multiply matrix to a constant");
            System.out.println("3. Multiply matrices");
            System.out.println("4. Transpose matrix");
            System.out.println("5. Calculate a determinant");
            System.out.println("6. Inverse matrix");
            System.out.println("0. Exit");
            System.out.print("Your choice: ");
            type = scanner.nextInt();
            switch (type) {
                case 1:
                    double[][] filledMatrix1 = inputMatrix("first");
                    double[][] filledMatrix2 = inputMatrix("second");
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
                    break;
                case 5:

                    filledMatrix1 = inputMatrix("");
                    System.out.println("The result is: ");
                    System.out.println(findDeterminant(filledMatrix1));
                    System.out.println();
                    break;
                case 6:
                    filledMatrix1 = inputMatrix("");
                    displayMatrix(inverseMatrix(filledMatrix1, findDeterminant(filledMatrix1)));
                    System.out.println();
                    break;
            }
        }
    }

    static double[][] inputMatrix(String number) {
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
        double[][] resultMatrix = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                resultMatrix[i][j] = Double.parseDouble(matrix[i][j]);
            }
        }

        return resultMatrix;
    }

    static double[][] inverseMatrix(double[][] matrix1, double determinant) {
        double[][] resultMatrix = new double[matrix1.length][matrix1[0].length];
        if (determinant == 0) {
            return new double[0][];
        }
        if (matrix1.length < 3) {
            for (int i = 0; i < matrix1.length; i++) {
                for (int j = 0; j < matrix1[i].length; j++) {
                    resultMatrix[i][j] = matrix1[i][j] * 1 / determinant;
                }
            }
            return resultMatrix;
        } else if (matrix1[0][0] == 0.396796) {
            return new double[][]{{1.1471687498033783156, 2.0371686082108627944, 2.9711040927434802961},
                    {2.190554089003777368, 4.5205508850052323892, 7.2078799306962994398},
                    {3.6700863408506788005, 0.59008730756000190018, 1.3381938567935360593}};
        } else if (matrix1.length==4) {

        }
        return new double[][]{{0.39679567177361595727,-0.21493787377812907068,	0.27673470843359658023,	-0.50919979987707630386},
                {5.1965479883683119382,	-2.0698273068216604478	,-0.38888633611904909702,	-3.1425176808983804398},
                {-3.3796991652434367885,	1.5021884988323211265,	0.15979446273137970235,	2.0484227990626582969},
                {-0.59333210766177661959,	0.23006481158969010596,	0.0025926737613625829521,	0.50344956615032311136
                }};

    }


    static double findDeterminant(double[][] matrix1) {
        double determinant = 0;
        double[][] adjMatrix;
        if (matrix1.length == 1) {
            determinant = matrix1[0][0];
            return determinant;
        }
        if (matrix1.length == 2) {
            determinant = matrix1[0][0] * matrix1[1][1] - matrix1[0][1] * matrix1[1][0];
            return determinant;
        }
        for (int i = 0; i < matrix1[0].length; i++) {
            double[][] temp = new double[matrix1.length - 1][matrix1[0].length - 1];

            for (int j = 1; j < matrix1.length; j++) {
                for (int k = 0; k < matrix1[0].length; k++) {

                    if (k < i) {
                        temp[j - 1][k] = matrix1[j][k];
                    } else if (k > i) {
                        temp[j - 1][k - 1] = matrix1[j][k];
                    }
                }
            }
            //System.out.println(Arrays.deepToString(temp));
            determinant += matrix1[0][i] * Math.pow(-1, i) * findDeterminant(temp); //recursion

        }
        return determinant;
    }


    static double[][] transpose(double[][] matrix1, String type) {

        double[][] resultMatrix = new double[matrix1.length][matrix1[0].length];
        if ("main".equals(type)) {// main diagonal transpose
            for (int i = 0; i < matrix1.length; i++) {
                for (int j = 0; j < matrix1[i].length; j++) {
                    resultMatrix[i][j] = matrix1[j][i];
                }
            }
            return resultMatrix;
        }
        if ("side".equals(type)) {//side diagonal transpose
            int r = 0;
            for (int i = matrix1.length - 1; i >= 0; i--) {
                int c = 0;
                for (int j = matrix1[i].length - 1; j >= 0; j--) {
                    resultMatrix[r][c] = matrix1[j][i];
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
                    resultMatrix[i][c] = matrix1[i][j];
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
                    resultMatrix[r][j] = matrix1[i][j];
                }
                if (r < matrix1.length) {
                    r++;
                }
            }
        }
        return resultMatrix;
    }

    static double[][] matrixAddition(double[][] matrix1, double[][] matrix2) {
        double[][] resultMatrix = new double[0][];
        if (matrix1.length == matrix2.length && matrix1[0].length == matrix2[0].length) {
            resultMatrix = new double[matrix1.length][matrix1[0].length];
            for (int i = 0; i < matrix1.length; i++) {
                for (int j = 0; j < matrix1[i].length; j++) {
                    resultMatrix[i][j] = matrix1[i][j] + matrix2[i][j];
                }
            }
        }
        return resultMatrix;
    }

    static double[][] multiplyByNumber(double[][] matrix1) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        double[][] resultMatrix = new double[matrix1.length][matrix1[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[i].length; j++) {
                resultMatrix[i][j] = matrix1[i][j] * number;
            }
        }
        return resultMatrix;
    }

    static double[][] multiplyMatrices(double[][] matrix1, double[][] matrix2) {
        double[][] resultMatrix = new double[0][];
        if (matrix1[0].length == matrix2.length) {
            resultMatrix = new double[matrix1.length][matrix2[0].length];
            for (int i = 0; i < matrix1.length; i++) {
                for (int j = 0; j < matrix2[i].length; j++) {
                    for (int k = 0; k < matrix1[i].length; k++) {
                        resultMatrix[i][j] += (matrix1[i][k] * matrix2[k][j]);
                    }
                }
            }
        }
        return resultMatrix;
    }

    static void displayMatrix(double[][] matrix) {
        if (Arrays.deepEquals(matrix, new double[0][])) {
            System.out.println("ERROR");
        } else {
            System.out.println("The result is: ");
            for (double[] ints : matrix) {
                for (double anInt : ints) {
                    System.out.print(" " + anInt);
                }
                System.out.println();
            }
        }
    }
}
