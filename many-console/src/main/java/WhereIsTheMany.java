import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.MAX_PRIORITY;
import static java.lang.Thread.MIN_PRIORITY;

/**
 * Created by jdev on 04.06.2017.
 */
public class WhereIsTheMany {
    //   static Integer account = 0;
    static AtomicInteger account = new AtomicInteger(0);
    static int transNum = 10000;
    static int threadNum = 6;

    public static void main(String... args) throws InterruptedException {
        Runnable transaction = new Runnable() {
            @Override
            public void run() {
                try {

                    System.out.println(Thread.currentThread().getName() + " start");
                    if (Math.random() * 10 / 2 > 2) {
                        System.out.println(Thread.currentThread().getName() + " MIN_PRIORITY");
                        Thread.currentThread().setPriority(MIN_PRIORITY);
                    } else if (Math.random() * 10 / 2 < 1) {
                        System.out.println(Thread.currentThread().getName() + " MAX_PRIORITY");
                        Thread.currentThread().setPriority(MAX_PRIORITY);
                    }
                    System.out.println(Thread.currentThread().getName() + " start sleep");
                    Thread.sleep(300);
                    System.out.println(Thread.currentThread().getName() + " end");
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                System.out.println(Thread.currentThread().getName());
                for (int i = 0; i < transNum; i++) {
//                        WhereIsTheMany.account++;
                    WhereIsTheMany.account.incrementAndGet();
                }

            }
        };

        //создаем заданное число нитей, и запускаем их на выполнение
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
        //если мы здесь то значит все нити завершили выполнение, выводим результат
        System.out.println("account = [" + account + "]" + " must be = [" + transNum * threadNum + "]");

        //удивляемся если разница не равна нулю
        if (transNum * threadNum - account.get() != 0)
            System.out.println("where is my : " + (transNum * threadNum - account.get()) + "$ !!!!!");
//        if (transNum*threadNum - account != 0)
//            System.out.println("where is my : " + (transNum*threadNum - account) + "$ !!!!!");
    }
}
