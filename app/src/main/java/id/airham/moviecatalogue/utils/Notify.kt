package id.airham.moviecatalogue.utils

import android.content.Context
import android.widget.Toast

object Notify {

    fun showToast(context: Context?, message: String, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, message, length).show()
    }
}