#include <stdio.h>
#include <jni.h>
#include "PassObjArray.h"

JNIEXPORT void JNICALL Java_PassObjArray_passArray(
    JNIEnv *env, jclass cls, jobjectArray array) {
    int i;
    jsize len = (*env)->GetArrayLength(env, array);
    printf("len = %d\n", len);

    for (i=0 ; i<len ; i++) {
        jobject o = (*env)->GetObjectArrayElement(env, array, i);
        jchar * bytes = (*env)->GetStringChars(env, o, NULL);
        printf("%s\n", bytes);
        (*env)->ReleaseStringChars(env, o, bytes);
    }
}

