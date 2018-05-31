package serverSide;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class studentDB {
	ArrayList<student> students;

	public studentDB() throws IOException, Exception {
		super();
		FileInputStream FI = new FileInputStream("students.ser");
		ObjectInputStream OIS = new ObjectInputStream(FI);
		students = (ArrayList<student>) OIS.readObject();

	}

	public student getStudent(String fname, String sname) throws RemoteException {

		for (student s : students) {
			if (s.getFirstName().equals(fname) && s.getSurName().equals(sname))
				return s;
		}
		return null;
	}

	public student getStudent(String fname) throws RemoteException {

		for (student s : students) {
			if (s.getFirstName().equals(fname))
				return s;
		}
		return null;
	}

}
