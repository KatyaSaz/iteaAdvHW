package iteaHW;

public class Transporter implements Runnable {

	private Cart cart;

	public Transporter(Cart cart) {
		this.cart = cart;
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		while (!cart.isTimeToStop()) {
			cart.workOfTransporter();
		}

	}

}
