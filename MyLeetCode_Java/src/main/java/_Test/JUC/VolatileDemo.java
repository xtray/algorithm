package _Test.JUC;


public class VolatileDemo {
    // private /*volatile*/ static boolean stop = false;
    private volatile static boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            int i = 0;
            while (!stop) {
                i++;
            }
        });
        thread.start();
        System.out.println("begin start thread");
        Thread.sleep(1000);
        System.out.println("change stop to true");
        stop = true;
    }

}
