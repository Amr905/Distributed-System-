package client;

import org.omg.CosNaming.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

import java.io.File;

import javax.imageio.ImageIO;

import org.omg.CORBA.*;


import TShirtApp.TShirtServer;
import TShirtApp.TShirtServerHelper;

import java.awt.Color;
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Client {

	private JFrame frame;
	private JTextField textfieldWidthSimpleTshirt;
	private JTextField textfieldHeightSimpleTshirt;
	private JTextField textfieldBrand;
	private JTextField textfieldName;
	private JTextField textfieldWidthSpecialTshirt;
	private JTextField textfieldHeightSpecialTshirt;
	private static String[] arg;

	public static void main(String[] args) {
		arg = args;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client window = new Client();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Client() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(new Color(230, 240, 240));
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("T-shirt maker");
		lblNewLabel.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblNewLabel.setBounds(198, 16, 164, 35);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblSizeOfThe = new JLabel("size of the tshirt:");
		lblSizeOfThe.setBounds(15, 77, 133, 20);
		frame.getContentPane().add(lblSizeOfThe);

		textfieldWidthSimpleTshirt = new JTextField();
		textfieldWidthSimpleTshirt.setBounds(208, 74, 60, 26);
		frame.getContentPane().add(textfieldWidthSimpleTshirt);
		textfieldWidthSimpleTshirt.setColumns(10);

		textfieldHeightSimpleTshirt = new JTextField();
		textfieldHeightSimpleTshirt.setBounds(355, 74, 60, 26);
		frame.getContentPane().add(textfieldHeightSimpleTshirt);
		textfieldHeightSimpleTshirt.setColumns(10);

		JLabel labelSimpleCost = new JLabel("Cost: 0 $");
		labelSimpleCost.setBounds(15, 113, 69, 20);
		frame.getContentPane().add(labelSimpleCost);

		JButton calcSimplelCost = new JButton("calculate cost");
		calcSimplelCost.setBounds(430, 73, 133, 29);
		frame.getContentPane().add(calcSimplelCost);
		calcSimplelCost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					if (!textfieldWidthSimpleTshirt.getText().equals("")
							&& !textfieldHeightSimpleTshirt.getText().equals("")) {
						ORB orb = ORB.init(arg, null);
						org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
						NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
						TShirtServer ss = TShirtServerHelper.narrow(ncRef.resolve_str("TSHIRT-SERVER"));

						int width, height;
						
						width = Integer.parseInt(textfieldWidthSimpleTshirt.getText());
						height = Integer.parseInt(textfieldHeightSimpleTshirt.getText());
						if (width > 100 || height > 100) {
							JOptionPane.showMessageDialog(null, "maximium size is 100");
						} else {
						int cost = ss.getPriceOfSimpleTshirt(width, height);
						labelSimpleCost.setText("Cost: " + cost + " $");}
					} else
						JOptionPane.showMessageDialog(null, "you cant have empty cells");

				} catch (Exception ee) {
					System.out.println("Exception: " + ee.getMessage());
				}

			}
		});

		JLabel lblSpecialTshirt = new JLabel("special T-Shirt:");
		lblSpecialTshirt.setBounds(15, 153, 419, 20);
		frame.getContentPane().add(lblSpecialTshirt);

		textfieldBrand = new JTextField();
		textfieldBrand.setText("");
		textfieldBrand.setColumns(10);
		textfieldBrand.setBounds(503, 183, 60, 26);
		frame.getContentPane().add(textfieldBrand);

		JLabel labelSpecialCost = new JLabel("Cost: 0 $");
		labelSpecialCost.setBounds(15, 228, 69, 20);
		frame.getContentPane().add(labelSpecialCost);

		// Color[] colorArray = { Color.black, Color.red, Color.blue };
		String colors[] = { "black", "red", "blue" };
		JComboBox comboBoxColor = new JComboBox(colors);
	
		comboBoxColor.setBounds(342, 183, 75, 26);
		frame.getContentPane().add(comboBoxColor);

		JLabel lblBrand = new JLabel("brand:");
		lblBrand.setBounds(438, 186, 50, 20);
		frame.getContentPane().add(lblBrand);

		JButton calcSpecialCost = new JButton("calculate special T-shirt cost");
		calcSpecialCost.setBounds(216, 224, 229, 29);
		frame.getContentPane().add(calcSpecialCost);
		calcSpecialCost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!textfieldWidthSpecialTshirt.getText().equals("")
							&& !textfieldHeightSpecialTshirt.getText().equals("")
							&& !textfieldBrand.getText().equals("")) {
						ORB orb = ORB.init(arg, null);
						org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
						NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
						TShirtServer ss = TShirtServerHelper.narrow(ncRef.resolve_str("TSHIRT-SERVER"));

						int width, height;
						width = Integer.parseInt(textfieldWidthSpecialTshirt.getText());
						height = Integer.parseInt(textfieldHeightSpecialTshirt.getText());
						if (width > 100 || height > 100) {
							JOptionPane.showMessageDialog(null, "maximium size is 100");
						} else {
							String brand = textfieldBrand.getText();
							
							TShirtApp.Color color = TShirtApp.Color.black;
							if (comboBoxColor.getSelectedItem().equals("blue"))
								color = TShirtApp.Color.blue;
							if (comboBoxColor.getSelectedItem().equals("red"))
								color = TShirtApp.Color.red;
							TShirtApp.Tshirt Tshirt = new TShirtApp.Tshirt(width, height, color, brand);

							int cost = ss.getPriceOfSpecialTshirt(Tshirt);
							if (cost == 0)
								JOptionPane.showMessageDialog(null, "the chosen brand doesnt exist in the server");
							else
								labelSpecialCost.setText("Cost: " + cost + " $");
						}
					} else
						JOptionPane.showMessageDialog(null, "you cant have empty cells");

				} catch (Exception ee) {
					System.out.println("Exception: " + ee.getMessage());

				}
			}
		});

		JLabel lblTryItNow = new JLabel("Try your name on sample T-shirt now ");
		lblTryItNow.setBounds(15, 270, 250, 20);
		frame.getContentPane().add(lblTryItNow);

		JLabel lblYourName = new JLabel("your name: ");
		lblYourName.setBounds(25, 306, 94, 20);
		frame.getContentPane().add(lblYourName);

		textfieldName = new JTextField();
		textfieldName.setBounds(120, 303, 146, 26);
		frame.getContentPane().add(textfieldName);
		textfieldName.setColumns(10);

		JButton getTshirt = new JButton("Get the T-Shirt");
		getTshirt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!textfieldName.getText().equals("")) {
						ORB orb = ORB.init(arg, null);
						org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
						NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
						TShirtServer ss = TShirtServerHelper.narrow(ncRef.resolve_str("TSHIRT-SERVER"));

						// float arr[]= {1,2};
						String name = textfieldName.getText();
						byte[] bytes = ss.makeImage(name);
						BufferedImage img = ImageIO.read(new ByteArrayInputStream(bytes));
						File outputfile = new File("src/image.jpg");
						JFrame smallFrame = new JFrame();
						smallFrame.setSize(400, 600);
						smallFrame.setLocationRelativeTo(null);
				
						smallFrame.getContentPane().add(new JLabel(new ImageIcon(bytes)));
						smallFrame.setVisible(true);
						ImageIO.write(img, "jpg", outputfile);

					} else
						JOptionPane.showMessageDialog(null, "you cant have empty name cell");

				} catch (Exception ee) {
					System.out.println("Exception: " + ee.getMessage());
				}

			}
		});
		getTshirt.setBounds(281, 302, 164, 29);
		frame.getContentPane().add(getTshirt);

		JLabel lblSimpleTshirt = new JLabel("simple T-Shirt");
		lblSimpleTshirt.setBounds(15, 40, 104, 20);
		frame.getContentPane().add(lblSimpleTshirt);

		JLabel lblNewLabel_1 = new JLabel("width:");
		lblNewLabel_1.setBounds(145, 77, 50, 20);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblHeight = new JLabel("height :");
		lblHeight.setBounds(283, 77, 60, 20);
		frame.getContentPane().add(lblHeight);

		JLabel label_1 = new JLabel("width:");
		label_1.setBounds(15, 186, 50, 20);
		frame.getContentPane().add(label_1);

		textfieldWidthSpecialTshirt = new JTextField();
		textfieldWidthSpecialTshirt.setColumns(10);
		textfieldWidthSpecialTshirt.setBounds(78, 183, 60, 26);
		frame.getContentPane().add(textfieldWidthSpecialTshirt);

		JLabel label_2 = new JLabel("height :");
		label_2.setBounds(153, 183, 60, 20);
		frame.getContentPane().add(label_2);

		textfieldHeightSpecialTshirt = new JTextField();
		textfieldHeightSpecialTshirt.setColumns(10);
		textfieldHeightSpecialTshirt.setBounds(225, 183, 60, 26);
		frame.getContentPane().add(textfieldHeightSpecialTshirt);

		JLabel lblColor = new JLabel("color:");
		lblColor.setBounds(293, 186, 50, 20);
		frame.getContentPane().add(lblColor);

	}
}
