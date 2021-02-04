package id.airham.moviecatalogue.utils

object Keys {

    init {
        System.loadLibrary("native-lib")
    }

    external fun apiKey(): String
}