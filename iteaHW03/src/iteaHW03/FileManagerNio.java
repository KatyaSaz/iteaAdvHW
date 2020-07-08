package iteaHW03;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileManagerNio {

	private final String COPY = "copy";
	private final String CD = "cd";
	private final String TWO_DOTS = "..";
	private final String TO_ROOT = "/";
	private final String DIR = "dir";
	private final String REMOVE = "remove";
	private final String OPEN = "open";
	private final String HELP = "help";

	private Path pathCurrent;
	private String position;
	private String command;
	// private File currentFile;
	private Scanner inCommand;

	public FileManagerNio(String startPath) {
		pathCurrent = Paths.get(startPath);
		position = pathCurrent.toString();

		// currentFile = new File(startPath);
		// position = currentFile.getAbsolutePath();
		inCommand = new Scanner(System.in);
	}

	public void getInfoFromConcole() throws IOException {
		String[] arrCommand = command.split("\\s");
		int amountOfWord = arrCommand.length;
		if (amountOfWord == 3) {
			if (arrCommand[0].equals(COPY)) {
				makeCopy(arrCommand[1], arrCommand[2]);
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

	public void makeCopy(String fileNameFrom, String fileNameTo) throws IOException {
		Path pathFrom = Paths.get(position + "\\" + fileNameFrom);
		Path pathTo = Paths.get(position + "\\" + fileNameTo);
		Files.copy(pathFrom, pathTo, StandardCopyOption.REPLACE_EXISTING);
	}

	public void delete(String fileName) throws IOException {
		Path pathForDelete = Paths.get(position + "\\" + fileName);
		Files.delete(pathForDelete);
	}

	public void goTo(String path) {
		pathCurrent = Paths.get(path);
		position = pathCurrent.toString();
	}

	public void goToUpperDir() {
		pathCurrent = pathCurrent.getParent();
		position = pathCurrent.toString();
	}

	public void goToRoot() {
		pathCurrent = pathCurrent.getRoot();
		position = pathCurrent.toString();
	}

	public void showFilesInDir()  {		
		try(Stream<Path> lisTOfFiles =  Files.list(pathCurrent);){
			List<Path> listOfPath = lisTOfFiles.collect(Collectors.toList());
			for(Path p: listOfPath) {
				if(Files.isDirectory(p)) {
					System.out.println(p.getFileName() + "\t-	directory");
				}
			}
			for(Path p: listOfPath) {
				if(!Files.isDirectory(p)) {
					System.out.println(p.getFileName() + "\t-	file");
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void openTxtFile(String fileName) throws IOException {
		Path show = Paths.get(position + "\\"+fileName);
		List<String> lines = Files.readAllLines(show);
		for(String s: lines) {
			System.out.println(s);
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

	public void start() throws IOException {
		while (true) {
			getInputCommamd();
			getInfoFromConcole();
		}
	}
}
