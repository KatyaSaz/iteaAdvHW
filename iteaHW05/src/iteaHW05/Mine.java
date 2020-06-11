package iteaHW05;

public class Mine {

	private volatile int amountGoldNow = 1000;

	public boolean isEmpty() {
		return (amountGoldNow <= 0);
	}

	public int getAmountGoldNow() {
		return amountGoldNow;
	}

	public int getGold(int dose) {
		if (amountGoldNow >= dose) {
			amountGoldNow -= dose;
			return dose;
		} else {
			int tamount = amountGoldNow;
			amountGoldNow = 0;
			return tamount;
		}
	}

}
