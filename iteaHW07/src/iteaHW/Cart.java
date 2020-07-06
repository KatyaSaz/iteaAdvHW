package iteaHW;

import java.util.concurrent.TimeUnit;

public class Cart {

	private final int LOADER_TIME = 0;
	private final int TRANSPORTER_TIME = 1;
	private final int UNLOADER_TIME = 2;

	private int capacityCart;
	private int currentCapacity;
	private volatile int whoTimeToWork = LOADER_TIME;

	private boolean isTimeToStop;

	private boolean direction = true;

	public Cart(int capacityCart) {
		super();
		this.capacityCart = capacityCart;
	}

	public int getCapacityCart() {
		return capacityCart;
	}

	public int getCurrentCapacity() {
		return currentCapacity;
	}

	public void filledCart(int amountSand) {
		if (amountSand > 0) {
			if ((amountSand + currentCapacity) <= capacityCart) {
				currentCapacity += amountSand;
			}
		} else {
			if ((currentCapacity + amountSand) >= 0) {
				currentCapacity += amountSand;
			}
		}

	}

	public synchronized void workOfLoader(Heap heap, int loaderTake) {
		if (whoTimeToWork != LOADER_TIME) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		if (!isCartFull()) {
			heap.decreaseHeap(loaderTake);
			filledCart(loaderTake);
			Logger.log("Loader load " + loaderTake + " kg of sand.");
			Logger.log("Cart filled on " + getCurrentCapacity() + " of " + getCapacityCart());
			Logger.log("Sand left " + heap.getSandAmount());

			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			Logger.log("Cart full.");
			whoTimeToWork = TRANSPORTER_TIME;
			notify();
		}
	}

	public synchronized void workOfTransporter() {
		if (whoTimeToWork != TRANSPORTER_TIME) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		Logger.log("Move " + (direction ? "forward" : "backward") + " cart.");
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (direction) {
			whoTimeToWork = UNLOADER_TIME;
		} else {
			whoTimeToWork = LOADER_TIME;
		}

		direction = !direction;

		notify();
	}

	public synchronized void workOfUnloader(int unloaderTake) {
		if (whoTimeToWork != UNLOADER_TIME) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		if (!isCartEmpty()) {
			filledCart(-unloaderTake);
			Logger.log("Unloader unload " + unloaderTake + " kg of sand.");
			Logger.log("Cart filled on " + getCurrentCapacity() + " of " + getCapacityCart());
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			Logger.log("Cart is empty.");
			whoTimeToWork = TRANSPORTER_TIME;

			notify();
		}
	}

	public boolean isCartFull() {
		return (currentCapacity == capacityCart);
	}

	public boolean isCartEmpty() {
		return (currentCapacity == 0);
	}

	public boolean isTimeToStop() {
		return isTimeToStop;
	}

	public void setTimeToStop(boolean isTimeToStop) {
		this.isTimeToStop = isTimeToStop;
	}
}
