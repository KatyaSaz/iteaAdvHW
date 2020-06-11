package iteaHW05;

import java.util.concurrent.TimeUnit;

public class Barrack implements Runnable{
	
	private Mine mine;
	
	public Barrack(Mine mine) {
		this.mine = mine;
		Thread tread = new Thread(this);
		tread.start();
	}

	@Override
	public void run() {
		for (int i = 4; !(mine.isEmpty()); i++) {
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			new GoldMiner("golden miner " + i, mine);
		}
	}

}
