package 重入锁;

import java.util.concurrent.locks.Lock;

public class SynchronizedDemo {
    private static final Integer i = 0;
    public static void main(String[] args) {
        String s = "123";
        s.notify();
    }
}
