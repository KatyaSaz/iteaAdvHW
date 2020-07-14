package iteaHW;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
	public synchronized static void log(String message) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new Date());
		System.out.println(
				String.format(
						"{%s}  [%s] - %s",
						timeStamp,
						Thread.currentThread().getName(),message
						)
				);
		
	}
}
