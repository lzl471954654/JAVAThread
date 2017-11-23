package 重入锁;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class ReenTrantLockDemo implements Runnable {
    public static ReentrantLock lock = new ReentrantLock(false);
    public static int i = 0;
    public static void main(String[] args) throws InterruptedException {
        ReenTrantLockDemo demo = new ReenTrantLockDemo();
        ReenTrantLockDemo demo1 = new ReenTrantLockDemo();
        Thread thread1 = new Thread(demo,"1");
        Thread thread2 = new Thread(demo1,"2");
        Long start = System.currentTimeMillis();
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("last i :"+i);
        Long end = System.currentTimeMillis();
        System.out.println("Time spending "+(end-start));
    }

    @Override
    public void run() {
        for (int j = 0;j<1000;j++){
            lock.lock();
            try {
                i++;
                System.out.println(Thread.currentThread().getName());
            }finally {
                lock.unlock();
            }
        }
    }
}
