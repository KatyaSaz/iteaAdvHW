package pudge;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class PudgeInfo extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldButcher;
	private JTextField textFieldMeat;
	private JButton btnSave;
	private JButton btnLoad;
	private String countryNow;
	private String languageNow;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PudgeInfo frame = new PudgeInfo();
					frame.setTitle("Pudge Info");
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PudgeInfo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 392, 324);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnEnglish = new JButton("English");
		btnEnglish.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEnglish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choseLocal("en", "EN");
				
			}
		});
		btnEnglish.setBounds(261, 26, 97, 37);
		contentPane.add(btnEnglish);
		
		JButton btnDeutch = new JButton("Deutsche");
		btnDeutch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choseLocal("de", "DE");
			}
		});
		btnDeutch.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDeutch.setBounds(261, 73, 97, 37);
		contentPane.add(btnDeutch);
		
		JButton btnRuss = new JButton("Руский");
		btnRuss.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choseLocal("ru", "RU");
			}
		});
		btnRuss.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRuss.setBounds(261, 120, 97, 37);
		contentPane.add(btnRuss);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(28, 196, 205, 19);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldButcher = new JTextField();
		textFieldButcher.setColumns(10);
		textFieldButcher.setBounds(28, 225, 205, 19);
		contentPane.add(textFieldButcher);
		
		textFieldMeat = new JTextField();
		textFieldMeat.setColumns(10);
		textFieldMeat.setBounds(28, 254, 205, 19);
		contentPane.add(textFieldMeat);
		
		JLabel lblNewLabel = new JLabel("");
		
		Image img = new ImageIcon("image\\pudge_small.png").getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(28, 26, 205, 155);
		contentPane.add(lblNewLabel);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  try(FileWriter writer = new FileWriter("src\\lastLocal.txt", false))
			        {
			           writer.write(languageNow);
			           writer.write("\n");
			           writer.write(countryNow);
			        } catch (IOException ex) {
						ex.printStackTrace();
					}
			}});
		btnSave.setBounds(273, 224, 85, 21);
		contentPane.add(btnSave);
		
		btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try(BufferedReader reader = new BufferedReader(new FileReader("src\\lastLocal.txt")))
		        {
					languageNow = reader.readLine();
					//reader.readLine();
					countryNow = reader.readLine();
					choseLocal(languageNow, countryNow);
		        } catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnLoad.setBounds(273, 253, 85, 21);
		contentPane.add(btnLoad);
	}
	
	private void choseLocal(String language, String country) {
		//de - language, DE - country
		languageNow = language;
		countryNow = country;
		Locale currentLocale = new Locale(language, country);
		ResourceBundle messages = ResourceBundle.getBundle("itea", currentLocale);
		textFieldName.setText(messages.getString("name"));
		textFieldButcher.setText(messages.getString("butcher"));
		textFieldMeat.setText(messages.getString("meat"));
	}
}
