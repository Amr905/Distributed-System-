package clientSide;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import serverSide.student;

public class Client {

	static JLabel fNameLabel;
	static JFrame frame;
	static JTextField fNameTextField;
	static JTextField surNameTextField;
	static JTextField ageTextField;
	static ArrayList<student> students = new ArrayList<>();

	public static void buildFrame() {
		JFrame frame = new JFrame("student Finder");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.white);
		frame.setSize(500, 300);
		frame.setLocationRelativeTo(null);

		fNameLabel = new JLabel("first name");

		JButton button1 = new JButton("search by first name");

		button1.setBounds(263, 119, 170, 29);
		button1.setFocusable(true);
		frame.add(button1);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String fname = fNameTextField.getText();

				Socket s1;
				try {
					s1 = new Socket("localhost", 3000);
					DataOutputStream out = new DataOutputStream(s1.getOutputStream());
					ObjectInputStream input = new ObjectInputStream(s1.getInputStream());
					out.writeUTF(fname);
					student obj = (student) input.readObject();
					out.close();
					input.close();
					s1.close();
					if (obj != null) {
						surNameTextField.setText(obj.getSurName());
						ageTextField.setText(String.valueOf(obj.getAge()));
					} else
						JOptionPane.showMessageDialog(null, "no result found");
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				

			}
		});

		fNameLabel.setBounds(27, 83, 90, 20);
		frame.add(fNameLabel);

		fNameTextField = new JTextField();// fname
		fNameTextField.setBounds(118, 83, 70, 26);
		frame.add(fNameTextField);

		JLabel surNameLabel = new JLabel("sur name");
		surNameLabel.setBounds(220, 83, 70, 26);
		frame.add(surNameLabel);

		surNameTextField = new JTextField();// surname
		surNameTextField.setBounds(304, 83, 105, 26);
		surNameTextField.setEditable(false);
		frame.getContentPane().add(surNameTextField);

		JLabel labelAge = new JLabel("Age");
		labelAge.setBounds(37, 119, 70, 20);
		frame.add(labelAge);

		ageTextField = new JTextField();// age
		ageTextField.setBounds(118, 116, 70, 26);
		ageTextField.setEditable(false);
		frame.add(ageTextField);

		frame.setLayout(null);// absolute layout
		frame.setVisible(true);
	}

	public static void main(String[] args) throws Exception {
		buildFrame();

	}
}
