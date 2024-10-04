package main;

import java.util.Scanner;

import static main.Main.buildMatrix;
import static main.Main.display2DArray;

public class Var2 {

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


        int v = (int) Math.sqrt(word.length());
        display2DArray(matrix);
        StringBuilder stringBuilder = new StringBuilder();
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == word.charAt(0)) {
                    stringBuilder = checkNeighbors(matrix, row, col, word, 0, stringBuilder);

                }
            }
        }
        if (!stringBuilder.isEmpty()) {
            System.out.println(stringBuilder);
        } else {
            System.out.println("There is no word " + word + " from current string.");
        }



//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[i].length; j++) {
//                int charPosition = 0;
//                char[] chars = word.toCharArray();
//                if (matrix[i][j] == chars[charPosition]) {
//                    stringBuilder.append("[" + i + " " + j + "]");
//                    if (i - 1 > 0) {
//                        char matrix1 = matrix[i + 1][j];
//                        charPosition++;
//                        if (chars[charPosition] == matrix1) {
//                            stringBuilder.append("[" + i + 1 + " " + j + "]");
//                        }
//                    }
//                    if (matrix.length > i + 1) {
//
//                    }
//                }
//            }
//        }


    }

    public static StringBuilder checkNeighbors(char[][] matrix, int row, int col, String word, int charPosition, StringBuilder stringBuilder) {
        int n = matrix.length; // Size of the square matrix

        if (charPosition < word.length() - 1) {
            charPosition++;
        } else {
            return stringBuilder.append("[").append(row).append(", ").append(col).append("]");
        }
        char currentValue = matrix[row][col];
        char nextChar = word.charAt(charPosition);

        // Check the left element (if exists)
        if (col - 1 >= 0 && matrix[row][col - 1] == nextChar) {

            stringBuilder.append("[").append(row).append(", ").append(col).append("]");
            checkNeighbors(matrix, row, col - 1, word, charPosition, stringBuilder);
//            stringBuilder.delete(stringBuilder.length() - 6, stringBuilder.length());
            return stringBuilder;
        }


        // Check the right element (if exists)
        if (col + 1 < n && matrix[row][col + 1] == nextChar) {

            stringBuilder.append("[").append(row).append(", ").append(col).append("]");
            checkNeighbors(matrix, row, col + 1, word, charPosition, stringBuilder);
//            stringBuilder.delete(stringBuilder.length() - 6, stringBuilder.length());
            return stringBuilder;
        }

        // Check the top element (if exists)
        if (row - 1 >= 0 && matrix[row - 1][col] == nextChar) {

            stringBuilder.append("[").append(row).append(", ").append(col).append("]");
            checkNeighbors(matrix, row - 1, col, word, charPosition, stringBuilder);
//            stringBuilder.delete(stringBuilder.length() - 6, stringBuilder.length());
            return stringBuilder;
        }

        // Check the bottom element (if exists)
        if (row + 1 < n && matrix[row + 1][col] == nextChar) {
            stringBuilder.append("[").append(row).append(", ").append(col).append("]");
            checkNeighbors(matrix, row + 1, col, word, charPosition, stringBuilder);
//            stringBuilder.delete(stringBuilder.length() - 6, stringBuilder.length());
            return stringBuilder;
        }
        return stringBuilder;
    }
}
