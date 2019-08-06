package edu.epam.training.task3.entity;

import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class NumberSetter extends Thread{

    private Matrix matrix;
    private ReentrantLock locker;
    private int number;
    final Logger logger = Logger.getLogger(NumberSetter.class);


    public NumberSetter(Matrix matrix, ReentrantLock lock, int number) {
        this.matrix = matrix;
        this.locker = lock;
        this.number = number;
    }

    @Override
    public void run() {

        int[][] m = matrix.getMatrix();
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (i == j && m[i][j] == 0){
                    locker.tryLock();
                    if (m[i][j] == 0) {
                        matrix.setElement(number, i, j);
                        logger.info(getName() + " sets the number " + number
                                + " in position " + i + "-" + j);
                        try {
                            TimeUnit.MILLISECONDS.sleep(200);

                        } catch (InterruptedException e) {
                            logger.error(getName() + " couldn't set its number.");
                            logger.error(e.toString());
                        } finally {
                            if (locker.isHeldByCurrentThread()) {
                                locker.unlock();
                            }
                        }
                    }
                }
            }
        }
    }

}
