package iteaHW;

public class Loader implements Runnable {

	private final int LOADER_TAKE = 2; // kg
	private Heap heap;
	private Cart cart;
	private Thread thread;

	public Loader(Heap heap, Cart cart) {
		super();
		this.heap = heap;
		this.cart = cart;
		thread = new Thread(this);
		thread.start();
	}

	public Thread getThread() {
		return thread;
	}

	@Override
	public void run() {
		while (heap.getSandAmount() > 0) {
			cart.workOfLoader(heap, LOADER_TAKE);
		}
		cart.setTimeToStop(true);
	}

}
