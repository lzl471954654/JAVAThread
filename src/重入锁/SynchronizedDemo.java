package 重入锁;

public class SynchronizedDemo {
    private static final Integer i = 0;
    public static void main(String[] args) {
        synchronized (i){
            synchronized (i){
                System.out.println("重入");
            }
        }
    }
}
