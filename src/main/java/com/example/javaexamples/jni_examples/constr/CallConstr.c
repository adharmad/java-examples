#include <jni.h>
#include <stdio.h>
#include "CallConstr.h"

JNIEXPORT jstring JNICALL
Java_CallConstr_myNewString(JNIEnv *env, jobject obj, jcharArray chars, jint len) {
	jclass stringClass;
	jmethodID cid;
	jcharArray elemArr;
	jstring result;

	stringClass = (*env)->FindClass(env, "java/lang/String");
	if (stringClass==NULL) {
		return NULL; /* class not found */
	}

	/* get methodID of constructor */
	cid = (*env)->GetMethodID(env, stringClass, "<init>", "([C)V");
	if (cid==NULL) {
		return NULL; /* exception */
	}

	/* create an elemArr which holds the string characters */
	elemArr = (*env)->NewCharArray(env, len);
	if (elemArr==NULL) {
		return NULL; /* out of memory */
	}
	(*env)->SetCharArrayRegion(env, elemArr, 0, len, chars);

	/* construct a java.lang.String object */
	result = (*env)->NewObject(env, stringClass, cid, elemArr);

	/* free local referecnes */
	(*env)->DeleteLocalRef(env, elemArr);
	(*env)->DeleteLocalRef(env, stringClass);

	return result;
}
