package pl.sda.intermediate16;

import java.util.Random;

public class ClientActionRunnable implements Runnable {
    @Override
    public void run() {
        int i = new Random().nextInt(101);
        Bank.withdraw(i);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Bank.deposit(i);

    }
}
