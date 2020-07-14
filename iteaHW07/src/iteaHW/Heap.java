package iteaHW;

public class Heap {

	private int sandAmount;
	
	public Heap(int sandAmount) {
		super();
		this.sandAmount = sandAmount;
	}

	public int getSandAmount() {
		return sandAmount;
	}
	
	public void decreaseHeap(int amountTake) {
		sandAmount =sandAmount-amountTake;
	}
}
