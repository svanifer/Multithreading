package edu.epam.training.task3.util;

public class Converter {
    //Класс-конвертер.

    public String[][] convertStringsToArrays(String[] strings){

        String[][] dividedLines = new String[strings.length][];
        for (int i = 0; i < strings.length; i++){
            dividedLines[i] = strings[i].split("\\s+");
        }
        return dividedLines;
    }

    public int[][] convertStringToInt(String[][] list){

        int[][] matrix = new int[list.length][list[0].length];
        try {
            for (int i = 0; i < list.length; i++){
                for (int j = 0; j < list[i].length; j++){
                    matrix[i][j] = Integer.parseInt(list[i][j]);
                }
            }
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        return matrix;
    }

    public int[] convertStringToInt(String[] list){

        int[] numbers = new int[list.length];
        try {
            for (int i = 0; i < list.length; i++){
                numbers[i] = Integer.parseInt(list[i]);
            }
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        return numbers;
    }
}
