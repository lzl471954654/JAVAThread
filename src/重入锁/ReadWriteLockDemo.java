package 重入锁;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo  {
    static Lock RLock = new ReentrantLock();
    static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();
    private int value;

    public Integer readOperation(Lock lock) throws InterruptedException{
        try {
            lock.lock();
            Thread.sleep(1000);
            return value;
        }finally {
            lock.unlock();
        }
    }

    public void writeOperation(Lock lock,int newVaule) throws InterruptedException{
        try{
            lock.lock();
            Thread.sleep(1000);
            value = newVaule;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final ReadWriteLockDemo demo = new ReadWriteLockDemo();
        Runnable readRun = ()->{
            try{
                demo.readOperation(readLock);
                //demo.readOperation(RLock);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        };
        Runnable writeRun = ()->{
            try {
                demo.writeOperation(writeLock,new Random().nextInt());
                //demo.writeOperation(RLock,new Random().nextInt());
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        };
        Long start = System.currentTimeMillis();
        for (int i = 0;i<10;i++){
            new Thread(readRun).start();
        }
        Long end = System.currentTimeMillis();
        //System.out.println(end-start);
        for (int i = 0;i<10;i++){
            new Thread(writeRun).start();
        }
        end = System.currentTimeMillis();
        //System.out.println(end-start);
    }
}
