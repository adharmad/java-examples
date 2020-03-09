#include <jni.h>
#include <stdio.h>
#include "CatchThrow.h"

JNIEXPORT void JNICALL
Java_CatchThrow_doit(JNIEnv *env, jobject obj) {
	jthrowable exc;
	jclass cls = (*env)->GetObjectClass(env, obj);
	jmethodID mid = (*env)->GetMethodID(env, cls, "callback", "()V");
	if (mid==NULL) {
		return;
	}

	(*env)->CallVoidMethod(env, obj, mid);
	exc = (*env)->ExceptionOccurred(env);
	if (exc) {
		/* We dont do much with the exception except that we print a debug
		 * message for it, clear it, and throw a new exception */
		jclass newExcCls;
		(*env)->ExceptionDescribe(env);
		(*env)->ExceptionClear(env);
		newExcCls = (*env)->FindClass(env, 
						"java/lang/IllegalArgumentException");
		if (newExcCls==NULL) {
			return; /* unable to find exception class */
		}
		(*env)->ThrowNew(env, newExcCls, "thrown from C code");
	}

	return;
}
