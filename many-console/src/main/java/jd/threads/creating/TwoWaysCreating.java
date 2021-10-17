package jd.threads.creating;

/**
 * Created jdev on 07.06.2017.
 */
public class TwoWaysCreating {

    public static void main (String[] args) throws InterruptedException {

        ExtendingThread extending = new ExtendingThread();

        System.out.println("это main который выполняется в нити => " + Thread.currentThread().getName());

        //запуск нити, но это не run()!!!!
        extending.start();

        //дожидаемся завершения
        extending.join();

        //почувствуйте разницу - run() не запускает нить!!!, по-этому название нити тоже 'main'
        extending.run();

        //повторный запуск отработавшей нити - выбросит исключение
//        extending.start();


        ImplementingRunnable implementing = new ImplementingRunnable();

        //просто вызов метода без порождения нового потока
        implementing.run();

        //это порождение потока, без Thread не обойтись
        new Thread(implementing).start();
    }
}
