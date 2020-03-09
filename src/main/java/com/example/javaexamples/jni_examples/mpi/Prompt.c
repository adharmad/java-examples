#include <jni.h>
#include <stdio.h>
#include "Prompt.h"

JNIEXPORT jstring JNICALL
Java_Prompt_getLine(JNIEnv *env, jobject this, jstring prompt) {
/*
 *  This is one way of implementing the function. Another
 *  way is shown below.
	char buf[128];
	const jbyte *str;
	str = (*env)->GetStringUTFChars(env, prompt, NULL);
	if (str==NULL) {
		return NULL;
	}
	printf("%s", str);
	(*env)->ReleaseStringUTFChars(env, prompt, NULL);
	scanf("%s{", buf);
	return (*env)->NewStringUTF(env, buf);
*/
	char outbuf[128], inbuf[128];
	int len = (*env)->GetStringLength(env, prompt);
	(*env)->GetStringUTFRegion(env, prompt, 0, len, outbuf);
	printf("%s", outbuf);
	scanf("%s", inbuf);
	return (*env)->NewStringUTF(env, inbuf);	
}
