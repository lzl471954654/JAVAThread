package volatileDemo;

public class CountNumber {
    static int number = 0;

    static Runnable runnable = ()->{
        for (int i = 0; i < 2000; i++) {
            number++;
        }
    };

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            Thread thread = new Thread(runnable);
            thread.start();
        }
        for (int i = 0;i < 20;i++){

        }
        System.out.println(number);
    }


}
