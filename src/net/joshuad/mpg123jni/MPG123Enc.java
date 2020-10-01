package net.joshuad.mpg123jni;

public class MPG123Enc {
  public static final int EIGHT = 0x00f;
  public static final int SIXTEEN = 0x040;
  public static final int TWENTYFOUR = 0x4000; 
  public static final int THIRTYTWO = 0x100;
  public static final int SIGNED = 0x080; 
  public static final int FLOAT = 0xe00;
  public static final int SIGNED_16 = (EIGHT|SIGNED|0x10); 
  public static final int UNSIGNED_16 = (SIXTEEN|0x20);
  public static final int UNSIGNED_8 = 0x01; 
  public static final int SIGNED_8 = (SIGNED|0x02);
  public static final int ULAW_8 = 0x04; 
  public static final int ALAW_8 = 0x08;
  public static final int SIGNED_32 = THIRTYTWO|SIGNED|0x1000; 
  public static final int UNSIGNED_32 = THIRTYTWO|0x2000;
  public static final int SIGNED_24 = TWENTYFOUR|SIGNED|0x1000; 
  public static final int UNSIGNED_24 = TWENTYFOUR|0x2000;
  public static final int FLOAT_32 = 0x200; 
  public static final int FLOAT_64 = 0x400;
  public static final int ANY = 0x401; //This may not be right
}
