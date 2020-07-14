package iteaHW_exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class Loader implements Runnable {

	private final int LOADER_TAKE = 2; // kg
	private Heap heap;
	private Cart cart;
	private Exchanger<Cart> excLoaderTransp;

	public Loader(Heap heap, Cart cart, Exchanger<Cart> excLoaderTransp) {
		super();
		this.excLoaderTransp = excLoaderTransp;
		this.heap = heap;
		this.cart = cart;
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		while (heap.getSandAmount() > 0) {
			if (!cart.isCartFull()) {
				heap.decreaseHeap(LOADER_TAKE);
				cart.filledCart(LOADER_TAKE);
				System.out.println("Loader load " + LOADER_TAKE + " kg of sand.");
				System.out.println("Cart filled on " + cart.getCurrentCapacity() + " of " + cart.getCapacityCart());
				System.out.println("Sand left " + heap.getSandAmount());
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			else {
				System.out.println("Cart full.");
				try {
					excLoaderTransp.exchange(cart);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
		System.out.println("The work was done!");
	}
}