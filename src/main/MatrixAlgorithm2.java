package main;

import java.util.Scanner;

import static main.Main.buildMatrix;
import static main.Main.display2DArray;

public class MatrixAlgorithm2 {

    public static final int COORDINATES_LENGTH = 6;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your string: ");
        String string = scanner.nextLine();

        System.out.print("Enter your word: ");
        String word = scanner.nextLine();

        int length = string.length();
        int sqrt = (int) Math.sqrt(length);

        if (sqrt * sqrt != length) {
            System.out.println("Wrong String!");
            System.exit(0);
        }

        char[][] matrix = buildMatrix(string, sqrt);

        display2DArray(matrix);
        StringBuilder stringBuilder = new StringBuilder();
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == word.charAt(0)) {
                    stringBuilder = checkNeighbors(matrix, row, col, word, 0, stringBuilder);

                }
            }
        }

        int resultStringCoordinatesLength = COORDINATES_LENGTH * word.length();

        // Check if there are enough coordinates in the result string
        if (!(stringBuilder.length() < resultStringCoordinatesLength)) {

            // There is possible more than one way to find word in the matrix.
            // If yes, I leave only firs way.
            stringBuilder.delete(resultStringCoordinatesLength, stringBuilder.length());
            System.out.println(stringBuilder);
        } else {
            System.out.println("There is no word " + word + " from current string.");
        }


    }

    public static StringBuilder checkNeighbors(char[][] matrix, int row, int col, String word, int charPosition, StringBuilder stringBuilder) {
        int n = matrix.length;

        if (charPosition < word.length() - 1) {
            charPosition++;
        } else {
            return stringBuilder.append("[").append(row).append(", ").append(col).append("]");
        }
        char nextChar = word.charAt(charPosition);

        // Check the left element (if exists)
        if (col - 1 >= 0 && matrix[row][col - 1] == nextChar) {

            stringBuilder.append("[").append(row).append(", ").append(col).append("]");
            checkNeighbors(matrix, row, col - 1, word, charPosition, stringBuilder);
            return stringBuilder;
        }


        // Check the right element (if exists)
        if (col + 1 < n && matrix[row][col + 1] == nextChar) {

            stringBuilder.append("[").append(row).append(", ").append(col).append("]");
            checkNeighbors(matrix, row, col + 1, word, charPosition, stringBuilder);
            return stringBuilder;
        }

        // Check the top element (if exists)
        if (row - 1 >= 0 && matrix[row - 1][col] == nextChar) {

            stringBuilder.append("[").append(row).append(", ").append(col).append("]");
            checkNeighbors(matrix, row - 1, col, word, charPosition, stringBuilder);
            return stringBuilder;
        }

        // Check the bottom element (if exists)
        if (row + 1 < n && matrix[row + 1][col] == nextChar) {
            stringBuilder.append("[").append(row).append(", ").append(col).append("]");
            checkNeighbors(matrix, row + 1, col, word, charPosition, stringBuilder);
            return stringBuilder;
        }
        return stringBuilder;
    }
}
