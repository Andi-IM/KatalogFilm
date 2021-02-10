#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_id_airham_moviecatalogue_utils_Keys_getApiKey(
        JNIEnv *env,
        jobject /* thiz */) {
        // GET YOUR APIKEY FROM https://www.themoviedb.org/
    std::string key = "YOUR MOVIEDB APIKEY";
    return env->NewStringUTF(key.c_str());
}
