#include <jni.h>
#include <stdio.h>
#include <stdlib.h>
#include "C.h"

JNIEXPORT jint JNICALL
Java_C_atol(JNIEnv *env, jclass cls, jstring str) {
	const char *cstr = env->GetStringUTFChars(str, 0);
	if (cstr==NULL) {
		return 0;
	}
	int result = atol(cstr);
	env->ReleaseStringUTFChars(str, cstr);
	return result;
}
