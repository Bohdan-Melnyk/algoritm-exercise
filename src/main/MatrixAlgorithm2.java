package main;

import java.util.Scanner;

import static main.Main.buildMatrix;

public class MatrixAlgorithm2 {

    public static final int COORDINATES_LENGTH = 6;

    private static int counter = 0;

    public static void main(String[] args) {
        System.err.println("Type only capital letters please.");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your string: ");
        String string = scanner.nextLine();

        System.out.print("Enter your word: ");
        String word = scanner.nextLine();
        scanner.close();

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
        cutStringIfNecessary(stringBuilder, resultStringCoordinatesLength);
        printResult(stringBuilder, COORDINATES_LENGTH, word);

        // Check if there are enough coordinates in the result string
//        if (/*!(stringBuilder.length() < resultStringCoordinatesLength)*/ stringBuilder.length() > resultStringCoordinatesLength) {
//
//            // There is possible more than one way to find word in the matrix.
//            // If yes, I leave only firs way.
//            stringBuilder.delete(resultStringCoordinatesLength, stringBuilder.length());
//            System.out.println(stringBuilder);
//        } else {
//            System.out.println("There is no word " + word + " from current string.");
//        }
    }

    public static void display2DArray(char[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static StringBuilder checkNeighbors(char[][] matrix, int row, int col, String word, int charPosition, StringBuilder stringBuilder) {
        stringBuilder.append("[").append(row).append(", ").append(col).append("]");
        int n = matrix.length;
        counter++;

        if (charPosition < word.length() - 1 /*counter != word.length() - 1*/) {
            charPosition++;
        } else {
            return stringBuilder/*.append("[").append(row).append(", ").append(col).append("]")*/;
        }
        char nextChar = word.charAt(charPosition);

        // Check the left element (if exists)
        if (col - 1 >= 0 && matrix[row][col - 1] == nextChar) {

//            stringBuilder.append("[").append(row).append(", ").append(col).append("]");
            checkNeighbors(matrix, row, col - 1, word, charPosition, stringBuilder);
            if (/*charPosition != word.length() - 1*/counter != word.length() /*- 1*/) {
                counter--;
                stringBuilder.delete(stringBuilder.length() - COORDINATES_LENGTH, stringBuilder.length());
            } /*else {
                return stringBuilder;
            }*/
        }


        // Check the right element (if exists)
        if (col + 1 < n && matrix[row][col + 1] == nextChar) {

//            stringBuilder.append("[").append(row).append(", ").append(col).append("]");
            checkNeighbors(matrix, row, col + 1, word, charPosition, stringBuilder);
            if (/*charPosition != word.length() - 1*/ counter != word.length() /*- 1*/) {
                counter--;
                /*return */stringBuilder.delete(stringBuilder.length() - COORDINATES_LENGTH, stringBuilder.length());
            } /*else {
                return stringBuilder;
            }*/
        }

        // Check the top element (if exists)
        if (row - 1 >= 0 && matrix[row - 1][col] == nextChar) {

//            stringBuilder.append("[").append(row).append(", ").append(col).append("]");
            checkNeighbors(matrix, row - 1, col, word, charPosition, stringBuilder);
            if (/*charPosition != word.length() - 1*/ counter != word.length() /*- 1*/) {
                counter--;
                /*return */stringBuilder.delete(stringBuilder.length() - COORDINATES_LENGTH, stringBuilder.length());
            } /*else {
                return stringBuilder;
            }*/
        }

        // Check the bottom element (if exists)
        if (row + 1 < n && matrix[row + 1][col] == nextChar) {
//            stringBuilder.append("[").append(row).append(", ").append(col).append("]");
            checkNeighbors(matrix, row + 1, col, word, charPosition, stringBuilder);
            if (/*charPosition != word.length() - 1*/ counter != word.length() /*- 1*/) {
                counter--;
                /*return */stringBuilder.delete(stringBuilder.length() - COORDINATES_LENGTH, stringBuilder.length());
            } /*else {
                return stringBuilder;
            }*/
        }
        if (counter == 1) {
            counter--;
            stringBuilder.delete(stringBuilder.length() - COORDINATES_LENGTH, stringBuilder.length());
        }
        return stringBuilder;
    }

    public static StringBuilder back(StringBuilder stringBuilder) {
        stringBuilder.delete(stringBuilder.length() - COORDINATES_LENGTH, stringBuilder.length());
        return stringBuilder;
    }

    private static void cutStringIfNecessary(StringBuilder stringBuilder, int number) {
        if (stringBuilder.length() > number) {
            // There is possible more than one way to find word in the matrix.
            // If yes, I leave only firs way.
            stringBuilder.delete(number, stringBuilder.length());
        }
    }

    private static void printResult(StringBuilder stringBuilder, int number, String word) {
        if (/*!(stringBuilder.length() < resultStringCoordinatesLength)*/ !stringBuilder.isEmpty()) {
            System.out.println(stringBuilder);
        } else {
            System.out.println("There is no word " + word + " from current string.");
        }
    }
}
