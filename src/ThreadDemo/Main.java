package ThreadDemo;

import java.util.concurrent.ArrayBlockingQueue;

public class Main {
    public static ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(100,true);
    public static void main(String[] args) throws InterruptedException {
        Thread pro = new Thread(producer);
        Thread con = new Thread(consumer);
        pro.start();
        con.start();
        pro.join();
        con.join();
    }

    private static Runnable producer = () -> {
        System.out.println("即将开始生产");
        try {
            Thread.sleep(2000);
            for (int i = 0; i < 1000; i++) {
                System.out.println(Thread.currentThread().getId()+"生产："+i);
                queue.put(i);
            }
            System.out.println("生产完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };

    private static Runnable consumer = () -> {
        System.out.println("即将开始消费");
        try {
            for (int i = 0; i < 1000; i++) {
                int s = queue.take();
                System.out.println(Thread.currentThread().getId()+"消费："+i);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    };
}
