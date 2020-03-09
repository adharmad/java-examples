#include <jni.h>
#include <stdio.h>
#include <mpi.h>
#include "MpiTest.h"

JNIEXPORT void JNICALL
Java_MpiTest_mpitest(JNIEnv *env, jobject obj, jint argc, jobjectArray argv)  {
	const jbyte *str;
	str = (*env)->GetStringUTFChars(env, prompt, NULL);
	if (str==NULL) {
		return NULL;
	}
	printf("%s", str);
	(*env)->ReleaseStringUTFChars(env, prompt, NULL);
	scanf("%s{", buf);
	return (*env)->NewStringUTF(env, buf);
---------------------------
    
	MPI_Init(&argc, &argv);	
	return;
}
