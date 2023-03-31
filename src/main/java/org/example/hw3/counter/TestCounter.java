package org.example.hw3.counter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * стартуем 5 потоков, каждый из которых 1_000_000 раз делает инкремент
 */
public class TestCounter {
    private static final int COUNT_THREADS = 5;

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        ExecutorService executorService = Executors.newFixedThreadPool(COUNT_THREADS);

        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start");
            for (int i = 0; i < 1_000_000; i++) {
                counter.increment();
            }
            System.out.println(Thread.currentThread().getName() + " finish");
        });
        System.out.println("Стартуют " + COUNT_THREADS + " потоков ");
        for (int i = 0; i < COUNT_THREADS; i++) {
            executorService.execute(thread);
        }
        executorService.shutdown();

        Thread.sleep(1000);
        System.out.println("Результат инкремента 5 потоков по 1_000_000 раз = " + counter.getNumber());
    }
}
