package iteaHW06;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.ZonedDateTime;

public class Client implements Runnable{
	private String name;
	Cashier cashier;
	Thread t;

	public Client(String name, Cashier cashier) {
		super();
		this.name = name;
		this.cashier = cashier;
		t=new Thread(this);
		t.setDaemon(true);
		t.start();
		
	}
	
	public Thread getT() {
		return t;
	}
	
	@Override
	public void run() {
		cashier.serveVisitor(name);
	}

}
