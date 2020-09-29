#include <jni.h>
#include <iostream>
#include "mpg123.h"
#include "net_joshuad_mpg123jni_MPG123.h"

JNIEXPORT jint JNICALL Java_net_joshuad_mpg123jni_MPG123_init
  (JNIEnv *, jclass) {
  return mpg123_init();
}

JNIEXPORT jlong JNICALL Java_net_joshuad_mpg123jni_MPG123_makeNew
  (JNIEnv *, jclass) {
  int ret;
  mpg123_handle* m = mpg123_new(NULL, &ret);
  if(m == NULL) {
    fprintf(stderr,"Unable to create mpg123 handle: %s\n", mpg123_plain_strerror(ret));
    return 0;
  }
  return (jlong)m;
}

JNIEXPORT jint JNICALL Java_net_joshuad_mpg123jni_MPG123_openFeed
    (JNIEnv* env, jclass, jlong m) {
  return mpg123_open_feed((mpg123_handle*)m);
}

JNIEXPORT jintArray JNICALL Java_net_joshuad_mpg123jni_MPG123_decode
    (JNIEnv* env, jclass, jlong m, jbyteArray encodedIn, jbyteArray decodedOut) {
        
  int inSize = env->GetArrayLength(encodedIn);
  int outMax = env->GetArrayLength(decodedOut);
  
  jboolean isCopyIn;
  const unsigned char* in = (const unsigned char*)env->GetByteArrayElements(encodedIn, &isCopyIn);
  const unsigned char* out = (const unsigned char*)calloc(outMax, sizeof(char)); 
  
  size_t outLength;
  
  int returnCode = mpg123_decode((mpg123_handle*)m, in, inSize, (void*)out, outMax, &outLength);
  if(isCopyIn) {
    env->ReleaseByteArrayElements(encodedIn, (jbyte*)in, JNI_ABORT);
  }

	env->SetByteArrayRegion(decodedOut, 0, outLength, (const jbyte*)out);
  
  delete[] (out);
  
  jintArray retMe;
	retMe = env->NewIntArray(2);
	jint fill[2];
	fill[0] = (jint)returnCode;
	fill[1] = (jint)outLength;
	env->SetIntArrayRegion(retMe, 0, 2, fill);
  return retMe;
}

JNIEXPORT jintArray JNICALL Java_net_joshuad_mpg123jni_MPG123_getFormat
    (JNIEnv* env, jclass, jlong m) {
  long rate;
  int channels, enc;
  mpg123_getformat((mpg123_handle*)m, &rate, &channels, &enc);
  jintArray retMe = env->NewIntArray(3);
  jint fill[3];
	fill[0] = rate;
	fill[1] = channels;
  fill[2] = enc;
	env->SetIntArrayRegion(retMe, 0, 3, fill);
  return retMe;
}

JNIEXPORT void JNICALL Java_net_joshuad_mpg123jni_MPG123_delete
    (JNIEnv* env, jclass, jlong m) {
  mpg123_delete((mpg123_handle*)m);    
}

JNIEXPORT jstring JNICALL Java_net_joshuad_mpg123jni_MPG123_strerror
    (JNIEnv* env, jclass, jlong m) {
  const char* errorString = mpg123_strerror((mpg123_handle*)m);
  jstring result = env->NewStringUTF(errorString);
  return result;  
}

JNIEXPORT jint JNICALL Java_net_joshuad_mpg123jni_MPG123_param
    (JNIEnv *, jclass, jlong m, jint param, jint value, jdouble fvalue) {
  return mpg123_param((mpg123_handle*)m, (mpg123_parms)param, (long)value, (double)fvalue);
}

