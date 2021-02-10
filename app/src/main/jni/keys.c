#include <stdint.h>
//
// Created by Andi on 31/01/2021.
//

#include <jni.h>

JNIEXPORT jstring JNICALL
Java_id_airham_moviecatalogue_utils_Keys_getApiKey(JNIEnv *env, __unused jobject thiz) {
    return (*env)->NewStringUTF(env, "921583536fd3e09b74a1cdac13e87f52");
}