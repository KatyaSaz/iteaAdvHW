package iteaHW05;

import java.util.concurrent.TimeUnit;

public class GoldMiner implements Runnable {

	private final int GOLD_TAKE = 2;

	private String name;
	private int currentGold;

	private Mine mine;
	private Thread tread;

	public GoldMiner(String name, Mine mine) {
		super();
		this.name = name;
		this.mine = mine;
		tread = new Thread(this);
		tread.start();
	}

	@Override
	public void run() {
		do {
			synchronized (mine) {
				currentGold += mine.getGold(GOLD_TAKE);
				System.out.println(name + ": " + currentGold + ". Left gold in mine: " + mine.getAmountGoldNow());
			}
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!mine.isEmpty());

	}
}
