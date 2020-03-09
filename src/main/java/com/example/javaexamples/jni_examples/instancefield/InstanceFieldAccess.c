#include <jni.h>
#include <stdio.h>
#include "InstanceFieldAccess.h"

JNIEXPORT void JNICALL
Java_InstanceFieldAccess_accessField(JNIEnv *env, jobject this) {
	jfieldID fid;   /* store field id */
	jstring jstr;
	const char *str;

	/* Get a reference to obj's class */
	jclass cls = (*env)->GetObjectClass(env, this);

	printf("In C:\n");

	/* look for the instance field s in cls */
	fid = (*env)->GetFieldID(env, cls, "s", "Ljava/lang/String;");
	if (fid==NULL) {
		return;    /* failed to find field */
	}

	/* read the instance field */
	jstr = (*env)->GetObjectField(env, this, fid);
	str = (*env)->GetStringUTFChars(env, jstr, NULL);
	if (str==NULL) {
		return;   /* out of memory */
	}
	printf(" c.s = \"%s\"\n", str);
	(*env)->ReleaseStringUTFChars(env, jstr, str);

	/* Create a new string and overwrite the instance field */
	jstr = (*env)->NewStringUTF(env, "123");
	if (jstr==NULL) {
		return; /* out of memory */
	}
	(*env)->SetObjectField(env, this, fid, jstr);
	return;
}
