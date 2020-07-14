package iteaHW_exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class Transporter implements Runnable {

	private Cart cart;
	private boolean direction = true;
	private Exchanger<Cart> excLoaderTransp;
	private Exchanger<Cart> excUnloaderTransp;

	public Transporter(Cart cart, Exchanger<Cart> excLoaderTransp, Exchanger<Cart> excUnloaderTransp) {
		super();
		this.excLoaderTransp = excLoaderTransp;
		this.excUnloaderTransp = excUnloaderTransp;
		this.cart = cart;
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		while (true) {

			try {
				TimeUnit.SECONDS.sleep(7);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("Move " + (direction ? "forward" : "backward") + " cart.");
			
			if (direction) {
				try {
					excUnloaderTransp.exchange(cart);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				try {
					excLoaderTransp.exchange(cart);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			direction = !direction;
		}
	}
}