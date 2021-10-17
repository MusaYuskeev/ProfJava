import java.util.concurrent.atomic.AtomicInteger;

public class Atom {
    static int transNum = 10000;
    static int threadNum = 10;

    private static int value = 0;
    private static AtomicInteger atomic = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Runnable transaction = new Runnable() {
            @Override
            public void run() {
                try {

                    for (int i = 0; i < transNum; i++) {
                        value++;
                        atomic.incrementAndGet();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Thread[] threads = new Thread[threadNum];
        for (int i = 0; i < threadNum; i++) {
            threads[i] = new Thread(transaction);
            threads[i].start();
        }

        //начинаем ждать завершения всех нитей
        for (int i = 0; i < threads.length; i++) {
            Thread thread = threads[i];
            thread.join();
        }

        System.out.println("Ordinary int " + value);
        System.out.println("  Atomic int " + atomic.get());
    }
}