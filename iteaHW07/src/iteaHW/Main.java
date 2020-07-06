package iteaHW;

public class Main {
	public static void main(String[] args) {
		Cart cart = new Cart(6);
		Heap heap = new Heap(100);

		new Loader(heap, cart);
		new Unloader(cart);
		new Transporter(cart);
		
	}
}
