package serverSide;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class server implements Runnable {
	Socket cSocket;
	studentDB studentDataBase;

	public server(Socket cSocket) throws IOException, Exception {
		this.cSocket = cSocket;
		studentDataBase = new studentDB();
	}

	public static void main(String[] args) throws Exception {
		ServerSocket sSocket = new ServerSocket(3000);
		System.out.println("Listening");
		while (true) {
			Socket S = sSocket.accept();
			System.out.println("Connected");
			Thread Client = new Thread(new server(S));
			Client.start();
		}

	}

	@Override
	public void run() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(cSocket.getOutputStream());
			DataInputStream input = new DataInputStream(cSocket.getInputStream());
			String name = input.readUTF();
			out.writeObject(studentDataBase.getStudent(name));
			out.close();
			input.close();
			cSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
