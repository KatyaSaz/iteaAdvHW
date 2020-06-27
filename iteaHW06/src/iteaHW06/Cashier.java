package iteaHW06;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Cashier {

	private volatile boolean isClosed;

	public Cashier() {
	}

	public boolean isClosed() {
		return isClosed;
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
