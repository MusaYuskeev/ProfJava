package jd.consumers.vol;

/**
 * Created by jdev on 12.06.2017.
 */
public class StartExchange {
    //   static int counter = 0;
    static volatile int counter = 0;

    public static void main(String[] args) {

        new Producer().start();
        new Consumer().start();
    }
}
