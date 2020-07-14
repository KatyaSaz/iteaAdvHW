package iteaHW_exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class Unloader implements Runnable {

	private final int UNLOADER_TAKE = 3; // kg
	private Cart cart;
	private Exchanger<Cart> excUnloaderTransp;

	public Unloader(Cart cart, Exchanger<Cart> excUnloaderTransp) {
		super();
		this.cart = cart;
		this.excUnloaderTransp = excUnloaderTransp;
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		try {
			excUnloaderTransp.exchange(null);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		while (true) {
			if (!cart.isCartEmpty()) {
				cart.filledCart(-UNLOADER_TAKE);
				System.out.println("Unloader unload " + UNLOADER_TAKE + " kg of sand.");
				System.out.println("Cart filled on " + cart.getCurrentCapacity() + " of " + cart.getCapacityCart());

				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			} else {
				System.out.println("Cart is empty.");
				
				try {
					excUnloaderTransp.exchange(cart);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}