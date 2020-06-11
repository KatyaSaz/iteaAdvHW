package iteaHW05;

import java.util.concurrent.TimeUnit;

public class MinerMain {

	public static void main(String[] args) {
		Mine mine = new Mine();

		new GoldMiner("golden miner 1", mine);
		new GoldMiner("golden miner 2", mine);
		new GoldMiner("golden miner 3", mine);
		
		new Barrack(mine);
	}
}