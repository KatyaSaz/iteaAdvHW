package itea.hw;

import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class App extends JFrame {

	public App() {
		super("Dota2");
		setSize(400, 450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(2, 2));
		setResizable(false);

		URL url = this.getClass().getClassLoader().getResource("pudge.png");
		JLabel lablelPudge = new JLabel(new ImageIcon(url));
		JLabel lablelCrystalMaiden = new JLabel(new ImageIcon("crystalMaiden.png"));

		JTextArea textPudge = getTextFromResourse("/pudge.txt");
		JTextArea textCrystalMaiden = getTextOutside("crystalMaiden.txt");

		add(lablelPudge);
		add(textPudge);
		add(lablelCrystalMaiden);
		add(textCrystalMaiden);
		setVisible(true);
	}

	public JTextArea getTextFromResourse(String fileName) {
		JTextArea ta = new JTextArea();
		try (InputStream is = App.class.getResourceAsStream(fileName);
				BufferedReader br = new BufferedReader(new InputStreamReader(is));) {
			String line = "";
			while ((line = br.readLine()) != null) {
				ta.append("  " + line + "\n\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ta;
	}

	public JTextArea getTextOutside(String fileName) {
		JTextArea ta = new JTextArea();
		try (FileReader fr = new FileReader(new File(fileName)); BufferedReader br = new BufferedReader(fr);) {
			String line = "";
			while ((line = br.readLine()) != null) {
				ta.append("  " + line + "\n\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ta;
	}

	public static void main(String[] args) {
		new App();
	}
}
