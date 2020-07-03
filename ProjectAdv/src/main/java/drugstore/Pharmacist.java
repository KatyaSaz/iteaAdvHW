package drugstore;

import java.util.concurrent.TimeUnit;
import javafx.scene.control.TextArea;

public class Pharmacist {

	private String name;
	private int timeOfServe;
	private TextArea txtFieldPHName;
	private int amountOfClients;
	private int countClients;

	public Pharmacist(String name, TextArea txtFieldPHName, int amountOfClients) {
		super();
		this.txtFieldPHName = txtFieldPHName;
		this.name = name;
		this.amountOfClients = amountOfClients;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTimeOfServe() {
		return timeOfServe;
	}

	public synchronized void serveClient(Client client) {
		timeOfServe = 0;
		
		txtFieldPHName.appendText(name + " start serving.\n");
		txtFieldPHName.appendText(client.getName() + " is served now.\n");
		txtFieldPHName.appendText("His list:\n" + client.getListOfMedsForPrint() + "\n");

		for (int i = 0; i < client.getListOfMeds().size(); i++) {
			timeOfServe += client.getListOfMeds().get(i).getTime();
		}
		try {
			TimeUnit.SECONDS.sleep(timeOfServe);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		countClients++;
		txtFieldPHName.appendText(name + " finish serving of " + client.getName() + ".\n");
		txtFieldPHName.appendText("Time of serve " + timeOfServe + " sec.\n");
		txtFieldPHName.appendText("Clients serve: " + countClients + " / " + amountOfClients + "\n");
		txtFieldPHName.appendText("\n");
	}
}
