package iteaHW_exchanger;

import java.util.concurrent.Exchanger;

public class Main {
	public static void main(String[] args) {
		Cart cart = new Cart(6);
		Heap heap = new Heap(100);
		Exchanger<Cart> excLoaderTransp = new Exchanger<Cart>();
		Exchanger<Cart> excUnloaderTransp = new Exchanger<Cart>();

		new Loader(heap, cart, excLoaderTransp);
		new Transporter(cart, excLoaderTransp, excUnloaderTransp);
		new Unloader(cart, excUnloaderTransp);
	}
}
