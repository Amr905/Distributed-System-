package TShirtApp;


/**
* TShirtApp/TshirtHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from tshirt.idl
* Wednesday, April 25, 2018 4:07:27 PM GMT+02:00
*/

abstract public class TshirtHelper
{
  private static String  _id = "IDL:TShirtApp/Tshirt:1.0";

  public static void insert (org.omg.CORBA.Any a, TShirtApp.Tshirt that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static TShirtApp.Tshirt extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [4];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
          _members0[0] = new org.omg.CORBA.StructMember (
            "width",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
          _members0[1] = new org.omg.CORBA.StructMember (
            "height",
            _tcOf_members0,
            null);
          _tcOf_members0 = TShirtApp.ColorHelper.type ();
          _members0[2] = new org.omg.CORBA.StructMember (
            "color",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_wstring_tc (0);
          _members0[3] = new org.omg.CORBA.StructMember (
            "brand",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (TShirtApp.TshirtHelper.id (), "Tshirt", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static TShirtApp.Tshirt read (org.omg.CORBA.portable.InputStream istream)
  {
    TShirtApp.Tshirt value = new TShirtApp.Tshirt ();
    value.width = istream.read_long ();
    value.height = istream.read_long ();
    value.color = TShirtApp.ColorHelper.read (istream);
    value.brand = istream.read_wstring ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, TShirtApp.Tshirt value)
  {
    ostream.write_long (value.width);
    ostream.write_long (value.height);
    TShirtApp.ColorHelper.write (ostream, value.color);
    ostream.write_wstring (value.brand);
  }

}