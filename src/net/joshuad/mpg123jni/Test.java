package net.joshuad.mpg123jni;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
//import net.joshuad.waveformjni.HWaveOut;
//import net.joshuad.waveformjni.WaveHeader;

public class Test {
  
  //static HWaveOut waveOut; 
  
  public static void main(String[] args) throws Exception {
    System.load(System.getProperty("user.dir") + "/mpg123jni.dll");
    System.load(System.getProperty("user.dir") + "/waveformjni-0.0.2.dll");
    
    MPG123 mpg = new MPG123();
    mpg.getFormat();
    mpg.setParam(1, 0x20, 0);
    
    InputStream is = new FileInputStream(System.getProperty("user.dir") + "/2.mp3");
    
    //waveOut = new HWaveOut(0x0001, 2, 44100, 176400, 4, 16);
    //waveOut.setVolume(0x00000000);
    try {
      while (true) {
        byte[] encodedInput = new byte[32768];
        byte[] decodedOutput = new byte[65536];
        
        int bytesRead = is.readNBytes(encodedInput, 0, encodedInput.length);
        
        if (bytesRead<=0) {
          System.out.println("ByteRead <= 0: Got to the end of the file");
          break;
        }

        int[] returnStatus = mpg.decode(encodedInput, decodedOutput);
        int returnCode = returnStatus[0];
        int decodedLength = returnStatus[1];
        System.out.println("Decoded Length: " + decodedLength);
        System.out.println("return Code: " + returnCode);
        
        if (returnCode == MPG123Err.NEW_FORMAT) {
          int[] formatInfo = mpg.getFormat();
          System.out.println(
              "New Format: " + formatInfo[0] + ", " + formatInfo[1] + ", " + formatInfo[2]);
        }
        if (decodedLength < decodedOutput.length) {
 //         playBuffer(Arrays.copyOfRange(decodedOutput, 0, decodedLength));
        } else {
//          playBuffer(decodedOutput);
        }
        
        while (returnCode != MPG123Err.NEED_MORE) {
          returnStatus = mpg.decode(new byte[0], decodedOutput);
          returnCode = returnStatus[0];
          decodedLength = returnStatus[1];
          System.out.println("Decoded Length: " + decodedLength);
          System.out.println("return Code: " + returnCode);
          if (returnCode == MPG123Err.ERR) {
            System.out.println("Error, breaking: " + mpg.getError());
            throw new IOException();
          }
          if (decodedLength < decodedOutput.length) {
//            playBuffer(Arrays.copyOfRange(decodedOutput, 0, decodedLength));
          } else {
//           playBuffer(decodedOutput);
          }
        }
      }
    } catch (IOException e) {
      System.out.println("Exception: Got to the end of the file");
    }
    is.close();
    mpg.close();
    
  }
  
//  static void playBuffer(byte[] buffer) throws Exception {
//    WaveHeader header = new WaveHeader(buffer);
//    waveOut.prepareHeader(header);
//    waveOut.write(header);
//    while ((header.getFlags() & WaveHeader.DONE) != WaveHeader.DONE) {
//      Thread.sleep(1);
//    }
//    waveOut.unprepareHeader(header);
//    header.close();
//  }
}
