public class DeadlockTest {
    public static void main(String[] args) {
        final String res1 = "my  text";
        final String res2 = "alien text";


        Thread thread1 = new Thread() {
            public void run() {
                synchronized (res1) {
                    System.out.println("thread1 locks res1");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                    }

                    synchronized (res2) {
                        System.out.println("thread1 locks res2");
                    }
                }
            }
        };


        Thread thread2 = new Thread() {
            public void run() {
                synchronized (res2) {
                    System.out.println("thread2 locks res2");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                    synchronized (res1) {
                        System.out.println("thread2 locks res1");
                    }
                }
            }
        };

        thread1.start();
        thread2.start();

    }
}  