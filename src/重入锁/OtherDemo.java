package 重入锁;

import java.util.ArrayList;

public class OtherDemo {
    public static final ArrayList<Integer>  list = new ArrayList<>(10);
    public static void main(String[] args) throws InterruptedException {
        list.add(-1);
        Thread thread = new Thread(read);
        thread.start();
        Thread.sleep(10);
        Thread thread1 = new Thread(write);
        thread1.start();
    }

    static Runnable write = ()->{
        int i = 100;
        while (true){
            list.set(0,i);
            i++;
        }
    };

    static Runnable read = ()->{
        synchronized (list){
            while (true){
                System.out.println(list.get(0));
            }
        }
    };
}
