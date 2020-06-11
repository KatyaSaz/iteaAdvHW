package iteaHW05;

import java.util.concurrent.TimeUnit;

public class MinerMain {

	public static void main(String[] args) {
		Mine mine = new Mine();

		new GoldMiner("golden miner 1", mine);
		new GoldMiner("golden miner 2", mine);
		new GoldMiner("golden miner 3", mine);

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