package SingleDemo;

class Main{
    static Runnable runnable = () -> {
        SafeSingle2 safeSingle3 = SafeSingle2.getInstance();
        System.out.println(safeSingle3.toString());
    };
    public static void main(String[] args) {
        Thread[] threads = new Thread[40];
        for (int i = 0; i < 40; i++) {
            threads[i] = new Thread(runnable);
        }
        for (int i = 0; i < 40; i++) {
            threads[i].start();
        }
    }
}

public class UnSafeSingle {
    private static UnSafeSingle unSafeSingle = null;

    private UnSafeSingle(){}

    public static UnSafeSingle getInstance(){
        if(unSafeSingle == null)
            unSafeSingle = new UnSafeSingle();
        return unSafeSingle;
    }
}

class SafeSingle{
    private static SafeSingle safeSingle = new SafeSingle();

    private SafeSingle(){}

    public static SafeSingle getInstance(){
        return safeSingle;
    }
}

class SafeSingle2{
    private static SafeSingle2 safeSingle2 = null;

    private SafeSingle2(){}

    public static SafeSingle2 getInstance(){
        /*
        * double check
        * */
        if (safeSingle2 == null){
            synchronized (SafeSingle2.class){
                if(safeSingle2 == null)
                    safeSingle2 = new SafeSingle2();
            }
        }
        return safeSingle2;
    }
}

class SafeSingle3{

    private static class InstanceHolder{
        private static SafeSingle3 instance = new SafeSingle3();
    }

    public static SafeSingle3 getInstance() {
        return InstanceHolder.instance;
    }
}
