package iteaHW06;

import java.sql.Timestamp;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Cashier implements Runnable {

	private Thread t;
	private volatile boolean isClosed;

	public Cashier() {
		super();
		t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		System.out.println("Cashier open Mcdonalds.");
		while (!isClosed) {
		}

	}

	public synchronized void serveVisitor(String name) {
		Random random = new Random();
		int num = random.nextInt(2) + 1;

		if (!isClosed) {

			if (num == 1) {

				System.out.println("Cashier serve visitor: " + name);
				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				isClosed = true;
				System.out.println("Mcdonalds is close.");
			}
		}
	}
}
