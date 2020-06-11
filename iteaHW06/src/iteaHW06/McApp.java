package iteaHW06;

public class McApp {
	public static void main(String[] args) {
		Cashier cas = new Cashier();
		Client c1 = new Client("client 1", cas);
		Client c2 = new Client("client 2", cas);
		Client c3 = new Client("client 3", cas);
		Client c4 = new Client("client 4", cas);;
		Client c5 = new Client("client 5", cas);
	}
}
