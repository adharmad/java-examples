#include <jni.h>
#include <stdio.h>
#include "StaticMethodCall.h"

JNIEXPORT void JNICALL
Java_StaticMethodCall_nativeMethod(JNIEnv *env, jobject obj) {
	jclass cls = (*env)->GetObjectClass(env, obj);
	jmethodID mid = (*env)->GetStaticMethodID(env, cls, "callback", "()V");
	if (mid==NULL) {
		return; /* method not found */
	}
	printf("In C\n");
	/* Important to pass cls instead of object */
	(*env)->CallStaticVoidMethod(env, cls, mid);
}
