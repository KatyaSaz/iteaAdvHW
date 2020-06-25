package application;
	
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


public class PudgeFX extends Application {
	
	private String countryNow;
	private String languageNow;
	private TextField tfName;
	private TextField tfButcher;
	private TextField tfMeat;
	
	@Override
	public void start(Stage primaryStage) {
		pudgeInternatialization(primaryStage);		
	}

	private void pudgeInternatialization(Stage primaryStage) {
		GridPane root = new GridPane();
		
		tfName = new TextField();
		tfButcher = new TextField();
		tfMeat = new TextField();
		
		Image image = null;
		try(FileInputStream fis = new FileInputStream("C:/Users/kat-note/eclipse-workspace/iteaHW09/image/pudge_small.png");) {
			image = new Image(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ImageView imageView = new ImageView(image);
				
		Button btnEnglish = new Button("English");
		Button btnDeutsche = new Button("Deutsche");
		Button btnRussian = new Button("Руский");
		Button btnSave = new Button("Save");
		Button btnLoad = new Button("Load");
		
		VBox buttonBox = new VBox(10);
		buttonBox.setAlignment(Pos.CENTER_LEFT);
		buttonBox.setSpacing(10);
		buttonBox.getChildren().add(btnEnglish);
		buttonBox.getChildren().add(btnDeutsche);
		buttonBox.getChildren().add(btnRussian);
		
		root.setAlignment(Pos.CENTER);
		root.setVgap(10);
		root.setHgap(10);
		root.setPadding(new Insets(25, 25, 25,25));
		
		root.add(imageView, 0, 0); //button column, row
		root.add(tfName, 0, 1);
		root.add(tfButcher, 0, 2);
		root.add(tfMeat, 0, 3);
		root.add(buttonBox, 1, 0);
		root.add(btnSave, 1, 2);
		root.add(btnLoad, 1, 3);
		
		Scene scene = new Scene(root, 350, 350);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		
		btnDeutsche.setOnAction((arg)->choseLocal("de", "DE"));
		btnEnglish.setOnAction((arg)->choseLocal("en", "EN"));
		btnRussian.setOnAction((arg)->choseLocal("ru", "RU"));
		
		btnLoad.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try(BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\kat-note\\eclipse-workspace\\iteaHW09\\src\\lastLocal.txt")))
		        {
					languageNow = reader.readLine();
					countryNow = reader.readLine();
					choseLocal(languageNow, countryNow);
		        } catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		
		btnSave.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				  try(FileWriter writer = new FileWriter("C:\\Users\\kat-note\\eclipse-workspace\\iteaHW09\\src\\lastLocal.txt", false))
			       {
			           writer.write(languageNow);
			           writer.write("\n");
			           writer.write(countryNow);
			       } catch (IOException ex) {
						ex.printStackTrace();
			       }
			}
		});
		
	}
	
	private void choseLocal(String language, String country) {
		//de - language, DE - country
		languageNow = language;
		countryNow = country;
		Locale currentLocale = new Locale(language, country);
		ResourceBundle messages = ResourceBundle.getBundle("itea", currentLocale);
		tfName.setText(messages.getString("name"));
		tfButcher.setText(messages.getString("butcher"));
		tfMeat.setText(messages.getString("meat"));
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
