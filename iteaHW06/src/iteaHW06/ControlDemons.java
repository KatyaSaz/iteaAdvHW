package iteaHW06;

public class ControlDemons implements Runnable {

	private Thread t;
	private Cashier cashier;

	public ControlDemons(Cashier cashier) {
		this.cashier = cashier;
		t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		new Client("client 1", cashier);
		new Client("client 2", cashier);
		new Client("client 3", cashier);
		new Client("client 4", cashier);
		new Client("client 5", cashier);

		while (!cashier.isClosed()) {

		}

	}
}
