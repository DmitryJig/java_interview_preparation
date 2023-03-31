package org.example.hw3.pingPong;

public class PingPong {
    private volatile boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        PingPong pingPong = new PingPong();
        Thread threadPing = new Thread(() -> {
            while (true) {
                while (!pingPong.flag) {
                    try {
                        Thread.currentThread().join(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("Ping");
                pingPong.flag = false;
            }
        });
        Thread threadPong = new Thread(() -> {
            while (true) {
                while (pingPong.flag) {
                    try {
                        Thread.currentThread().join(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Pong");
                    pingPong.flag = true;
                }
            }
        });
        threadPing.setDaemon(true);
        threadPong.setDaemon(true);
        threadPing.start();
        threadPong.start();
        Thread.sleep(2000);
    }
}
