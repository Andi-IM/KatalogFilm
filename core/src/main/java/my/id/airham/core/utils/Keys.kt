package my.id.airham.core.utils

object Keys {
    init {
        System.loadLibrary("native-lib")
    }

    external fun getApiKey(): String?
}