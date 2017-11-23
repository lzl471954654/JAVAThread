package 重入锁;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockAndConditionDemo implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new LockAndConditionDemo());
        thread.start();
        Thread.sleep(2000);
        lock.lock();
        condition.signalAll();
        lock.unlock();
    }

    @Override
    public void run() {
        System.out.println("Thread started!");
        lock.lock();
        System.out.println("Thread wait");
        condition.awaitUninterruptibly();
        System.out.println("Thread is going");
        lock.unlock();
    }
}
