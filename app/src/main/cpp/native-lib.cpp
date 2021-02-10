#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_id_airham_moviecatalogue_utils_Keys_getApiKey(
        JNIEnv *env,
        jobject /* thiz */) {
    std::string key = "921583536fd3e09b74a1cdac13e87f52";
    return env->NewStringUTF(key.c_str());
}