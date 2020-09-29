package net.joshuad.mpg123jni;

public class MPG123 {

  private long handleAddress;
  
  static {
    init();
  }
  
  public MPG123 () throws Exception {
    handleAddress = makeNew();
    if (handleAddress == 0) {
      System.out.println("Unable to create mpg123 handle");
      throw new Exception(); //TODO: Exception type
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
  
  /* values taken from mpg123.h */
  public static final int DONE = -12;
  public static final int NEW_FORMAT = -11;
  public static final int NEED_MORE=-10;
  public static final int ERR = -1;
  public static final int OK = 0;
  public static final int BAD_OUTFORMAT = 1;
  public static final int BAD_CHANNEL = 2;
  public static final int BAD_RATE = 3;    
  public static final int ERR_16TO8TABLE = 4; 
  public static final int BAD_PARAM = 5;
  public static final int BAD_BUFFER = 6;  
  public static final int OUT_OF_MEM = 7; 
  public static final int NOT_INITIALIZED = 8;
  public static final int BAD_DECODER = 9;    
  public static final int BAD_HANDLE = 10;     
  public static final int NO_BUFFERS = 11; 
  public static final int BAD_RVA = 12;   
  public static final int NO_GAPLESS = 13;   
  public static final int NO_SPACE = 14;
  public static final int BAD_TYPES = 15;  
  public static final int BAD_BAND = 16; 
  public static final int ERR_NULL = 17;  
  public static final int ERR_READER = 18;  
  public static final int NO_SEEK_FROM_END = 19;
  public static final int BAD_WHENCE = 20;
  public static final int NO_TIMEOUT = 21;
  public static final int BAD_FILE = 22;
  public static final int NO_SEEK = 23;
  public static final int NO_READER = 24;    
  public static final int BAD_PARS = 25; 
  public static final int BAD_INDEX_PAR = 26;  
  public static final int OUT_OF_SYNC = 27;
  public static final int RESYNC_FAIL = 28;
  public static final int NO_8BIT = 29;
  public static final int BAD_ALIGN = 30;
  public static final int NULL_BUFFER = 31;
  public static final int NO_RELSEEK = 32;
  public static final int NULL_POINTER = 33;
  public static final int BAD_KEY = 34;
  public static final int NO_INDEX = 35;
  public static final int INDEX_FAIL = 36;
  public static final int BAD_DECODER_SETUP = 37;
  public static final int MISSING_FEATURE = 38;
  public static final int BAD_VALUE = 39;
  public static final int LSEEK_FAILED = 40;
  public static final int BAD_CUSTOM_IO = 41;
  public static final int LFS_OVERFLOW = 42;
  public static final int INT_OVERFLOW = 43;
}
