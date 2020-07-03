package drugstore;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class DrugStoreGUI extends Application {

	private Pharmacist pharmacist;
	
	private TextField tfLCasName1;
	private TextField tfLCasName2;
	private TextField tfLCasName3;
	private TextField tfAmountV1;
	private TextField tfAmountV2;
	private TextField tfAmountV3;
	private volatile TextArea  taTest1;
	private volatile TextArea  taTest2;
	private volatile TextArea  taTest3;

	@Override
	public void start(Stage primaryStage) {		
		startWindow(primaryStage);
	}
	
	private void showAlert(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Ops, something went wrong!");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	private boolean checkInputFields() {
		if (tfLCasName1.getText().equals("") || tfLCasName2.getText().equals("")
				|| tfLCasName3.getText().equals("") || tfAmountV1.getText().equals("")
				|| tfAmountV2.getText().equals("") || tfAmountV3.getText().equals("")) {
			showAlert("All input fields should be filled!");
			return false;
		}

		try {
			int amount1 = Integer.parseInt(tfAmountV1.getText());
			int amount2 = Integer.parseInt(tfAmountV2.getText());
			int amount3 = Integer.parseInt(tfAmountV3.getText());
			
			if (amount1 <= 0 || amount2 <= 0 || amount3 <= 0) {
				showAlert("In 'Amount of clients' must be only positive numbers!");
				return false;
			}
		} catch (NumberFormatException ex) {
			showAlert("'Amount of clients' must be filled with numbers only!");
			return false;
		}
		return true;
	}
	
	public void startWindow(final Stage primaryStage) {
		
		GridPane root = new GridPane();
		root.setId("startroot");
		Label labelChashireName = new Label("Input Name of pharmacists: ");
		labelChashireName.setId("start");
		Label labelAmountClients = new Label("Input amount of clients: ");
		labelAmountClients.setId("start");
		
		InputStream imgStream = null;
		try {
			imgStream = getClass().getResource("pharm1.gif").openStream();
		} catch (IOException e) {
			e.printStackTrace();
		}

		ImageView imageView1 = new ImageView(new Image(imgStream));
		imageView1.setFitHeight(200);
		imageView1.setFitWidth(300);

		imgStream = null;
		try {
			imgStream = getClass().getResource("qeue.gif").openStream();
		} catch (IOException e) {
			e.printStackTrace();
		}

		ImageView imageView2 = new ImageView(new Image(imgStream));
		imageView2.setFitHeight(200);
		imageView2.setFitWidth(200);

		tfLCasName1 = new TextField();
		tfLCasName2 = new TextField();
		tfLCasName3 = new TextField();
		tfAmountV1 = new TextField();
		tfAmountV2 = new TextField();
		tfAmountV3 = new TextField();

		Button button = new Button("Start");

		HBox hButton = new HBox(10);
		hButton.setAlignment(Pos.CENTER_RIGHT);
		hButton.getChildren().add(button);

		root.setAlignment(Pos.CENTER);
		root.setVgap(20);
		root.setHgap(20);
		root.setPadding(new Insets(25, 25, 25, 25));
		
		root.add(labelChashireName, 0, 0); // button column, row
		root.add(labelAmountClients, 1, 0);
		root.add(tfLCasName1, 0, 1); 
		root.add(tfLCasName2, 0, 2); 
		root.add(tfLCasName3, 0, 3); 
		root.add(tfAmountV1, 1, 1); 
		root.add(tfAmountV2, 1, 2); 
		root.add(tfAmountV3, 1, 3);
		root.add(imageView1, 0, 4);
		root.add(imageView2, 1, 4);
		root.add(hButton, 1, 5);
		
		Scene scene = new Scene(root, 600, 550);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if (checkInputFields()) {
					showPharmacy(primaryStage);
				}
			}
		});
	}

	private void createDrugStore(String pharmasistName, TextArea textarea, int amountOfClients) {
		pharmacist = new Pharmacist(pharmasistName, textarea, amountOfClients);

		for (int i = 0; i < amountOfClients; i++) {
			new Client("Client-" + (i + 1), pharmacist);
		}
	}

	private void showPharmacy(Stage primaryStage) {
		GridPane root = new GridPane();
		
		Label labelCass1 = new Label("Cass number #1");
		Label labelCass2 = new Label("Cass number #2");
		Label labelCass3 = new Label("Cass number #3");	
		
		taTest1 = new TextArea();
		taTest1.setMaxSize(700, 350);
		taTest2 = new TextArea();
		taTest2.setMaxSize(700, 350);
		taTest3 = new TextArea();
		taTest3.setMaxSize(700, 350);

		root.setAlignment(Pos.CENTER);
		root.setVgap(20);
		root.setHgap(30);
		root.setPadding(new Insets(20, 20, 20, 20));
		root.add(labelCass1, 0, 0);
		root.add(labelCass2, 1, 0);
		root.add(labelCass3, 2, 0);
		root.add(taTest1, 0, 1);
		root.add(taTest2, 1, 1);
		root.add(taTest3, 2, 1);
		root.setId("pane"); 

		Scene scene = new Scene(root, 1300, 700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
		
		primaryStage.show();
		
		
		createDrugStore(tfLCasName1.getText(), taTest1, Integer.parseInt(tfAmountV1.getText()));
		createDrugStore(tfLCasName2.getText(), taTest2, Integer.parseInt(tfAmountV2.getText()));
		createDrugStore(tfLCasName3.getText(), taTest3, Integer.parseInt(tfAmountV3.getText()));
	}

	 public static void main( String[] args )  {
	   	launch(args);
	  }
	
}
