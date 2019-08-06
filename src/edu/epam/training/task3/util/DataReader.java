package edu.epam.training.task3.util;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DataReader {
    //Класс-reader.

    final static Logger logger = Logger.getLogger(DataReader.class);

    public String[] lineRead(String fileName){
        /* Этот метод читает строки и возвращает массив строк. */

        ArrayList<String> list = new ArrayList();

        try (BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(Paths.get(fileName))))){
            String line;
            while ((line = reader.readLine()) != null){
                list.add(line);
            }
        } catch (FileNotFoundException e) {
            logger.error(e.toString());
        } catch (IOException e) {
            logger.error(e.toString());
        }

        String[] lines = new String[list.size()];
        for (int i = 0; i < list.size(); i++){
            lines[i] = list.get(i);
        }
        return lines;
    }
}
