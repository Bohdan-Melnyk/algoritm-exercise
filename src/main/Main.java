package main;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your string: ");
        String string = scanner.nextLine();

        System.out.print("Enter your word: ");
        String word = scanner.nextLine();

        int length = string.length();
        int N = (int) Math.sqrt(length);

        if (N * N != length) {
            System.out.println("Wrong String!");
            System.exit(0);
        }
        char[][] matrix = buildMatrix(string, N);
        display2DArray(matrix);

        Map<Character, List<String>> mapOfCharacterCoordinates = getMapOfCharacterCoordinates(string, matrix);

        String result = getResult(word, mapOfCharacterCoordinates);

        System.out.println(result);
    }

    public static char[][] buildMatrix(String str, int n) {
        char[][] matrix = new char[n][n];
        for (int i = 0; i < str.length(); i++) {
            matrix[i / n][i % n] = str.charAt(i);
        }
        return matrix;
    }

    public static void display2DArray(char[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static String checkIfMatrixContainsSymbols(char[][] matrix, String s) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (s.contains(Character.toString(matrix[i][j]))){
                    stringBuilder.append(matrix[i][j]);
                }
            }
        }
        return stringBuilder.toString();
    }

    public static Map<Character, List<String>> getMapOfCharacterCoordinates(String string, char[][] matrix) {
        Map<Character, List<String>> map = new HashMap<>();
        for (int i = 0; i < string.length(); i++) {
            if (map.containsKey(string.charAt(i))) {
                map.get(string.charAt(i)).add(convertToCoordinates(i, string.length()));
            } else {
                LinkedList<String> coordinates = new LinkedList<>();
                coordinates.add(convertToCoordinates(i, string.length()));
                map.put(string.charAt(i), coordinates);
            }
        }
        return map;
    }

    public static String getResult(String str, Map<Character, List<String>> map) {
        StringBuilder stringBuilder = new StringBuilder("Coordinates by characters step by step: ");
        for (int i = 0; i < str.length(); i++) {
            if (map.containsKey(str.charAt(i))) {
                String first = map.get(str.charAt(i)).getFirst();
                stringBuilder.append(first);
            } else {
                return "It is not possible to create word your word from given string.";
            }
        }
        return stringBuilder.append(".").toString();
    }

    private static String convertToCoordinates(int i, int length) {
        return "[" + i / (int) Math.sqrt(length) + " " + i % (int) Math.sqrt(length) + "]";
    }
}
