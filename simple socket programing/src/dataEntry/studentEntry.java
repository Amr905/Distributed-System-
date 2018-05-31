package dataEntry;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import serverSide.student;

public class studentEntry {
	static JLabel label1;
	static JFrame frame;
	static JTextField fNameTextField;
	static JTextField surNameTextField;
	static JTextField ageTextField;
	static ArrayList<student> students = new ArrayList<>();

	public static void buildFrame() {
		JFrame frame = new JFrame("student data entery");
		frame.getContentPane().setBackground(Color.white);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 300);
		frame.setLocationRelativeTo(null);
		JLabel numberOfStudentsLabel = new JLabel("number of saved students : 0");
		numberOfStudentsLabel.setBounds(27, 42, 205, 20);
		frame.getContentPane().add(numberOfStudentsLabel);
		label1 = new JLabel("first name");
		label1.setBounds(27, 83, 90, 20);
		frame.add(label1);

		fNameTextField = new JTextField();// fname
		fNameTextField.setBounds(118, 83, 70, 26);
		frame.add(fNameTextField);

		JLabel surNameLabel = new JLabel("sur name");
		surNameLabel.setBounds(220, 83, 70, 26);
		frame.add(surNameLabel);

		surNameTextField = new JTextField();// surname
		surNameTextField.setBounds(304, 83, 70, 26);
		frame.getContentPane().add(surNameTextField);

		JLabel ageLabel = new JLabel("Age");
		ageLabel.setBounds(37, 119, 70, 20);
		frame.add(ageLabel);

		ageTextField = new JTextField();// age
		ageTextField.setBounds(118, 116, 70, 26);
		frame.add(ageTextField);

		JButton button1 = new JButton("add student");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// add student
				String fname, sname;
				int age;
				fname = fNameTextField.getText();
				sname = surNameTextField.getText();
				age = Integer.valueOf(ageTextField.getText());
				try {
					students.add(new student(fname, sname, age));
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				numberOfStudentsLabel.setText("number of saved students : " + students.size());
				JOptionPane.showMessageDialog(null, "student inserted !");
				fNameTextField.setText("");
				surNameTextField.setText("");
				ageTextField.setText("");
			}
		});
		button1.setBounds(263, 119, 115, 29);
		frame.getContentPane().add(button1);

		JButton button2 = new JButton("save them in files");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// save students in files here
				FileOutputStream FO;
				try {
					FO = new FileOutputStream("students.ser");
					ObjectOutputStream OOS = new ObjectOutputStream(FO);
					OOS.writeObject(students);
					OOS.close();
					FO.close();
					JOptionPane.showMessageDialog(null, "students saved in files !");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		button2.setBounds(143, 180, 150, 29);

		frame.getContentPane().add(button2);
		frame.setLayout(null);
		frame.setVisible(true);
	}

	public static void main(String[] args) throws IOException {

		buildFrame();

		// students.add(new student("Amr","Morsy",21)) ;

		// System.out.println("object saved");
	}
}
