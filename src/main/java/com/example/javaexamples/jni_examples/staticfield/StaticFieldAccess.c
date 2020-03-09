#include <jni.h>
#include <stdio.h>
#include "StaticFieldAccess.h"

JNIEXPORT void JNICALL
Java_StaticFieldAccess_accessField(JNIEnv *env, jobject this) {
	jfieldID fid;   /* store field id */
	jint si;

	/* Get a reference to obj's class */
	jclass cls = (*env)->GetObjectClass(env, this);

	printf("In C:\n");

	/* look for the instance field s in cls */
	fid = (*env)->GetStaticFieldID(env, cls, "si", "I");
	if (fid==NULL) {
		return;    /* failed to find field */
	}

	/* read the instance field */
	si = (*env)->GetStaticIntField(env, this, fid);
	printf(" StaticFieldAccess.si = %d\n", si);
	(*env)->SetStaticIntField(env, this, fid, 1000);
	return;
}

