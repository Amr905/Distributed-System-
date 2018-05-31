package serverSide;

import java.io.Serializable;
import java.rmi.RemoteException;

public class student implements Serializable {

	private String FirstName;
	private String SurName;
	private int Age;

	public student() throws RemoteException {
		super();
	}

	public student(String firstName, String surName, int age) throws RemoteException {
		super();
		FirstName = firstName;
		SurName = surName;
		Age = age;
	}

	public String getFirstName() throws RemoteException {

		return FirstName;
	}

	public String getSurName() throws RemoteException {

		return SurName;
	}

	public int getAge() throws RemoteException {

		return Age;
	}

}
