package net.joshuad.mpg123jni;

public class MPG123 {

  private long handleAddress;
  
  static {
    init();
  }
  
  public MPG123 () throws MPG123Exception {
    handleAddress = makeNew();
    if (handleAddress == 0) {
      throw new MPG123Exception("Unable to create mpg123 handle");
    }
    openFeed(handleAddress);
    // if m == null, it's an error, not sure how to check for this
  }
  
  public int[] decode(byte[] encodedIn, byte[] decodedOut) {
    int[] returnInfo = decode(handleAddress, encodedIn, decodedOut);
    return returnInfo;
  }
  
  public int[] getFormat() {
    return getFormat(handleAddress);
  }
  
  public void close() {
    delete(handleAddress);
  }
  
  public String getError() {
    return strerror(handleAddress);
  }
  
  public void setParam(int parameter, int value, double floatValue) {
    param(handleAddress, parameter, value, floatValue);
  }
  
  //Native interface
  private static native int init();
  
  private static native long makeNew(); //returns an address for an mpg123_handle
  
  private static native int openFeed(long m);
  
  //returns int[0] = returnCode, int[1] = decodedOutSize
  private static native int[] decode(long m, byte[] encodedIn, byte[] decodedOut);
  
  private static native int[] getFormat(long m); 

  private static native void delete(long m);
  
  private static native String strerror(long m);
  
  private static native int param(long m, int parameter, int value, double floatValue);
}
