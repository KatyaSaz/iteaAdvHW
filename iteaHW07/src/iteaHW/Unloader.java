package iteaHW;

public class Unloader implements Runnable{

	private final int UNLOADER_TAKE = 3; // kg
	private Cart cart;
	private Thread thread;
	
	public Unloader(Cart cart) {
		this.cart = cart;
		thread = new Thread(this);
		thread.start();
	}

	public Thread getThread() {
		return thread;
	}

	@Override
	public void run() {
		while (!cart.isTimeToStop()) {
			cart.workOfUnloader(UNLOADER_TAKE);	
		}
		
	}
}
