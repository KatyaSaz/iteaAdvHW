package iteaHW02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class FileManager {

	private final String COPY = "copy";
	private final String CD = "cd";
	private final String TWO_DOTS = "..";
	private final String TO_ROOT = "/";
	private final String DIR = "dir";
	private final String REMOVE = "remove";
	private final String OPEN = "open";
	private final String HELP = "help";

	private String position;
	private String command;
	private File currentFile;
	private Scanner inCommand;

	public FileManager(String startPath) {
		currentFile = new File(startPath);
		position = currentFile.getAbsolutePath();
		inCommand = new Scanner(System.in);
	}

	public void getInfoFromConcole() {
		String[] arrCommand = command.split("\\s");
		int amountOfWord = arrCommand.length;
		if (amountOfWord == 3) {
			if (arrCommand[0].equals(COPY)) {
				copy(arrCommand[1], arrCommand[2]);
			}
		} else if (amountOfWord == 2) {
			if (arrCommand[0].equals(OPEN)) {
				openTxtFile(arrCommand[1]);
			}
			if (arrCommand[0].equals(REMOVE)) {
				delete(arrCommand[1]);
			}
			if (arrCommand[0].equals(CD)) {
				if (arrCommand[1].equals(TWO_DOTS)) {
					goToUpperDir();
				} else if (arrCommand[1].equals(TO_ROOT)) {
					goToRoot();
				} else {
					goTo(arrCommand[1]);
				}
			}
		} else if (amountOfWord == 1) {
			if (command.equals(DIR)) {
				showFilesInDir();
			}
			if (command.equals(HELP)) {
				help();
			}
		}
	}

	public void copy(String fileNameFrom, String fileNameTo) {
		File from = new File(position + "\\" + fileNameFrom);
		File to = new File(position + "\\" + fileNameTo);
		try (InputStream is = new FileInputStream(from); OutputStream os = new FileOutputStream(to);) {
			int r;
			while ((r = is.read()) != -1) {
				os.write(r);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void delete(String fileName) {
		File forDelete = new File(position + "\\" + fileName);
		forDelete.delete();
	}

	public void goTo(String path) {
		currentFile = new File(path);
		position = currentFile.getAbsolutePath();
	}

	public void goToUpperDir() {
		currentFile = currentFile.getParentFile();
		position = currentFile.getAbsolutePath();
	}

	public void goToRoot() {
		currentFile = new File("/");
		position = currentFile.getAbsolutePath();
	}

	public void showFilesInDir() {
		File[] lisTOfFiles = currentFile.listFiles();
		for (File f : lisTOfFiles) {
			if (f.isDirectory()) {
				System.out.println(f.getName() + "\t-	directory");
			}
		}

		for (File f : lisTOfFiles) {
			if (f.isFile()) {
				System.out.println(f.getName() + "\t-	file");
			}
		}
	}

	public void openTxtFile(String fileName) {
		File fileToOpen = new File(position + "\\" + fileName);
		try (FileReader fr = new FileReader(fileToOpen);
				BufferedReader br = new BufferedReader(fr);) {
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			System.out.println("\nThere are no file as " + fileName + " in this directory.");
		} catch (IOException e) {
			System.out.println("\nThere is impossible to open this file " + fileName);
		}
	}

	public void help() {
		System.out.println(CD + " " + TO_ROOT + "\t-\tgo to root directory");
		System.out.println(CD + " " + TWO_DOTS + "\t-\tgo to upper directory");
		System.out.println(CD + " path \t-\tgo to this directory");
		System.out.println(COPY + " fileName fileName2\t- \tcopy file (fileName) to fileName2");
		System.out.println(DIR + "\t- \tshows full list of files and directiries in this directory");
		System.out.println(HELP + "\t- \tshows list of all possible commands");
		System.out.println(OPEN + " fileName\t- \topen text file fileName");
		System.out.println(REMOVE + " fileName\t- \tdelete file fileName");
	}

	public void getInputCommamd() {
		System.out.println("\n" + position + " > ");
		command = inCommand.nextLine();
	}

	public void start() {
		while (true) {
			getInputCommamd();
			getInfoFromConcole();
		}
	}
}
