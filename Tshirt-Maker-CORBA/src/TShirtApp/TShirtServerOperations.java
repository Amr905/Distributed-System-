package TShirtApp;


/**
* TShirtApp/TShirtServerOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from tshirt.idl
* Wednesday, April 25, 2018 4:07:27 PM GMT+02:00
*/

public interface TShirtServerOperations 
{
  int getPriceOfSimpleTshirt (int width, int height);
  int getPriceOfSpecialTshirt (TShirtApp.Tshirt TShirt);
  byte[] makeImage (String s);
} // interface TShirtServerOperations