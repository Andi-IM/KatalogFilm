//
// Created by Andi on 31/01/2021.
//

#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring

JNICALL
Java_id_airham_moviecatalogue_utils_Keys_apiKey(JNIEnv *env, jobject object) {
    std::string api_key = "921583536fd3e09b74a1cdac13e87f52";
    return env->NewStringUTF(api_key.c_str());
}
