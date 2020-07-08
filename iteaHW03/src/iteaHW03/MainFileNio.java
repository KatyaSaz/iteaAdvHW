package iteaHW03;

import java.io.IOException;

public class MainFileNio {
	public static void main(String[] args) {
		FileManagerNio fileManagerNio = new FileManagerNio("C:\\lesson2_1\\itea2");
		try {
			fileManagerNio.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
