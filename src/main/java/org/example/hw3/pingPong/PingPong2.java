package org.example.hw3.pingPong;

public class PingPong2 {
    public volatile String msg = "Ping";

    public static void main(String[] args) throws InterruptedException {
        PingPong2 pingPong2 = new PingPong2();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                pingPong2.printPing();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                pingPong2.printPong();
            }
        });
        thread1.setDaemon(true);
        thread2.setDaemon(true);
        thread1.start();
        thread2.start();
        Thread.sleep(1000);
    }

    public synchronized void printPing() {
        while (msg.equals("Ping")) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(msg);
            msg = "Ping";
            notify();
        }
    }

    public synchronized void printPong() {
        while (msg.equals("Pong")) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(msg);
        msg = "Pong";
        notify();
    }
}
