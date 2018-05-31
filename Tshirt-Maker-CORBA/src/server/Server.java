package server;

import TShirtApp.Color;
import TShirtApp.TShirtServer;
import TShirtApp.TShirtServerHelper;
import TShirtApp.TShirtServerPOA;

import org.omg.CosNaming.*;

import java.awt.Font;
import java.awt.Graphics;

import java.awt.image.BufferedImage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.omg.CORBA.*;
import org.omg.PortableServer.*;

public class Server extends TShirtServerPOA {

	public static void main(String[] args) {
		try {
			ORB orb = ORB.init(args, null);
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();
			Server ssi = new Server();

			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(ssi);
			TShirtServer ss = TShirtServerHelper.narrow(ref);
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			NameComponent path[] = ncRef.to_name("TSHIRT-SERVER");
			ncRef.rebind(path, ss);
			System.out.println("TSHIRT Maker Server is running . . . ");
			orb.run();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}

	@Override
	public byte[] makeImage(String name) {
		BufferedImage bufferedImage = new BufferedImage(379, 561, BufferedImage.TYPE_INT_RGB);
		Graphics graphic = bufferedImage.getGraphics();
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("src/server/tshirt.JPG"));
		} catch (IOException e) {
		}
		
		graphic.drawImage(img, 0, 0, null);
		graphic.setFont(new Font("TimesRoman", Font.PLAIN, 40)); 
		graphic.drawString(name, 95, 150);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(bufferedImage, "jpg", baos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] bytes = baos.toByteArray();

		return bytes;

	}

	@Override
	public int getPriceOfSimpleTshirt(int width, int height) {
		return width + height;

	}

	@Override
	public int getPriceOfSpecialTshirt(TShirtApp.Tshirt TShirt) {
		int total = TShirt.width + TShirt.height;

		if (TShirt.color == Color.black)
			total += 50;
		else if (TShirt.color == Color.blue)
			total += 25;
		else if (TShirt.color == Color.red)
			total += 10;

		if (TShirt.brand.equals("us"))
			total += 60;
		else if (TShirt.brand.equals("addidas"))
			total += 100;
		else if (TShirt.brand.equals("zara"))
			total += 120;
		else
			return 0;// if not brand notify user

		return total;
	}

}
