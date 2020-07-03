package drugstore;

import java.util.ArrayList;
import java.util.List;

public class Client implements Runnable {

	private String name;
	private Pharmacist pharmacist;
	private Thread thread;
	private DBWork dbWork;
	private List<Meds> listOfMeds;
	private boolean isWorked;

	public Thread getThread() {
		return thread;
	}

	public boolean isWorked() {
		return isWorked;
	}

	public Client(String name, Pharmacist pharmacist) {
		super();
		this.pharmacist = pharmacist;
		this.name = name;
		thread = new Thread(this);
		dbWork = new DBWork();
		listOfMeds = generateListOfMeds();
		thread.start();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Meds> getListOfMeds() {
		return listOfMeds;
	}

	private List<Meds> generateListOfMeds() {
		List<Meds> allMeds = dbWork.getFullListOfMeds();
		List<Meds> currentList = new ArrayList<Meds>();
		int amountOfMeds = (int) ((Math.random() * 6) + 1);

		for (int i = 0; i < amountOfMeds; i++) {
			int index = (int) ((Math.random() * (allMeds.size() - 1)) + 1);
			currentList.add(allMeds.get(index));
		}
		return currentList;
	}

	public String getListOfMedsForPrint() {
		String str = "(";
		for (int i = 0; i < listOfMeds.size(); i++) {
			str += " " + listOfMeds.get(i).getName() + " ";
		}
		str += ")";
		return str;
	}

	@Override
	public void run() {
		pharmacist.serveClient(this);
		dbWork.insertLog(pharmacist, this);
	}

}
