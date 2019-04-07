package pl.sda.intermediate16;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ThreadsExample {
    @Test
    void runnableBasics() {

        Runnable runnable = () -> System.out.println(Thread.currentThread().getName() + " Lambda runnable");   // lambda
        Runnable anonymousRunnable = new Runnable() {                               // klasa anonimowa
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " anonymous runnable");
            }
        };
        Runnable ourRunnable = new OurRunnable();      // zwykła klasa,

        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread thread2 = new Thread(anonymousRunnable);
        thread2.start();
        Thread thread3 = new Thread(ourRunnable);
        thread3.start();
    }

    @Test
    void bankSequential() {
        for (int i = 0; i < 100; i++) {
            Runnable car = new ClientActionRunnable();
            car.run();
        }
    }


    @Test
    void bankWithThreads() {
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Runnable car = new ClientActionRunnable();
            Thread thread = new Thread(car);
            thread.start();
            threadList.add(thread);
        }
        threadList.forEach(t->{
                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });

        System.out.println("Liczba operacji" + Bank.counter);


    }
}
