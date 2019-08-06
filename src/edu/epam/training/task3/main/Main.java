package edu.epam.training.task3.main;

import edu.epam.training.task3.entity.Matrix;
import edu.epam.training.task3.entity.NumberSetter;
import edu.epam.training.task3.util.Converter;
import edu.epam.training.task3.util.DataReader;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {

        final Logger logger = Logger.getLogger(Main.class);
        final String FILEMATRIX = "source/mat.txt";
        final String FILETHREAD = "source/tr.txt";
        ReentrantLock locker = new ReentrantLock();
        //BasicConfigurator.configure();

        DataReader reader = new DataReader();
        //logger.info("Reading a data from file for matrix.");
        String[] dataForMatrix = reader.lineRead(FILEMATRIX);
        Converter converter = new Converter();
        String[][] matrixStr = converter.convertStringsToArrays(dataForMatrix);
        int[][] matrixInt = converter.convertStringToInt(matrixStr);
        Matrix matrix = Matrix.getInstance();
        matrix.setMatrix(matrixInt);
        logger.info(matrix.toString());
        //logger.info("Reading a data from file for threads.");
        String[] dataForThreads = reader.lineRead(FILETHREAD);
        int[] numbers = converter.convertStringToInt(dataForThreads);

        Thread[] threads = new Thread[numbers.length];
        for (int i = 0; i < numbers.length; i++){
            Thread thread = new Thread(new NumberSetter(matrix, locker, numbers[i]));
            threads[i] = thread;
            thread.start();
        }

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        logger.info(matrix.toString());
    }
}
